package org.dgutstu.dgutshop.core.util;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 *
 * 响应操作结果
 * <pre>
 *     {
 *         errno：错误码，
 *         errmsg：错误消息，
 *         data：响应数据
 *     }
 * </pre>
 *
 * 错误码：
 * 0：成功；
 * 4xx，前端错误，前端开发者需重新了解后端接口使用规范；
 *      401：参数错误，即前端没有传递后端需要的参数；
 *      402：参数值错误，即前端传递的参数值不符合后端接收范围；
 *
 * 5xx，后端错误，除501外，说明后端开发者应该继续优化代码，尽量避免返回后端错误码；
 *      501：验证失败，即后端要求用户登录；
 *      502：系统内部错误，即没有合适命名的后端内部错误；
 *      503：业务不支持，即后端虽然定义了接口，但是还没有实现功能；
 *      504，更新数据失效，即后端采用了乐观锁更新，而并发更新时存在数据更新失效；
 *      505，更新数据失败，即后端数据库更新失败（正常情况应该更新成功）。
 *
 * 6xx，小商城后端业务错误码，
 * 7xx，管理后台后端业务错误码
 * @Date: Create in 18:56 2020/12/12
 */
public class ResponseUtil {
    public static Object ok(){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        return obj;
    }


    //  绑定返回前台的响应操作结果
    //  便于有bug时校验错误
    public static Object ok(Object data){
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("errno", 0);
        obj.put("errmsg", "成功");
        obj.put("data", data);
        return obj;
    }

    //  对返回到前台的数据进行分页设置
    public static Object okList(List list){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);

        if(list instanceof Page){
            Page page = (Page) list;
            data.put("total", page.getTotal()); //设置总个数
            data.put("page", page.getPageNum());//设置页码
            data.put("limit", page.getPageSize());//设置每页的最多条数
            data.put("pages", page.getPages());//设置总页数
        }else {
            data.put("total", list.size());
            data.put("page", 1);
            data.put("limit", list.size());
            data.put("pages", 1);
        }

        return ok(data);
    }


    public static Object fail(int errno, String errmsg){
        Map<String, Object> obj = new HashMap<>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }

    public static Object badArgument(){
        return fail(401, "参数不对");
    }

    public static Object updatedDataFailed() {
        return fail(505, "更新数据失败");
    }
}
