<template>
  <div style="text-align: center">
    <el-row :gutter="20" style="margin-top: 50px">
      <el-col :span="24">
        <div>
          <el-card class="box-card" style="width: 50%; margin: auto">
            <div slot="header" class="clearfix">
              <span>订单配置信息</span>
            </div>
            <div>
              <el-form label-width="140px" size="small" label-position="right">
                <el-form-item label="配送费" prop="dgutshop_order_delivery">
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.dgutshop_order_delivery"
                    placeholder="0.00"
                  >
                  <template slot="append">元</template>
                  </el-input>
                </el-form-item>
                <el-form-item
                  label="用户下单后超时"
                  prop="dgutshop_order_unpaid"
                >
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.dgutshop_order_unpaid"
                    placeholder="0"
                    ><template slot="append">分钟</template></el-input
                  >
                </el-form-item>
                <el-form-item
                  label="订单发货后超期"
                  prop="dgutshop_order_unconfirmed"
                >
                  <el-input
                    auto-complete="off"
                    v-model="dataForm.dgutshop_order_unconfirmed"
                    placeholder="0"
                    ><template slot="append">时</template></el-input
                  >
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
        dgutshop_order_delivery: "",
        dgutshop_order_unpaid: "",
        dgutshop_order_unconfirmed: "",
      },
    };
  },
  methods: {
    async getList() {
      this.token = this.$store.state.token;
      const { data: res } = await this.$http.get("/admin/config/order");
      console.log(res);
      this.dataForm.dgutshop_order_delivery = res.data.dgutshop_order_delivery;
      this.dataForm.dgutshop_order_unpaid = res.data.dgutshop_order_unpaid;
      this.dataForm.dgutshop_order_unconfirmed =
        res.data.dgutshop_order_unconfirmed;
    },
    async handleUpdate() {
      this.$confirm("此操作将修改该店信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/config/order",
            this.dataForm
          );
          this.getList();
          this.$message({
            type: "success",
            message: "修改成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
      this.isShowUpdate = false;
    },
    uploadPicUrl: function (response) {
      console.log(response);
      this.dataForm.dgutshop_shop_poster = response.data.url;
    },
  },
  computed: {
    headers() {
      return { Authorization: this.token };
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
