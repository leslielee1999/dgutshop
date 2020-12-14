package org.dgutstu.dgutshop.admin.util;

import org.dgutstu.dgutshop.db.domain.DgutshopLog;
import org.dgutstu.dgutshop.db.service.DgutshopLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: leesk
 * @Description:
 *  一般日志：用户觉得需要查看的一般操作日志，设为默认的日志级别
 *  安全日志：用户安全相关的操作日志，例如登录、删除管理员
 *  订单日志：用户交易相关的操作日志，例如订单发货、退款
 *  其他日志：如果以上三种不合适，可以选择其他日志，设为优先级最低的日志级别
 * @Date: Create in 20:51 2020/12/14
 */
@Component
public class LogHelper {
    public static final Integer LOG_TYPE_GENERAL = 0;   //一般日志
    public static final Integer LOG_TYPE_AUTH = 1;      //安全日志
    public static final Integer LOG_TYPE_ORDER = 2;     //订单日志
    public static final Integer LOG_TYPE_OTHER = 3;     //其他日志

    @Autowired
    private DgutshopLogService logService;

    public void logAdmin(Integer type, String action, Boolean succeed, String result, String comment){
        DgutshopLog log = new DgutshopLog();


    }
}
