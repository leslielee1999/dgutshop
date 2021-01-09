# 基于微信小程序的新零售移动电商系统设计与实现

`学校`：东莞理工学院

`学院`：网络空间安全学院

`课程名称`：企业级开发框架专题

`指导老师`：黎志雄

`小组组名`：请你喝奶茶店

`班级`：18级网络工程4班

`组长`：李树康（201841413418）`组员`：李良堃（201841413416）、林振锟（201841413422）



## 介绍

一个奶茶销售系统：dgutshop。

**技术栈：Spring Boot后端 + MyBatis持久层+Vue管理员前端 + uniapp微信小程序用户前端**



## 核心技术

### 登录校验与鉴权

小程序端与管理后台端统一采用**Spring Security+Jwt**进行登录校验。

> 管理后台端因有不同角色故有进行鉴权，目前有**超级管理员和普通员工**两种。
>
> 小程序端用户可能会被禁用和禁止交易故数据库保存了一个status字段供小程序端匹配。

* spring security的核心配置代码

  其中配置了登录成功、登录失败以及登出等多个自定义处理器，当然也实现了登录逻辑验证和权限验证等校验处理器。

  

![security config](https://images.gitee.com/uploads/images/2021/0110/033753_5f221aaa_7689859.png "security config.png")

  

* jwt配置于.yml文件

  

![jwt](https://images.gitee.com/uploads/images/2021/0110/033816_696102d5_7689859.png "jwt.png")



### **OSS对象存储**

所有图片信息都放在七牛云的对象存储当中

同时本项目可扩展为本地存储模式还有其它对象存储模式，例如阿里云、腾讯云等服务商，如下图，想采用哪一种方式直接<u>active</u>就好了



![OSS对象存储](https://images.gitee.com/uploads/images/2021/0110/034358_00a5ec42_7689859.jpeg "OSS对象存储.jpg")



### **自定义校验注解**

用于校验HTTP参数，实现@`Sort`和`@Order`注解对**排序字段**及**排序类型**进行限制

其中排序字段有<u>创建时间和id</u>

排序类型就是基本的<u>desc降序和asc升序</u>



![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034505_21c2c6b8_7689859.png "自定义注解Sort.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034516_267226f0_7689859.png "自定义注解Order.png")



### **后台管理系统的页面基本实现导出功能**

这里有两种实现方式，一种为后端代码实现，另一种为前端js实现。

本项目采用**前端js**实现此功能，管理员点击按钮可**根据搜索结果导出相应的数据**或者**在不搜索的情况下导出该功能的全部数据。**



![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034529_23882e12_7689859.jpeg "导出展示.jpg")



### 定时任务

设置定时任务，用户下单后超过30mins内用户没有进行支付操作或取消订单操作，系统自动将订单设为取消状态；

* 提交订单时添加任务

  

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034542_ab862aba_7689859.jpeg "订单提交后超期自动取消.jpg")

  

* 完成订单支付模拟后移除任务

  

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034553_9e780761_7689859.png "完成订单支付后移除任务.png")







## 亮点功能

### 后台管理人员定制小程序侧边栏功能

这里是设计给管理人员方便地对小程序的侧边栏进行定制服务，例如对小程序的分类或分类单项进行整体排序，更改其在小程序的位置，同时也可以对某个分类进行饮品添加，当然也会添加对应的分类单项，示例图如下：



![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034604_c3750e19_7689859.png "小程序侧边栏定制.png")



### 超级管理员可禁用普通管理员功能

超级管理员可禁用普通管理员的功能其实和管理员对用户状态进行管理的功能（本项目实现了此功能，即管理员可修改某个用户的状态为正常、不可交易或不可登录）类似，突出了超级管理员的“超级”，实现店家对该店进行更好的人员管理。



![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034616_2a61535d_7689859.png "超级管理员禁用.png")





其它基本功能请详见以下介绍与演示过程。



## 基本功能

### 奶茶店微信小程序功能

* 首页
* 分类列表、分类详情
* 商品列表、商品详情
* 下单、完成订单后删除
* 订单列表、订单详情
* 地址列表、地址详情、地址增删改

.......

### 管理平台功能

* 用户管理
* 商家管理
* 饮品管理
* 小料管理
* 微信小程序定制管理
* 订单管理
* 统计报表
* 个人信息管理

.......

## 项目实例

### 小程序实例

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034634_f32c6a9b_7689859.png "小程序首页.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034642_6faa6671_7689859.png "小程序饮品页.png")

### 管理后台实例



![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034652_4341bf26_7689859.jpeg "后台首页.jpg")



## 项目部署

本项目采用的是基于阿里云的单机单服务部署的docker部署方案。

在项目的docker目录中存放着编排好的docker-compose文件供开发者部署使用，这里想详见可跳转到快速启动目录查看具体启动方案。



## 快速启动

> 本项目的后端应用程序已打包为docker镜像，并使用docker-compose编排容器部署

1. 配置最小开发环境（下面仅介绍在云服务器上启动方法）：

   * [阿里云服务器](https://www.aliyun.com/)
   * [JDK1.8或以上](http://www.oracle.com/technetwork/java/javase/overview/index.html)
   * [MySQL](https://dev.mysql.com/downloads/mysql/)（docker镜像拉取）
   * [Maven](https://maven.apache.org/download.cgi)（开发机需）
   * [Nodejs](https://nodejs.org/en/download/)（开发机需）
   * [微信开发者工具](https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html)（开发机需）

2. 数据库更新本项目的最新备份文件：

   位置：db文件夹

3. 启动管理后台的后端服务

   1. 远程连接阿里云服务器，安装docker、docker-compose、jdk

   2. 在开发机上打开命令行，输入以下命令：

      ```
      cd dgutshop/dgutshop-all
      mvn install
      mvn clean package
      ```

   3. 通过idea连接云服务器，直接将项目deploy到服务器主机的一个新建文件夹（project）上

   4. 在project下编排docker-compose

      docker-compose.yml：

      ```
      version: "3.5"
      services:
        mysql:
          image: mysql:latest
          ports:
            - 3306:3306
          environment:
            MYSQL_DATABASE: dgutshop
            MYSQL_ROOT_PASSWORD: root
            MYSQL_USER: dev
            MYSQL_PASSWORD: password
          restart: always
          networks:
            default:
              ipv4_address: 10.100.0.2
        spring:
          image: adoptopenjdk/openjdk8:jdk8u275-b01-alpine-slim
          volumes:
            - ./dgutshop-all/target/dgutshop-all-0.0.1-SNAPSHOT-exec.jar:/app.jar
            - ./dgutshop-all/src/main/resources/application.yml:/application.yml
            - ./tomcat.keystore:/tomcat.keystore
          ports:
            - 8080:8080
            - 8082:8082
          environment:
            - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/dgutshop?useTimezone=true&serverTimezone=GMT
          entrypoint: ["java","-jar","/app.jar"]
          depends_on:
            - mysql
          networks:
            default:
              ipv4_address: 10.100.0.3
      networks:
        default:
          driver: bridge
          ipam:
            config:
              - subnet: "10.100.0.0/24"
      ```

   5. 运行docker-compose.yml

      ```
      docker-compose up
      ```

4. 启动管理后台前端

   开发机打开命令行，输入以下命令：

   ```
   cd dgutshop/dgutshop-admin
   npm install
   npm run serve
   ```

   打开浏览器，输入网址：`http://localhost:1234/`，进入管理后台登录页面

5. 启动微信小程序

   1. 微信开发工具导入dgutshop-wechat项目；
   2. 项目配置，启用“不校验合法域名、web-view（业务域名）、TLS 版本以及 HTTPS 证书”（ssl暂无，故先作调试使用）
   3. 点击“编译”，即可在微信开发工具预览效果；



## 项目代码

* [gitee](https://gitee.com/lishukang/dgutshop.git)



## 项目架构



![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034726_b19f4cff_7689859.jpeg "项目架构.jpg")



## 系统架构

本项目采用Maven构建多模块项目，系统及模块如下：



![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034737_de8447cd_7689859.png "系统及模块.png")



注意dgutshop-all模块仅用于包裹其它模块，将两个spring boot模块融合到一个单独的Spring Boot可执行jar包中。

* 多模块配置依赖的实现方案：

  采用一种基于`spring.profiles.active`的方式，例如我的dgutshop-core模块存在application.yml和application-core.yml两个配置文件，在application-core.yml配置文件中存放着core模块的相关配置信息，然后application.yml中存放的配置如下：

  ```
  spring:
    profiles:
      active: core, db
    messages:
      encoding: UTF-8
  ```

  如果单独启动dgutshop-core模块，系统会先读取core模块的application.yml配置文件，根据`spring.profiles.active`读取dgutshop-db模块下存在的application-db.yml和dgutshop-core模块application-core.yml配置文件，这样的话就会自动读取dgutshop-db模块的配置文件了。


## 开发计划

管理后台已基本实现所有表的CRUD操作，小程序端也实现基本商家的需求；

但由于时间、精力有限，功能需求不够完善、代码细节优化不足，未来开发的大致方向如下：

1. 域名备案、申请ssl，完成真机调试演示；
2. 实现微信支付功能、用户评论功能、用户与商家联系、手机端下单实现后台端即时反馈（websocket有尝试，调试未完成）；
3. 管理后台实现所有统计功能、日志功能；
4. 缓存功能及性能优化；
5. 业务代码及细节代码优化；



## API

> 本目录用于记录前后端交互的详细过程，开发者也可用于开发时进行一定的参考对比。
>
> 篇幅较多，请谨慎查看或者点击对应子目录进行查看。

### Admin-Api

#### 饮品管理

##### 查询功能

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

##### 增加功能

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

##### 删除功能

* 给个id字段即可：`POST http://localhost:8083/admin/products/delete?id=`

##### 修改功能

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
#### 管理员管理

##### 查询功能

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
##### 增加功能

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
##### 删除功能

* 给个id字段即可：`POST http://localhost:8083/admin/admin/delete?id=`

##### 修改功能

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
#### 角色管理

##### 查询功能

1.  列举所有角色：<br/>
`GET http://localhost:8083/admin/role/list`
2.  根据角色名查询某个角色（模糊查询）<br/>
`GET http://localhost:8083/admin/role/list?name=`
* 关于分页
    * 指定页数（默认为第1页）：<br/>`GET http://localhost:8083/admin/role/list?page=`
    * 指定每页获得多少种饮品（默认为10种）：<br/>`GET http://localhost:8083/admin/role/list?limit=`
    * 排序方式默认为降序，也可以指定为升序：<br/>`GET http://localhost:8083/admin/role/list?sort=`
    * 排序字段默认为id，也可以指定为按日期：<br/>`GET http://localhost:8083/admin/role/list?order=`
##### 增加功能

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
##### 删除功能

* 给个id字段即可：`POST http://localhost:8083/admin/role/delete?id=`

##### 修改功能

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
#### 小程序的菜单分类管理

##### 查询功能

1.  列举所有分类：<br/>
`GET http://localhost:8083/admin/category/list`
    
    * 获取到的数据
    > 根据`categoryItems`可以获取分类单项，另外分类单项也有个index字段可用来排序
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
##### 增加功能

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
##### 删除功能

* 给个id字段即可：`POST http://localhost:8083/admin/category/delete?id=`

##### 修改功能

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

##### 分类单项的增加功能

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
##### 分类单项的删除功能

* 给个id字段即可：`POST http://localhost:8083/admin/category/categoryitem/delete?id=`

##### 分类单项更改顺序功能

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

#### 订单管理

* 后台管理人员只拥有查询功能
##### 查询订单

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

#### 用户管理

* 后台管理人员拥有查询功能、查看用户详情功能和设置用户状态权限功能
##### 查询用户

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
##### 获取某个用户的详情信息

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
##### 更改某个用户的状态

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

#### 小料管理

##### 查询功能

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

##### 增加功能

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
##### 删除功能

* 给个id字段即可：`POST http://localhost:8083/admin/toppings/delete?id=4`
* 输出
    ```
  {
    "errno": 0,
    "errmsg": "成功"
  }
  ```

##### 修改功能

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
  
#### 用户地址管理

* 后台管理人员只拥有查询功能
##### 查询功能

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
    
#### 小程序的轮播图管理

##### 查询功能

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
    
##### 增加功能

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

##### 删除功能

* 给个id字段即可：
    `POST http://localhost:8083/admin/slideshow/delete?id=5`
    
##### 修改功能（一次传递多个以更改顺序）

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
  
#### 后台管理员的发货功能

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

#### 商家信息管理

##### 查看商家信息

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

##### 修改商家信息

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

#### 登录登出

##### 登录验证

接口链接

    POST http://localhost:8083/login/userLogin

请求参数
    
    username：管理员的昵称
    password：管理员的密码

响应内容

    {"msg":"登录成功","code":"200","token":"Leesk-eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI2Iiwic3ViIjoibGVlc2siLCJpYXQiOjE2MDg3MDYwOTQsImlzcyI6ImxlZXNrIiwiYXV0aG9yaXRpZXMiOiJbe1wiYXV0aG9yaXR5XCI6XCJST0xFX0FETUlOXCJ9XSIsImV4cCI6MTYwODc5MjQ5NH0.YlTVtXvyLkuelJteZ1V35SccccDHhZZENvfKbH6Vc4av6cv-0TsKGlkMtbpBhEuXtYp1FWciVLBCMJ3VxZefPQ"}

##### 退出登录（前端清除token）

接口链接

    POST http://localhost:8083/login/userLogout

请求参数
    
    无

响应内容

    {
        "code": 401,
        "message": "未登录"
    }

#### 图片功能

##### 上传文件

接口链接

    POST http://localhost:8083/admin/storage/create

请求参数
    
    file：选择的文件

响应内容

    {
        "errno": 0,
        "data": {
            "id": 3,
            "key": "fv2d93sde21dq9enxnbb.jpg",
            "name": "1.jpg",
            "type": "image/jpeg",
            "size": 827705,
            "url": "qlqwnyjmi.hn-bkt.clouddn.com/fv2d93sde21dq9enxnbb.jpg",
            "createTime": "2020-12-23T14:53:48.733",
            "updateTime": "2020-12-23T14:53:48.733",
            "deleted": null
        },
        "errmsg": "成功"
    }

#### 个人信息管理

##### 查看个人信息

接口链接

    http://localhost:8083/admin/profile/getinfo

请求参数
    
    无

响应内容

    {
        "errno": 0,
        "data": {
            "id": 6,
            "roleId": 1,
            "name": "",
            "nickname": "leesk",
            "password": null,
            "code": "dgut-7",
            "picture": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
            "createTime": "2020-12-22T01:35:14",
            "updateTime": "2020-12-23T22:25:29",
            "lastLoginIp": "",
            "lastLoginTime": null,
            "status": null,
            "deleted": null
        },
        "errmsg": "成功"
    }

##### 修改个人信息

接口链接

    http://localhost:8083/admin/admin/update

请求参数（没有id，没有password）
    
    {
        "id":,
        "roleId": 2,
        "name": "leeeeeeeesk",
        "nickname": "lesile",
        "password": "",
        "code": "dgut-11",
        "picture": "http://asdasdasdasdas.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
        "createTime": "2020-12-12T23:11:10",
        "updateTime": "2020-12-12T22:17:52",
        "lastLoginIp": "",
        "lastLoginTime": null,
        "status": "NORMAL",
        "deleted": null
    }


​    
响应内容

    {
        "errno": 0,
        "data": {
            "id": 6,
            "roleId": 2,
            "name": "leeeeeeeesk",
            "nickname": "lesile",
            "password": null,
            "code": "dgut-11",
            "picture": "http://asdasdasdasdas.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
            "createTime": "2020-12-12T23:11:10",
            "updateTime": "2020-12-23T23:11:10.053",
            "lastLoginIp": "",
            "lastLoginTime": null,
            "status": "NORMAL",
            "deleted": null
        },
        "errmsg": "成功"
    }

##### 修改个人密码

接口链接

    http://localhost:8083/admin/profile/password

请求参数
    
    {
        "oldPassword": "wwqwwwqw",
        "newPassword": "pppppppppp"
    }

响应内容

    {
        "errno": 0,
        "errmsg": "成功"
    }
    
    {
        "errno": 605,
        "errmsg": "账号密码不对"
    }

#### 接入腾讯防水墙

接口链接

    GET http://localhost:8083/login/verify

请求参数
    
    ticket：t03pLjGAnrKqw_URetrG1If6VAiz96Fn4gHizVw4ccL9t033g6iL9AJXLMXJaC5wboTch7AftSuE6OGU6HQeXzX2nvAjqjo7n9eFIDSz5Z7wow*
    randstr：@ouR

响应内容
    
    正常：
    
    {
        "evilLevel": 0,
        "code": 1
    }
    
    错误：
    -1

#### 获取echart数据

##### 获取一周内订单各个状态的数量

接口链接

    GET http://localhost:8083/admin/stat/list

请求参数
    
    无

响应内容

    注：日期从上往下依次是周一~周日
        状态从上往下依次是unpaid、producing、untaken、delivering、finished
    
       {
           "errno": 0,
           "data": [
               [
                   0,
                   3,
                   0,
                   0,
                   0
               ],
               [
                   1,
                   0,
                   0,
                   0,
                   0
               ],
               [
                   2,
                   1,
                   0,
                   0,
                   1
               ],
               [
                   0,
                   0,
                   0,
                   0,
                   0
               ],
               [
                   0,
                   0,
                   0,
                   0,
                   1
               ],
               [
                   6,
                   0,
                   4,
                   0,
                   0
               ],
               [
                   3,
                   0,
                   0,
                   0,
                   3
               ]
           ],
           "errmsg": "成功"
       }

##### 获取各种表的数量

接口链接

    GET http://localhost:8083/admin/stat/getNum

请求参数
    
    无

响应内容

     注：从上往下依次是用户数量、产品数量、小料数量、订单数量
    {
        "errno": 0,
        "data": [
            3,
            20,
            5,
            36
        ],
        "errmsg": "成功"
    }

#### 2021/1/4 补充

1. 订单所有的状态如下：
   
         * 订单状态
         * 【101待支付、201制作中、301待取货、401派送中、501已完成】
         *
         * 订单状态
         * 自取与外卖服务共有：
         * 101【待支付】：订单生成，未支付：102，下单后未支付用户取消；103，下单后未支付超时系统自动取消
         * 201【制作中】：支付完成，生成取件码，奶茶正在制作中
         * 501【已完成】：用户点击已确认收货
         *
         * 自取：
         * 301【待取货】：奶茶已制作完毕
         *
         * 外卖：
         * 401【派送中】：派送员正在派送；
         * 402：骑手已到达
         * 502：骑手送达或待取货的状态超过一定时间自动确认
    
2. 修好订单页面无页数的Bug

3. 增加侧边栏图标的修改功能

接口链接

    POST http://localhost:8083/admin/category/updateOne

请求参数
    
    json
    {
        "id": 1,
        "index": 6,
        "name": "热销",
        "icon": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
        "createTime": null,
        "updateTime": null,
        "deleted": false
    }

响应内容

    {
        "errno": 0,
        "data": {
            "categoryItems": null,
            "id": 1,
            "index": 6,
            "name": "热销",
            "icon": "http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png",
            "createTime": null,
            "updateTime": "2021-01-04T09:02:04.544",
            "deleted": false
        },
        "errmsg": "成功"
    }

### Wechat-Api

#### 类目服务

##### 侧边栏列表

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
                    "categoryItems": [],
                    "id": 3,
                    "index": 3,
                    "name": "纯茶",
                    "icon": "http://qlqwnyjmi.hn-bkt.clouddn.com/prxoijcdcl8ka19bqmde.png",
                    "createTime": "2020-12-15 20:01:13",
                    "updateTime": "2021-01-06 00:06:30",
                    "deleted": false
            },
            {
                    "categoryItems": [
                        {
                            "product": {
                                "id": 9,
                                "code": "ccc",
                                "name": "芋泥波波牛乳",
                                "bigOriginalPrice": 30.00,
                                "bigPromotePrice": null,
                                "mediumOriginalPrice": 27.00,
                                "mediumPromotePrice": null,
                                "description": "冷热皆宜，甄选当季“春见”柑橘，手剥先制，肉质饱满多汁，融合鲜爽绿研茶汤，搭配嫩滑桂花冻和Q弹脆波波，酸甜又沁口。",
                                "status": true,
                                "picture": "http://qlqwnyjmi.hn-bkt.clouddn.com/389ktgcraxau9hdsb35h.jpg",
                                "sales": 1,
                                "createTime": "2020-12-13 00:39:56",
                                "updateTime": "2021-01-04 13:27:20",
                                "heated": false,
                                "deleted": false
                            },
                            "id": 45,
                            "index": 3,
                            "cid": 4,
                            "pid": 9,
                            "createTime": "2021-01-04 14:15:34",
                            "updateTime": "2021-01-04 14:15:34",
                            "deleted": false
                        },
                        {
                            "product": {
                                "id": 7,
                                "code": "ccc",
                                "name": "烤黑糖啵啵牛乳",
                                "bigOriginalPrice": 25.00,
                                "bigPromotePrice": null,
                                "mediumOriginalPrice": 21.00,
                                "mediumPromotePrice": null,
                                "description": "冷热皆宜，甄选当季“春见”柑橘，手剥先制，肉质饱满多汁，融合鲜爽绿研茶汤，搭配嫩滑桂花冻和Q弹脆波波，酸甜又沁口。",
                                "status": true,
                                "picture": "http://qlqwnyjmi.hn-bkt.clouddn.com/c6hhgx38nkbmmm6529wa.jpg",
                                "sales": 1,
                                "createTime": "2020-12-13 00:35:00",
                                "updateTime": "2021-01-04 13:29:39",
                                "heated": false,
                                "deleted": false
                            },
                            "id": 44,
                            "index": 2,
                            "cid": 4,
                            "pid": 7,
                            "createTime": "2021-01-04 14:15:34",
                            "updateTime": "2021-01-04 14:15:34",
                            "deleted": false
                        },
                        {
                            "product": {
                                "id": 13,
                                "code": "ddd",
                                "name": "考黑糖波波牛乳",
                                "bigOriginalPrice": 25.00,
                                "bigPromotePrice": null,
                                "mediumOriginalPrice": 21.00,
                                "mediumPromotePrice": null,
                                "description": "冷热皆宜，甄选当季“春见”柑橘，手剥先制，肉质饱满多汁，融合鲜爽绿研茶汤，搭配嫩滑桂花冻和Q弹脆波波，酸甜又沁口。",
                                "status": true,
                                "picture": "http://qlqwnyjmi.hn-bkt.clouddn.com/ig93tqfqhwxtg6ine4ef.jpg",
                                "sales": 1,
                                "createTime": "2020-12-13 00:48:19",
                                "updateTime": "2021-01-04 13:24:10",
                                "heated": false,
                                "deleted": false
                            },
                            "id": 43,
                            "index": 1,
                            "cid": 4,
                            "pid": 13,
                            "createTime": "2021-01-04 14:15:22",
                            "updateTime": "2021-01-04 14:15:22",
                            "deleted": false
                        },
                        {
                            "product": {
                                "id": 19,
                                "code": "mmm",
                                "name": "厚牛乳芋泥波波",
                                "bigOriginalPrice": 35.00,
                                "bigPromotePrice": null,
                                "mediumOriginalPrice": 28.00,
                                "mediumPromotePrice": null,
                                "description": "冷热皆宜，甄选当季“春见”柑橘，手剥先制，肉质饱满多汁，融合鲜爽绿研茶汤，搭配嫩滑桂花冻和Q弹脆波波，酸甜又沁口。",
                                "status": true,
                                "picture": "http://qlqwnyjmi.hn-bkt.clouddn.com/o20g3lfu2d5z5pm9t19t.jpg",
                                "sales": 1,
                                "createTime": "2020-12-13 10:30:49",
                                "updateTime": "2021-01-04 13:18:54",
                                "heated": false,
                                "deleted": false
                            },
                            "id": 42,
                            "index": 0,
                            "cid": 4,
                            "pid": 19,
                            "createTime": "2021-01-04 14:15:22",
                            "updateTime": "2021-01-04 14:15:22",
                            "deleted": false
                        }
                    ],
                    "id": 4,
                    "index": 6,
                    "name": "牛乳茶",
                    "icon": "http://qlqwnyjmi.hn-bkt.clouddn.com/5pg9ca3oqc8km34snt28.png",
                    "createTime": "2020-12-15 20:19:46",
                    "updateTime": "2021-01-06 00:06:30",
                    "deleted": false
            },
            ....
        ]
      },
      "errmsg": "成功"
    }

##### 定位到特定侧边栏


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
    		....
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

#### 饮品服务

##### 饮品详情

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

#### 小料服务

##### 小料列表

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

#### 轮播图服务

##### 轮播图列表

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

#### 商家信息服务

##### 商家信息列表

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

#### 订单服务

##### 订单列表

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

##### 提交订单

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

##### 支付订单

接口链接

    http://localhost:8082/wechat/order/pay-notify

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

##### 取消订单

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

##### 确认订单

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

##### 删除订单

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

#### 用户地址

注

    1. isDeafult
        0：默认地址
        1：非默认地址
        
    2. 增加地址与修改地址api相同
        不同之处：
            增加地址时封装在json中的地址信息中的地址id设为null或0（表示最新添加的）；
            修改地址时封装在json中的地址信息具有原来的地址id       

##### 地址列表

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

##### 增加地址

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

##### 修改地址

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

##### 删除地址

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

#### 微信小程序用户登录

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


## 数据库

### 数据库表结构

#### 饮品、小程序定制相关

灰色框中是与小程序定制相关的表，有轮播图、侧边栏及侧边栏单项

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034805_5d704bf9_7689859.jpeg "数据库饮品、小程序定制相关.jpg")

#### 订单相关

topping：小料表

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034817_5c82bd88_7689859.png "数据库订单相关.png")

#### 用户相关

delivery：外卖服务的配送员表

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034835_0b7155f5_7689859.png "数据库用户相关.png")

#### 管理员相关

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034842_76536170_7689859.jpeg "数据库管理员相关.jpg")

#### 其他

storage：对象存储服务表

system：商家信息配置表

![输入图片说明](https://images.gitee.com/uploads/images/2021/0110/034857_941f6059_7689859.jpeg "数据库其它相关.jpg")

### 数据库表设计

#### 通用设计

##### deleted

基本上所有表都存在`deleted`字段，支持逻辑删除。 因此目前删除数据时，不会直接删除数据，而是修改`deleted`字段。 当然，数据库管理员可以连接到数据库直接删除数据。

##### create_time

基本上所有表都存在`create_time`字段，记录数据创建时间。

##### update_time

基本上所有表都存在`update_time`字段，记录数据修改时间。我们可以利用update_time来实现乐观锁更新机制。

#### 饮品设计

每种饮品都设置了大杯、中杯的原价与优惠价，但没有设置为非null，管理员可根据实际需求进行增删改。

每种饮品都有一个status字段保存当前状态表示是否为上架状态，也有一个heated字段保存当前饮品是否可制作为热饮：0为否，1为是。

#### 小程序定制设计

轮播图、侧边栏和侧边栏单项是小程序端的基本配置，管理人员可以在后台对小程序端的页面进行对应的增删查改操作。

同时轮播图、侧边栏和侧边栏单项都有一个index字段保存序号用于排序，管理员可在后台管理系统对它们进行排序进行侧边栏的排序定制。

#### 订单设计

订单信息囊括了基本信息、饮品信息、地址信息、费用信息、配送信息、支付信息和其他信息。

* 基本信息

  订单创建时的一些基本信息，例如用户、订单状态和订单留言等。 其中订单状态是最重要的信息。

* 饮品信息

  每个订单可以有多个订单项，每个订单项对应每一款饮品，所以订单的饮品信息由独立的订单项表保存。

* 地址信息

  订单中的收货地址由用户下单时从单独的地址表中对应的地址列表信息选择得到。

* 费用信息

  订单的费用情况，例如订单项的价格和，优惠价和等。

* 配送信息

  订单中的配送信息由管理员对可发货订单进行发货处理时从单独的配送表中的列表信息选择得到。

* 支付信息

  未接上微信支付，暂作保留字段。

* 其他信息

##### 订单状态

* 基本状态：

  101【待支付】：订单生成，未支付；

  201【制作中】：支付完成，生成取件码，奶茶正在制作中；

  301【待取货】：奶茶已制作完毕，等待用户取货；

  401【派送中】：派送员正在派送；

  501【已完成】：用户点击已确认收货；

* 其它情况：

  102【已取消】：下单后未支付用户取消；

  103【已取消】：下单后未支付超时系统自动取消

  502【已完成】：骑手送达或待取货的状态超过一定时间自动确认完成订单

##### 状态变化

![订单状态变化](E:\MyLearning\项目练习\微信小程序+管理员后台的奶茶销售项目\文档图片\订单状态变化.png)

##### 用户操作

对于用户而言，真正重要的是他们能够进行的操作，目前所支持的服务有：

* `支付`，如果下单后未立即支付，则订单详情页面会出现`支付`按钮；
* `取消`，如果用户未支付，则订单详情页面会出现`取消`按钮；
* `确认收货`，如果管理员已发货，则订单详情页面会出现`确认收货`按钮；
* `删除`，如果当前订单状态码是102、103、203、401和402时，则订单详情页面会出现`删除订单`按钮；

##### 订单状态码与用户操作之间的映射关系

* 101

  用户可以`支付`、`取消`

* 102 

  用户可以`删除`

* 103 

  用户可以`删除`

* 301

  用户可以`确认收货`

* 401

  用户可以`确认收货`

* 501

  用户可以`删除`

* 502

  用户可以`删除`

#### 对象存储设计

存储对象表dgutshop_storage记录了上传文件信息，当用户或管理员上传图像时，图像文件会保存到本地或者第三方云存储服务器中， 同时在存储对象表中记录一下。

当然对应表中有图片url字段的更新或添加时也会作记录。

#### 商家、订单信息配置设计

系统信息配置表dgutshop_system保存系统的配置信息。

超级管理员具有对商家信息和订单对应配置信息进行查看与修改功能。

这里需要注意的是，在Java代码层系统配置表只能执行更新操作， 不能执行创建和删除操作。也就是说，系统配置数据都应该是开发者 基于系统的配置需求在数据库中手动创建。

#### 管理人员信息设计

目前使用Spring Security进行登录验证及鉴权时用到的表：

dgutshop_admin：管理人员信息；

dgutshop_role：角色信息；

dgutshop_user_role：管理人员与角色的连接表；

...



## 致谢

本项目的架构基本上是参考[litmall小商城项目](https://gitee.com/linlinjava/litemall/tree/master)的东西从0开始撸出来的，并对某些关键技术进行改变或者完善，从中学习了很多开发经验。



## 警告

本项目仅作学习使用。