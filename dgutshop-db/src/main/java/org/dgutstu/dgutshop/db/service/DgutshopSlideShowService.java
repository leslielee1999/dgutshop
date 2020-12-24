package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopSlideshowMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopSlideshow;
import org.dgutstu.dgutshop.db.domain.DgutshopSlideshowExample;
import org.dgutstu.dgutshop.db.domain.DgutshopTopping;
import org.dgutstu.dgutshop.db.domain.DgutshopToppingExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 12:41 2020/12/19
 */
@Service
public class DgutshopSlideShowService {
    @Resource
    private DgutshopSlideshowMapper slideshowMapper;


    public List<DgutshopSlideshow> list(){
        DgutshopSlideshowExample example = new DgutshopSlideshowExample();
        DgutshopSlideshowExample.Criteria criteria = example.createCriteria();
        criteria.andLogicalDeleted(true);
        return slideshowMapper.selectByExample(example);
    }

    public List<DgutshopSlideshow> querySelective(Integer id, Integer index, LocalDateTime start, LocalDateTime end,
                                                Integer page, Integer limit, String sort, String order){
        DgutshopSlideshowExample example = new DgutshopSlideshowExample();
        DgutshopSlideshowExample.Criteria criteria = example.createCriteria();

        if(id != null){
            criteria.andIdEqualTo(id);
        }
        if(index != null){
            criteria.andIndexEqualTo(index);
        }

        if (!StringUtils.isEmpty(start)){
            criteria.andCreateTimeGreaterThanOrEqualTo(start);
        }
        if (!StringUtils.isEmpty(end)){
            criteria.andCreateTimeLessThanOrEqualTo(end);
        }

        criteria.andDeletedEqualTo(false);

        //  设置排序
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        //  设置分页
        PageHelper.startPage(page, limit);
        return slideshowMapper.selectByExample(example);
    }


    public void add(DgutshopSlideshow slideshow){
        slideshow.setCreateTime(LocalDateTime.now());
        slideshow.setUpdateTime(LocalDateTime.now());
        slideshowMapper.insertSelective(slideshow);
    }


    public void deleteById(Integer id){
        slideshowMapper.logicalDeleteByPrimaryKey(id);
    }


    public int update(DgutshopSlideshow slideshow){
        slideshow.setUpdateTime(LocalDateTime.now());
        return slideshowMapper.updateByPrimaryKeySelective(slideshow);
    }


    public DgutshopSlideshow get(int id){
        return slideshowMapper.selectByPrimaryKey(id);
    }
}
