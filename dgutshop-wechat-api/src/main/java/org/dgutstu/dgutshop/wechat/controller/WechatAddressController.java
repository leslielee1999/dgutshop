package org.dgutstu.dgutshop.wechat.controller;

import org.dgutstu.dgutshop.core.util.RegexUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopAddress;
import org.dgutstu.dgutshop.db.service.DgutshopAddressService;
import org.dgutstu.dgutshop.wechat.annotation.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 1:04 2020/12/24
 */
@RestController
@RequestMapping("/wechat/address")
@Validated
public class WechatAddressController {

    @Autowired
    private DgutshopAddressService addressService;


    /**
     * 用户收货地址列表
     *
     * @param userId 用户ID
     * @return 收货地址列表
     */
    @GetMapping("list")
    public Object list(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        List<DgutshopAddress> addressList = addressService.queryByUid(userId);
        return ResponseUtil.okList(addressList);
    }

    /**
     * 收货地址详情
     *
     * @param userId 用户ID
     * @param id     收货地址ID
     * @return 收货地址详情
     */
    @GetMapping("detail")
    public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        DgutshopAddress address = addressService.query(userId, id);
        if (address == null) {
            return ResponseUtil.badArgumentValue();
        }
        return ResponseUtil.ok(address);
    }

    private Object validate(DgutshopAddress address) {
        String name = address.getUserName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        // 测试收货手机号码是否正确
        String mobile = address.getUserPhone();
        if (StringUtils.isEmpty(mobile)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileSimple(mobile)) {
            return ResponseUtil.badArgument();
        }

        String userAddress = address.getUserAddress();
        if (StringUtils.isEmpty(userAddress)) {
            return ResponseUtil.badArgument();
        }

        String userRoom = address.getUserRoom();
        if (StringUtils.isEmpty(userRoom)) {
            return ResponseUtil.badArgument();
        }

        Byte isDefault = address.getIsDefault();
        if (isDefault == null) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    /**
     * 添加或更新收货地址
     *
     * @param userId  用户ID
     * @param address 用户收货地址
     * @return 添加或更新操作结果
     */
    @PostMapping("save")
    public Object save(@LoginUser Integer userId, @RequestBody DgutshopAddress address) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(address);
        if (error != null) {
            return error;
        }

        if (address.getId() == null || address.getId().equals(0)) {     //  添加地址
            if (address.getIsDefault()  == 0) {                         //  若新添加地址设为默认地址
                // 重置其他收货地址的默认选项
                addressService.resetDefault(userId);
            }

            address.setId(null);
            address.setUserId(userId);
            addressService.add(address);
        } else {                                                        //  更新地址
            DgutshopAddress dgutshopAddress = addressService.query(userId, address.getId());
            if (dgutshopAddress == null) {
                return ResponseUtil.badArgumentValue();
            }

            if (address.getIsDefault() == 0) {
                // 重置其他收货地址的默认选项
                addressService.resetDefault(userId);
            }

            address.setUserId(userId);
            addressService.update(address);
        }
        return ResponseUtil.ok(address.getId());
    }

    /**
     * 删除收货地址
     *
     * @param userId  用户ID
     * @param address 用户收货地址，{ id: xxx }
     * @return 删除操作结果
     */
    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody DgutshopAddress address) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer id = address.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        DgutshopAddress litemallAddress = addressService.query(userId, id);
        if (litemallAddress == null) {
            return ResponseUtil.badArgumentValue();
        }

        addressService.delete(id);
        return ResponseUtil.ok();
    }
}
