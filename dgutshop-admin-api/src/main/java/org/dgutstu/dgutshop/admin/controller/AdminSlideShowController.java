package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.admin.service.AdminSlideShowService;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopSlideshow;
import org.dgutstu.dgutshop.db.domain.DgutshopTopping;
import org.dgutstu.dgutshop.db.service.DgutshopSlideShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 12:40 2020/12/19
 */

@RestController
@RequestMapping("/admin/slideshow")
@Validated
public class AdminSlideShowController {

    @Autowired
    private AdminSlideShowService slideShowService;

    @GetMapping("/list")
    public Object list(Integer id, Integer index,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "id") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        return slideShowService.list(id, index, start, end, page, limit, sort, order);
    }

    @PostMapping("/create")
    public Object create(@RequestBody DgutshopSlideshow slideshow){
        return slideShowService.create(slideshow);
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam Integer id){
        return slideShowService.delete(id);
    }

    /**
     * 更改轮播图顺序
     * @param slideshowList
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody List<DgutshopSlideshow> slideshowList){
        return slideShowService.update(slideshowList);
    }
}
