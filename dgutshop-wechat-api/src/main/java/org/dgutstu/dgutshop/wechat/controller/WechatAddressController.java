package org.dgutstu.dgutshop.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.dgutstu.dgutshop.core.util.RegexUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopAddress;
import org.dgutstu.dgutshop.db.domain.DgutshopUser;
import org.dgutstu.dgutshop.db.service.DgutshopAddressService;
import org.dgutstu.dgutshop.db.service.DgutshopUserService;
import org.dgutstu.dgutshop.wechat.service.WechatAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 1:04 2020/12/24
 */
@Slf4j
@RestController
@RequestMapping("/wechat/address")
@Validated
public class WechatAddressController {

    @Autowired
    private DgutshopAddressService addressService;
    @Autowired
    private DgutshopUserService userService;
    @Autowired
    private WechatAuthService wechatAuthService;

    /**
     * 用户收货地址列表
     *
     * @return 收货地址列表
     */
    @GetMapping("list")
    public Object list(HttpServletRequest request, HttpServletResponse response) {
        Object obj = wechatAuthService.validate(request, response);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);
        List<DgutshopAddress> addressList = addressService.queryByUid(userId);
        return ResponseUtil.okList(addressList);
    }

    /**
     * 收货地址详情
     *
     * @param id 收货地址ID
     * @return 收货地址详情
     */
    @GetMapping("detail")
    public Object detail(HttpServletRequest request, HttpServletResponse response, @NotNull Integer id) {
        Object obj = wechatAuthService.validate(request, response);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);
        DgutshopAddress address = addressService.query(userId, id);
        if (address == null) {
            return ResponseUtil.badArgumentValue();
        }
        return ResponseUtil.ok(address);
    }

    private Object validateAddress(DgutshopAddress address) {
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
            return ResponseUtil.fail(801, "不符合规范的手机号码格式，请重新输入");
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
     * @param address 用户收货地址
     * @return 添加或更新操作结果
     */
    @PostMapping("save")
    public Object save(HttpServletRequest request, HttpServletResponse response, @RequestBody DgutshopAddress address) {
        Object obj = wechatAuthService.validate(request, response);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);

        Object error = validateAddress(address);
        if (error != null) {
            return error;
        }
        String openId = wechatAuthService.getOpenId(request, response);
        address.setOpenId(openId);
        if (address.getId() == null || address.getId().equals(0)) {     //  添加地址
            if (address.getIsDefault() == 0) {                         //  若新添加地址设为默认地址
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
     * @param address 用户收货地址，{ id: xxx }
     * @return 删除操作结果
     */
    @PostMapping("delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response, @RequestBody DgutshopAddress address) {
        Object obj = wechatAuthService.validate(request, response);
        if (obj instanceof Map) {
            return obj;
        }
        Integer userId = wechatAuthService.getUserId(obj);

        Object error = validateAddress(address);
        if (error != null) {
            return error;
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
