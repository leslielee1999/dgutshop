## API
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
5.  根据订单的创建时间和结束时间查询某些订单<br/>
`GET http://localhost:8083/admin/order/list?start=2018-02-01 00:00:00&end=2019-02-01 00:00:00`
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/order/list?page=`
    * 指定每页获得多少个订单（默认为10种）：<br/>`GET http://localhost:8083/admin/order/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/order/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/order/list?order=`