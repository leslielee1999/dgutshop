package org.dgutstu.dgutshop.admin.controller;

import org.dgutstu.dgutshop.admin.service.AdminOrderService;
import org.dgutstu.dgutshop.core.security.util.ResultUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.core.validator.Order;
import org.dgutstu.dgutshop.core.validator.Sort;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.service.DgutshopOrderItemService;
import org.dgutstu.dgutshop.db.service.DgutshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 21:47 2020/12/24
 */
@RestController
@RequestMapping("/admin/stat")
@Validated
public class AdminStatController {

    @Autowired
    private DgutshopOrderService orderService;

    private static short UNPAID = 101;
    private static short PRODUCING = 201;
    private static short UNTAKEN = 301;
    private static short DELIVERING = 401;
    private static short FINISHED = 501;
    /**
     * 查询订单
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public Object list(){
        Map<String, Object> data = new HashMap<String, Object>();
        List<DgutshopOrder> orders = orderService.list();       //  获取前7天的订单
        List list = new LinkedList();
        Map<String, Object> date = new HashMap<String, Object>();
        int unpaid = 0;
        int producing = 0;
        int untaken = 0;
        int delivering = 0;
        int finished = 0;
//        String dayOfWeek = orders.get(0).getCreateTime().getDayOfWeek().toString();
        String[] dayOfWeek = new String[7];
        int[][] nums = new int[7][5];
        for (int i = 0; i < orders.size(); i++) {
            String day = orders.get(i).getCreateTime().getDayOfWeek().toString();
            if (day.equals("MONDAY")){
                if (orders.get(i).getOrderStatus().equals(UNPAID)){
                    nums[0][0]++;       //  unpaid
                }else if (orders.get(i).getOrderStatus().equals(PRODUCING)){
                    nums[0][1]++;       //  producing
                }else if (orders.get(i).getOrderStatus().equals(UNTAKEN)){
                    nums[0][2]++;       //  untaken
                }else if (orders.get(i).getOrderStatus().equals(DELIVERING)){
                    nums[0][3]++;       //  delivering
                }else if (orders.get(i).getOrderStatus().equals(FINISHED)){
                    nums[0][4]++;       //  finished
                }
            }else if (day.equals("THURSDAY")){
                if (orders.get(i).getOrderStatus().equals(UNPAID)){
                    nums[1][0]++;       //  unpaid
                }else if (orders.get(i).getOrderStatus().equals(PRODUCING)){
                    nums[1][1]++;       //  producing
                }else if (orders.get(i).getOrderStatus().equals(UNTAKEN)){
                    nums[1][2]++;       //  untaken
                }else if (orders.get(i).getOrderStatus().equals(DELIVERING)){
                    nums[1][3]++;       //  delivering
                }else if (orders.get(i).getOrderStatus().equals(FINISHED)){
                    nums[1][4]++;       //  finished
                }
            }else if (day.equals("WEDNESDAY")){
                if (orders.get(i).getOrderStatus().equals(UNPAID)){
                    nums[2][0]++;       //  unpaid
                }else if (orders.get(i).getOrderStatus().equals(PRODUCING)){
                    nums[2][1]++;       //  producing
                }else if (orders.get(i).getOrderStatus().equals(UNTAKEN)){
                    nums[2][2]++;       //  untaken
                }else if (orders.get(i).getOrderStatus().equals(DELIVERING)){
                    nums[2][3]++;       //  delivering
                }else if (orders.get(i).getOrderStatus().equals(FINISHED)){
                    nums[2][4]++;       //  finished
                }
            }else if (day.equals("THURSDAY")){
                if (orders.get(i).getOrderStatus().equals(UNPAID)){
                    nums[3][0]++;       //  unpaid
                }else if (orders.get(i).getOrderStatus().equals(PRODUCING)){
                    nums[3][1]++;       //  producing
                }else if (orders.get(i).getOrderStatus().equals(UNTAKEN)){
                    nums[3][2]++;       //  untaken
                }else if (orders.get(i).getOrderStatus().equals(DELIVERING)){
                    nums[3][3]++;       //  delivering
                }else if (orders.get(i).getOrderStatus().equals(FINISHED)){
                    nums[3][4]++;       //  finished
                }
            }else if (day.equals("FRIDAY")){
                if (orders.get(i).getOrderStatus().equals(UNPAID)){
                    nums[4][0]++;       //  unpaid
                }else if (orders.get(i).getOrderStatus().equals(PRODUCING)){
                    nums[4][1]++;       //  producing
                }else if (orders.get(i).getOrderStatus().equals(UNTAKEN)){
                    nums[4][2]++;       //  untaken
                }else if (orders.get(i).getOrderStatus().equals(DELIVERING)){
                    nums[4][3]++;       //  delivering
                }else if (orders.get(i).getOrderStatus().equals(FINISHED)){
                    nums[4][4]++;       //  finished
                }
            }else if (day.equals("SATURDAY")){
                if (orders.get(i).getOrderStatus().equals(UNPAID)){
                    nums[5][0]++;       //  unpaid
                }else if (orders.get(i).getOrderStatus().equals(PRODUCING)){
                    nums[5][1]++;       //  producing
                }else if (orders.get(i).getOrderStatus().equals(UNTAKEN)){
                    nums[5][2]++;       //  untaken
                }else if (orders.get(i).getOrderStatus().equals(DELIVERING)){
                    nums[5][3]++;       //  delivering
                }else if (orders.get(i).getOrderStatus().equals(FINISHED)){
                    nums[5][4]++;       //  finished
                }
            }else{
                if (orders.get(i).getOrderStatus().equals(UNPAID)){
                    nums[6][0]++;       //  unpaid
                }else if (orders.get(i).getOrderStatus().equals(PRODUCING)){
                    nums[6][1]++;       //  producing
                }else if (orders.get(i).getOrderStatus().equals(UNTAKEN)){
                    nums[6][2]++;       //  untaken
                }else if (orders.get(i).getOrderStatus().equals(DELIVERING)){
                    nums[6][3]++;       //  delivering
                }else if (orders.get(i).getOrderStatus().equals(FINISHED)){
                    nums[6][4]++;       //  finished
                }
            }
        }
        list.add(nums[0]);
        list.add(nums[1]);
        list.add(nums[2]);
        list.add(nums[3]);
        list.add(nums[4]);
        list.add(nums[5]);
        list.add(nums[6]);
//        data.put("周一", nums[0]);
//        data.put("周二", nums[1]);
//        data.put("周三", nums[2]);
//        data.put("周四", nums[3]);
//        data.put("周五", nums[4]);
//        data.put("周六", nums[5]);
//        data.put("周日", nums[6]);
        data.put("周一", list.get(0));
        data.put("周二",list.get(1));
        data.put("周三",list.get(2));
        data.put("周四", list.get(3));
        data.put("周五", list.get(4));
        data.put("周六", list.get(5));
        data.put("周日", list.get(6));
        return ResponseUtil.ok(list);
    }

}
