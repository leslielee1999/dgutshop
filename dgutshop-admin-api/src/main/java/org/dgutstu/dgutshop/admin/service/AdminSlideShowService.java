package org.dgutstu.dgutshop.admin.service;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopSlideshow;
import org.dgutstu.dgutshop.db.domain.DgutshopTopping;
import org.dgutstu.dgutshop.db.service.DgutshopSlideShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 12:49 2020/12/19
 */
@Service
public class AdminSlideShowService {
    @Autowired
    private DgutshopSlideShowService slideShowService;

    public Object list(Integer id, Integer index, LocalDateTime start, LocalDateTime end,
                       Integer page, Integer limit, String sort, String order){
        List<DgutshopSlideshow> slideshowList = slideShowService.querySelective(id, index, start, end, page, limit, sort, order);
        return ResponseUtil.okList(slideshowList);
    }

    //  校验添加饮小料所提交数据
    private Object validate(DgutshopSlideshow slideshow){
        Integer index = slideshow.getIndex();
        if (StringUtils.isEmpty(index)){
            return ResponseUtil.badArgument();
        }
        //  如果需要添加其它限制可以继续添加
        //  ...

        return null;
    }

    @Transactional
    public Object create(DgutshopSlideshow slideshow){
        Object error = validate(slideshow);
        if (error != null){
            return error;
        }
        slideShowService.add(slideshow);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object delete(Integer id){
        if (id == null){
            return ResponseUtil.badArgument();
        }
        slideShowService.deleteById(id);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object update(List<DgutshopSlideshow> slideshowList){
        for (DgutshopSlideshow slideshow : slideshowList) {
            Object error = validate(slideshow);
            if (error != null){
                return error;
            }
            Integer id = slideshow.getId();
            if (id == null) {
                return ResponseUtil.badArgument();
            }
            if (slideShowService.update(slideshow) == 0){
                return ResponseUtil.updatedDataFailed();
            }
        }

        return ResponseUtil.okList(slideshowList);
    }
}
