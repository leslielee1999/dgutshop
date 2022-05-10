<template>
  <div style="text-align: left">
    <el-row :gutter="20" style="margin-top: 50px">
      <el-col :span="24">
        <div>
          <el-card class="box-card" style="width: 50%; margin: auto">
            <div slot="header" class="clearfix">
              <span>本店信息</span>
            </div>
            <div>
              <el-form label-width="80px" size="small" label-position="right">
                <el-form-item label="店名" prop="dgutshop_shop_name">
                  <el-input
                    disabled
                    auto-complete="off"
                    v-model="dataForm.dgutshop_shop_name"
                  ></el-input>
                </el-form-item>
                <el-form-item label="所在位置" prop="dgutshop_shop_address">
                  <el-input
                    disabled
                    auto-complete="off"
                    v-model="dataForm.dgutshop_shop_address"
                  ></el-input>
                </el-form-item>

                <el-form-item label="经度" prop="dgutshop_shop_longitude">
                  <el-input
                    disabled
                    auto-complete="off"
                    v-model="dataForm.dgutshop_shop_longitude"
                  ></el-input>
                </el-form-item>
                <el-form-item label="纬度" prop="dgutshop_shop_latitude">
                  <el-input
                    disabled
                    auto-complete="off"
                    v-model="dataForm.dgutshop_shop_latitude"
                  ></el-input>
                </el-form-item>
                <el-form-item
                  label="营业时间"
                  prop="dgutshop_shop_businesshours"
                >
                  <input
                    v-model="dataForm.dgutshop_shop_businesshours"
                    hidden
                  />
                  <el-time-select
                    placeholder="起始时间"
                    v-model="startTime"
                    :picker-options="{
                      start: '08:30',
                      step: '00:15',
                      end: '10:30'
                    }"
                  >
                  </el-time-select>
                  <el-time-select
                    placeholder="结束时间"
                    v-model="endTime"
                    :picker-options="{
                      start: '21:30',
                      step: '00:15',
                      end: '23:00',
                      minTime: startTime
                    }"
                  >
                  </el-time-select>
                </el-form-item>
                <el-form-item label="联系电话" prop="dgutshop_shop_phone">
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.dgutshop_shop_phone"
                  ></el-input>
                </el-form-item>
                <el-form-item label="QQ" prop="dgutshop_shop_qq">
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.dgutshop_shop_qq"
                  ></el-input>
                </el-form-item>
                <el-form-item label="商家图片" prop="dgutshop_shop_poster">
                  <el-upload
                    :headers="headers"
                    action="/api/admin/storage/create"
                    :show-file-list="false"
                    :on-success="uploadPicUrl"
                    class="avatar-uploader"
                    accept=".jpg,.jpeg,.png,.gif"
                  >
                    <img
                      v-if="dataForm.dgutshop_shop_poster"
                      :src="dataForm.dgutshop_shop_poster"
                      class="avatar"
                    />
                    <i v-else class="el-icon-plus avatar-uploader-icon" />
                  </el-upload>
                </el-form-item>
                <el-form-item label="是否关店" prop="dgutshop_shop_isclosed">
                  <el-switch
                    v-model="dataForm.dgutshop_shop_isclosed"
                    :active-value="1"
                    :inactive-value="2"
                    active-color="#008080"
                    inactive-color="#B9B9B9"
                  />
                </el-form-item>
                <el-form-item
                  label="关店原因"
                  prop="dgutshop_shop_closedreason"
                  v-if="dataForm.dgutshop_shop_isclosed === 1"
                >
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.dgutshop_shop_closedreason"
                  ></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button size="mini" type="primary" @click="handleUpdate"
                  >提交修改</el-button
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      token: "",
      dataForm: {
        dgutshop_shop_address: "",
        dgutshop_shop_businesshours: "",
        dgutshop_shop_latitude: "",
        dgutshop_shop_longitude: "",
        dgutshop_shop_name: "",
        dgutshop_shop_phone: "",
        dgutshop_shop_qq: "",
        dgutshop_shop_poster: "",
        dgutshop_shop_isclosed: "",
        dgutshop_shop_closedreason: ""
      },
      startTime: "",
      endTime: ""
    };
  },
  methods: {
    async getList() {
      this.token = this.$store.state.token;
      const { data: res } = await this.$http.get("/admin/config/mall");
      // console.log("123123",res);
      this.dataForm.dgutshop_shop_address = res.data.dgutshop_shop_address;
      this.dataForm.dgutshop_shop_businesshours =
        res.data.dgutshop_shop_businesshours;
      this.startTime = res.data.dgutshop_shop_businesshours.split("~")[0];
      this.endTime = res.data.dgutshop_shop_businesshours.split("~")[1];
      this.dataForm.dgutshop_shop_latitude = res.data.dgutshop_shop_latitude;
      this.dataForm.dgutshop_shop_longitude = res.data.dgutshop_shop_longitude;
      this.dataForm.dgutshop_shop_phone = res.data.dgutshop_shop_phone;
      this.dataForm.dgutshop_shop_name = res.data.dgutshop_shop_name;
      this.dataForm.dgutshop_shop_qq = res.data.dgutshop_shop_qq;
      this.dataForm.dgutshop_shop_poster = res.data.dgutshop_shop_poster;
      this.dataForm.dgutshop_shop_isclosed =
        res.data.dgutshop_shop_isclosed == "1" ? 1 : 2;
      this.dataForm.dgutshop_shop_closedreason =
        res.data.dgutshop_shop_closedreason;
    },
    async handleUpdate() {
      this.$confirm("此操作将修改该店信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(async () => {
          this.dataForm.dgutshop_shop_businesshours = this.businesshours;
          const { data: res } = await this.$http.post(
            "/admin/config/mall",
            this.dataForm
          );
          this.getList();
          this.$message({
            type: "success",
            message: "修改成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
      this.isShowUpdate = false;
    },
    uploadPicUrl: function(response) {
      // console.log(response);
      this.dataForm.dgutshop_shop_poster = response.data.url;
    }
  },
  computed: {
    headers() {
      return { Authorization: this.token };
    },
    businesshours() {
        return this.startTime + "~" + this.endTime;
    }
  },
  created: function() {
    this.getList();
    // console.info(this.dataForm);
  }
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
.avatar-uploader {
  margin: 0;
}
.avatar-uploader img {
  width: 100%;
  height: 100%;
}
</style>
