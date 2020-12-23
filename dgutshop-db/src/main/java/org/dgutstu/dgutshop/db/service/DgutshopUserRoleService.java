package org.dgutstu.dgutshop.db.service;

import org.dgutstu.dgutshop.db.dao.DgutshopRoleMapper;
import org.dgutstu.dgutshop.db.dao.DgutshopUserRoleMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopRole;
import org.dgutstu.dgutshop.db.domain.DgutshopUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 1:16 2020/12/22
 */
@Service
public class DgutshopUserRoleService {

    @Autowired
    private DgutshopUserRoleMapper userRoleMapper;

    public void add(DgutshopUserRole userRole){
        userRoleMapper.insertSelective(userRole);
    }

}
