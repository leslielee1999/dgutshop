<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.userId"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入用户的ID"
      />
      <el-input
        v-model="listQuery.userName"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入用户的姓名"
      />
      <el-input
        v-model="listQuery.phone"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入用户的联系方式"
      />
      <!-- <el-input
        v-model="listQuery.wechatId"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入用户的微信号"
      /> -->
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
    >
      <el-table-column align="center" label="ID" prop="id" sortable />
      <el-table-column align="center" label="用户ID" prop="userId" sortable />

      <el-table-column
        align="center"
        label="用户姓名"
        prop="userName"
        sortable
      />
      <el-table-column
        align="center"
        label="用户联系方式"
        prop="userPhone"
        sortable
      />

      <el-table-column
        align="center"
        label="用户地址"
        prop="userAddress"
        sortable
      />
      <el-table-column align="center" label="房间号" prop="userRoom" sortable />
      <el-table-column
        align="center"
        label="地址经度"
        prop="latitude"
        sortable
      />
      <el-table-column
        align="center"
        label="地址纬度"
        prop="longitude"
        sortable
      />

      <el-table-column
        align="center"
        label="地址状态"
        prop="isDefault"
        sortable
      >
        <template slot-scope="scope">
          <el-tag>{{ statusAddress[scope.row.isDefault] }}</el-tag>
        </template>
      </el-table-column>

      <!-- <el-table-column
        align="center"
        label="操作"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="showUpdate(scope.row)"
            >编辑</el-button
          >
        </template>
      </el-table-column> -->
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />
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
// import { uploadPath } from '@/api/storage'
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination

export default {
  name: "UserManage",
  components: { Pagination },
  data() {
    return {
      listAll: [],
      excelData: [],
      // uploadPath,
      isShowUpdate: false,
      isShowForm: false,
      list: [],
      total: 0,
      roleOptions: null,
      listLoading: true,
      listQuery: {
        timeArray: [],
        page: 1,
        limit: 10,
        userName: "",
        userId: "",
        wechatId: "",
        phone: "",
        sort: "add_time",
        order: "desc"
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
      statusAddress: ["默认地址", "非默认地址"],
      address: {
        id: 0,
        userId: 0,
        userName: "",
        userPhone: "",
        wechatId: "",
        userAddress: "",
        userRoom: "",
        create_time: null,
        update_time: null,
        isDefault: 0,
        deleted: 0
      },
      dialogFormVisible: false,
      dialogStatus: "",
      // rules: {
      //   username: [
      //     { required: true, message: "用户不能为空", trigger: "blur" },
      //     { min: 6, max: 20, message: "长度在 6 到 20 个字符" },
      //   ],
      //   password: [
      //     { required: true, message: "密码不能为空", trigger: "blur" },
      //     { min: 12, max: 20, message: "长度在 12 到 20 个字符" },
      //   ],
      // },
      downloadLoading: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //      async getAllList(){
    //  const { data: res_excel } = await this.$http
    //         .get("/admin/address/getAllAddress")
    //         // console.log(res_excel)
    //         this.excelData = res_excel
    //         },
    async getList() {
      this.listLoading = true;

      if (this.listQuery.timeArray && this.listQuery.timeArray.length === 2) {
        this.listQuery.start = this.listQuery.timeArray[0];
        this.listQuery.end = this.listQuery.timeArray[1];
      } else {
        this.listQuery.start = "";
        this.listQuery.end = "";
      }
      this.listLoading = true;
      const { data: res } = await this.$http
        .get(
          "/admin/address/list?page=" +
            this.listQuery.page +
            "&userName=" +
            this.listQuery.userName +
            "&wechatId=" +
            this.listQuery.wechatId +
            "&phone=" +
            this.listQuery.phone +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end +
            "&order=asc" +
            "&userId=" +
            this.listQuery.userId
        )
        .catch(() => {
          this.list = [];
          this.total = 0;
          this.listLoading = false;
        });
      this.list = res.data.list;
      this.total = res.data.total;
      this.listLoading = false;
      console.log(res.data);
    },
    handleReflash() {
      this.getList();
    },
    resetForm() {
      this.address = {
        id: 0,
        userId: 0,
        userName: "",
        userPhone: "",
        wechatId: "",
        userAddress: "",
        userRoom: "",
        create_time: null,
        update_time: null,
        isDefault: 0,
        deleted: 0
      };
    },

    // uploadAvatar: function(response) {
    //   this.dataForm.avatar = response.data.url
    // },
    hiddnForm() {
      this.isShowForm = false;
    },
    showUpdate(row) {
      this.isShowUpdate = true;
      this.user = row;
      console.log(row);
    },
    hiddnUpdateForm() {
      this.isShowUpdate = false;
    },
    handleUpdate(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$confirm("此操作将修改该用户状态, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true
          })
            .then(async () => {
              console.log(this.user);
              const { data: res } = await this.$http.post(
                "/admin/user/update",
                this.user
              );
              console.log(res);
              this.getList();
              this.isShowUpdate = false;
              this.$message({
                type: "success",
                message: "修改成功!"
              });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "发生错误"
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
      this.$confirm("此操作将删除该管理员, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/admin/delete?id=" + row.id
          );
          this.getList();
          this.$message({
            type: "success",
            message: "删除成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
          this.getList();
        });
    },
    async handleDownload() {
      const { data: res_excel } = await this.$http.get(
        "/admin/address/getAllAddress"
      );
      // console.log(res_excel)
      this.excelData = res_excel;
      this.downloadLoading = true;
      // this.getAllList();
      import("@/vendor/Export2Excel").then(excel => {
        const tHeader = [
          "ID",
          "用户ID",
          "用户姓名",
          "用户联系方式",
          "用户地址",
          "房间号",
          "地址状态"
        ];
        const filterVal = [
          "id",
          "userId",
          "userName",
          "userPhone",
          "userAddress",
          "userRoom",
          "isDefault"
        ];
        excel.export_json_to_excel2(
          tHeader,
          this.excelData,
          filterVal,
          "用户的地址信息"
        );
        this.downloadLoading = false;
      });
    },
    async handleDownloadPage() {
      if (this.listQuery.timeArray && this.listQuery.timeArray.length === 2) {
        this.listQuery.start = this.listQuery.timeArray[0];
        this.listQuery.end = this.listQuery.timeArray[1];
      } else {
        this.listQuery.start = "";
        this.listQuery.end = "";
      }
      this.listLoading = true;
      const { data: res } = await this.$http
        .get(
          "/admin/address/list?userName=" +
            this.listQuery.userName +
            "&wechatId=" +
            this.listQuery.wechatId +
            "&phone=" +
            this.listQuery.phone +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end +
            "&limit=" +
            this.total +
            "&order=asc" +
            "&userId=" +
            this.listQuery.userId
        )
        .catch(() => {
          this.list = [];
          this.total = 0;
          this.listLoading = false;
        });
      this.listAll = res.data.list;
      this.total = res.data.total;
      this.listLoading = false;
      console.log(res.data);

      this.downloadLoading = true;
      import("@/vendor/Export2Excel").then(excel => {
        const tHeader = [
          "ID",
          "用户ID",
          "用户姓名",
          "用户联系方式",
          "用户地址",
          "房间号",
          "地址状态"
        ];
        const filterVal = [
          "id",
          "userId",
          "userName",
          "userPhone",
          "userAddress",
          "userRoom",
          "isDefault"
        ];
        excel.export_json_to_excel2(
          tHeader,
          this.listAll,
          filterVal,
          "用户的地址信息"
        );
        this.downloadLoading = false;
      });
    }
  }
};
</script>
