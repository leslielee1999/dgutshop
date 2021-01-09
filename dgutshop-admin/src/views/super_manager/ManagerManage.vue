<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.name"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入管理员名字"
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

      <el-table-column align="center" label="管理员身份" prop="roleId" sortable>
        <template slot-scope="scope">
          <el-tag>{{ identifyAdmin[scope.row.roleId] }}</el-tag>
        </template>
      </el-table-column>

      <!-- <el-table-column align="center" label="管理员状态" prop="status" sortable>
        <template slot-scope="scope">
          <el-tag :type="scope.row.status ? 'NORMAL' : 'PROHIBIT'">{{
            scope.row.status ? "正常" : "冻结"
          }}</el-tag>
        </template>
      </el-table-column> -->

      <el-table-column align="center" label="管理员编号" prop="code" sortable />
      <el-table-column align="center" label="管理员名字" prop="name" sortable />
      <el-table-column
        align="center"
        label="管理员账号"
        prop="nickname"
        sortable
      />

      <el-table-column
        align="center"
        label="管理员头像"
        prop="picture"
        sortable
      >
        <template slot-scope="scope">
          <img v-if="scope.row.picture" :src="scope.row.picture" width="40" />
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

    <!-- 增加对话框 -->
    <el-dialog :visible.sync="isShowForm" title="增加管理员">
      <!-- <h3>增加管理员</h3> -->
      <el-form
        ref="admin"
        :rules="rules"
        :model="admin"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="管理员账号" prop="nickname">
          <el-input v-model="admin.nickname" />
        </el-form-item>
        <el-form-item label="管理员密码" prop="password">
          <el-input v-model="admin.password" auto-complete="off" />
        </el-form-item>

        <el-form-item label="管理员编号" prop="code">
          <el-input v-model="admin.code" />
        </el-form-item>
        <el-form-item label="管理员名字" prop="name">
          <el-input v-model="admin.name" />
        </el-form-item>
        <el-form-item label="身份" prop="roleId">
          <el-radio-group v-model="admin.roleId">
            <el-radio :label="1">超级管理员</el-radio>
            <el-radio :label="2">普通管理员</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="管理员头像" prop="avatar">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :with-credentials="true"
            :show-file-list="false"
            :on-success="uploadAvatar"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="admin.picture" :src="admin.picture" class="picture" />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="info" @click="hiddnForm()">取消</el-button>
        <el-button type="success" @click="handleCreate('admin')"
          >添加</el-button
        >
      </span>
    </el-dialog>

    <!-- 修改对话框 -->
    <el-dialog :visible.sync="isShowUpdate" title="修改管理员信息">
      <!-- <h3>修改管理员信息</h3> -->
      <br />
      <el-form
        ref="admin"
        :rules="rules"
        :model="admin"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="管理员账号" prop="nickname">
          <el-input v-model="admin.nickname" />
        </el-form-item>
        <!-- <el-form-item label="管理员ID" prop="roleId">
          <el-input v-model="admin.roleId" />
        </el-form-item> -->
        <el-form-item label="管理员编号" prop="code">
          <el-input v-model="admin.code" />
        </el-form-item>
        <el-form-item label="管理员名字" prop="name">
          <el-input v-model="admin.name" />
        </el-form-item>
        <el-form-item label="身份" prop="roleId">
          <el-radio-group v-model="admin.roleId">
            <el-radio :label="1">超级管理员</el-radio>
            <el-radio :label="2">普通管理员</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="管理员状态" prop="status" v-if="admin.roleId == 2">
          <el-radio-group v-model="admin.status">
            <el-radio :label="'PROHIBIT'">冻结</el-radio>
            <el-radio :label="'NORMAL'">正常</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="管理员头像" prop="picture">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :with-credentials="true"
            :show-file-list="false"
            :on-success="uploadAvatar"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="admin.picture" :src="admin.picture" class="picture" />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <br />
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="info" @click="hiddnUpdateForm()">取消</el-button>
        <el-button type="success" @click="handleUpdate('admin')"
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
// import { uploadPath } from '@/api/storage'
import Pagination from "@/components/Pagination"; // Secondary package based on el-pagination

export default {
  name: "ManagerManage",
  components: { Pagination },
  data() {
    return {
      listAll: [],
      excelData: [],
      token: "",
      // uploadPath:"/api/admin/storage/create",
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
        name: "",
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
      identifyAdmin: ["未知", "超级管理员", "普通管理员"],
      admin: {
        id: 0,
        roleId: 1,
        status: "NORMAL",
        name: "",
        nickname: "",
        password: "",
        code: "",
        picture: "",
        create_time: null,
        update_time: null,
        lastLoginIp: null,
        lastLoginTime: null,
        deleted: 0,
      },
      dialogFormVisible: false,
      dialogStatus: "",
      rules: {
        nickname: [
          { required: true, message: "管理员账号不能为空", trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符" },
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符" },
        ],
      },
      downloadLoading: false,
    };
  },
  //   computed: {
  //   headers() {
  //     return {
  //       'Leeks-Leesk-': this.$store.state.token
  //     }
  //   },
  // },
  created() {
    this.getList();
  },
  computed: {
    headers() {
      return { Authorization: this.token };
    },
  },
  methods: {
    //  async getAllList(){
    //  const { data: res_excel } = await this.$http
    //         .get("/admin/admin/getAllAdmin")

    //         this.excelData = res_excel
    //         console.log(this.excelData)
    //         },
    async getList() {
      this.token = this.$store.state.token;
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
          "/admin/admin/list?page=" +
            this.listQuery.page +
            "&name=" +
            this.listQuery.name +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end +
            "&order=asc"
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
    resetForm() {
      this.admin = {
        id: 0,
        roleId: 1,
        status:"NORMAL",
        name: "",
        nickname: "",
        password: "",
        code: "",
        picture: "",
        create_time: null,
        update_time: null,
        lastLoginIp: null,
        lastLoginTime: null,
        deleted: 0,
      };
    },

    uploadAvatar: function (response) {
      console.log(response);
      this.admin.picture = response.data.url;
    },
    hiddnForm() {
      this.isShowForm = false;
    },
    showForm() {
      this.resetForm();
      this.isShowForm = true;
    },
    handleCreate(formName) {
      console.log(this.admin);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将创建新管理员, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.admin);
              const { data: res } = await this.$http.post(
                "/admin/admin/create",
                this.admin
              );
              console.log(res);
              this.getList();
              if (res.errno == 0) {
                this.$message({
                  type: "success",
                  message: "创建成功!",
                });
              } else if (res.errno == 602) {
                this.$message({
                  type: "error",
                  message: "账号重复，创建失败!",
                });
              }
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
      this.admin = row;
      console.log(row);
    },
    hiddnUpdateForm() {
      this.isShowUpdate = false;
      this.getList();
    },
    handleUpdate(formName) {
      console.log(this.admin);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将修改该管理员信息, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.admin);
              const { data: res } = await this.$http.post(
                "/admin/admin/update",
                this.admin
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
      this.$confirm("此操作将删除该管理员, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/admin/delete?id=" + row.id
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
      const { data: res_excel } = await this.$http.get(
        "/admin/admin/getAllAdmin"
      );

      this.excelData = res_excel;
      this.downloadLoading = true;

      console.log(this.excelData);
      import("@/vendor/Export2Excel").then((excel) => {
        const tHeader = ["管理员编号", "管理员名字", "管理员身份"];
        const filterVal = ["code", "name", "roleId"];
        excel.export_json_to_excel2(
          tHeader,
          this.excelData,
          filterVal,
          "管理员信息"
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
          "/admin/admin/list?name=" +
            this.listQuery.name +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end +
            "&limit=" +
            this.total +
            "&order=asc"
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
      import("@/vendor/Export2Excel").then((excel) => {
        const tHeader = ["管理员编号", "管理员名字", "管理员身份"];
        const filterVal = ["code", "name", "roleId"];
        excel.export_json_to_excel2(
          tHeader,
          this.listAll,
          filterVal,
          "管理员信息"
        );
        this.downloadLoading = false;
      });
    },
  },
};
</script>
