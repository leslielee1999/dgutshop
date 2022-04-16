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
            "picture": "https://dgutshop-1304570519.cos.ap-guangzhou.myqcloud.com/3i17lsvrec4wknd184mm.jpg",
            "pictureDetail": "https://dgutshop-1304570519.cos.ap-guangzhou.myqcloud.com/3i17lsvrec4wknd184mm.jpg",
            "sales": 2,
            "createTime": "2018-02-01T00:00:00",
            "updateTime": "2022-03-02T23:08:36",
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
        "dgutshop_shop_qq": "878994561",
        "dgutshop_shop_address": "东莞松山湖大学路1号",
        "dgutshop_shop_isclosed": "1",
        "dgutshop_shop_phone": "021-1234-2222",
        "dgutshop_shop_businesshours": "周一至周日，早上9点~晚上9点",
        "dgutshop_shop_name": "高仿喜茶",
        "dgutshop_shop_closedreason": "因疫情防控需要，本店暂不营业，如有给您带来不便，敬请谅解！"
      },
      "errmsg": "成功"
    }

### 六、订单服务

#### 6.1 订单列表

接口链接

    GET http://localhost:8082/wechat/order/list
    
请求参数

    无
    
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
        "type": 0,
        "productPrice": 12.00,
        "deliveryPrice": 2.00,
        "orderPrice": 14.00,
        "consignee": "小卡",
        "consigneeAddress": "广东东莞松山湖",
        "consigneeRoom": "东莞理工学院大学路1号",
        "consigneePhone": "13333333333",
        "message": "加糖",
        "shopName": "店铺1",
        "shopAddress": "深圳",
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
    

#### 6.3 支付订单

接口链接

    http://localhost:8082/wechat/order/h5pay
    
请求参数
        
    json：
        {
            "orderId": 38
        }
    
响应内容

    {
        "errno": 0,
        "errmsg": "成功"
    }

#### 6.4 取消订单

接口链接

    http://localhost:8082/wechat/order/cancel
    
请求参数
        
    json：
        {
        "orderId":11
        }
    
响应内容

    {
        "errno": 0,
        "errmsg": "成功"
    }
    
注
    
         1. 在订单状态为待支付状态下支持用户点击取消订单
                101【待支付】-->102【下单后未支付用户取消】
         2. 在订单状态为待支付状态下超时，系统自动取消
                101【待支付】-->103【下单后未支付超时，系统自动取消】
                
#### 6.5 确认订单

接口链接

    http://localhost:8082/wechat/order/confirm
    
请求参数
        
    json：
        {
        "orderId":11
        }
    
响应内容

    {
        "errno": 0,
        "errmsg": "成功"
    }
    
注
    
     1. 完成用户点击确认收货的操作
               301【待取货】-->501【已完成】
               402【骑手已到达】-->501【已完成】
     2. 另外，待取货或骑手到达后的时间阈值一旦超过则自动确认收货：
               301【待取货】-->502【已完成】
               402【骑手已到达】-->502【已完成】
               
#### 6.6 删除订单

接口链接

    http://localhost:8082/wechat/order/delete
    
请求参数
        
    json：
        {
        "orderId":11
        }
    
响应内容

    {
        "errno": 0,
        "errmsg": "成功"
    }
    
注
    
     1. 如果订单已经取消【102/103】或是已完成【501/502】，则可删除
               
### 七、用户地址

注

    1. isDeafult
        0：默认地址
        1：非默认地址
        
    2. 增加地址与修改地址api相同
        不同之处：
            增加地址时封装在json中的地址信息中的地址id设为null或0（表示最新添加的）；
            修改地址时封装在json中的地址信息具有原来的地址id       

#### 7.1 地址列表

接口链接

    GET http://localhost:8082/wechat/address/list
    
请求参数

    参数：
        userId
    
响应内容

    {
        "errno": 0,
        "data": {
            "total": 2,
            "pages": 1,
            "limit": 2,
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
                    "latitude": null,
                    "longitude": null,
                    "isDefault": 0,
                    "createTime": "2020-12-13T09:51:50",
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
                    "latitude": null,
                    "longitude": null,
                    "isDefault": 1,
                    "createTime": "2020-11-13T09:51:50",
                    "updateTime": null,
                    "deleted": false
                }
            ]
        },
        "errmsg": "成功"
    }
    
#### 7.2 增加地址
        
接口链接

    POST http://localhost:8082/wechat/address/save
    
请求参数

    参数：
        userId
    
    json：
        {
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "钢铁加鲁鲁",
            "userPhone": "12322212312",
            "userAddress": "广东",
            "userRoom": "东莞",
            "latitude": null,
            "longitude": null,
            "isDefault": 1,
            "createTime": null,
            "updateTime": null,
            "deleted": false
        }  
        
响应内容

    {
        "errno": 0,
        "data": 6,
        "errmsg": "成功"
    }
    
#### 7.3 修改地址
        
接口链接

    POST http://localhost:8082/wechat/address/save
    
请求参数

    参数：
        userId
    
    json：
        {
            "id": 5,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "亚古兽",
            "userPhone": "13333333333",
            "userAddress": "广东东莞松山湖",
            "userRoom": "东莞理工学院大学路1号",
            "latitude": null,
            "longitude": null,
            "isDefault": 0,
            "createTime": "2020-12-13T09:51:50",
            "updateTime": null,
            "deleted": false
        }
        
响应内容

    {
        "errno": 0,
        "data": 5,
        "errmsg": "成功"
    }
    
#### 7.4 删除地址
        
接口链接

    POST http://localhost:8082/wechat/address/delete
    
请求参数

    参数：
        userId
    
    json：
        {
            "id": 5,
            "userId": 1,
            "wechatId": "kk545771383",
            "userName": "钢铁加鲁鲁",
            "userPhone": "12322212312",
            "userAddress": "广东",
            "userRoom": "东莞",
            "latitude": null,
            "longitude": null,
            "isDefault": 1,
            "createTime": null,
            "updateTime": null,
            "deleted": false
        }  
        
响应内容

    {
        "errno": 0,
        "errmsg": "成功"
    }
    
### 八、微信小程序用户登录

接口链接

    POST http://localhost:8082/login/wechat
    
请求参数
    
    json：
        {
            "code": "0733h30w3gdFAV2L1g1w3oJsC113h30B",
            "encryptedData": "shA5PgImvUfRWY8bt9CsXFf6Xy5cGc3Dl2y8pMZ+5DDFZM/58wrnAKFdO8VRNxg97T/zpWq3Fc8365siVIkk2NcoMChBCebufVaQuZ7YaaAv2KnsXzpbyKHIhaNxTeGdPyRhGUZWkE2GFQUNFLp4b7pd4QJy2hFICCkWjDCbCRF5wi0F2kn3LOyKlmv6SOAdtz3Fwkb2kDXOk0S2KPMjZ+rWHhS42sWTlxRWcrKuxRWnQhQSMgDxrpE/BZs5jMg9iRZEJC60Tbuncey2ZEfgrmQwTJqKXgy4aG2wlwZy+7sgdx2KjbZIK/w9PMcaYGKHGQhFMdvwKVyNb4m5waq/p2X+WINo1sSmpHu7sjZumMQNRNBHGFO9hHptQOowPxKB2JgDifsjTgL6Vcw6G4MQPZRShnX088PQhhLeIJpfkzlPloxZOVejqKldLFG7Iald7mZMS5896mS/UElpgt8aDA==",
            "iv": "M+EDO/v6EztGcdEeNi7rFg=="
        }
        
响应内容

    {
        "success": true,
        "errCode": "ok",
        "errMsg": null,
        "module": {
            "openId": "oD78r4xOwDGT90U8bltXNBqLh_tM",
            "token": "Leesk-eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNosjEELgjAYQP_Lzg42-7Zpx1K6aIIpdJOZn7gSLDdRiP57C7q-93hvYpeW7MmUqGiGrViTUxWzOmpHdz0fXtnQuJwERC9umGbjDFofl0WWNsf6UhV5Wnp7d8ZT1gvFJJdUCckpqLCncYeaql4xjdiFXQs-NtqRPZcsFiHnAjywv-eIaB_NirfB-4Dg9vxXADsBny8AAAD__w.YOuIpEdEGKHwRSK4X4im2QoA7ktP5KRyqj7R3Z_RKaKKwMheUQg36tnhyyijWl88v53bgSeeTKP_5n04UZfhPw"
        }
    }
    
    注：其它功能需要userId参数的都已改为用token获取
    另：模拟支付完成
    
### 2021.1.9补充

获取订单配置

接口链接

    GET http://59.110.50.181:8080/wechat/config/order
    
请求参数
    
    无
    
响应内容

    {
        "errno": 0,
        "data": {
            "dgutshop_order_delivery": "6",
            "dgutshop_order_unpaid": "30",
            "dgutshop_order_unconfirmed": "7"
        },
        "errmsg": "成功"
    }