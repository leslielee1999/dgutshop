package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.core.util.RegexUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.util.bcrypt.BCryptPasswordEncoder;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.*;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryItemService;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryService;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(String name, Integer index,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<DgutshopCategory> categoryList = categoryService.querySelective(name, index, start, end, page, limit, sort, order);
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

//        List<DgutshopCategory> categoryList = categoryService.findByName(name);

//        if (categoryList.size() > 0) {
//            return ResponseUtil.fail(CATEGORY_NAME_EXIST, "分类已经存在");//650
//        }
        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/create")
    public Object create(@RequestBody DgutshopCategory category) {
        Object error = validate(category);
        if (error != null) {
            return error;
        }
        int index = -1;
        List<DgutshopCategory> categoryList = categoryService.list();
        for (DgutshopCategory dgutshopCategory : categoryList) {
            if (index < dgutshopCategory.getIndex()){
                index = dgutshopCategory.getIndex();
            }
        }
        category.setIndex(++index);
        categoryService.add(category);
        //  记录日志
        //  改用spring security实现
//        logHelper.logAuthSucceed("添加分类", username);
        return ResponseUtil.ok(category);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update")
    public Object update(@RequestBody List<DgutshopCategory> categoryList) {
        for (DgutshopCategory category : categoryList) {
            Object error = validate(category);
            if (error != null) {
                return error;
            }
            Integer id = category.getId();
            if (id == null) {
                return ResponseUtil.badArgument();
            }
            if (categoryService.update(category) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        //  记录日志
        //  用spring security 实现
        //  logHelper.logAuthSucceed("编辑分类", admin.getName());
        return ResponseUtil.okList(categoryList);
    }

    /**
     * 用于修改侧边栏图标
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/updateOne")
    public Object updateOne(@RequestBody DgutshopCategory category) {
        Integer id = category.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
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
    private Object validate(OrderItemVo orderItemVo) {
        int cid = orderItemVo.getCid();
        int[] pids = orderItemVo.getPids();
        if (StringUtils.isEmpty(cid)) {
            return ResponseUtil.badArgument();
        }
        for (int pid : pids) {
            if (StringUtils.isEmpty(pid)) {
                return ResponseUtil.badArgument();
            }
        }

        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/categoryitem/create")
    public Object create(@RequestBody OrderItemVo orderItemVo){
        Object error = validate(orderItemVo);
        if (error != null) {
            return error;
        }
        int cid = orderItemVo.getCid();
        int pid = 0;
        int index = -1;
        int[] pids = orderItemVo.getPids();
        List<DgutshopCategoryItem> categoryItemList = new LinkedList<>();
        //  根据分类项获取对应分类下的所有分类单项
        DgutshopCategory category = categoryService.get(cid);
        System.out.println(category);
        categoryItemService.fill(category);
        List<DgutshopCategoryItem> items = category.getCategoryItems();
        System.out.println(items);
//        List<DgutshopCategoryItem> categoryItems = categoryService.get(cid).getCategoryItems();
        for (DgutshopCategoryItem item : items) {
            if (index < item.getIndex()){
                index = item.getIndex();
            }
            for (int i : pids) {
                if (i == item.getPid()){
                    return ResponseUtil.fail(111, "请删除已存在分类单项");
                }
            }
        }
        for (int i = 0; i < pids.length; i++) {
            pid = pids[i];
            index++;
            DgutshopCategoryItem item = new DgutshopCategoryItem();
            DgutshopProduct product = productService.get(pid);
            item.setCid(cid);
            item.setPid(pid);
            item.setIndex(index);
            item.setProduct(product);
            categoryItemService.add(item);
        }
        return ResponseUtil.okList(categoryItemList);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/categoryitem/update")
    public Object updateCategoryItem(@RequestBody List<DgutshopCategoryItem> categoryItemList){
        for (DgutshopCategoryItem categoryItem : categoryItemList) {
            Integer id = categoryItem.getId();
            if (id == null) {
                return ResponseUtil.badArgument();
            }
            if (categoryItemService.update(categoryItem) == 0) {
                return ResponseUtil.updatedDataFailed();
            }
        }
        return ResponseUtil.okList(categoryItemList);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
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
