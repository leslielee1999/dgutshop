<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.nickname"
        clearable
        class="filter-item"
        style="width: 160px"
        placeholder="请输入用户昵称"
      />
      <el-input
        v-model="listQuery.consignee"
        clearable
        class="filter-item"
        style="width: 160px"
        placeholder="请输入收货人名称"
      />
      <el-input
        v-model="listQuery.code"
        clearable
        class="filter-item"
        style="width: 160px"
        placeholder="请输入订单编号"
      />
      <el-date-picker
        v-model="listQuery.timeArray"
        type="datetimerange"
        value-format="yyyy-MM-dd HH:mm:ss"
        class="filter-item"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :picker-options="pickerOptions"
      />
      <el-select
        v-model="listQuery.orderStatusArray"
        multiple
        style="width: 200px"
        class="filter-item"
        placeholder="请选择订单状态"
      >
        <el-option
          v-for="(key, value) in statusMap"
          :key="key"
          :label="key"
          :value="value"
        />
      </el-select>
      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="getList"
        >查找</el-button
      >
      <el-button
        class="filter-item"
        type="success"
        icon="el-icon-download"
        @click="handleDownloadPage"
        >导出</el-button
      >
      <el-button
        class="filter-item"
        type="success"
        icon="el-icon-refresh"
        @click="handleReflash"
        >刷新</el-button
      >
      <!-- <el-button
        class="filter-item"
        type="success"
        icon="el-icon-download"
        @click="handleDownload"
        >导出所有</el-button
      > -->
    </div>

    <!-- 查询结果 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="正在查询中。。。"
      border
      fit
      highlight-current-row
      @expand-change="expandSelect"
      @cell-mouse-enter="getList_orderItems"
      :row-key="getRowKeys"
      :expand-row-keys="expands"
    >
      <el-table-column type="expand">
        <el-table
          :data="list_orderItems"
          v-loading="listLoading"
          element-loading-text="正在查询中。。。"
          border
          fit
          highlight-current-row
          row-key="id"
        >
          <!-- <el-table-column align="center" label="ID" prop="id" sortable /> -->
          <el-table-column align="center" label="饮品ID" prop="pid" sortable />

          <el-table-column
            align="center"
            min-width="100"
            label="饮品名称"
            prop="productName"
            sortable
          />
          <el-table-column
            align="center"
            property="productPicture"
            label="图片"
            sortable
          >
            <template slot-scope="scope">
              <img :src="scope.row.productPicture" width="40" />
            </template>
          </el-table-column>
          <el-table-column align="center" label="饮品规格" prop="size" sortable>
            <template slot-scope="scope">
              <el-tag>{{ DrinkSize[scope.row.size] }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            sortable
            align="center"
            label="饮品原售价"
            prop="productOriginalPrice"
          />
          <el-table-column
            sortable
            align="center"
            label="饮品现售价"
            prop="productPromotePrice"
          />

          <el-table-column align="center" label="销量" prop="sum" sortable />
          <el-table-column
            sortable
            align="center"
            label="添加的小料"
            prop="toppingName"
          />
          <el-table-column
            sortable
            align="center"
            label="小料的价格"
            prop="toppingPrice"
          />
          <el-table-column align="center" label="备注" prop="note" sortable />
        </el-table>
      </el-table-column>

      <!-- <el-table-column
        sortable
        align="center"
        min-width="120"
        label="ID"
        prop="id"
      /> -->
      <el-table-column
        sortable
        align="center"
        min-width="120"
        label="订单编号"
        prop="code"
      />
      <el-table-column align="center" label="用户ID" prop="userId" sortable />
      <el-table-column
        align="center"
        label="下单用户"
        prop="userName"
        sortable
      />

      <el-table-column
        sortable
        align="center"
        label="下单时间"
        prop="createTime"
        min-width="100"
      >
        <template slot-scope="scope">
          {{ (scope.row.createTime || "").substring(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="订单类型" prop="type" sortable>
        <template slot-scope="scope">
          <el-tag>{{ identifyOrder[scope.row.type] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="订单状态"
        prop="orderStatus"
        sortable
      >
        <template slot-scope="scope">
          <el-tag>{{ scope.row.orderStatus | orderStatusFilter }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="取餐码" prop="takeCode" sortable />
      <el-table-column
        align="center"
        label="支付时间"
        prop="payDate"
        sortable
      />
      <el-table-column
        align="center"
        label="完成时间"
        prop="endTime"
        sortable
      />

      <el-table-column
        align="center"
        label="订单备注"
        prop="message"
        sortable
      />
      <el-table-column align="center" label="收货人" prop="consignee" sortable>
        <template slot-scope="scope">
          <span style="color: red; font-weight: bold">{{
            scope.row.consignee
          }}</span>
        </template>
      </el-table-column>

      <el-table-column
        sortable
        align="center"
        label="收货电话"
        prop="consigneePhone"
        min-width="100"
      />
      <el-table-column
        sortable
        align="center"
        label="收货地址"
        prop="consigneeAddress"
        min-width="100"
      />
      <el-table-column
        sortable
        align="center"
        label="详细地址"
        prop="consigneeRoom"
        min-width="100"
      />
      <el-table-column
        align="center"
        label="下单渠道"
        prop="deliveryCompany"
        sortable
      />
      <el-table-column
        align="center"
        label="配送费用"
        prop="deliveryPrice"
        sortable
      />
      <el-table-column
        align="center"
        label="奶茶费用"
        prop="productPrice"
        sortable
      />
      <el-table-column
        align="center"
        label="总费用"
        prop="orderPrice"
        sortable
      />
      <el-table-column
        fixed="right"
        align="center"
        label="操作"
        width="100"
        class-name="oper"
      >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.orderStatus == 201 && scope.row.type == 0"
            type="primary"
            @click="handleShip(scope.row)"
            >发货</el-button
          >
          <el-button
            v-if="scope.row.orderStatus == 201 && scope.row.type == 1"
            type="primary"
            @click="handleTakeSelf(scope.row)"
            >制作完成</el-button
          >
          <el-button
            v-if="scope.row.orderStatus !== 201"
            disabled
            type="info"
            @click="handleTakeSelf(scope.row)"
            >无操作</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />
    <!-- 发货对话框 -->
    <el-dialog :visible.sync="shipDialogVisible" title="发货">
      <el-form
        ref="shipForm"
        :model="shipForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="订单ID" prop="orderId">
          <el-input disabled v-model="shipForm.orderId" />
        </el-form-item>
        <el-form-item label="订单ID" prop="orderCode">
          <el-input disabled v-model="shipForm.orderCode" />
        </el-form-item>
        <el-form-item label="送货员姓名" prop="deliverymanName">
          <el-input disabled v-model="shipForm.deliverymanName" />
        </el-form-item>
        <el-form-item label="送货员联系方式" prop="deliverymanPhone">
          <el-input disabled v-model="shipForm.deliverymanPhone" />
        </el-form-item>

        <el-table
          v-loading="listLoading"
          :data="list_delivery"
          element-loading-text="正在查询中。。。"
          border
          fit
          highlight-current-row
        >
          <el-table-column align="center" label="ID" prop="id" sortable />

          <el-table-column
            align="center"
            label="送货员姓名"
            prop="name"
            sortable
          />
          <el-table-column align="center" label="送货员联系方式" prop="phone" />

          <el-table-column
            align="center"
            label="操作"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="mini"
                @click="handleChoice(scope.row)"
                >选择</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="shipDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmShip">发货</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.el-table--medium th,
.el-table--medium td {
  padding: 3px 0;
}

.el-input-number--medium {
  width: 100%;
}

.oper .el-button--mini {
  padding: 7px 4px;
  width: 40px;
  font-size: 10px;
  margin-left: 1px;
}

::v-deep .el-table__expanded-cell {
  padding: 6px 80px;
}

.order-goods {
  display: flex;
  justify-content: space-around;
  justify-items: center;
  align-items: center;
  padding: 6px 0;
}

.name {
  width: 400px;
}

.spec {
  width: 180px;
}

.price {
  width: 120px;
}

.num {
  width: 120px;
}
</style>

<script>
const statusMap = {
  101: "待支付",
  102: "用户取消",
  103: "系统取消",
  201: "制作中",
  301: "待取货",
  401: "派送中",
  402: "骑车到达",
  501: "已完成",
  502: "自动确认"
};
import Pagination from "@/components/Pagination";
export default {
  name: "SolveOrder",
  components: { Pagination },
  filters: {
    orderStatusFilter(status) {
      return statusMap[status];
    }
  },
  data() {
    return {
      excelData: [],
      expands: [],
      getRowKeys(row) {
        return row.id;
      },
      identifyOrder: ["外卖", "自取"],
      DrinkSize: ["中杯", "大杯"],
      list: [],
      listAll: [],
      list_delivery: [],
      total_delivery: 0,
      list_orderItems: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        nickname: "",
        consignee: "",
        code: "",
        timeArray: [],
        orderStatusArray: [],
        sort: "add_time",
        order: "desc"
      },
      statusStr: "",
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      statusMap,
      orderDialogVisible: false,
      orderDetail: {
        order: {},
        user: {},
        orderGoods: []
      },
      shipForm: {
        orderId: undefined,
        orderCode: undefined,
        deliverymanName: undefined,
        deliverymanPhone: undefined
      },
      shipDialogVisible: false,
      payForm: {
        orderId: undefined,
        orderSn: "",
        oldMoney: 0,
        newMoney: 0,
        goodsList: []
      },
      payDialogVisible: false,
      refundForm: {
        orderId: undefined,
        refundMoney: undefined
      },
      refundDialogVisible: false,
      downloadLoading: false,
      channels: []
    };
  },
  created() {
    this.getList();
    // this.getAllList();
  },
  methods: {
    async getList() {
      this.listLoading = true;
      console.log(this.listQuery.timeArray);
      if (
        this.listQuery.nickname != "" ||
        this.listQuery.code != "" ||
        this.listQuery.consignee != "" ||
        this.listQuery.timeArray != null ||
        this.listQuery.timeArray != undefined ||
        this.listQuery.timeArray != [] ||
        this.listQuery.orderStatusArray != null ||
        this.listQuery.orderStatusArray != undefined ||
        this.listQuery.orderStatusArray != []
      ) {
        const { data: res_delivery } = await this.$http
          .get("/admin/delivery/list")
          .catch(() => {
            this.list_delivery = [];
            this.total_delivery = 0;
            this.listLoading = false;
          });
        console.log(res_delivery);
        this.list_delivery = res_delivery.data.list;
        this.total_delivery = res_delivery.data.total;
        this.listLoading = false;

        this.statusStr = "";
        if (this.listQuery.timeArray && this.listQuery.timeArray.length === 2) {
          this.listQuery.start = this.listQuery.timeArray[0];
          this.listQuery.end = this.listQuery.timeArray[1];
        } else {
          this.listQuery.start = "";
          this.listQuery.end = "";
        }
        this.listLoading = true;
        for (var i = 0; i < this.listQuery.orderStatusArray.length; i++) {
          this.statusStr =
            this.statusStr +
            "&orderStatusArray=" +
            this.listQuery.orderStatusArray[i];
        }

        const { data: res } = await this.$http
          .get(
            "/admin/order/list?page=" +
              this.listQuery.page +
              "&order=desc" +
              "&nickname=" +
              this.listQuery.nickname +
              "&code=" +
              this.listQuery.code +
              "&consignee=" +
              this.listQuery.consignee +
              "&start=" +
              this.listQuery.start +
              "&end=" +
              this.listQuery.end +
              this.statusStr +
              "&sort=create_time"
          )
          .catch(() => {
            this.list = [];
            this.total = 0;
            this.listLoading = false;
          });

        this.list = res.data.list;
        this.total = res.data.total;
        this.listLoading = false;
      } else {
        const { data: res_delivery } = await this.$http
          .get("/admin/delivery/list")
          .catch(() => {
            this.list_delivery = [];
            this.total_delivery = 0;
            this.listLoading = false;
          });
        console.log(res_delivery);
        this.list_delivery = res_delivery.data.list;
        this.total_delivery = res_delivery.data.total;
        this.listLoading = false;

        const { data: res } = await this.$http
          .get(
            "/admin/order/list?page=" +
              this.listQuery.page +
              "&order=desc" +
              "&sort=create_time"
          )
          .catch(() => {
            this.list = [];
            this.total = 0;
            this.listLoading = false;
          });

        this.list = res.data.list;
        this.total = res.data.total;
        this.listLoading = false;
        this.listQuery.timeArray = [];
        this.listQuery.orderStatusArray = [];
        this.listAll = [];
        console.log(this.listAll);
      }
    },
    getList_orderItems(row, column, cell, event) {
      this.list_orderItems = row.orderItemList;
    },
    expandSelect: function(row, expandedRows) {
      if (expandedRows.length) {
        this.expands = [];
        if (row) {
          this.expands.push(row.id);
        }
      } else {
        this.expands = [];
      }
    },
    handleReflash() {
      this.getList();
    },
    handleChoice(row) {
      this.shipForm.deliverymanName = row.name;
      this.shipForm.deliverymanPhone = row.phone;
    },
    handleShip(row) {
      console.log(row);
      (this.shipForm.deliverymanName = ""),
        (this.shipForm.deliverymanPhone = "");
      this.shipForm.orderCode = row.code;
      this.shipForm.orderId = row.id;
      this.shipDialogVisible = true;
      this.$nextTick(() => {
        this.$refs["shipForm"].clearValidate();
      });
    },
    handleTakeSelf(row) {
      this.$confirm("此操作将触发制作完成操作, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/order/completed",
            {
              orderId: row.id
            }
          );
          this.getList();
          console.log(res);
          this.getList();
          this.shipDialogVisible = false;
          if (res.errno == 0) {
            this.$message({
              type: "success",
              message: "修改成功!"
            });
          } else {
            this.$message({
              type: "danger",
              message: "发生错误!"
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "发生错误"
          });
          this.shipDialogVisible = false;
        });
    },
    confirmShip() {
      this.$refs["shipForm"].validate(valid => {
        console.log(this.shipForm);
        if (valid) {
          this.$confirm("此操作将触发发货操作, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true
          })
            .then(async () => {
              const { data: res } = await this.$http.post(
                "/admin/order/delivery",
                this.shipForm
              );
              this.getList();
              console.log(res);
              this.getList();
              this.shipDialogVisible = false;
              if (res.errno == 0) {
                this.$message({
                  type: "success",
                  message: "修改成功!"
                });
              } else {
                this.$message({
                  type: "danger",
                  message: "发生错误!"
                });
              }
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "发生错误"
              });
              this.shipDialogVisible = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async handleDownload() {
      const { data: res_excel } = await this.$http.get(
        "/admin/order/getAllOrder"
      );
      this.excelData = res_excel;
      this.downloadLoading = true;
      import("@/vendor/Export2Excel").then(excel => {
        const tHeader = [
          "订单编号",
          "下单用户",
          "下单时间",
          "订单类型",
          "订单状态",
          "支付时间",
          "订单备注",
          "收货人",
          "收货电话",
          "收货地址",
          "详细地址",

          "下单渠道",
          "配送费用",
          "奶茶费用",
          "总费用"
        ];
        const filterVal = [
          "code",
          "userName",
          "createTime",
          "type",
          "orderStatus",
          "payDate",
          "message",
          "consignee",
          "consigneePhone",
          "consigneeAddress",
          "consigneeRoom",

          "deliveryCompany",
          "deliveryPrice",
          "productPrice",
          "orderPrice"
        ];
        excel.export_json_to_excel2(
          tHeader,
          this.excelData,
          filterVal,
          "订单信息"
        );
        this.downloadLoading = false;
      });
    },
    async handleDownloadPage() {
      console.log(this.listQuery.timeArray);
      if (
        this.listQuery.nickname != "" ||
        this.listQuery.code != "" ||
        this.listQuery.consignee != "" ||
        this.listQuery.timeArray != null ||
        this.listQuery.timeArray != undefined ||
        this.listQuery.timeArray != [] ||
        this.listQuery.orderStatusArray != null ||
        this.listQuery.orderStatusArray != undefined ||
        this.listQuery.orderStatusArray != []
      ) {
        const { data: res_all } = await this.$http
          .get(
            "/admin/order/list?nickname=" +
              this.listQuery.nickname +
              "&code=" +
              this.listQuery.code +
              "&consignee=" +
              this.listQuery.consignee +
              "&start=" +
              this.listQuery.start +
              "&end=" +
              this.listQuery.end +
              this.statusStr +
              "&limit=" +
              this.total +
              "&sort=create_time" +
              "&order=desc"
          )
          // .get("/admin/order/list?orderStatusArray=101")
          .catch(() => {
            this.list = [];
            this.total = 0;
            this.listLoading = false;
          });
        this.listAll = res_all.data.list;
      }

      this.downloadLoading = true;
      import("@/vendor/Export2Excel").then(excel => {
        const tHeader = [
          "订单编号",
          "下单用户",
          "下单时间",
          "订单类型",
          "订单状态",
          "支付时间",
          "订单备注",
          "收货人",
          "收货电话",
          "收货地址",
          "详细地址",

          "下单渠道",
          "配送费用",
          "奶茶费用",
          "总费用"
        ];
        const filterVal = [
          "code",
          "userName",
          "createTime",
          "type",
          "orderStatus",
          "payDate",
          "message",
          "consignee",
          "consigneePhone",
          "consigneeAddress",
          "consigneeRoom",

          "deliveryCompany",
          "deliveryPrice",
          "productPrice",
          "orderPrice"
        ];
        // if (this.listAll.length == 0) {
        //   console.log("list")
        //   excel.export_json_to_excel2(
        //     tHeader,
        //     this.list,
        //     filterVal,
        //     "订单信息"
        //   );
        //   this.downloadLoading = false;
        // }
        // if (this.listAll.length != 0) {
        excel.export_json_to_excel2(
          tHeader,
          this.listAll,
          filterVal,
          "订单信息"
        );
        this.downloadLoading = false;
        // }
      });
    },
    printOrder() {
      //   this.$print(this.$refs.print)
      //   this.orderDialogVisible = false
    }
  }
};
</script>
