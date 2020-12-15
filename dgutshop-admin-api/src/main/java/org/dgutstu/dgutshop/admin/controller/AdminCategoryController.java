package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.util.RegexUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.util.bcrypt.BCryptPasswordEncoder;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.domain.DgutshopCategory;
import org.dgutstu.dgutshop.db.domain.DgutshopCategoryItem;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryItemService;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryService;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.dgutstu.dgutshop.admin.util.AdminResponseCode.*;

/**
 * @Author: leesk
 * @Description:
 * 关于分类项
 * 更改：
 *      分类项对应的是饮品，无需更改分类项；
 *      删除分类下的某个分类项
 * @Date: Create in 11:24 2020/12/15
 */
@RestController
@RequestMapping("/admin/category")
@Validated
public class AdminCategoryController {

    @Autowired
    private DgutshopCategoryService categoryService;
    @Autowired
    private DgutshopCategoryItemService categoryItemService;
    @Autowired
    private DgutshopProductService productService;

    @GetMapping("/list")
    public Object list(String name, Integer index,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<DgutshopCategory> categoryList = categoryService.querySelective(name, index, page, limit, sort, order);
        categoryItemService.fill(categoryList);
        return ResponseUtil.okList(categoryList);
    }

    //  校验添加分类所提交数据
    private Object validate(DgutshopCategory dgutshopCategory) {
        String name = dgutshopCategory.getName();//分类名
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        int index = dgutshopCategory.getIndex();//分类顺序
        if (StringUtils.isEmpty(index)) {
            return ResponseUtil.badArgument();
        }

        List<DgutshopCategory> categoryList = categoryService.findByName(name);

        if (categoryList.size() > 0) {
            return ResponseUtil.fail(CATEGORY_NAME_EXIST, "分类已经存在");//650
        }
        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @PostMapping("/create")
    public Object create(@RequestBody DgutshopCategory category) {
        Object error = validate(category);
        if (error != null) {
            return error;
        }
        int index = category.getIndex();//分类顺序
        List<DgutshopCategory> categoryList = categoryService.findByIndex(index);
        if (categoryList.size() > 0){
            return ResponseUtil.fail(CATEGORY_INDEX_EXIST, "分类顺序已被占用");//651
        }
        categoryService.add(category);
        //  记录日志
        //  改用spring security实现
//        logHelper.logAuthSucceed("添加分类", username);
        return ResponseUtil.ok(category);
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        categoryService.deleteById(id);

        //  记录日志
        //  用spring security 实现
//        DgutshopCategory category = categoryService.findById(id);
//        logHelper.logAuthSucceed("删除分类", category.getName());

        return ResponseUtil.ok();
    }

    @PostMapping("/update")
    public Object update(@RequestBody DgutshopCategory category) {
        Object error = validate(category);
        if (error != null) {
            return error;
        }
        Integer adminId = category.getId();
        if (adminId == null) {
            return ResponseUtil.badArgument();
        }
        int index = category.getIndex();//分类顺序
        List<DgutshopCategory> categoryList = categoryService.findByIndex(index);
        for (DgutshopCategory dgutshopCategory : categoryList) {
            if (dgutshopCategory.getId() != category.getId()){
                return ResponseUtil.fail(CATEGORY_INDEX_EXIST, "分类顺序已被占用");//651
            }
        }

        if (categoryService.update(category) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        //  记录日志
        //  用spring security 实现
        //  logHelper.logAuthSucceed("编辑分类", admin.getName());
        return ResponseUtil.ok(category);
    }


    //  校验添加分类项所提交数据
    private Object validate(Integer index, Integer cid, Integer pid) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(cid) || StringUtils.isEmpty(pid)) {
            return ResponseUtil.badArgument();
        }

        //  根据分类项获取对应分类下的所有分类单项
        DgutshopCategory category = categoryService.get(cid);
        System.out.println(category);
        categoryItemService.fill(category);
        List<DgutshopCategoryItem> items = category.getCategoryItems();
        System.out.println(items);
        List<DgutshopCategoryItem> categoryItems = categoryService.get(cid).getCategoryItems();
        if (items != null){
            for (DgutshopCategoryItem item : items) {
                if (item.getPid() == pid){
                    return ResponseUtil.fail(CATEGORYITEM_NAME_EXIST, "分类项已经存在");//652
                }
            }
        }

        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @PostMapping("/categoryitem/create")
    public Object create(@RequestParam Integer index, @RequestParam Integer cid, @RequestParam Integer pid){
        Object error = validate(index, cid, pid);
        if (error != null) {
            return error;
        }
        List<DgutshopCategoryItem> categoryItemList = categoryItemService.findByIndex(index);
        if (categoryItemList.size() > 0){
            return ResponseUtil.fail(CATEGORYITEM_INDEX_EXIST, "分类顺序已被占用");//653
        }
        DgutshopProduct product = productService.get(pid);
        DgutshopCategoryItem categoryItem = new DgutshopCategoryItem();
        categoryItem.setIndex(index);
        categoryItem.setCid(cid);
        categoryItem.setPid(pid);
        categoryItem.setProduct(product);
        categoryItemService.add(categoryItem);
        return ResponseUtil.ok(categoryItem);
    }

    @PostMapping("/categoryitem/delete")
    public Object deleteCategoryItem(@RequestParam Integer id) {
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        categoryItemService.deleteById(id);

        //  记录日志
        //  用spring security 实现
//        DgutshopCategory category = categoryService.findById(id);
//        logHelper.logAuthSucceed("删除分类", category.getName());

        return ResponseUtil.ok();
    }
}
