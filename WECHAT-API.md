## Wechat-Api

### 一、类目服务

#### 1.1 侧边栏列表

接口链接

    GET http://localhost:8082/wechat/catalog/list
    
请求参数

    无
    
响应内容

    {
      "errno": 0,
      "data": {
        "total": 7,
        "pages": 1,
        "limit": 7,
        "page": 1,
        "list": [
          {
            "categoryItems": [
              {
                "product": {
                  "id": 11,
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
                  "createTime": "2020-12-13T00:46:49",
                  "updateTime": "2020-12-13T00:46:49",
                  "heated": false,
                  "deleted": false
                },
                "id": 17,
                "index": 8,
                "cid": 1,
                "pid": 11,
                "createTime": "2020-12-17T00:32:07",
                "updateTime": "2020-12-17T00:32:07",
                "deleted": false
              },
              {
                "product": {
                  "id": 10,
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
                  "createTime": "2020-12-13T00:41:13",
                  "updateTime": "2020-12-13T00:41:13",
                  "heated": false,
                  "deleted": false
                },
                "id": 16,
                "index": 7,
                "cid": 1,
                "pid": 10,
                "createTime": "2020-12-17T00:00:25",
                "updateTime": "2020-12-17T00:00:25",
                "deleted": false
              },
              {
                "product": {
                  "id": 9,
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
                  "createTime": "2020-12-13T00:39:56",
                  "updateTime": "2020-12-13T00:39:56",
                  "heated": false,
                  "deleted": false
                },
                "id": 15,
                "index": 6,
                "cid": 1,
                "pid": 9,
                "createTime": "2020-12-17T00:00:25",
                "updateTime": "2020-12-17T00:00:25",
                "deleted": false
              },
              {
                "product": {
                  "id": 7,
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
                  "createTime": "2020-12-13T00:35:00",
                  "updateTime": "2020-12-13T00:35:00",
                  "heated": false,
                  "deleted": false
                },
                "id": 14,
                "index": 5,
                "cid": 1,
                "pid": 7,
                "createTime": "2020-12-17T00:00:25",
                "updateTime": "2020-12-17T00:00:25",
                "deleted": false
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
                "id": 13,
                "index": 4,
                "cid": 1,
                "pid": 6,
                "createTime": "2020-12-17T00:00:25",
                "updateTime": "2020-12-17T00:00:25",
                "deleted": false
              },
              {
                "product": {
                  "id": 10,
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
                  "createTime": "2020-12-13T00:41:13",
                  "updateTime": "2020-12-13T00:41:13",
                  "heated": false,
                  "deleted": false
                },
                "id": 12,
                "index": 3,
                "cid": 1,
                "pid": 10,
                "createTime": "2020-12-16T23:57:25",
                "updateTime": "2020-12-16T23:57:25",
                "deleted": false
              },
              {
                "product": {
                  "id": 9,
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
                  "createTime": "2020-12-13T00:39:56",
                  "updateTime": "2020-12-13T00:39:56",
                  "heated": false,
                  "deleted": false
                },
                "id": 11,
                "index": 2,
                "cid": 1,
                "pid": 9,
                "createTime": "2020-12-16T23:57:25",
                "updateTime": "2020-12-16T23:57:25",
                "deleted": false
              },
              {
                "product": {
                  "id": 7,
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
                  "createTime": "2020-12-13T00:35:00",
                  "updateTime": "2020-12-13T00:35:00",
                  "heated": false,
                  "deleted": false
                },
                "id": 10,
                "index": 1,
                "cid": 1,
                "pid": 7,
                "createTime": "2020-12-16T23:57:25",
                "updateTime": "2020-12-16T23:57:25",
                "deleted": false
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
                "id": 9,
                "index": 0,
                "cid": 1,
                "pid": 6,
                "createTime": "2020-12-16T23:57:25",
                "updateTime": "2020-12-16T23:57:25",
                "deleted": false
              }
            ],
            "id": 1,
            "index": 6,
            "name": "热销",
            "icon": "",
            "createTime": null,
            "updateTime": "2020-12-16T22:59:49",
            "deleted": false
          },
          {
            "categoryItems": [
              {
                "product": {
                  "id": 1,
                  "code": "aaa",
                  "name": "珍珠奶茶",
                  "bigOriginalPrice": 10.00,
                  "bigPromotePrice": 9.00,
                  "mediumOriginalPrice": 8.00,
                  "mediumPromotePrice": 7.00,
                  "description": "黑珍珠",
                  "status": true,
                  "picture": "",
                  "sales": 1,
                  "createTime": "2018-02-01T00:00:00",
                  "updateTime": "2018-02-01T00:00:00",
                  "heated": false,
                  "deleted": false
                },
                "id": 8,
                "index": 8,
                "cid": 2,
                "pid": 1,
                "createTime": "2020-12-16T23:54:12",
                "updateTime": "2020-12-16T23:54:12",
                "deleted": false
              },
              {
                "product": {
                  "id": 3,
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
                  "createTime": "2020-12-13T00:25:46",
                  "updateTime": "2020-12-13T00:25:46",
                  "heated": false,
                  "deleted": true
                },
                "id": 7,
                "index": 7,
                "cid": 2,
                "pid": 3,
                "createTime": "2020-12-16T23:54:12",
                "updateTime": "2020-12-16T23:54:12",
                "deleted": false
              },
              {
                "product": {
                  "id": 2,
                  "code": "bbb",
                  "name": "抹茶奶盖",
                  "bigOriginalPrice": 12.00,
                  "bigPromotePrice": 10.00,
                  "mediumOriginalPrice": 10.00,
                  "mediumPromotePrice": 8.00,
                  "description": "",
                  "status": true,
                  "picture": "",
                  "sales": 1,
                  "createTime": "2018-02-02T00:00:00",
                  "updateTime": "2018-02-02T00:00:00",
                  "heated": false,
                  "deleted": false
                },
                "id": 6,
                "index": 6,
                "cid": 2,
                "pid": 2,
                "createTime": "2020-12-16T23:54:12",
                "updateTime": "2020-12-16T23:54:12",
                "deleted": false
              },
              {
                "product": {
                  "id": 1,
                  "code": "aaa",
                  "name": "珍珠奶茶",
                  "bigOriginalPrice": 10.00,
                  "bigPromotePrice": 9.00,
                  "mediumOriginalPrice": 8.00,
                  "mediumPromotePrice": 7.00,
                  "description": "黑珍珠",
                  "status": true,
                  "picture": "",
                  "sales": 1,
                  "createTime": "2018-02-01T00:00:00",
                  "updateTime": "2018-02-01T00:00:00",
                  "heated": false,
                  "deleted": false
                },
                "id": 5,
                "index": 5,
                "cid": 2,
                "pid": 1,
                "createTime": "2020-12-16T23:54:12",
                "updateTime": "2020-12-16T23:54:12",
                "deleted": false
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
                "id": 3,
                "index": 2,
                "cid": 2,
                "pid": 6,
                "createTime": "2020-12-16T00:33:04",
                "updateTime": "2020-12-17T21:03:01",
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
                "id": 2,
                "index": 3,
                "cid": 2,
                "pid": 4,
                "createTime": null,
                "updateTime": "2020-12-17T21:03:01",
                "deleted": false
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
                "id": 1,
                "index": 1,
                "cid": 2,
                "pid": 8,
                "createTime": null,
                "updateTime": "2020-12-17T21:03:01",
                "deleted": false
              }
            ],
            "id": 2,
            "index": 5,
            "name": "现磨咖啡",
            "icon": "",
            "createTime": null,
            "updateTime": "2020-12-16T22:59:49",
            "deleted": false
          },
          {
            "categoryItems": [],
            "id": 3,
            "index": 4,
            "name": "秋季特惠",
            "icon": "",
            "createTime": "2020-12-15T20:01:13",
            "updateTime": "2020-12-16T22:59:49",
            "deleted": false
          },
          {
            "categoryItems": [],
            "id": 4,
            "index": 3,
            "name": "冬季特惠",
            "icon": "",
            "createTime": "2020-12-15T20:19:46",
            "updateTime": "2020-12-16T22:59:49",
            "deleted": false
          },
          {
            "categoryItems": [],
            "id": 5,
            "index": 2,
            "name": "春季特惠",
            "icon": "",
            "createTime": "2020-12-15T20:37:24",
            "updateTime": "2020-12-16T22:59:49",
            "deleted": false
          },
          {
            "categoryItems": [],
            "id": 6,
            "index": 1,
            "name": "爆款",
            "icon": "",
            "createTime": "2020-12-16T00:14:27",
            "updateTime": "2020-12-16T22:59:49",
            "deleted": false
          },
          {
            "categoryItems": [],
            "id": 7,
            "index": 7,
            "name": "折扣",
            "icon": "",
            "createTime": "2020-12-18T09:17:52",
            "updateTime": "2020-12-18T09:17:52",
            "deleted": false
          }
        ]
      },
      "errmsg": "成功"
    }

#### 1.2 定位到特定侧边栏


接口链接

    GET http://localhost:8082/wechat/catalog/current
    
请求参数

    id：侧边栏的id
    
响应内容

    {
      "errno": 0,
      "data": {
        "currentCategory": {
          "categoryItems": [
            {
              "product": {
                "id": 1,
                "code": "aaa",
                "name": "珍珠奶茶",
                "bigOriginalPrice": 10.00,
                "bigPromotePrice": 9.00,
                "mediumOriginalPrice": 8.00,
                "mediumPromotePrice": 7.00,
                "description": "黑珍珠",
                "status": true,
                "picture": "",
                "sales": 1,
                "createTime": "2018-02-01T00:00:00",
                "updateTime": "2018-02-01T00:00:00",
                "heated": false,
                "deleted": false
              },
              "id": 8,
              "index": 8,
              "cid": 2,
              "pid": 1,
              "createTime": "2020-12-16T23:54:12",
              "updateTime": "2020-12-16T23:54:12",
              "deleted": false
            },
            {
              "product": {
                "id": 3,
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
                "createTime": "2020-12-13T00:25:46",
                "updateTime": "2020-12-13T00:25:46",
                "heated": false,
                "deleted": true
              },
              "id": 7,
              "index": 7,
              "cid": 2,
              "pid": 3,
              "createTime": "2020-12-16T23:54:12",
              "updateTime": "2020-12-16T23:54:12",
              "deleted": false
            },
            {
              "product": {
                "id": 2,
                "code": "bbb",
                "name": "抹茶奶盖",
                "bigOriginalPrice": 12.00,
                "bigPromotePrice": 10.00,
                "mediumOriginalPrice": 10.00,
                "mediumPromotePrice": 8.00,
                "description": "",
                "status": true,
                "picture": "",
                "sales": 1,
                "createTime": "2018-02-02T00:00:00",
                "updateTime": "2018-02-02T00:00:00",
                "heated": false,
                "deleted": false
              },
              "id": 6,
              "index": 6,
              "cid": 2,
              "pid": 2,
              "createTime": "2020-12-16T23:54:12",
              "updateTime": "2020-12-16T23:54:12",
              "deleted": false
            },
            {
              "product": {
                "id": 1,
                "code": "aaa",
                "name": "珍珠奶茶",
                "bigOriginalPrice": 10.00,
                "bigPromotePrice": 9.00,
                "mediumOriginalPrice": 8.00,
                "mediumPromotePrice": 7.00,
                "description": "黑珍珠",
                "status": true,
                "picture": "",
                "sales": 1,
                "createTime": "2018-02-01T00:00:00",
                "updateTime": "2018-02-01T00:00:00",
                "heated": false,
                "deleted": false
              },
              "id": 5,
              "index": 5,
              "cid": 2,
              "pid": 1,
              "createTime": "2020-12-16T23:54:12",
              "updateTime": "2020-12-16T23:54:12",
              "deleted": false
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
              "id": 3,
              "index": 2,
              "cid": 2,
              "pid": 6,
              "createTime": "2020-12-16T00:33:04",
              "updateTime": "2020-12-17T21:03:01",
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
              "id": 2,
              "index": 3,
              "cid": 2,
              "pid": 4,
              "createTime": null,
              "updateTime": "2020-12-17T21:03:01",
              "deleted": false
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
              "id": 1,
              "index": 1,
              "cid": 2,
              "pid": 8,
              "createTime": null,
              "updateTime": "2020-12-17T21:03:01",
              "deleted": false
            }
          ],
          "id": 2,
          "index": 5,
          "name": "现磨咖啡",
          "icon": "",
          "createTime": null,
          "updateTime": "2020-12-16T22:59:49",
          "deleted": false
        }
      },
      "errmsg": "成功"
    }

### 二、饮品服务

#### 2.1 饮品详情

接口链接

    GET http://localhost:8082/wechat/product/detail
    
请求参数

    id：饮品id
    
响应内容

    {
      "errno": 0,
      "data": {
        "info": {
          "id": 1,
          "code": "aaa",
          "name": "珍珠奶茶",
          "bigOriginalPrice": 10.00,
          "bigPromotePrice": 9.00,
          "mediumOriginalPrice": 8.00,
          "mediumPromotePrice": 7.00,
          "description": "黑珍珠",
          "status": true,
          "picture": "",
          "sales": 1,
          "createTime": "2018-02-01T00:00:00",
          "updateTime": "2018-02-01T00:00:00",
          "heated": false,
          "deleted": false
        }
      },
      "errmsg": "成功"
    }

### 三、小料服务

#### 3.1 小料列表

接口链接

    GET http://localhost:8082/wechat/topping/list
    
请求参数

    无
    
响应内容

    [
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
        "id": 4,
        "code": "yyyyuw",
        "name": "绿豆",
        "price": 2.00,
        "description": "好吃",
        "status": 1,
        "picture": "",
        "createTime": "2020-08-18T10:25:44",
        "updateTime": "2020-12-18T10:26:55",
        "deleted": true
      },
      {
        "id": 5,
        "code": "iuuuou",
        "name": "仙草",
        "price": 2.00,
        "description": "黑色的，非常好吃",
        "status": 1,
        "picture": "",
        "createTime": "2020-12-18T10:40:32",
        "updateTime": "2020-12-18T10:43:21",
        "deleted": false
      }
    ]
    
### 四、轮播图服务

#### 4.1 轮播图列表

接口链接

    GET http://localhost:8082/wechat/slideshow/list
    
请求参数

    无
    
响应内容

    [
      {
        "id": 1,
        "index": 4,
        "picture": "",
        "createTime": null,
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
        "id": 3,
        "index": 3,
        "picture": "",
        "createTime": "2020-12-19T13:10:56",
        "updateTime": "2020-12-19T13:10:56",
        "deleted": true
      },
      {
        "id": 4,
        "index": 1,
        "picture": "",
        "createTime": "2020-12-19T13:11:13",
        "updateTime": "2020-12-19T13:17:22",
        "deleted": false
      },
      {
        "id": 5,
        "index": 6,
        "picture": "",
        "createTime": "2020-12-19T14:52:06",
        "updateTime": "2020-12-19T14:52:06",
        "deleted": true
      }
    ]
    
### 五、商家信息服务

#### 5.1 商家信息列表

接口链接

    GET http://localhost:8082/wechat/config/mall
    
请求参数

    无
    
响应内容

    {
      "errno": 0,
      "data": {
        "dgutshop_shop_latitude": "31.201900",
        "dgutshop_shop_longitude": "121.587839",
        "dgutshop_shop_qq": "545771383",
        "dgutshop_shop_address": "东莞",
        "dgutshop_shop_phone": "021-xxxx-xxxx",
        "dgutshop_shop_businesshours": "周一至周五，早上9点~晚上9点",
        "dgutshop_shop_name": "dgutshop"
      },
      "errmsg": "成功"
    }

### 六、订单服务

#### 6.1 订单列表

接口链接

    GET http://localhost:8082/wechat/order/list
    
请求参数

    userId
    
响应内容

    [
      {
        "orderItemList": [
          {
            "product": {
              "id": 5,
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
              "createTime": "2020-12-13T00:26:31",
              "updateTime": "2020-12-13T00:26:31",
              "heated": false,
              "deleted": false
            },
            "id": 4,
            "oid": 1,
            "pid": 5,
            "productName": "抹茶奶盖4",
            "size": 0,
            "productOriginalPrice": 10.00,
            "productPromotePrice": 8.00,
            "toppingId": [
              1,
              2
            ],
            "toppingName": [
              "珍珠",
              "仙草"
            ],
            "toppingPrice": 0.00,
            "productActualPrice": 8.00,
            "productPicture": "",
            "sum": 2,
            "note": "",
            "createTime": null,
            "updateTime": null,
            "deleted": false
          },
          {
            "product": {
              "id": 1,
              "code": "aaa",
              "name": "珍珠奶茶",
              "bigOriginalPrice": 10.00,
              "bigPromotePrice": 9.00,
              "mediumOriginalPrice": 8.00,
              "mediumPromotePrice": 7.00,
              "description": "黑珍珠",
              "status": true,
              "picture": "",
              "sales": 1,
              "createTime": "2018-02-01T00:00:00",
              "updateTime": "2018-02-01T00:00:00",
              "heated": false,
              "deleted": false
            },
            "id": 2,
            "oid": 1,
            "pid": 1,
            "productName": "抹茶奶盖2",
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
            "createTime": null,
            "updateTime": null,
            "deleted": false
          }
        ],
        "id": 1,
        "userId": 2,
        "code": "reqww",
        "type": 1,
        "orderStatus": 401,
        "aftersaleStatus": 0,
        "consignee": "小王",
        "consigneePhone": "12222222222",
        "consigneeAddress": "广东东莞",
        "consigneeRoom": "松山湖",
        "message": "不错",
        "payId": "31111",
        "payDate": "2020-12-16T18:57:53",
        "deliveryCode": "123333",
        "deliveryCompany": "自家配送",
        "deliverymanName": "林一",
        "deliverymanPhone": "16432234546",
        "deliveryTime": "2020-12-19T17:15:17",
        "productPrice": 200.00,
        "deliveryPrice": 10.00,
        "orderPrice": 210.00,
        "takeCode": "3111",
        "createTime": "2018-02-01T00:00:00",
        "updateTime": "2020-12-19T17:15:17",
        "refundAmount": null,
        "refundType": "",
        "refundContent": "",
        "refundTime": null,
        "confirmTime": "2020-12-17T18:59:22",
        "comments": null,
        "endTime": "2018-12-19T21:30:13",
        "deleted": false
      }
    ]

#### 6.2 提交订单

接口链接

    POST http://localhost:8082/wechat/order/submit
    
请求参数

    Content-Type: application/json
    
      {
        "userId": 1,
        "type": 0,
        "productPrice": 12.00,
        "deliveryPrice": 2.00,
        "orderPrice": 14.00,
        "consignee": "小卡",
        "consigneeAddress": "广东东莞松山湖",
        "consigneeRoom": "东莞理工学院大学路1号",
        "consigneePhone": "13333333333",
        "message": "加糖",
        "orderItemList": [
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
            "size": 0,
            "productOriginalPrice": 12.00,
            "productPromotePrice": 10.00,
            "toppingPrice": 1.00,
            "productActualPrice": 13.00,
            "sum": 1,
            "note": ""
          },
          {
            "product": {
              "id": 2,
              "code": "bbb",
              "name": "抹茶奶盖",
              "bigOriginalPrice": 12.00,
              "bigPromotePrice": 10.00,
              "mediumOriginalPrice": 10.00,
              "mediumPromotePrice": 8.00,
              "description": "",
              "status": true,
              "picture": "",
              "sales": 1,
              "createTime": "2018-02-02T00:00:00",
              "updateTime": "2018-02-02T00:00:00",
              "heated": false,
              "deleted": false
            },
            "size": 0,
            "productOriginalPrice": 10.00,
            "productPromotePrice": 8.00,
            "toppingPrice": 4.00,
            "productActualPrice": 12.00,
            "sum": 3,
            "note": ""
          }
        ]
      }
    
响应内容

    {
      "errno": 0,
      "data": {
        "orderId": 7
      },
      "errmsg": "成功"
    }