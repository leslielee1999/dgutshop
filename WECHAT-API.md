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
