<template>
  <div class="sidebar">
    <el-menu
      class="sidebar-el-menu"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#FFFFFF	"
      text-color="#000000	"
      active-text-color="#DCDCDC	"
      unique-opened
      router
    >
      <el-menu-item index="/Main">
        <!-- 图标 -->
        <i class="el-icon-s-home" style=""></i>
        <!-- 文本 -->
        <span>首页</span>
      </el-menu-item>

      <el-menu-item
        index="/MallManage"
        v-if="MallManageActive && this.obj.roleId === 1"
      >
        <!-- 图标 -->
        <i class="el-icon-s-shop" style=""></i>
        <!-- 文本 -->
        <span>商家信息管理</span>
      </el-menu-item>

      <!-- 超级管理员 -->
      <!-- 一级菜单 -->
      <el-submenu index="2">
        <!-- 一级菜单的模板区 -->
        <template slot="title">
          <!-- 图标 -->
          <i class="el-icon-user-solid" style=""></i>
          <!-- 文本 -->
          <span>人员信息管理</span>
        </template>
        <!-- 二级菜单 -->
        <el-menu-item
          index="/ManagerManage"
          v-if="ManagerManageActive && this.obj.roleId === 1"
        >
          <template slot="title">
            <!-- 图标 -->
            <i class="el-icon-coordinate" style=""></i>
            <!-- 文本 -->
            <span>管理员管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/UserManage" v-if="UserManageActive">
          <template slot="title">
            <!-- 图标 -->
            <i class="el-icon-user" style=""></i>
            <!-- 文本 -->
            <span>用户管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/AddressManage" v-if="AddressManageActive">
          <template slot="title">
            <!-- 图标 -->
            <i class="el-icon-map-location" style=""></i>
            <!-- 文本 -->
            <span>用户地址管理</span>
          </template>
        </el-menu-item>
      </el-submenu>
      <!-- 管理员 -->
      <!-- 一级菜单 -->
      <el-submenu index="3">
        <!-- 一级菜单的模板区 -->
        <template slot="title">
          <!-- 图标 -->
          <i class="el-icon-s-order" style=""></i>
          <!-- 文本 -->
          <span>订单管理</span>
        </template>
        <!-- 二级菜单 -->
        <el-menu-item
          index="/OrderConfiguration"
          v-if="OrderConfigurationActive"
        >
          <template slot="title">
            <!-- 图标 -->
            <i class="el-icon-document-add" style=""></i>
            <!-- 文本 -->
            <span>订单配置</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/SolveOrder" v-if="SolveOrderActive">
          <template slot="title">
            <!-- 图标 -->
            <i class="el-icon-tickets" style=""></i>
            <!-- 文本 -->
            <span>处理订单</span>
          </template>
        </el-menu-item>

        <!-- <el-menu-item index="/CheckOrder" v-if="CheckOrderActive">
          <template slot="title">
            <i class="el-icon-location" style=""></i>
            <span>查看订单</span>
          </template>
        </el-menu-item> -->
      </el-submenu>
      <el-submenu index="4">
        <!-- 一级菜单的模板区 -->
        <template slot="title">
          <!-- 图标 -->
          <i class="el-icon-s-goods" style=""></i>
          <!-- 文本 -->
          <span>饮品</span>
        </template>
        <!-- 二级菜单 -->
        <el-menu-item index="/DrinkManage" v-if="DrinkManageActive">
          <template slot="title">
            <i class="el-icon-ice-drink" style=""></i>
            <span>饮品管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/ToppingsManage" v-if="ToppingsManageActive">
          <template slot="title">
            <i class="el-icon-cherry" style=""></i>
            <span>小料管理</span>
          </template>
        </el-menu-item>
      </el-submenu>

      <el-submenu index="5">
        <!-- 一级菜单的模板区 -->
        <template slot="title">
          <!-- 图标 -->
          <i class="el-icon-menu" style=""></i>
          <!-- 文本 -->
          <span>小程序定制</span>
        </template>
        <!-- 二级菜单 -->
        <el-menu-item index="/DrinkSortManage" v-if="DrinkSortManageActive">
          <template slot="title">
            <!-- 图标 -->
            <i class="el-icon-bangzhu" style=""></i>
            <!-- 文本 -->
            <span>小程序侧边栏分类管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/SlideShowManage" v-if="SlideShowActive">
          <template slot="title">
            <!-- 图标 -->
            <i class="el-icon-film" style=""></i>
            <!-- 文本 -->
            <span>轮播图管理</span>
          </template>
        </el-menu-item>
      </el-submenu>

      <!-- <el-submenu index="5">
        <template slot="title">
          <i class="el-icon-menu" style=""></i>
          <span>活动</span>
        </template>
        <el-menu-item index="/ActiveManage" v-if="ActiveManageActive">
          <template slot="title">
            <i class="el-icon-location" style=""></i>
            <span>活动管理</span>
          </template>
        </el-menu-item>
      </el-submenu> -->
    </el-menu>
  </div>
</template>

<script>
import storage from "@/storage";
import bus from "../Home/bus.js";
export default {
  created: function() {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on("collapse", msg => {
      this.collapse = msg;
      bus.$emit("collapse-content", msg);
    });
    var obj = JSON.parse(localStorage.getItem("user"));
    console.log(obj.roleId);
    if (obj.roleId === 1) {
      this.TestActive = true;
      this.SlideShowActive = true;
      this.MallManageActive = true;
      this.OrderConfigurationActive = true;
      this.UserManageActive = true;
      this.ToppingsManageActive = true;
      this.AddressManageActive = true;
      this.ManagerManageActive = true;
      this.SolveOrderActive = true;
      this.CheckOrderActive = true;
      this.DrinkManageActive = true;
      this.ActiveManageActive = true;
      this.CheckActiveActive = true;
      this.DrinkSortManageActive = true;
    }
    if (obj.roleId === 2) {
      this.TestActive = true;
      this.SlideShowActive = true;
      this.UserManageActive = true;
      this.ToppingsManageActive = true;
      this.AddressManageActive = true;
      this.SolveOrderActive = true;
      this.CheckOrderActive = true;
      this.DrinkManageActive = true;
      this.ActiveManageActive = true;
      this.CheckActiveActive = true;
      this.DrinkSortManageActive = true;
    }
  },
  computed: {
    onRoutes() {
      return this.$route.path.replace("/", "");
    }
  },
  data: function() {
    return {
      obj: JSON.parse(localStorage.getItem("user")),
      collapse: false,
      OrderConfigurationActive: false,
      TestActive: false,
      SlideShowActive: false,
      MallManageActive: false,
      UserManageActive: false,
      ManagerManageActive: false,
      SolveOrderActive: false,
      CheckOrderActive: false,
      DrinkManageActive: false,
      ActiveManageActive: false,
      CheckActiveActive: false,
      DrinkSortManageActive: false,
      AddressManageActive: false,
      ToppingsManageActive: false
    };
  }
};
</script>
<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
}
</style>
