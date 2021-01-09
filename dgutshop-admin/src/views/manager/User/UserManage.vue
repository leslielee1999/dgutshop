<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <!-- <el-input
        v-model="listQuery.name"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入用户的名字"
      /> -->
            <el-input
        v-model="listQuery.nickname"
        clearable
        class="filter-item"
        style="width: 200px"
        placeholder="请输入用户的账号"
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
      <el-table-column align="center" label="ID" prop="id" sortable/>


      <!-- <el-table-column align="center" label="用户姓名" prop="name" sortable/> -->
      <el-table-column align="center" label="openId" prop="openId" sortable/>
      <el-table-column align="center" label="微信昵称" prop="nickname" sortable/>
      <el-table-column align="center" label="用户性别" prop="gender" sortable>
        <template slot-scope="scope">
          <el-tag>{{ genderUser[scope.row.gender] }}</el-tag>
        </template>
      </el-table-column>    
        <el-table-column align="center" label="注册时间" prop="createTime" sortable/>
        
        <!-- <el-table-column align="center" label="用户微信号" prop="wechatId" sortable/> -->
        <el-table-column align="center" label="用户最近一次的登陆时间" prop="lastLoginTime" sortable/>
        <el-table-column align="center" label="用户最近一次的登陆IP" prop="lastLoginIp" sortable/>

      <el-table-column align="center" label="用户状态" prop="status" sortable>
        <template slot-scope="scope">
          <el-tag>{{ statusUser[scope.row.status] }}</el-tag>
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
          <!-- <el-button type="danger" size="mini" @click="handleDelete(scope.row)"
            >删除</el-button
          > -->
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


    <!-- 修改对话框 -->
    <el-dialog :visible.sync="isShowUpdate"  title="修改用户的状态">
      <!-- <h3>修改用户的状态</h3> -->
      <br />
      <el-form
        ref="user"
        :rules="rules"
        :model="user"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="用户状态" prop="status">
          <el-radio-group v-model="user.status">
            <el-radio :label="'0'">可用</el-radio>
            <el-radio :label="'1'">不可登陆</el-radio>
            <el-radio :label="'2'">不可交易</el-radio>
          </el-radio-group>
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
        @click="handleUpdate('user')"
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
  name: "UserManage",
  components: { Pagination },
  data() {
    return {
      listAll:[],
      excelData:[],
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
        name: "",
        nickname:"",
        wechatId:"",
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
      genderUser: ["未知", "男", "女"],
      statusUser: ["可用", "不可登陆", "不可交易"],
      user: {
        id: 0,
        name: "",
        nickname: "",
        password: "",
        gender: "",
        phone:"",
        wechatId:"",
        sessionKey:"",
        create_time: null,
        update_time: null,
        lastLoginIp: null,
        lastLoginTime: null,
        status:0,
        lastLoginIp:"",
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
          { min: 12, max: 20, message: "长度在 12 到 20 个字符" },
        ],
      },
      downloadLoading: false,
    };
  },
  created() {
    this.getList();

  },
  methods: {
    //     fastSort(arr) {
    //   //  console.log(arr)
    //   if (arr <= 1) {
    //     return arr;
    //   }
    //   var index = parseInt(arr.length / 2);
    //   var item = arr.splice(index, 1);
    //   var left = [];
    //   var right = [];
    //   for (var i = 0; i < arr.length; i++) {
    //     if (arr[i].id < item[0].id) {
    //       left.push(arr[i]);
    //     }
    //     if (arr[i].id > item[0].id) {
    //       right.push(arr[i]);
    //     }
    //   }
    //   // console.log(left)
    //   // console.log(right)
    //   // console.log(item)
    //   return this.fastSort(left).concat(item, this.fastSort(right));
    // },
//         async getAllList(){
// const { data: res_excel } = await this.$http
//         .get("/admin/user/getAllUser")
        
//         this.excelData = res_excel
//         console.log(this.excelData)
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
          "/admin/user/list?page=" +
              this.listQuery.page +
              "&name="+
            this.listQuery.name +
            "&nickname="+
            this.listQuery.nickname+
            "&wechatId="+
            this.listQuery.wechatId+
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end+
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
      this.user = {
        id: 0,
        name: "",
        nickname: "",
        password: "",
        gender: "",
        phone:"",
        wechatId:"",
        sessionKey:"",
        create_time: null,
        update_time: null,
        lastLoginIp: null,
        lastLoginTime: null,
        status:0,
        lastLoginIp:"",
        deleted: 0,
      };
    },

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
      this.getList()
    },
    handleUpdate(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将修改该用户状态, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
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
      this.downloadLoading = true
          // this.getAllList();
          const { data: res_excel } = await this.$http
        .get("/admin/user/getAllUser")
        
        this.excelData = res_excel
          console.log(this.excelData)
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['ID', '用户姓名', '用户账号','用户性别', '用户联系方式',  '用户最近一次的登陆时间', '用户最近一次的登陆IP','用户状态']
        const filterVal = ['id', 'name', 'nickname','gender', 'phone','lastLoginTime', 'lastLoginIp','status']
        excel.export_json_to_excel2(
          tHeader,
          this.excelData,
          filterVal,
          '用户信息'
        )
        this.downloadLoading = false
      })
    },
      async  handleDownloadPage() {
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
          "/admin/user/list?name=" +
            this.listQuery.name +
            "&nickname="+
            this.listQuery.nickname+
            "&wechatId="+
            this.listQuery.wechatId+
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end+"&limit=" +
              this.total+"&order=asc"
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





      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['ID', '用户姓名', '用户账号','用户性别', '用户联系方式', '用户最近一次的登陆时间', '用户最近一次的登陆IP','用户状态']
        const filterVal = ['id', 'name', 'nickname','gender', 'phone','lastLoginTime', 'lastLoginIp','status']
        excel.export_json_to_excel2(
          tHeader,
          this.listAll,
          filterVal,
          '用户信息'
        )
        this.downloadLoading = false
      })
    },
  },
};
</script>
