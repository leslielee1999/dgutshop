<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.name"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入小料名字"
      />
      <el-input
        v-model="listQuery.code"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入小料编号"
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
        style="width: 200px"
        class="filter-item"
        placeholder="请选择小料状态"
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
        type="primary"
        icon="el-icon-edit"
        @click="showForm()"
        >添加</el-button
      >
            <el-button
        class="filter-item"
        type="success"
        icon="el-icon-download"
        @click="handleDownloadPage"
        >导出</el-button
      >

    </div>

    <!-- 查询结果 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="正在查询中。。。"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" prop="id" sortable />

      <el-table-column align="center" label="小料编号" prop="code" sortable />
      <el-table-column align="center" label="小料名字" prop="name" />
      <el-table-column align="center" label="小料图片" prop="picture" sortable>
        <template slot-scope="scope">
          <img v-if="scope.row.picture" :src="scope.row.picture" width="40" />
        </template>
      </el-table-column>
      <el-table-column align="center" label="小料价格" prop="price" sortable />
      <el-table-column
        align="center"
        label="小料描述"
        prop="description"
        sortable
      />

      <el-table-column align="center" label="小料状态" prop="status" sortable>
        <template slot-scope="scope">
          <el-tag>{{ statusToppings[scope.row.status] }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        label="操作"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="showUpdate(scope.row)"
            >编辑</el-button
          >
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)"
            >删除</el-button
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

    <!-- 添加改对话框 -->
    <el-dialog :visible.sync="isShowForm" title="增加小料">
      <!-- <h3>增加小料</h3> -->
      <el-form
        ref="topping"
        :rules="rules"
        :model="topping"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="小料名字" prop="name">
          <el-input v-model="topping.name" />
        </el-form-item>

        <el-form-item label="小料编号" prop="code">
          <el-input v-model="topping.code" />
        </el-form-item>
        <el-form-item label="小料价格" prop="price">
          <el-input v-model="topping.price" />
        </el-form-item>
        <el-form-item label="小料描述" prop="description">
          <el-input v-model="topping.description" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="topping.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">下架</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="小料照片" prop="picture">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :show-file-list="false"
            :on-success="uploadAvatar"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="topping.picture" :src="topping.picture" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button type="info" @click="hiddnForm()"
        >取消</el-button
      >
      <el-button
        type="success"
        @click="handleCreate('topping')"
        >添加</el-button
      >
       </span>
    </el-dialog>

    <!-- 修改对话框 -->
    <el-dialog :visible.sync="isShowUpdate" title="修改小料信息">
      <!-- <h3>修改小料信息</h3> -->
      <br />
      <el-form
        ref="topping"
        :rules="rules"
        :model="topping"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="小料名字" prop="name">
          <el-input v-model="topping.name" />
        </el-form-item>

        <el-form-item label="小料编号" prop="code">
          <el-input v-model="topping.code" />
        </el-form-item>
        <el-form-item label="小料价格" prop="price">
          <el-input v-model="topping.price" />
        </el-form-item>
        <el-form-item label="小料描述" prop="description">
          <el-input v-model="topping.description" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="topping.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">下架</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="小料照片" prop="picture">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :show-file-list="false"
            :on-success="uploadAvatar"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="topping.picture" :src="topping.picture" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <br />
      </el-form>
       <span slot="footer" class="dialog-footer">
      <el-button
        type="info"
        @click="hiddnUpdateForm()"
        >取消</el-button
      >
      <el-button
        type="success"
        @click="handleUpdate('topping')"
        >修改</el-button
      >
      </span>
    </el-dialog>
  </div>
</template>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
}
</style>

<script>
const statusMap = {
  1: "正常",
  0: "下架",
};
// import { uploadPath } from '@/api/storage'
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination

export default {
  name: "ToppingsManage",
  components: { Pagination },
  filters: {
    orderStatusFilter(status) {
      return statusMap[status];
    },
  },
  data() {
    return {
      listAll:[],
      excelData:[],
      // uploadPath,
      token: "",
      isShowUpdate: false,
      isShowForm: false,
      list: [],
      total: 0,
      roleOptions: null,
      listLoading: true,
      listQuery: {
        timeArray: [],
        orderStatusArray: [],
        page: 1,
        limit: 10,
        name: "",
        code: "",
        sort: "add_time",
        order: "desc",
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      statusMap,
      statusToppings: ["下架", "正常"],
      topping: {
        id: 0,
        code: "",
        name: "",
        price: "",
        picture: "",
        description: "",
        status: 0,
        createTime: null,
        updateTime: null,
        deleted: 0,
      },
      dialogFormVisible: false,
      dialogStatus: "",
      rules: {
        name: [
          { required: true, message: "小料名字不能为空", trigger: "blur" },
          { min: 1, max: 20, message: "长度在 1 到 20 个字符" },
        ],
      },
      downloadLoading: false,
    };
  },
  computed: {
    headers() {
      return { Authorization: this.token };
    },
  },
  created() {
    this.getList();

  },
  methods: {

      //        async getAllList(){
      //  const { data: res_excel } = await this.$http
      //   .get("/admin/toppings/getAllTopping")
      //   // console.log(res_excel)
      //   this.excelData = res_excel
      //   },

    async getList() {
      this.token = this.$store.state.token;
      this.listLoading = true;

      if (
        this.listQuery.name != "" ||
        this.listQuery.code != "" ||
        this.listQuery.timeArray != null ||
        this.listQuery.timeArray != undefined ||
        this.listQuery.timeArray != [] ||
        this.listQuery.orderStatusArray != null ||
        this.listQuery.orderStatusArray != undefined ||
        this.listQuery.orderStatusArray != []
      ){
        this.statusStr = "";
      if (this.listQuery.timeArray && this.listQuery.timeArray.length === 2) {
        this.listQuery.start = this.listQuery.timeArray[0];
        this.listQuery.end = this.listQuery.timeArray[1];
      } else {
        this.listQuery.start = "";
        this.listQuery.end = "";
      }
      this.listQuery.page = 1;
      this.listLoading = true;
      for (var i = 0; i < this.listQuery.orderStatusArray.length; i++) {
        this.statusStr =
          this.statusStr + "&status=" + this.listQuery.orderStatusArray[i];
      }
      console.log(this.statusStr);
      const { data: res } = await this.$http
        .get(
          "/admin/toppings/list?page=" +
              this.listQuery.page +
              "&name="+
            this.listQuery.name +
            "&code=" +
            this.listQuery.code +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end +
            this.statusStr+
            "&order=asc"
        )
        // .get("/admin/order/list?orderStatusArray=101")
        .catch(() => {
          this.list = [];
          this.total = 0;
          this.listLoading = false;
        });
      this.list = res.data.list;
      this.total = res.data.total;
      this.listLoading = false;
      console.log(res);
      }else{
        const { data: res } = await this.$http
        .get("/admin/toppings/list?page=" + this.listQuery.page+"&order=asc")
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
      console.log(res.data);
      }
      
    },
    resetForm() {
      this.topping = {
        id: 0,
        code: "",
        name: "",
        price: "",
        picture: "",
        description: "",
        status: 0,
        createTime: null,
        updateTime: null,
        deleted: 0,
      };
    },

    uploadAvatar: function (response) {
      this.topping.picture = response.data.url;
    },
    hiddnForm() {
      this.isShowForm = false;
      this.getList();
    },
    showForm() {
      this.resetForm();
      this.isShowForm = true;
    },
    handleCreate(formName) {
      console.log(this.topping);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将创建新小料, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.topping);
              const { data: res } = await this.$http.post(
                "/admin/toppings/create",
                this.topping
              );
              console.log(res);
              this.getList();
              this.$message({
                type: "success",
                message: "创建成功!",
              });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "发生错误",
              });
            });

          this.isShowForm = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    showUpdate(row) {
      this.isShowUpdate = true;
      this.topping = row;
      console.log(row);
    },
    hiddnUpdateForm() {
      this.isShowUpdate = false;
      this.getList();
    },
    handleUpdate(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将修改该小料信息, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.topping);
              const { data: res } = await this.$http.post(
                "/admin/toppings/update",
                this.topping
              );
              console.log(res);
              this.getList();
              this.isShowUpdate = false;
              this.$message({
                type: "success",
                message: "修改成功!",
              });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "发生错误",
              });
              this.isShowUpdate = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    handleDelete(row) {
      console.log(row.id);
      this.$confirm("此操作将删除该小料, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/toppings/delete?id=" + row.id
          );
          this.getList();
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
          this.getList();
        });
    },
   async handleDownload() {
             const { data: res_excel } = await this.$http
        .get("/admin/toppings/getAllTopping")
        // console.log(res_excel)
        this.excelData = res_excel
      this.downloadLoading = true;
      // this.getAllList();
      import("@/vendor/Export2Excel").then((excel) => {
        const tHeader = [
          "ID",
          "小料编号",
          "小料图片",
          "小料价格",
          "小料描述",
          "小料状态",
        ];
        const filterVal = [
          "id",
          "code",
          "picture",
          "price",
          "description",
          "status",
        ];
        excel.export_json_to_excel2(tHeader, this.excelData, filterVal, "小料信息");
        this.downloadLoading = false;
      });
    },
      async  handleDownloadPage() {
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
          "/admin/toppings/list?name=" +
            this.listQuery.name +
            "&code=" +
            this.listQuery.code +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end +
            this.statusStr+
              "&limit=" +
              this.total+
            "&order=asc"
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
      import("@/vendor/Export2Excel").then((excel) => {
        const tHeader = [
          "ID",
          "小料编号",
          "小料图片",
          "小料价格",
          "小料描述",
          "小料状态",
        ];
        const filterVal = [
          "id",
          "code",
          "picture",
          "price",
          "description",
          "status",
        ];
        excel.export_json_to_excel2(tHeader, this.listAll, filterVal, "小料信息");
        this.downloadLoading = false;
      });
    },
  },
};
</script>
