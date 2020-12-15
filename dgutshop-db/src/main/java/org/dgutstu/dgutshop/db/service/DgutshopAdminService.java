package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgutstu.dgutshop.db.dao.DgutshopAdminMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin;
import org.dgutstu.dgutshop.db.domain.DgutshopAdminExample;
import org.dgutstu.dgutshop.db.domain.DgutshopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.dgutstu.dgutshop.db.domain.DgutshopAdmin.Column;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 20:41 2020/12/14
 */
@Service
public class DgutshopAdminService {
    //  限制返回前台的数据，不允许展示密码等私人信息
    private final Column[] result = new Column[]{Column.id, Column.name, Column.nickname, Column.code, Column.picture, Column.roleId, Column.createTime, Column.updateTime, Column.lastLoginIp, Column.lastLoginTime};
    @Autowired
    private DgutshopAdminMapper adminMapper;

    /**
     * 列出管理员（可根据姓名查询）
     * @param nickname
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<DgutshopAdmin> querySelective(String name, String nickname, Integer page, Integer limit, String sort, String order){
        DgutshopAdminExample example = new DgutshopAdminExample();
        DgutshopAdminExample.Criteria criteria = example.createCriteria();

        //  判断是否为模糊查询
        if(!StringUtils.isEmpty(nickname)){
            criteria.andNicknameLike("%" + nickname + "%");
        }
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }

        //  判断是否为逻辑删除的饮品
        criteria.andDeletedEqualTo(false);

        //  设置排序
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        //  设置分页
        PageHelper.startPage(page, limit);

        return adminMapper.selectByExampleSelective(example, result);
    }

    /**
     * 增加一位管理员
     * @param admin
     */
    public void add(DgutshopAdmin admin){
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    /**
     * 删除第id个管理员
     * @param id
     */
    public void deleteById(Integer id){
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 编辑一位管理员
     * @param admin
     * @return
     */
    public int update(DgutshopAdmin admin){
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    /**
     * 根据id查询某位管理员
     * @param id
     * @return
     */
    public DgutshopAdmin findById(Integer id){
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    /**
     * 返回昵称为name的管理员
     * @param name
     * @return
     */
    public List<DgutshopAdmin> findAdmin(String name){
        DgutshopAdminExample example = new DgutshopAdminExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public List<DgutshopAdmin> all(){
        DgutshopAdminExample example = new DgutshopAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }


}