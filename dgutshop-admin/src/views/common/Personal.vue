<template>
  <div style="text-align:center;">
    <el-row :gutter="20" style="margin-top: 50px;">
      <el-col :span="24" >
          
        <!-- <div class="grid-content bg-purple"> -->
          <el-card class="box-card" style="width:50%;margin: auto;">
            <div slot="header" class="clearfix">
              <span>个人信息</span>
            </div>
            <div>
              <el-form label-width="110px" size="small" label-position="right"  :model="dataForm">
                <el-form-item label="个人头像" prop="avatar">
                  <el-upload
                  
                    :headers="headers"
                    action="/api/admin/storage/create"
                    :with-credentials="true"
                    :show-file-list="false"
                    :on-success="uploadAvatar"
                    class="avatar-uploader"
                    accept=".jpg,.jpeg,.png,.gif"
                  >
                    <img
                    style="width:150px; height:150px; border-radius:50%; "
                      v-if="dataForm.picture"
                      :src="dataForm.picture"
                      class="picture"
                    />
                    <i v-else class="el-icon-plus avatar-uploader-icon" />
                  </el-upload>
                </el-form-item>

                <el-form-item label="姓名" prop="name" >
                  <el-input
                  style="width:30%"
                    auto-complete="off"
                    v-model="dataForm.name"
                  ></el-input>
                </el-form-item>
                <el-form-item label="账号" prop="nickname">
                  <el-input
                  style="width:30%"
                    disabled
                    auto-complete="off"
                    v-model="dataForm.nickname"
                  ></el-input>
                </el-form-item>
                <el-form-item label="序列号" prop="code">
                  <el-input
                  style="width:30%"
                    disabled
                    auto-complete="off"
                    v-model="dataForm.code"
                  ></el-input>
                </el-form-item>
                <el-form-item label="身份" prop="roleId">
                  <template>
                    <el-tag>{{ identifyAdmin[dataForm.roleId] }}</el-tag>
                  </template>
                </el-form-item>
                <el-form-item label="最后登陆的IP" prop="lastLoginIp">
                  <el-input
                  style="width:30%"
                    disabled
                    auto-complete="off"
                    v-model="dataForm.lastLoginIp"
                  ></el-input>
                </el-form-item>
                <el-form-item label="最后登陆的时间" prop="lastLoginTime">
                  <el-input
                  style="width:30%"
                    disabled
                    auto-complete="off"
                    v-model="dataForm.lastLoginTime"
                  ></el-input>
                </el-form-item>
              </el-form>
              <div
                slot="footer"
                class="dialog-footer"
                style="text-align: center"
              >
                <el-button size="mini" type="primary" @click="handleUpdate"
                  >提交修改</el-button
                >
              </div>
            </div>
          </el-card>
        <!-- </div> -->
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
        token:"",
      identifyAdmin: ["未知", "超级管理员", "普通管理员"],
      dataForm: {
        id: "",
        roleId: "",
        name: "",
        nickname: "",
        password: "",
        code: "",
        picture: "",
        createTime: "",
        updateTime: "",
        lastLoginIp: "",
        lastLoginTime: "",
        status: "",
        deleted: "",
      },
    };
  },
  methods: {
    async getList() {
        
        this.token = this.$store.state.token;

      // const res = this.$store.state.user;
      const {data: res} = await this.$http.post("/admin/profile/getinfo");
       this .$store.commit( 'setUser' ,  res.data)
      console.log(res)
      this.dataForm.id = res.data.id;
      this.dataForm.roleId = res.data.roleId;
      this.dataForm.name = res.data.name;
      this.dataForm.nickname = res.data.nickname;
      this.dataForm.password = res.data.password;
      this.dataForm.code = res.data.code;
      this.dataForm.picture = res.data.picture;
      this.dataForm.lastLoginIp = res.data.lastLoginIp;
      this.dataForm.status = res.data.status;
      this.dataForm.lastLoginTime = res.data.lastLoginTime;
    },
    uploadAvatar: function(response) {
      this.dataForm.picture = response.data.url
    },
    async handleUpdate() {
        console.log(this.dataForm)
      this.$confirm("此操作将修改该个人信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/admin/update",
            this.dataForm
          );
          console.log(res)
          this.getList();
          if(res.errno==0){
          this.$message({
            type: "success",
            message: "修改成功!",
          });
          }

        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
      this.isShowUpdate = false;
    },
  },
    computed:{
        headers() {
      return {'Authorization' : this.token};
    },
  },
  created: function () {
    this.getList();
  },
};
</script>

<style lang="scss" scoped>
//卡片样式
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 100%;
}
//文本样式区
.name-role {
  font-size: 16px;
  padding: 5px;
  text-align: center;
}
.sender {
  text-align: center;
}
.registe-info {
  text-align: center;
  padding-top: 10px;
}
.personal-relation {
  font-size: 16px;
  padding: 0px 5px 15px;
  margin-right: 1px;
  width: 100%;
}

.relation-item {
  padding: 12px;
}
.dialog-footer {
  padding-top: 10px;
  padding-left: 10%;
}
//布局样式区
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
