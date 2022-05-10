<template>
  <div class="content">
    <div class="filter-container">
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
        @click="handleFilter"
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
        icon="el-icon-edit"
        @click="changeSort"
        >改变类目在侧边栏的顺序</el-button
      >
      <el-button
        :loading="downloadLoading"
        class="filter-item"
        type="success"
        icon="el-icon-download"
        @click="handleDownload"
        >导出</el-button
      >
    </div>
    <br />
    <br />
    <!-- 查询结果 -->
    <el-table
      :data="list"
      element-loading-text="正在查询中。。。"
      border
      fit
      highlight-current-row
      ref="refTable"
      :row-key="getRowKeys"
      :expand-row-keys="expands"
    >
      >

      <!-- <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row row-key="id"> -->

      <el-table-column align="center" label="轮播图ID" prop="id" />

      <el-table-column
        align="center"
        label="在小程序的位置顺序"
        prop="index"
        sortable
      />

      <el-table-column align="center" label="在小程序的页面">
        <template slot-scope="scope">
          {{scope.row.page==0?"首页":"点餐页"}}
        </template>
      </el-table-column>

      <el-table-column align="center" property="picture" label="轮播图片">
        <template slot-scope="scope">
          <img v-if="scope.row.picture" :src="scope.row.picture" width="40" />
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        label="操作"
        width="200"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            :disabled="scope.$index === 0"
            @click.stop="moveUp(scope.$index, scope.row)"
            ><i class="el-icon-arrow-up"></i
          ></el-button>
          <el-button
            size="mini"
            :disabled="scope.$index === list.length - 1"
            @click.stop="moveDown(scope.$index, scope.row)"
            ><i class="el-icon-arrow-down"></i
          ></el-button>
          <!-- <el-button
              type="primary"
              size="mini"
              @click="handleUpdate(scope.row)"
              >编辑</el-button
            > -->
          <el-button
            type="danger"
            size="mini"
            @click.stop="handleDelete(scope.row)"
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

    <el-dialog :visible.sync="isShowForm" title="增加轮播图">
      <!-- <h3>增加轮播图</h3> -->
      <el-form
        ref="slideList"
        :rules="rules"
        :model="slideList"
        label-width="150px"
      >
        <el-form-item label="轮播图片">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img
              v-if="slideList.picture"
              :src="slideList.picture"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>

        <el-form-item label="页面">
          <el-select
            style="width: 200px"
            class="filter-item"
            placeholder="请选择页面"
            v-model="slideList.page"
          >
            <el-option
              v-for="(value, index) in pages"
              :key="value"
              :label="value"
              :value="index"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="info" @click="hiddnForm()">取消</el-button>
        <el-button type="success" @click="handleCreate('slideList')"
          >添加</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from "@/components/Pagination";
export default {
  name: "SlideShowManage",
  components: { Pagination },
  data() {
    return {
      token: "",
      expands: [],
      getRowKeys(row) {
        return row.id;
      },
      downloadLoading: false,
      isShowForm: false,
      list: [],
      multipleSelection: [],
      categorySelected: 0,
      idsSelected: [],
      category: [],
      list_categoryItems: [],
      total: 0,
      totalDrink: 0,
      listLoading: true,
      catL1: {},
      listQuery: {
        page: 1,
        limit: 10,
        id: "",
        code: "",
        name: "",
        timeArray: []
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
      listDrinkQuery: {
        page: 1,
        limit: 10,
        id: undefined,
        code: undefined,
        name: undefined
        // sort: "add_time",
        // order: "desc",
      },
      slideList: {
        id: 0,
        index: 0,
        picture: "",
        page: ""
      },
      dialogFormVisible: false,
      dialogStatus: "",
      textMap: {
        update: "编辑",
        create: "创建"
      },
      rules: {
        name: [{ required: true, message: "类目名不能为空", trigger: "blur" }]
      },

      //下拉框的数据
      options: [],
      value: "",

      //右边表的数据
      tableData: [],
      multipleSelection: [],

      // 轮播图页面
      pages: ["首页", "点餐页"]
    };
  },
  computed: {
    headers() {
      return { Authorization: this.token };
    }
  },
  created() {
    this.getList();
  },
  methods: {
    //上移
    moveUp(index, row) {
      var that = this;
      // console.log('上移',  row);

      // console.log(that.list[index]);
      if (index > 0) {
        let upDate = that.list[index - 1];
        that.list.splice(index - 1, 1);
        that.list.splice(index, 0, upDate);

        row.index = index;

        for (var i = 0; i < index; i++) {
          that.list[i].index = i;
        }
        for (var i = index; i < that.total; i++) {
          that.list[i].index = i;
        }
      } else {
        alert("已经是第一条，不可上移");
      }
    },
    //上移
    moveUpItem(index, row) {
      var that = this;
      // console.log('上移',  row);

      // console.log(that.list[index]);
      if (index > 0) {
        let upDate = that.list_categoryItems[index - 1];
        that.list_categoryItems.splice(index - 1, 1);
        that.list_categoryItems.splice(index, 0, upDate);

        row.index = index;

        for (var i = 0; i < index; i++) {
          that.list_categoryItems[i].index = i;
        }
        for (var i = index; i < that.list_categoryItems.length; i++) {
          that.list_categoryItems[i].index = i;
        }
      } else {
        alert("已经是第一条，不可上移");
      }
    },
    //下移
    moveDown(index, row) {
      var that = this;
      console.log("下移", index, row);
      if (index + 1 === that.list.length) {
        alert("已经是最后一条，不可下移");
      } else {
        console.log(index);
        // 保存下一条数据
        let downDate = that.list[index + 1];
        // 删除下一条数据
        that.list.splice(index + 1, 1);
        // 增添被删除的那一条数据
        that.list.splice(index, 0, downDate);
        row.index = index + 2;
        for (var i = 0; i < index; i++) {
          that.list[i].index = i;
        }
        for (var i = index; i < that.total; i++) {
          that.list[i].index = i;
        }
      }
    },
    //下移
    moveDownItem(index, row) {
      var that = this;
      console.log("下移", index, row);
      if (index + 1 === that.list_categoryItems.length) {
        alert("已经是最后一条，不可下移");
      } else {
        console.log(index);
        // 保存下一条数据
        let downDate = that.list_categoryItems[index + 1];
        // 删除下一条数据
        that.list_categoryItems.splice(index + 1, 1);
        // 增添被删除的那一条数据
        that.list_categoryItems.splice(index, 0, downDate);
        row.index = index + 2;
        for (var i = 0; i < index; i++) {
          that.list_categoryItems[i].index = i;
        }
        for (var i = index; i < that.total; i++) {
          that.list_categoryItems[i].index = i;
        }
      }
    },
    //TODO

    expandSelect: function(row, expandedRows) {
      if (expandedRows.length) {
        this.expands = [];
        if (row) {
          this.expands.push(row.id);
        }
      } else {
        this.expands = [];
      }
      // console.log(row.categoryItems);
      this.getList();
      this.list_categoryItems = row.categoryItems;
    },
    fastSort(arr) {
      //  console.log(arr)
      if (arr <= 1) {
        return arr;
      }
      var index = parseInt(arr.length / 2);
      var item = arr.splice(index, 1);
      var left = [];
      var right = [];
      for (var i = 0; i < arr.length; i++) {
        if (arr[i].index < item[0].index) {
          left.push(arr[i]);
        }
        if (arr[i].index > item[0].index) {
          right.push(arr[i]);
        }
      }
      // console.log(left)
      // console.log(right)
      // console.log(item)
      return this.fastSort(left).concat(item, this.fastSort(right));
    },
    async getList() {
      this.token = this.$store.state.token;
      this.category = [];
      this.listLoading = true;
      const { data: res } = await this.$http
        .get("/admin/slideshow/list?page=" + this.listQuery.page)
        .catch(() => {
          this.list = [];
          this.total = 0;
          this.listLoading = false;
        });

      this.list = res.data.list;
      console.log("11111",this.list);
      this.list = this.fastSort(this.list);

      this.total = res.data.total;
      this.listLoading = false;
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },

    changeSort() {
      console.log(this.list);
      this.$confirm("此操作将修改轮播图的顺序, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(async () => {
          console.log(this.list);
          const { data: res } = await this.$http.post(
            "/admin/slideshow/update",
            this.list
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

    onLevelChange: function(value) {
      // if (value === 'L1') {
      //   this.dataForm.pid = 0
      // }
    },
    resetForm() {
      this.slideList = {
        id: 0,
        index: 0,
        picture: "",
        page: ""
      };
    },
    hiddnForm() {
      this.isShowForm = false;
    },
    showForm() {
      this.resetForm();
      this.isShowForm = true;
    },
    handleCreate(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (!this.slideList.picture) {
            this.$message({
              type: "info",
              message: "请添加一张图片"
            });
            return;
          } else if (!this.slideList.page) {
            this.$message({
              type: "info",
              message: "请选择一个页面"
            });
            return;
          }
          this.$confirm("此操作将创建新轮播图, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true
          })
            .then(async () => {
              const { data: res } = await this.$http.post(
                "/admin/slideshow/create",
                this.slideList
              );
              console.log(res);
              this.getList();
              // this.getList_categoryItems();
              this.$message({
                type: "success",
                message: "创建成功!"
              });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消"
              });
            });
          this.isShowForm = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async handleFilter() {
      this.listQuery.page = 1;
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
          "/admin/slideshow/list?start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end
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
    uploadPicUrl: function(response) {
      this.slideList.picture = response.data.url;
    },
    createData() {
      // this.$refs['dataForm'].validate(valid => {
      //   if (valid) {
      //     createCategory(this.dataForm)
      //       .then(response => {
      //         this.getList()
      //         // 更新L1目录
      //         this.getCatL1()
      //         this.dialogFormVisible = false
      //         this.$notify.success({
      //           title: '成功',
      //           message: '创建成功'
      //         })
      //       })
      //       .catch(response => {
      //         this.$notify.error({
      //           title: '失败',
      //           message: response.data.errmsg
      //         })
      //       })
      //   }
      // })
    },
    handleUpdate(row) {
      // this.dataForm = Object.assign({}, row)
      // this.dialogStatus = 'update'
      // this.dialogFormVisible = true
      // this.$nextTick(() => {
      //   this.$refs['dataForm'].clearValidate()
      // })
    },
    updateData() {
      // this.$refs['dataForm'].validate(valid => {
      //   if (valid) {
      //     updateCategory(this.dataForm)
      //       .then(() => {
      //         this.getList()
      //         // 更新L1目录
      //         this.getCatL1()
      //         this.dialogFormVisible = false
      //         this.$notify.success({
      //           title: '成功',
      //           message: '更新成功'
      //         })
      //       })
      //       .catch(response => {
      //         this.$notify.error({
      //           title: '失败',
      //           message: response.data.errmsg
      //         })
      //       })
      //   }
      // })
    },
    handleDelete(row) {
      console.log(row.id);
      this.$confirm("此操作将删除该轮播图, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/slideshow/delete?id=" + row.id
          );
          this.getList();
          // this.getList_categoryItems();
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

          // this.getList_categoryItems();
        });
      this.getList();
    },

    handleDownload() {
      this.downloadLoading = true;
      import("@/vendor/Export2Excel").then(excel => {
        const tHeader = ["轮播图ID", "在小程序的位置顺序", "轮播图片"];
        const filterVal = ["id", "index", "picture"];
        excel.export_json_to_excel2(
          tHeader,
          this.list,
          filterVal,
          "轮播图信息"
        );
        this.downloadLoading = false;
      });
    }
  }
};
</script>

<style scoped></style>
