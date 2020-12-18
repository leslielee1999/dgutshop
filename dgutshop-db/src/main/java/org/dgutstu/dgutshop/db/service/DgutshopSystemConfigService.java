package org.dgutstu.dgutshop.db.service;

import org.dgutstu.dgutshop.db.dao.DgutshopSystemMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopSystem;
import org.dgutstu.dgutshop.db.domain.DgutshopSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 14:21 2020/12/18
 */
@Service
public class DgutshopSystemConfigService {
    @Resource
    private DgutshopSystemMapper systemMapper;

    public Map<String, String> listMall() {
        DgutshopSystemExample example = new DgutshopSystemExample();
        example.or().andKeyNameLike("dgutshop_shop_%").andDeletedEqualTo(false);
        List<DgutshopSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(DgutshopSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            DgutshopSystemExample example = new DgutshopSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            DgutshopSystem system = new DgutshopSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }
}
