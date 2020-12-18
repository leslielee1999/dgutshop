package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopCategory;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryItemService;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryService;
import org.dgutstu.dgutshop.wechat.service.HomeCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 19:18 2020/12/17
 */
@RestController
@RequestMapping("/wechat/catalog")
@Validated
public class WechatCatalogController {

    @Autowired
    private DgutshopCategoryService categoryService;
    @Autowired
    private DgutshopCategoryItemService categoryItemService;
    /**
     *
     * 当前分类类目
     * @param id 分类类目id
     * @return 当前分类类目
     */
    @GetMapping("current")
    public Object current(@NotNull Integer id){
        DgutshopCategory currentCategory = categoryService.get(id);
        if(currentCategory == null){
            return ResponseUtil.badArgumentValue();
        }
        categoryItemService.fill(currentCategory);
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", currentCategory);
        return ResponseUtil.ok(data);
    }

    @GetMapping("/list")
    public Object list(){
        //  优先从缓存中读取
        if (HomeCacheManager.hasData(HomeCacheManager.CATALOG)){
            return ResponseUtil.ok(HomeCacheManager.getCacheData(HomeCacheManager.CATALOG));
        }
        List<DgutshopCategory> categoryList = categoryService.list();
        categoryItemService.fill(categoryList);
        return ResponseUtil.okList(categoryList);
    }
}
