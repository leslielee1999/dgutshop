<template>
  <div class="login_container">
    <div class="photo"></div>
    <div id="dateTime" style="display: none"></div>
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/img/head.jpg" alt="" />
      </div>

      <el-form
        ref="loginFormRef"
        label-width="0px"
        :model="loginForm"
        :rules="loginFormRules"
        class="login_form"
      >
        <el-form-item prop="username">
          <el-input
            prefix-icon="iconfont icon-user"
            v-model="loginForm.username"
            placeholder="用户名"
            type="text"
          ></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            prefix-icon="iconfont icon-3702mima"
            v-model="loginForm.password"
            placeholder="密码"
            type="password"
          ></el-input>
        </el-form-item>

        <el-form-item class="btns">
          <el-button type="primary" class="btnLogin" @click="login"
            >登录</el-button
          >
          <el-button type="primary" class="btnReset" @click="resetLoginForm"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import Qs from "qs";
export default {
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
        Ticket: "",
        Randstr: ""
      },
      loginFormRules: {
        // username: [
        //   { retuired: true, message: "请输入登录名称", trigger: "blur" },
        //   { min: 3, max: 10, message: "长度在3到10个字符", trigger: "blur" },
        // ],
        // password: [
        //   { retuired: true, message: "请输入登录密码", trigger: "blur" },
        //   { min: 5, max: 15, message: "长度在5到15个字符", trigger: "blur" },
        // ],
      }
    };
  },
  created() {
    if (window.TencentCaptcha === undefined) {
      let script = document.createElement("script");
      let head = document.getElementsByTagName("head")[0];
      script.type = "text/javascript";
      script.charset = "UTF-8";
      script.src = "https://ssl.captcha.qq.com/TCaptcha.js";
      head.appendChild(script);
    }
  },
  methods: {
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    async login() {
      console.log(this.loginForm);
      let captcha = new TencentCaptcha("2071933573", async rsp => {
        console.log(rsp);
        if (rsp.ret === 0) {
          this.loginForm.Ticket = rsp.ticket;
          this.loginForm.Randstr = rsp.randstr;
          const { data: res } = await this.$http.get(
            "/login/verify?ticket=" +
              this.loginForm.Ticket +
              "&randstr=" +
              this.loginForm.Randstr
          );
          if (res.code === 1) {
            this.$refs.loginFormRef.validate(async valid => {
              if (!valid) return;
              //TODO
              const loading = this.$loading({
                lock: true,
                text: "登录中...",
                spinner: "el-icon-loading",
                background: "rgba(0, 0, 0, 0.7)"
              });
              const res = await this.$http.post(
                "login/userLogin?username=" +
                  this.loginForm.username +
                  "&password=" +
                  this.loginForm.password
              );
              loading.close();
              console.log(res);
              if (res.data.message == "用户名不存在")
                return this.$message.error("用户名不存在");
              if (res.data.message == "用户名密码不正确")
                return this.$message.error("用户名密码不正确");
              if (res.data.message == "用户被冻结")
                return this.$message.error("用户被冻结");
              if (res.data.code !== "200")
                return this.$message.error("登录失败");

              // this.$message.success("登录成功");
              this.$message({
                message: "登录成功",
                type: "success",
                duration: 1200
              });
              this.$store.commit("setToken", res.data.token);
              const { data: user_res } = await this.$http.post(
                "/admin/profile/getinfo"
              );
              console.log(user_res.data);
              // this .$store.commit( 'setType' ,  user_res.data.roleId)
              // document.cookie='type='+user_res.data.roleId
              // console.log(user_res)
              this.$store.commit("setUser", user_res.data);
              await this.$router.push("/Main");
              // TODO  实现token
              // document.cookie='token='+res.data.token
              // this.$store.state.token=res.data.token
            });
          }
        } else {
          this.$message({
            message: "放弃了验证",
            type: "warning"
          });
        }
      });
      captcha.show();
    }
  }
};
</script>

<style lang="less" scoped>
.login_container {
  background: url("../assets/img/bg4.jpg") center;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  background-color: var(--themeColor);
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #ffffff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-shadow: 0 0 6px 3px rgba(0, 0, 0, 0.1);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}
.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
.btns {
  display: flex;
  justify-content: flex-end;
}
</style>
