package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopCategory;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.dgutstu.dgutshop.db.service.DgutshopCategoryService;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.dgutstu.dgutshop.wechat.annotation.LoginUser;
import org.dgutstu.dgutshop.wechat.service.HomeCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:饮品服务
 * @Date: Create in 19:00 2020/12/17
 */
@RestController
@RequestMapping("/wechat/product")
@Validated
public class WechatProductController {
    @Autowired
    private DgutshopCategoryService categoryService;
    @Autowired
    private DgutshopProductService productService;

    @GetMapping("detail")
    public Object detail(@NotNull Integer id){
        DgutshopProduct info = productService.get(id);
        if(info == null){
            return ResponseUtil.badArgumentValue();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("info", info);
        return ResponseUtil.ok(data);
    }

    //  加入购物车

    @GetMapping("/list")
    public Object list(
            Integer categoryId,
            String keyword,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @Sort(accepts = {"create_time", "big_original_price", "name"}) @RequestParam(defaultValue = "create_time") String sort,
            @Order @RequestParam(defaultValue = "desc") String order){
        //添加到搜索历史
//        if (userId != null && !StringUtils.isEmpty(keyword)) {
//            LitemallSearchHistory searchHistoryVo = new LitemallSearchHistory();
//            searchHistoryVo.setKeyword(keyword);
//            searchHistoryVo.setUserId(userId);
//            searchHistoryVo.setFrom("wx");
//            searchHistoryService.save(searchHistoryVo);
//        }
        List<DgutshopCategory> categoryList = categoryService.list();
//        categoryItemService.fill(categoryList);
        return ResponseUtil.okList(categoryList);
    }

}
