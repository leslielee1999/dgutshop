## Admin-Api
### 一、饮品管理
#### 1.1 查询功能
1.  列举所有饮品：<br/>
`GET http://localhost:8083/admin/products/list`
2.  根据id查询某种饮品<br/>
`GET http://localhost:8083/admin/products/list?productId=`
3.  根据饮品名查询某种饮品<br/>
`GET http://localhost:8083/admin/products/list?name=`
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/products/list?page=`
    * 指定每页获得多少种饮品（默认为10种）：<br/>`GET http://localhost:8083/admin/products/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/products/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/products/list?order=`

#### 1.2 增加功能
* 传递json
    ```
    POST http://localhost:8083/admin/products/create
    Content-Type: application/json
    
    {
      "id": 3,
      "code": "kkk",
      "name": "波波奶绿",
      "big_original_price": 12.00,
      "big_promote_price": 13.00,
      "medium_original_price":13.00,
      "medium_promote_price": 5.00,
      "description": "波波好吃",
      "status": 1,
      "picture": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
      "sales": 1,
      "create_time": null,
      "update_time": null,
      "deleted": 0
    }
    ```

#### 1.3 删除功能
* 给个id字段即可：`POST http://localhost:8083/admin/products/delete?id=`

#### 1.4 修改功能
* 传递json
    ```
    POST http://localhost:8083/admin/products/update
    Content-Type: application/json
    
    {
      "id": 4,
      "code": "mmm",
      "name": "热摩卡咖啡",
      "bigOriginalPrice": 15.00,
      "bigPromotePrice": 14.00,
      "mediumOriginalPrice":13.00,
      "mediumPromotePrice": 12.0,
      "description": "热的好吃",
      "status": 1,
      "picture": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
      "sales": 1,
      "create_time": null,
      "update_time": null,
      "heated": 1,
      "deleted": 0
    }
    ```
### 二、管理员管理
#### 2.1 查询功能
1.  列举所有管理员：<br/>
`GET http://localhost:8083/admin/admin/list`
2.  根据账户名查询某个员工（模糊查询）<br/>
`GET http://localhost:8083/admin/admin/list?nickname`
3.  根据真实姓名查询某个员工（模糊查询）<br/>
`GET http://localhost:8083/admin/admin/list?name`
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/admin/list?page=`
    * 指定每页获得多少种饮品（默认为10种）：<br/>`GET http://localhost:8083/admin/admin/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/admin/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/admin/list?order=`
#### 2.2 增加功能
* nickname：昵称，用作登录账号，范围必须为6~20
* 传递json
    ```
    POST http://localhost:8083/admin/admin/create
    Content-Type: application/json
    
    {
      "id": 3,
      "roleId": 1,
      "name": "林一",
      "nickname": "liyiiii",
      "password": "11231123",
      "code": "dgut-4",
      "picture":"http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
      "create_time": null,
      "update_time": null,
      "lastLoginIp": null,
      "lastLoginTime": null,
      "deleted": 0
    }
    ```
#### 2.3 删除功能
* 给个id字段即可：`POST http://localhost:8083/admin/admin/delete?id=`

#### 2.4 修改功能
* 传递json
    ```
    POST http://localhost:8083/admin/admin/update
    Content-Type: application/json
    
    {
      "id": 1,
      "roleId": 1,
      "name": "林一",
      "nickname": "linyiii",
      "password": "11231123",
      "code": "dgut-1",
      "picture":"http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
      "create_time": null,
      "update_time": null,
      "lastLoginIp": null,
      "lastLoginTime": null,
      "deleted": 0
    }
    ```
### 三、角色管理
#### 3.1 查询功能
1.  列举所有角色：<br/>
`GET http://localhost:8083/admin/role/list`
2.  根据角色名查询某个角色（模糊查询）<br/>
`GET http://localhost:8083/admin/role/list?name=`
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/role/list?page=`
    * 指定每页获得多少种饮品（默认为10种）：<br/>`GET http://localhost:8083/admin/role/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/role/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/role/list?order=`
#### 3.2 增加功能
* 传递json（仅作示例）
    ```
    POST http://localhost:8083/admin/role/create
    Content-Type: application/json
    
    {
      "id": 3,
      "name": "店员",
      "desc": "拥有改变订单状态功能",
      "enabled": true,
      "createTime": null,
      "updateTime": null,
      "deleted": false
    }
    ```
#### 3.3 删除功能
* 给个id字段即可：`POST http://localhost:8083/admin/role/delete?id=`

#### 3.4 修改功能
* 传递json
    ```
    POST http://localhost:8083/admin/role/update
    Content-Type: application/json
    
    {
      "id": 3,
      "name": "商家",
      "desc": "拥有关店功能",
      "enabled": true,
      "createTime": null,
      "updateTime": null,
      "deleted": false
    }
    ```
### 四、小程序的菜单分类管理
#### 4.1 查询功能
1.  列举所有分类：<br/>
`GET http://localhost:8083/admin/category/list`
    * 获取到的数据
    > 根据`categoryItems`可以获取分类单项，另外分类单项也有个index字段可用来排序
    ```$xslt
    {
      "errno": 0,
      "data": {
        "total": 3,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "categoryItems": [],
            "id": 5,
            "index": 6,
            "name": "春季特惠",
            "icon": "",
            "createTime": "2020-12-15T20:37:24",
            "updateTime": "2020-12-15T20:37:24",
            "deleted": false
          },
          {
            "categoryItems": [
              {
                "product": {
                  "id": 8,
                  "code": "ccc",
                  "name": "波波奶绿",
                  "bigOriginalPrice": null,
                  "bigPromotePrice": null,
                  "mediumOriginalPrice": null,
                  "mediumPromotePrice": null,
                  "description": "波波好吃",
                  "status": true,
                  "picture": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
                  "sales": 1,
                  "createTime": "2020-12-13T00:35:34",
                  "updateTime": "2020-12-13T00:35:34",
                  "heated": false,
                  "deleted": false
                },
                "id": 4,
                "index": 4,
                "cid": 2,
                "pid": 8,
                "createTime": "2020-12-16T00:35:06",
                "updateTime": "2020-12-16T00:35:06",
                "deleted": true
              },
              {
                "product": {
                  "id": 8,
                  "code": "ccc",
                  "name": "波波奶绿",
                  "bigOriginalPrice": null,
                  "bigPromotePrice": null,
                  "mediumOriginalPrice": null,
                  "mediumPromotePrice": null,
                  "description": "波波好吃",
                  "status": true,
                  "picture": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
                  "sales": 1,
                  "createTime": "2020-12-13T00:35:34",
                  "updateTime": "2020-12-13T00:35:34",
                  "heated": false,
                  "deleted": false
                },
                "id": 3,
                "index": 3,
                "cid": 2,
                "pid": 8,
                "createTime": "2020-12-16T00:33:04",
                "updateTime": "2020-12-16T00:33:04",
                "deleted": true
              },
              {
                "product": {
                  "id": 6,
                  "code": "kkk",
                  "name": "热可可",
                  "bigOriginalPrice": null,
                  "bigPromotePrice": null,
                  "mediumOriginalPrice": null,
                  "mediumPromotePrice": null,
                  "description": "可可也好吃",
                  "status": true,
                  "picture": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
                  "sales": 1,
                  "createTime": "2020-12-13T00:31:02",
                  "updateTime": "2020-12-13T00:31:02",
                  "heated": false,
                  "deleted": false
                },
                "id": 2,
                "index": 2,
                "cid": 2,
                "pid": 6,
                "createTime": null,
                "updateTime": null,
                "deleted": false
              },
              {
                "product": {
                  "id": 4,
                  "code": "mmm",
                  "name": "热摩卡咖啡",
                  "bigOriginalPrice": 15.00,
                  "bigPromotePrice": 14.00,
                  "mediumOriginalPrice": 13.00,
                  "mediumPromotePrice": 12.00,
                  "description": "热的好吃",
                  "status": true,
                  "picture": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
                  "sales": 1,
                  "createTime": "2020-12-13T00:26:10",
                  "updateTime": "2020-12-13T13:14:01",
                  "heated": true,
                  "deleted": false
                },
                "id": 1,
                "index": 1,
                "cid": 2,
                "pid": 4,
                "createTime": null,
                "updateTime": null,
                "deleted": false
              }
            ],
            "id": 2,
            "index": 2,
            "name": "现磨咖啡",
            "icon": "",
            "createTime": null,
            "updateTime": null,
            "deleted": false
          },
          {
            "categoryItems": [],
            "id": 1,
            "index": 1,
            "name": "热销",
            "icon": "",
            "createTime": null,
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    
    Response code: 200; Time: 73ms; Content length: 2354 bytes
    ```
2.  根据分类名查询某种分类（模糊查询）<br/>
`GET http://localhost:8083/admin/category/list?name=`
3.  根据分类顺序查询某种分类<br/>
`GET http://localhost:8083/admin/category/list?index=`
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/category/list?page=`
    * 指定每页获得多少种饮品（默认为10种）：<br/>`GET http://localhost:8083/admin/category/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/category/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/category/list?order=`
#### 4.2 增加功能
* 传递json
    ```
     ###
        POST http://localhost:8083/admin/category/update
        Content-Type: application/json
        
        [
        {
        "id": 1,
        "index": 6,
        "name": "热销",
        "icon": "",
        "createTime": null,
        "updateTime": null,
        "deleted": false
        },
        {
        "id": 2,
        "index": 5,
        "name": "现磨咖啡",
        "icon": "",
        "createTime": null,
        "updateTime": null,
        "deleted": false
        },
        {
        "id": 3,
        "index": 4,
        "name": "秋季特惠",
        "icon": "",
        "createTime": null,
        "updateTime": null,
        "deleted": false
        },
        {
        "id": 4,
        "index": 3,
        "name": "冬季特惠",
        "icon": "",
        "createTime": null,
        "updateTime": null,
        "deleted": false
        },
        {
        "id": 5,
        "index": 2,
        "name": "春季特惠",
        "icon": "",
        "createTime": null,
        "updateTime": null,
        "deleted": false
        },
        {
          "id": 6,
          "index": 1,
          "name": "爆款",
          "icon": "",
          "createTime": null,
          "updateTime": null,
          "deleted": false
        }
        ]
    ```
#### 4.3 删除功能
* 给个id字段即可：`POST http://localhost:8083/admin/category/delete?id=`

#### 4.4 修改功能
* 传递json
    ```
    ###
    POST http://localhost:8083/admin/category/update
    Content-Type: application/json
    
    [
    {
    "id": 1,
    "index": 6,
    "name": "热销",
    "icon": "",
    "createTime": null,
    "updateTime": null,
    "deleted": false
    },
    {
    "id": 2,
    "index": 5,
    "name": "现磨咖啡",
    "icon": "",
    "createTime": null,
    "updateTime": null,
    "deleted": false
    },
    {
    "id": 3,
    "index": 4,
    "name": "秋季特惠",
    "icon": "",
    "createTime": null,
    "updateTime": null,
    "deleted": false
    },
    {
    "id": 4,
    "index": 3,
    "name": "冬季特惠",
    "icon": "",
    "createTime": null,
    "updateTime": null,
    "deleted": false
    },
    {
    "id": 5,
    "index": 2,
    "name": "春季特惠",
    "icon": "",
    "createTime": null,
    "updateTime": null,
    "deleted": false
    },
    {
      "id": 6,
      "index": 1,
      "name": "爆款",
      "icon": "",
      "createTime": null,
      "updateTime": null,
      "deleted": false
    }
    ]
    ```
<big>注：</big><strong>分类单项只有增加和删除功能</strong>
#### 4.5 分类单项的增加功能
* 传递JSON
    ```
      ###
      POST http://localhost:8083/admin/category/categoryitem/create
      Content-Type: application/json
      
      {
        "cid": 2,
        "pids": [
        11,12,13
        ]
      }
    ```
#### 4.6 分类单项的删除功能
* 给个id字段即可：`POST http://localhost:8083/admin/category/categoryitem/delete?id=`

#### 4.7 分类单项更改顺序功能
* 传递JSON
    ```
    ###
    POST http://localhost:8083/admin/category/categoryitem/update
    Content-Type: application/json
    
    [
      {
        "id": 1,
        "index": 1,
        "cid": 2,
        "pid": 8,
        "createTime": null,
        "updateTime": null,
        "deleted": false
      },
      {
        "id": 2,
        "index": 3,
        "cid": 2,
        "pid": 4,
        "createTime": null,
        "updateTime": null,
        "deleted": false
      },
      {
        "id": 3,
        "index": 2,
        "cid": 2,
        "pid": 6,
        "createTime": null,
        "updateTime": null,
        "deleted": false
      }
    ]
    ```

//  TODO：<br/>
//  1. 更改菜单项的顺序时前端可能会传来多个菜单项，解决接收多个菜单项并更新菜单项表的问题，排序问题不考虑，由前端保证；<br/>
//  2. 添加菜单单项时前端可能会传来多个菜单单项，解决接收多个菜单单项并更新菜单单项的问题；
//  3. 添加更改菜单单项顺序功能的API
> 解决，详见上方更新的API

### 五、订单管理
* 后台管理人员只拥有查询功能
#### 5.1 查询订单
1.  列举所有订单：<br/>
`GET http://localhost:8083/admin/order/list`
    * 获取到的数据
    > 根据`orderItemList`可以获取订单项
    ```$xslt
    {
      "errno": 0,
      "data": {
        "limit": 10,
        "total": 2,
        "page": 1,
        "list": [
          {
            "id": 2,
            "code": "edfs",
            "type": "1",
            "orderStatus": 101,
            "productPrice": 22.00,
            "deliveryPrice": 1.00,
            "orderPrice": 23.00,
            "createTime": "2020-12-16T21:30:13",
            "userId": 1,
            "userName": "kseel",
            "consignee": "小李",
            "consigneeAddress": "广东汕头",
            "consigneeRoom": "松山湖",
            "consigneePhone": "14444444444",
            "deliveryCompany": "饿了么",
            "deliveryCode": "3433333",
            "message": "好",
            "payDate": "2020-12-17T19:00:22",
            "orderItemList": [
              {
                "id": 2,
                "oid": 2,
                "pid": 2,
                "productName": "抹茶奶盖",
                "size": 0,
                "productOriginalPrice": 10.00,
                "productPromotePrice": 8.00,
                "toppingId": [
                  1,
                  2
                ],
                "toppingName": [
                  "珍珠",
                  "冰沙"
                ],
                "toppingPrice": 4.00,
                "productActualPrice": 12.00,
                "productPicture": "",
                "sum": 3,
                "note": "",
                "createTime": "2020-12-16T21:30:13",
                "updateTime": null,
                "deleted": false
              }
            ]
          },
          {
            "id": 1,
            "code": "reqww",
            "type": "1",
            "orderStatus": 101,
            "productPrice": 200.00,
            "deliveryPrice": 10.00,
            "orderPrice": 210.00,
            "createTime": "2018-02-01T00:00:00",
            "userId": 2,
            "userName": "bbbbbb",
            "consignee": "小王",
            "consigneeAddress": "广东东莞",
            "consigneeRoom": "松山湖",
            "consigneePhone": "12222222222",
            "deliveryCompany": "美团",
            "deliveryCode": "123333",
            "message": "不错",
            "payDate": "2020-12-16T18:57:53",
            "orderItemList": [
              {
                "id": 1,
                "oid": 1,
                "pid": 1,
                "productName": "抹茶奶盖",
                "size": 0,
                "productOriginalPrice": 10.00,
                "productPromotePrice": 8.00,
                "toppingId": [
                  1,
                  2
                ],
                "toppingName": [
                  "珍珠",
                  "冰沙"
                ],
                "toppingPrice": 4.00,
                "productActualPrice": 12.00,
                "productPicture": "",
                "sum": 2,
                "note": "",
                "createTime": "2018-02-01T00:00:00",
                "updateTime": null,
                "deleted": false
              }
            ]
          }
        ]
      },
      "errmsg": "成功"
    }
    
    Response code: 200; Time: 434ms; Content length: 1469 bytes
    ```
2.  根据用户昵称名（网名）查询某个订单（模糊查询）<br/>
`GET http://localhost:8083/admin/order/list?nickname=`
3.  根据外卖配送员查询某些订单（模糊查询）<br/>
`GET http://localhost:8083/admin/order/list?consignee=`
4.  根据订单编号查询某个订单<br/>
`GET http://localhost:8083/admin/order/list?code=`
5.  根据给定的订单的创建时间范围查询某些订单<br/>
`GET http://localhost:8083/admin/order/list?start=2018-02-01 00:00:00&end=2019-02-01 00:00:00`
6.  根据订单状态（可以一次输入多个）查询某些订单
* 订单状态
    * 101待支付
    * 201待出货
    * 301已出货
    * 401已完成<br/>
`GET http://localhost:8083/admin/order/list?orderStatusArray[]=101&orderStatusArray[]=201`
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/order/list?page=`
    * 指定每页获得多少个订单（默认为10种）：<br/>`GET http://localhost:8083/admin/order/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/order/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/order/list?order=`
    
> 记录：<br/>
> 1. 增加订单状态的查询，一次可输入多个（见上方）<br/>
> 2. 新增用户管理功能<br/>
> 3. 所有查询功能均支持综合查询
> 4. 增加未有按时间查询的已有功能的时间查询功能，统一所有时间查询功能为：按给定创建某记录时间的范围来查找
>   1. 订单管理完成；<br/>
>       `GET http://localhost:8083/admin/order/list?start=2018-02-01 00:00:00&end=2019-02-01 00:00:00`
>   2. 管理员管理完成
>       `GET http://localhost:8083/admin/admin/list?start=2020-12-12 23:11:10&end=2020-12-14 23:11:10`
>   3. 侧边栏完成
>       `GET http://localhost:8083/admin/category/list?start=2020-12-15 20:19:46&end=2020-12-15 20:40:46`
>   4. 饮品完成
>       `GET http://localhost:8083/admin/products/list?start=2020-12-13 09:51:50&end=2020-12-13 10:30:49`
>   5. 用户详见下方

### 六、用户管理
* 后台管理人员拥有查询功能、查看用户详情功能和设置用户状态权限功能
#### 6.1 查询用户
1. 列举所有用户<br/>
`GET http://localhost:8083/admin/user/list` 
    ```
    {
      "errno": 0,
      "data": {
        "total": 3,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 1,
            "name": "leesk",
            "password": "123",
            "gender": 1,
            "phone": "18025534180",
            "wechatId": "kk545771383",
            "nickname": "kseel",
            "sessionKey": "asdasdqa",
            "createTime": null,
            "updateTime": null,
            "lastLoginTime": null,
            "status": 0,
            "lastLoginIp": "",
            "deleted": false
          },
          {
            "id": 2,
            "name": "qwqwe",
            "password": "123123",
            "gender": 0,
            "phone": "21312312312",
            "wechatId": "efdasdasd",
            "nickname": "bbbbbb",
            "sessionKey": "sdsd11",
            "createTime": null,
            "updateTime": null,
            "lastLoginTime": null,
            "status": 0,
            "lastLoginIp": "",
            "deleted": false
          },
          {
            "id": 3,
            "name": "rqqsasxj",
            "password": "11231",
            "gender": 0,
            "phone": "7777777777",
            "wechatId": "qweqwe",
            "nickname": "sadfhg",
            "sessionKey": "32523",
            "createTime": null,
            "updateTime": null,
            "lastLoginTime": null,
            "status": 0,
            "lastLoginIp": "",
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
2. 根据用户id精准查找某个用户<br/>
`GET http://localhost:8083/admin/user/list?id=1`
    ```
    {
      "errno": 0,
      "data": {
        "total": 1,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 1,
            "name": "leesk",
            "password": "123",
            "gender": 1,
            "phone": "18025534180",
            "wechatId": "kk545771383",
            "nickname": "kseel",
            "sessionKey": "asdasdqa",
            "createTime": null,
            "updateTime": null,
            "lastLoginTime": null,
            "status": 0,
            "lastLoginIp": "",
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
3. 根据用户姓名、用户昵称、用户微信号进行模糊查询<br/>
`GET http://localhost:8083/admin/user/list?name=q&nickname=a`
    ```
    {
      "errno": 0,
      "data": {
        "total": 1,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 3,
            "name": "rqqsasxj",
            "password": "11231",
            "gender": 0,
            "phone": "7777777777",
            "wechatId": "qweqwe",
            "nickname": "sadfhg",
            "sessionKey": "32523",
            "createTime": null,
            "updateTime": null,
            "lastLoginTime": null,
            "status": 0,
            "lastLoginIp": "",
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
    `GET http://localhost:8083/admin/user/list?name=q&wechatId=a`
    ```
   {
     "errno": 0,
     "data": {
       "total": 1,
       "pages": 1,
       "limit": 10,
       "page": 1,
       "list": [
         {
           "id": 2,
           "name": "qwqwe",
           "password": "123123",
           "gender": 0,
           "phone": "21312312312",
           "wechatId": "efdasdasd",
           "nickname": "bbbbbb",
           "sessionKey": "sdsd11",
           "createTime": null,
           "updateTime": null,
           "lastLoginTime": null,
           "status": 0,
           "lastLoginIp": "",
           "deleted": false
         }
       ]
     },
     "errmsg": "成功"
   }
   ```
4. 按给定创建时间的范围来查找<br/>
`GET http://localhost:8083/admin/user/list?start=2020-09-12 23:11:10&end=2020-12-14 23:11:10`
    ```
   {
     "errno": 0,
     "data": {
       "total": 2,
       "pages": 1,
       "limit": 10,
       "page": 1,
       "list": [
         {
           "id": 1,
           "name": "leesk",
           "password": "123",
           "gender": 1,
           "phone": "18025534180",
           "wechatId": "kk545771383",
           "nickname": "kseel",
           "sessionKey": "asdasdqa",
           "createTime": "2020-11-14T23:11:10",
           "updateTime": null,
           "lastLoginTime": null,
           "status": 0,
           "lastLoginIp": "",
           "deleted": false
         },
         {
           "id": 2,
           "name": "qwqwe",
           "password": "123123",
           "gender": 0,
           "phone": "21312312312",
           "wechatId": "efdasdasd",
           "nickname": "bbbbbb",
           "sessionKey": "sdsd11",
           "createTime": "2020-10-14T23:11:10",
           "updateTime": null,
           "lastLoginTime": null,
           "status": 0,
           "lastLoginIp": "",
           "deleted": false
         }
       ]
     },
     "errmsg": "成功"
   }
    ```
#### 6.2 获取某个用户的详情信息
> 这里前端可根据id通过之前获得的list来获取某个用户的信息，也可以通过下面的api

`GET http://localhost:8083/admin/user/detail?id=2`
```
{
  "errno": 0,
  "data": {
    "id": 2,
    "name": "qwqwe",
    "password": "123123",
    "gender": 0,
    "phone": "21312312312",
    "wechatId": "efdasdasd",
    "nickname": "bbbbbb",
    "sessionKey": "sdsd11",
    "createTime": "2020-10-14T23:11:10",
    "updateTime": null,
    "lastLoginTime": null,
    "status": 0,
    "lastLoginIp": "",
    "deleted": false
  },
  "errmsg": "成功"
}
```
#### 6.3 更改某个用户的状态
> 这里前端应当设置用户信息除了状态都不能改动

* 用户状态【0 可用, 1 不可登录, 2 不可交易】

```
POST http://localhost:8083/admin/user/update
Content-Type: application/json

{
"id": 2,
"name": "qwqwe",
"password": "123123",
"gender": 0,
"phone": "21312312312",
"wechatId": "efdasdasd",
"nickname": "bbbbbb",
"sessionKey": "sdsd11",
"createTime": "2020-10-14T23:11:10",
"updateTime": null,
"lastLoginTime": null,
"status": 2,
"lastLoginIp": "",
"deleted": false
}
```
* 返回信息
```
{
  "errno": 0,
  "data": 1,
  "errmsg": "成功"
}
```
> 记录，完善：
> 创建类目的时候，新建的index自动为比原有类目最大的index还大1的值<br/>
```
POST http://localhost:8083/admin/category/create
Content-Type: application/json

{
  "id": 3,
  "index": 1,
  "name": "折扣",
  "icon": "",
  "createTime": null,
  "updateTime": null,
  "deleted": false
}
```


### 七、小料管理
#### 7.1 查询功能
1.  列举所有小料：<br/>
`GET http://localhost:8083/admin/toppings/list`
    ```
    {
      "errno": 0,
      "data": {
        "total": 4,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 4,
            "code": "yyyyuw",
            "name": "绿豆",
            "price": 2.00,
            "description": "好吃",
            "status": 1,
            "picture": "",
            "createTime": "2020-08-18T10:25:44",
            "updateTime": "2020-12-18T10:26:55",
            "deleted": false
          },
          {
            "id": 3,
            "code": "ssqqsa",
            "name": "珍珠",
            "price": 2.00,
            "description": "黑糖的",
            "status": 0,
            "picture": "",
            "createTime": "2020-09-18T10:25:44",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 2,
            "code": "oowiak",
            "name": "布丁",
            "price": 2.00,
            "description": "韧性好",
            "status": 1,
            "picture": "",
            "createTime": "2020-10-18T10:25:44",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 1,
            "code": "xxaaqq",
            "name": "红豆",
            "price": 2.00,
            "description": "甜度适中",
            "status": 0,
            "picture": "",
            "createTime": "2020-11-18T10:25:44",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
2.  根据id查询某种小料<br/>
`GET http://localhost:8083/admin/toppings/list?toppingId=`
    ```
    {
      "errno": 0,
      "data": {
        "total": 1,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 2,
            "code": "oowiak",
            "name": "布丁",
            "price": 2.00,
            "description": "韧性好",
            "status": 1,
            "picture": "",
            "createTime": "2020-10-18T10:25:44",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
3.  根据小料名查询某种小料（模糊查询）<br/>
`GET http://localhost:8083/admin/toppings/list?name=`
    ```
    {
      "errno": 0,
      "data": {
        "total": 1,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 3,
            "code": "ssqqsa",
            "name": "珍珠",
            "price": 2.00,
            "description": "黑糖的",
            "status": 0,
            "picture": "",
            "createTime": "2020-09-18T10:25:44",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
4.  根据小料编号查询某种小料（模糊查询）<br/>
`GET http://localhost:8083/admin/toppings/list?code=q`
    ```
    {
      "errno": 0,
      "data": {
        "total": 2,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 3,
            "code": "ssqqsa",
            "name": "珍珠",
            "price": 2.00,
            "description": "黑糖的",
            "status": 0,
            "picture": "",
            "createTime": "2020-09-18T10:25:44",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 1,
            "code": "xxaaqq",
            "name": "红豆",
            "price": 2.00,
            "description": "甜度适中",
            "status": 0,
            "picture": "",
            "createTime": "2020-11-18T10:25:44",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
5.  根据商品状态查询某些小料商品状态【0：正常；1：下架】<br/>
`GET http://localhost:8083/admin/toppings/list?status=1`
    ```
    {
      "errno": 0,
      "data": {
        "total": 2,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 4,
            "code": "yyyyuw",
            "name": "绿豆",
            "price": 2.00,
            "description": "好吃",
            "status": 1,
            "picture": "",
            "createTime": "2020-08-18T10:25:44",
            "updateTime": "2020-12-18T10:26:55",
            "deleted": false
          },
          {
            "id": 2,
            "code": "oowiak",
            "name": "布丁",
            "price": 2.00,
            "description": "韧性好",
            "status": 1,
            "picture": "",
            "createTime": "2020-10-18T10:25:44",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
6.  按给定创建时间的范围来查找<br/>
`GET http://localhost:8083/admin/toppings/list?start=2020-10-13 09:51:50&end=2020-12-13 10:30:49`
    ```
    {
      "errno": 0,
      "data": {
        "total": 2,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 2,
            "code": "oowiak",
            "name": "布丁",
            "price": 2.00,
            "description": "韧性好",
            "status": 1,
            "picture": "",
            "createTime": "2020-10-18T10:25:44",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 1,
            "code": "xxaaqq",
            "name": "红豆",
            "price": 2.00,
            "description": "甜度适中",
            "status": 0,
            "picture": "",
            "createTime": "2020-11-18T10:25:44",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```

* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/toppings/list?page=`
    * 指定每页获得多少种小料（默认为10种）：<br/>`GET http://localhost:8083/admin/toppings/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/toppings/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/toppings/list?order=`

#### 7.2 增加功能
* 传递json
    ```
    POST http://localhost:8083/admin/toppings/create
    Content-Type: application/json
    
    {
      "id": 1,
      "code": "iuuuou",
      "name": "仙草",
      "price": 2.00,
      "description": "黑色",
      "status": 1,
      "picture": "",
      "createTime": null,
      "updateTime": null,
      "deleted": false
    }
    ```
* 输出
    ```
  {
    "errno": 0,
    "errmsg": "成功"
  }
  ```
#### 7.3 删除功能
* 给个id字段即可：`POST http://localhost:8083/admin/toppings/delete?id=4`
* 输出
    ```
  {
    "errno": 0,
    "errmsg": "成功"
  }
  ```

#### 7.4 修改功能
* 传递json
    ```
    POST http://localhost:8083/admin/toppings/update
    Content-Type: application/json
    
    {
      "id": 4,
      "code": "yyyyuw",
      "name": "绿豆",
      "price": 2.00,
      "description": "好吃",
      "status": 1,
      "picture": "",
      "createTime": null,
      "updateTime": null,
      "deleted": false
    }
    ```
* 输出
    ```
    {
      "errno": 0,
      "errmsg": "成功"
    }
  ```
  
### 八、用户地址管理
* 后台管理人员只拥有查询功能
#### 8.1 查询功能
1.  列举所有用户地址：<br/>
`GET http://localhost:8083/admin/address/list`
    * 获取到的数据
    ```
    {
      "errno": 0,
      "data": {
        "total": 3,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 3,
            "userId": 2,
            "wechatId": "efdasdasd",
            "userName": "冰冰",
            "userPhone": "12222222222",
            "userAddress": "内蒙古",
            "userRoom": "胡同2号",
            "isDefault": 1,
            "createTime": "2020-10-13T09:51:50",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 2,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "琳琳",
            "userPhone": "19999999999",
            "userAddress": "北京",
            "userRoom": "胡同1号",
            "isDefault": 1,
            "createTime": "2020-11-13T09:51:50",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 1,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "小卡",
            "userPhone": "13333333333",
            "userAddress": "广东东莞松山湖",
            "userRoom": "东莞理工学院大学路1号",
            "isDefault": 0,
            "createTime": "2020-12-13T09:51:50",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
2.  根据用户id查询某些用户地址<br/>
`GET http://localhost:8083/admin/address/list?userId=2`
    ```
    {
      "errno": 0,
      "data": {
        "total": 1,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 3,
            "userId": 2,
            "wechatId": "efdasdasd",
            "userName": "冰冰",
            "userPhone": "12222222222",
            "userAddress": "内蒙古",
            "userRoom": "胡同2号",
            "isDefault": 1,
            "createTime": "2020-10-13T09:51:50",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ``` 
3.  根据用户微信号查询某些用户地址（模糊查询）<br/>
`GET http://localhost:8083/admin/address/list?wechatId=3`
    ```
    {
      "errno": 0,
      "data": {
        "total": 2,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 2,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "琳琳",
            "userPhone": "19999999999",
            "userAddress": "北京",
            "userRoom": "胡同1号",
            "isDefault": 1,
            "createTime": "2020-11-13T09:51:50",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 1,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "小卡",
            "userPhone": "13333333333",
            "userAddress": "广东东莞松山湖",
            "userRoom": "东莞理工学院大学路1号",
            "isDefault": 0,
            "createTime": "2020-12-13T09:51:50",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
4.  根据收货人姓名查询某些用户地址（模糊查询）<br/>
`GET http://localhost:8083/admin/address/list?userName=卡`
    ```
    {
      "errno": 0,
      "data": {
        "total": 1,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 1,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "小卡",
            "userPhone": "13333333333",
            "userAddress": "广东东莞松山湖",
            "userRoom": "东莞理工学院大学路1号",
            "isDefault": 0,
            "createTime": "2020-12-13T09:51:50",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
5.  根据收货人手机号码查询某些用户地址（模糊查询）<br/>
`GET http://localhost:8083/admin/address/list?phone=2`
    ```
    {
      "errno": 0,
      "data": {
        "total": 1,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 3,
            "userId": 2,
            "wechatId": "efdasdasd",
            "userName": "冰冰",
            "userPhone": "12222222222",
            "userAddress": "内蒙古",
            "userRoom": "胡同2号",
            "isDefault": 1,
            "createTime": "2020-10-13T09:51:50",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
6.  按给定创建时间的范围来查找<br/>
`GET http://localhost:8083/admin/address/list?start=2020-11-13 09:51:50&end=2020-12-13 10:30:49`
    ```
    {
      "errno": 0,
      "data": {
        "total": 2,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 2,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "琳琳",
            "userPhone": "19999999999",
            "userAddress": "北京",
            "userRoom": "胡同1号",
            "isDefault": 1,
            "createTime": "2020-11-13T09:51:50",
            "updateTime": null,
            "deleted": false
          },
          {
            "id": 1,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "小卡",
            "userPhone": "13333333333",
            "userAddress": "广东东莞松山湖",
            "userRoom": "东莞理工学院大学路1号",
            "isDefault": 0,
            "createTime": "2020-12-13T09:51:50",
            "updateTime": null,
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/address/list?page=`
    * 指定每页获得多少个订单（默认为10种）：<br/>`GET http://localhost:8083/admin/address/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/address/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/address/list?order=`
    
### 九、小程序的轮播图管理
#### 9.1 查询功能
1.  列举所有图片：<br/>

    `GET http://localhost:8083/admin/slideshow/list`
    ```
    {
      "errno": 0,
      "data": {
        "total": 3,
        "pages": 1,
        "limit": 10,
        "page": 1,
        "list": [
          {
            "id": 4,
            "index": 1,
            "picture": "",
            "createTime": "2020-12-19T13:11:13",
            "updateTime": "2020-12-19T13:17:22",
            "deleted": false
          },
          {
            "id": 2,
            "index": 3,
            "picture": "",
            "createTime": null,
            "updateTime": "2020-12-19T13:17:22",
            "deleted": false
          },
          {
            "id": 1,
            "index": 4,
            "picture": "",
            "createTime": null,
            "updateTime": "2020-12-19T13:17:22",
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }
    ```
    
#### 9.2 增加功能
* 传递json
    ```
    ###
    POST http://localhost:8083/admin/slideshow/create
    Content-Type: application/json
    
    {
      "id": 2,
      "index": 6,
      "picture": ""
    }
    ```

#### 9.3 删除功能
* 给个id字段即可：
    `POST http://localhost:8083/admin/slideshow/delete?id=5`
    
#### 9.4 修改功能（一次传递多个以更改顺序）
* 传递json
    ```
  ###
  POST http://localhost:8083/admin/slideshow/update
  Content-Type: application/json
  
  [
    {
      "id": 1,
      "index": 4,
      "picture": ""
    },
    {
      "id": 2,
      "index": 3,
      "picture": ""
    },
    {
      "id": 4,
      "index": 1,
      "picture": ""
    }
  ]
  ```
  
### 十、后台管理员的发货功能

* 点击发货后获取到所有配送员的列表

接口链接

    GET http://localhost:8083/admin/delivery/list
    
请求参数

    无
    
响应内容

    {
      "errno": 0,
      "data": {
        "total": 3,
        "pages": 1,
        "limit": 3,
        "page": 1,
        "list": [
          {
            "id": 1,
            "name": "林林",
            "phone": "14523441344"
          },
          {
            "id": 2,
            "name": "王王",
            "phone": "17284483913"
          },
          {
            "id": 3,
            "name": "张张",
            "phone": "19283381782"
          }
        ]
      },
      "errmsg": "成功"
    }
    
* 选择好配送员后将相关信息用json传给后台

接口链接

    POST http://localhost:8083/admin/order/delivery
    
请求参数

    Content-Type: application/json
    
    {
      "orderId": "2",
      "orderCode": "edfs",
      "deliverymanName": "林一",
      "deliverymanPhone": "16432234546"
    }
    
响应内容

    {
      "errno": 0,
      "errmsg": "成功"
    }

### 十一、商家信息管理

#### 1. 查看商家信息

接口链接

    GET http://localhost:8083/admin/config/mall
    
请求参数

    无
    
响应内容

    {
      "errno": 0,
      "data": {
        "dgutshop_shop_latitude": "31.201900",
        "dgutshop_shop_longitude": "121.587839",
        "dgutshop_shop_qq": "545771383",
        "dgutshop_shop_address": "广东东莞松山湖校区",
        "dgutshop_shop_phone": "021-xxxx-xxxx",
        "dgutshop_shop_businesshours": "周一至周五，早上9点~晚上9点",
        "dgutshop_shop_name": "请你喝"
      },
      "errmsg": "成功"
    }
    
#### 2. 修改商家信息

接口链接

    POST http://localhost:8083/admin/config/mall
    
请求参数

    Content-Type: application/json
    
    {
      "dgutshop_shop_latitude": "31.201900",
      "dgutshop_shop_longitude": "121.587839",
      "dgutshop_shop_qq": "545771383",
      "dgutshop_shop_address": "东莞理工学院",
      "dgutshop_shop_phone": "021-xxxx-xxxx",
      "dgutshop_shop_businesshours": "周一至周五，早上9点~晚上9点",
      "dgutshop_shop_name": "请你喝"
    }
    
响应内容

    {
      "errno": 0,
      "errmsg": "成功"
    }