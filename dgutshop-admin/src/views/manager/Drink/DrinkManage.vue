<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input
        v-model="listQuery.name"
        clearable
        class="filter-item"
        style="width: 160px"
        placeholder="请输入商品名称"
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
        style="margin-left: 30px"
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
      <el-table-column align="center" label="奶茶编号" prop="code" sortable />
      <el-table-column
        sortable
        align="center"
        min-width="100"
        label="名称"
        prop="name"
      />
      <el-table-column align="center" property="picture" label="图片" sortable>
        <template slot-scope="scope">
          <img :src="scope.row.picture" width="40" />
        </template>
      </el-table-column>
      <el-table-column
        sortable
        align="center"
        label="大杯原售价"
        prop="bigOriginalPrice"
      />
      <el-table-column
        sortable
        align="center"
        label="大杯现售价"
        prop="bigPromotePrice"
      />
      <el-table-column
        sortable
        align="center"
        label="中杯原售价"
        prop="mediumOriginalPrice"
      />
      <el-table-column
        sortable
        align="center"
        label="中杯现售价"
        prop="mediumPromotePrice"
      />
      <el-table-column align="center" label="销量" prop="sales" sortable />
      <el-table-column
        min-width="150%"
        align="center"
        label="描述"
        prop="description"
        sortable
      />

      <el-table-column align="center" label="奶茶状态" prop="status" sortable>
        <template slot-scope="scope">
          <el-tag :type="scope.row.status ? 'true' : 'false'">{{
            scope.row.status ? "上架中" : "已下架"
          }}</el-tag>
        </template>
      </el-table-column>
            <el-table-column align="center" label="是否有热饮" prop="heated" sortable>
        <template slot-scope="scope">
          <el-tag :type="scope.row.heated ? 'true' : 'false'">{{
            scope.row.heated ? "有热饮" : "无热饮"
          }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        label="操作"
        width="200"
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

    <el-dialog :visible.sync="isShowForm" title="增加奶茶">
      <!-- <h3>增加奶茶</h3> -->
      <el-form ref="drink" :rules="rules" :model="drink" label-width="150px">
        <el-form-item label="奶茶编号" prop="code">
          <el-input v-model="drink.code" />
        </el-form-item>
        <el-form-item label="奶茶名称" prop="name">
          <el-input v-model="drink.name" />
        </el-form-item>
        <el-form-item label="奶茶中杯原价" prop="mediumOriginalPrice">
          <el-input v-model="drink.mediumOriginalPrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
                <el-form-item label="奶茶中杯折扣价" prop="bigPromotePrice">
          <el-input v-model="drink.mediumPromotePrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="奶茶大杯原价" prop="bigOriginalPrice">
          <el-input v-model="drink.bigOriginalPrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
                <el-form-item label="奶茶大杯折扣价" prop="bigOriginalPrice">
          <el-input v-model="drink.bigPromotePrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="奶茶描述" prop="description">
          <el-input v-model="drink.description" />
        </el-form-item>

        <el-form-item label="是否上架" prop="status">
          <el-radio-group v-model="drink.status">
            <el-radio :label="true">上架</el-radio>
            <el-radio :label="false">下架</el-radio>
          </el-radio-group>
        </el-form-item>
                        <el-form-item label="能否为热饮" prop="heated">
          <el-radio-group v-model="drink.heated">
            <el-radio :label="true">能</el-radio>
            <el-radio :label="false">否</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="奶茶图片" prop="picture">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="drink.picture" :src="drink.picture" class="avatar" />
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
    
        @click="handleCreate('drink')"
        >添加</el-button
      >
       </span>
    </el-dialog>

    <el-dialog :visible.sync="isShowUpdate"  title="修改奶茶">
      <!-- <h3>修改奶茶</h3> -->
      <el-form ref="drink" :rules="rules" :model="drink" label-width="150px">
        <el-form-item label="奶茶编号" prop="code">
          <el-input v-model="drink.code" />
        </el-form-item>
        <el-form-item label="奶茶名称" prop="name">
          <el-input v-model="drink.name" />
        </el-form-item>
        <el-form-item label="奶茶中杯原价" prop="mediumOriginalPrice">
          <el-input v-model="drink.mediumOriginalPrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
                <el-form-item label="奶茶中杯折扣价" prop="bigPromotePrice">
          <el-input v-model="drink.mediumPromotePrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="奶茶大杯原价" prop="bigOriginalPrice">
          <el-input v-model="drink.bigOriginalPrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
                <el-form-item label="奶茶大杯折扣价" prop="bigOriginalPrice">
          <el-input v-model="drink.bigPromotePrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="奶茶描述" prop="description">
          <el-input v-model="drink.description" />
        </el-form-item>
        <el-form-item label="奶茶图片" prop="picture">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="drink.picture" :src="drink.picture" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="是否上架" prop="status">
          <el-radio-group v-model="drink.status">
            <el-radio :label="true">上架</el-radio>
            <el-radio :label="false">下架</el-radio>
          </el-radio-group>
        </el-form-item>
                <el-form-item label="能否为热饮" prop="heated">
          <el-radio-group v-model="drink.heated">
            <el-radio :label="true">能</el-radio>
            <el-radio :label="false">否</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
      <el-button
        type="info"
        @click="hiddnUpdateForm()"
        >取消</el-button
      >
      <el-button
        type="success"
        @click="handleUpdate('drink')"
        >修改</el-button
      >
       </span>
    </el-dialog>

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
.table-expand {
  font-size: 0;
}
.table-expand label {
  width: 100px;
  color: #99a9bf;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
}
.gallery {
  width: 80px;
  margin-right: 10px;
}
.goods-detail-box img {
  width: 100%;
}
</style>

<script>
import Pagination from "@/components/Pagination";
export default {
  name: "DrinkManage",
  components: { Pagination },
  data() {
    return {
      listAll:[],
      drinkConfim:{},
      excelData:[],
      token:"",
      isShowUpdate: false,
      isShowForm: false,
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        timeArray: [],
        page: 1,
        limit: 10,
        id: "",
        code: "",
        name: "",
        // sort: "add_time",
        // order: "desc",
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
      drink: {
        id: 0,
        code: "",
        name: "",
        bigOriginalPrice: 0,
        bigPromotePrice: 0,
        mediumOriginalPrice: 0,
        mediumPromotePrice: 0,
        description: "",
        status: 1,
        picture: "",
        sales: 0,
        create_time: null,
        update_time: null,
        heated:0,
        deleted: 0,
      },
      goodsDetail: "",
      detailDialogVisible: false,
      downloadLoading: false,
      rules: {
        code: [
          { required: true, message: "奶茶编号不能为空", trigger: "blur" },
        ],
        name: [
          { required: true, message: "奶茶名称不能为空", trigger: "blur" },
        ],
        // mediumOriginalPrice: [
        //   { required: true, message: "中杯价格不能为空", trigger: "blur" },
        // ],
      },
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

    async getList() {
            this.token = this.$store.state.token;
      this.listLoading = true;

      // if (
      //   this.listQuery.name != "" ||
      //   this.listQuery.timeArray != null ||
      //   this.listQuery.timeArray != undefined ||
      //   this.listQuery.timeArray != []
      // ){
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
          "/admin/products/list?page=" +
              this.listQuery.page +
              "&name="+
            this.listQuery.name +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end+"&order=asc"
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
    //   } else {
    //      this.listLoading = true;
    //   const { data: res } = await this.$http
    //     .get(
    //       "/admin/products/list?page=" + this.listQuery.page + "&order=desc"
    //     )
    //     .catch(() => {
    //       this.list = [];
    //       this.total = 0;
    //       this.listLoading = false;
    //     });
    //  this.list = res.data.list;
    //     this.total = res.data.total;
    //     this.listLoading = false;
    //     this.listQuery.timeArray = [];
    //     this.listQuery.orderStatusArray = [];
    //     this.listAll = [];
    //   console.log(res.data);
    //   }
             

    },

    resetForm() {
      this.drink = {
        id: 0,
        code: "",
        name: "",
        bigOriginalPrice: 0,
        bigPromotePrice: 0,
        mediumOriginalPrice: 0,
        mediumPromotePrice: 0,
        description: "",
        status: 1,
        picture: "",
        sales: 0,
        create_time: null,
        update_time: null,
        deleted: 0,
      };
    },
    uploadPicUrl: function (response) {
      console.log(response);
      this.drink.picture = response.data.url;
    },
    hiddnForm() {
      this.isShowForm = false;
    },
    showForm() {
      this.resetForm();
      this.isShowForm = true;
    },
    handleCreate(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将创建新品奶茶, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.drink);
              const { data: user_res } = await this.$http.post(
                "/admin/products/create",
                this.drink
              );
              this.getList();
              this.$message({
                type: "success",
                message: "创建成功!",
              });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消",
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
      this.drink = row;
      this.drinkConfim = row;
      // console.log(row);
            console.log(this.drink.status)
      console.log(this.drinkConfim.status)
    },
    hiddnUpdateForm() {
      this.isShowUpdate = false;
      this.getList();
    },
    handleUpdate(formName) {
      console.log(this.drink.status)
      console.log(this.drinkConfim.status)
      this.$refs[formName].validate((valid) => {
        if (valid && this.drink.status!=false) {
          this.$confirm("此操作将修改该奶茶, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.drink);
              const { data: res } = await this.$http.post(
                "/admin/products/update",
                this.drink
              );
              
              if(res.errno===0){
              this.$message({
                type: "success",
                message: "修改成功!",
              });
              this.isShowUpdate = false;
              }

            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消",
              });
            });
          
        }
        else if(valid && this.drink.status==false) {
                    this.$confirm("您选中了下架功能，可能会对侧边栏单项造成影响, 是否继续修改奶茶信息?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.drink);
              const { data: res } = await this.$http.post(
                "/admin/products/update",
                this.drink
              );
              
              if(res.errno===0){
              this.$message({
                type: "success",
                message: "修改成功!",
              });
              this.isShowUpdate = false;
              }

            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消",
              });
            });
        }
        else {
          console.log("error submit!!");
          return false;
        }
      });
      
    },
    showDetail(detail) {
      this.goodsDetail = detail;
      this.detailDialogVisible = true;
    },
    handleDelete(row) {
      console.log(row.id);
      this.$confirm("此操作将删除该奶茶, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/products/delete?id=" + row.id
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
        .get("/admin/products/getAllProduct")
        // console.log(res_excel)
        this.excelData = res_excel
      this.downloadLoading = true;
          // this.getAllList();
      import("@/vendor/Export2Excel").then((excel) => {
        const tHeader = [
          "ID",
          "奶茶编号",
          "名称",
          "图片",
          "大杯原售价",
          "大杯现售价",
          "中杯原售价",
          "中杯现售价",
          "销量",
          "描述",
          "奶茶状态",
        ];
        const filterVal = [
          "id",
          "code",
          "name",
          "picture",
          "bigOriginalPrice",
          "bigPromotePrice",
          "mediumOriginalPrice",
          "mediumPromotePrice",
          "sales",
          "description",
          "status",
        ];
        excel.export_json_to_excel2(tHeader, this.excelData, filterVal, "奶茶信息");
        this.downloadLoading = false;
      });
    },
        async handleDownloadPage() {
          console.log(this.listQuery.timeArray);
      if (
        this.listQuery.name != "" ||
        this.listQuery.timeArray != null ||
        this.listQuery.timeArray != undefined ||
        this.listQuery.timeArray != [] 
      ) {
        const { data: res_all } = await this.$http
          .get(
          "/admin/products/list?name=" +
            this.listQuery.name +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end+
              "&limit=" +
              this.total+"&order=asc"
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
          "奶茶编号",
          "名称",
          "图片",
          "大杯原售价",
          "大杯现售价",
          "中杯原售价",
          "中杯现售价",
          "销量",
          "描述",
          "奶茶状态",
        ];
        const filterVal = [
          "id",
          "code",
          "name",
          "picture",
          "bigOriginalPrice",
          "bigPromotePrice",
          "mediumOriginalPrice",
          "mediumPromotePrice",
          "sales",
          "description",
          "status",
        ];
        excel.export_json_to_excel2(tHeader, this.listAll, filterVal, "奶茶信息");
        this.downloadLoading = false;
      });
    },
  },
};
</script>
