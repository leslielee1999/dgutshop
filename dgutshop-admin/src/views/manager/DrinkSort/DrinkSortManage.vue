<template>
  <div class="box">
    <!-- <div style="width: 48%; margin: 10px; display: inline-block"> -->
    <el-card
      class="sort-card"
      style="width: 48%; margin: 10px; display: inline-block"
    >
      <div style="height: 100%; overflow: auto">
        <div slot="header" class="clearfix">
          <span style="font-weight: 700; margin-left: 20px"
            >小程序侧边栏分类管理</span
          >
        </div>
        <!-- <el-table
        :row-class-name="tableRowClassName"
        :data="rooms"
        v-loading="roomsLoading"
        
        :show-header="false"
        max-height="350"
      >       -->
        <!-- 查询和其他操作 -->
        <div class="filter-container">
          <el-input
            v-model="listQuery.name"
            clearable
            class="filter-item"
            style="width: 200px"
            placeholder="请输入类目名字"
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
          <br />
          <br />
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
            class="filter-item"
            type="success"
            icon="el-icon-download"
            @click="handleDownloadPage"
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
          @expand-change="expandSelect"
          @row-click="getList_categoryItems"
          ref="refTable"
          :row-key="getRowKeys"
          :expand-row-keys="expands"
        >
          >

          <el-table-column type="expand" sortable>
            <el-button
              v-loading="listLoading"
              class="filter-item"
              type="success"
              icon="el-icon-edit"
              @click="changeSortItem"
              >改变类目单项在类目的顺序</el-button
            >
            <el-table
              height="350"
              :data="list_categoryItems"
              v-loading="listLoading"
              element-loading-text="正在查询中。。。"
              border
              fit
              highlight-current-row
              row-key="id"
            >
              <el-table-column align="center" label="ID" prop="id" />
              <el-table-column
                align="center"
                label="奶茶编号"
                prop="product.code"
              />

              <el-table-column
                align="center"
                min-width="100"
                label="名称"
                prop="product.name"
              />

              <el-table-column
                align="center"
                property="product.picture"
                label="图片"
              >
                <template slot-scope="scope">
                  <img :src="scope.row.product.picture" width="40" />
                </template>
              </el-table-column>

              <el-table-column
                align="center"
                label="大杯原售价"
                prop="product.bigOriginalPrice"
              />
              <el-table-column
                align="center"
                label="大杯现售价"
                prop="product.bigPromotePrice"
              />
              <el-table-column
                align="center"
                label="中杯原售价"
                prop="product.mediumOriginalPrice"
              />
              <el-table-column
                align="center"
                label="中杯现售价"
                prop="product.mediumPromotePrice"
              />
              <el-table-column
                align="center"
                label="销量"
                prop="product.sales"
              />
              <!-- <el-table-column
              sortable
              align="center"
              label="描述"
               min-width="255%"
              prop="product.description"
            /> -->

              <el-table-column
                align="center"
                label="奶茶状态"
                prop="product.status"
              >
                <template slot-scope="scope">
                  <el-tag :type="scope.row.product.status ? 'true' : 'false'">{{
                    scope.row.product.status ? "上架中" : "已下架"
                  }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column
                sortable
                align="center"
                label="在类目中的顺序"
                prop="index"
              />
              <el-table-column
                fixed="right"
                align="center"
                label="操作"
                width="200"
                class-name="small-padding fixed-width"
              >
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    :disabled="scope.$index === 0"
                    @click="moveUpItem(scope.$index, scope.row)"
                    ><i class="el-icon-arrow-up"></i
                  ></el-button>
                  <el-button
                    size="mini"
                    :disabled="scope.$index === list.length - 1"
                    @click="moveDownItem(scope.$index, scope.row)"
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
                    @click="handleDeleteItem(scope.row, scope.$index)"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </el-table-column>

          <!-- <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row row-key="id"> -->

          <el-table-column align="center" label="类目ID" prop="id" />

          <el-table-column align="center" label="类目名" prop="name" />
          <el-table-column
            align="center"
            label="在小程序的位置顺序"
            prop="index"
            sortable
          />

          <el-table-column align="center" property="icon" label="类目图标">
            <template slot-scope="scope">
              <img v-if="scope.row.icon" :src="scope.row.icon" width="40" />
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
                type="primary"
                size="mini"
                @click="showUpdate(scope.row)"
                >编辑</el-button
              >
              <el-button
                type="danger"
                size="mini"
                @click.stop="handleDelete(scope.row)"
                >删除</el-button
              >
              <br />
              <br />
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
      </div>
    </el-card>

    <el-card style="width: 48%; margin: 10px; display: inline-block">
      <div style="height: 100%; overflow: auto">
        <div slot="header" class="clearfix">
          <span style="font-weight: 700; margin-left: 20px"
            >侧边栏分类分配奶茶</span
          >
        </div>
        <br />
        <el-card style="width: 80%">
          <span style="font-weight: 700; margin-left: 0px">选择侧边栏类目</span>
          <br />
          <br />
          <el-select v-model="value" clearable placeholder="请选择">
            <el-option
              v-for="(item, index) in category"
              :key="index"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
          <el-button
            type="primary"
            style="font-weight: 700; margin-left: 10px"
            @click="selectCategoryItem"
            >选中</el-button
          >
        </el-card>

        <el-divider></el-divider>
        <!-- <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
      > -->
        <el-card style="width: 80%">
          <span style="font-weight: 700; margin-left: 0px"
            >为该类目选择类目单项</span
          >
        </el-card>
        <br />
        <el-table
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          border
          fit
          highlight-current-row
          row-key="id"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55" sortable>
          </el-table-column>
          <el-table-column align="center" label="奶茶ID" prop="id" sortable />
          <el-table-column
            align="center"
            label="奶茶编号"
            prop="code"
            sortable
          />

          <el-table-column
            align="center"
            min-width="100"
            label="名称"
            prop="name"
            sortable
          />

          <el-table-column
            align="center"
            property="picture"
            label="图片"
            sortable
          >
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
          <!-- <el-table-column align="center" label="描述" prop="description" sortable min-width="255%"/> -->

          <el-table-column
            align="center"
            label="奶茶状态"
            prop="status"
            sortable
          >
            <template slot-scope="scope">
              <el-tag :type="scope.row.status ? 'true' : 'false'">{{
                scope.row.status ? "上架中" : "已下架"
              }}</el-tag>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="totalDrink > 0"
          :total="totalDrink"
          :page.sync="listDrinkQuery.page"
          :limit.sync="listDrinkQuery.limit"
          @pagination="getDrinkList"
        />
      </div>
    </el-card>
    <el-dialog :visible.sync="isShowForm" title="增加类目">
      <!-- <h3>增加类目</h3> -->
      <el-form
        ref="categoryList"
        :rules="rules"
        :model="categoryList"
        label-width="150px"
      >
        <el-form-item label="类目名字" prop="name">
          <el-input v-model="categoryList.name" />
        </el-form-item>

        <el-form-item label="类目图片">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img
              v-if="categoryList.icon"
              :src="categoryList.icon"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="info" @click="hiddnForm()">取消</el-button>
        <el-button type="success" @click="handleCreate('categoryList')"
          >添加</el-button
        >
      </span>
    </el-dialog>

    <el-dialog :visible.sync="isShowUpdate" title="修改类目图标">
      <!-- <h3>修改类目图标</h3> -->
      <br />
      <el-form
        ref="categoryList"
        :rules="rules"
        :model="categoryList"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left: 50px"
      >
        <el-form-item label="类目名" prop="name">
          <el-input v-model="categoryList.name" />
        </el-form-item>
        <el-form-item label="类目图片" prop="icon">
          <el-upload
            :headers="headers"
            action="/api/admin/storage/create"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img
              v-if="categoryList.icon"
              :src="categoryList.icon"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>

        <br />
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="info" @click="hiddnUpdateForm()">取消</el-button>
        <el-button type="success" @click="handleUpdate('categoryList')"
          >修改</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from "@/components/Pagination";
export default {
  name: "DrinkSortManage",
  components: { Pagination },
  data() {
    return {
      excelData: [],
      token: "",
      expands: [],
      getRowKeys(row) {
        return row.id;
      },
      downloadLoading: false,
      isShowUpdate: false,
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
        timeArray: [],
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
      listDrinkQuery: {
        page: 1,
        limit: 10,
        id: undefined,
        code: undefined,
        name: undefined,
        // sort: "add_time",
        // order: "desc",
      },
      categoryList: {
        id: 0,
        index: 0,
        name: "",
        icon: "",
        create_time: null,
        update_time: null,
        deleted: 0,
      },
      dialogFormVisible: false,
      dialogStatus: "",
      textMap: {
        update: "编辑",
        create: "创建",
      },
      rules: {
        name: [{ required: true, message: "类目名不能为空", trigger: "blur" }],
      },

      //下拉框的数据
      options: [],
      value: "",

      //右边表的数据
      tableData: [],
      multipleSelection: [],
    };
  },
  computed: {
    headers() {
      return { Authorization: this.token };
    },
  },
  created() {
    this.getList();
    this.getDrinkList();
    this.getAllList();
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

    expandSelect: function (row, expandedRows) {
      if (expandedRows.length) {
        this.expands = [];
        if (row) {
          this.expands.push(row.id);
        }
      } else {
        this.expands = [];
      }
      console.log(row.categoryItems);
      this.getList();
      this.list_categoryItems = row.categoryItems;
      this.list_categoryItems = this.fastSortItem(this.list_categoryItems);
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
    fastSortItem(arr) {
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

      return this.fastSort(left).concat(item, this.fastSort(right));
    },

    async getAllList() {
      const { data: res_excel } = await this.$http.get(
        "/admin/category/getAllCategory"
      );
      // console.log(res_excel)
      this.excelData = res_excel;
      for (var i = 0; i < res_excel.length; i++) {
        this.category.push(res_excel[i]);
      }
    },

    async getList() {
      this.token = this.$store.state.token;
      // this.category = [];
      this.listLoading = true;
      // this.getAllList();

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
          "/admin/category/list?page=" +
              this.listQuery.page +
              "&name="+
            this.listQuery.name +
            "&start=" +
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
      this.list = this.fastSort(this.list);
      this.total = res.data.total;
      this.listLoading = false;
      console.log(res.data);
    },
    async getDrinkList() {
      this.listLoading = true;
      const { data: res } = await this.$http
        .get("/admin/category/getProducts?page=" + this.listDrinkQuery.page+"&order=asc")
        .catch(() => {
          this.totalDrink = [];
          this.total = 0;
          this.listLoading = false;
        });
      console.log(res);
      this.tableData = res.data.list;
      this.totalDrink = res.data.total;
      this.listLoading = false;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    selectCategoryItem() {
      this.idsSelected = [];
      this.categorySelected = [];
      this.categorySelected = this.value;
      for (var i = 0; i < this.multipleSelection.length; i++) {
        this.idsSelected.push(this.multipleSelection[i].id);
      }

      this.$confirm("此操作将为该类目分配类目单项, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/category/categoryitem/create",
            {
              cid: this.categorySelected,
              pids: this.idsSelected,
            }
          );
          console.log(res);

          if (res.errno === 0) {
            this.$message({
              type: "success",
              message: "创建成功!",
            });
          }
          if (res.errno == 111) {
            this.$message({
              type: "error",
              message: "请删除已存在的分类单项!",
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
        });
      this.getAllList();
      this.getList();
      // this.getList_categoryItems();
    },

    changeSort() {
      console.log(this.list);
      this.$confirm("此操作将修改该小程序侧边栏的顺序, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          console.log(this.list);
          const { data: res } = await this.$http.post(
            "/admin/category/update",
            this.list
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
      // this.isShowUpdate = false;
    },
    changeSortItem() {
      console.log(this.list_categoryItems);
      this.$confirm("此操作将修改该类目的单项顺序, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          console.log(this.list_categoryItems);
          const { data: res } = await this.$http.post(
            "/admin/category/categoryitem/update",
            this.list_categoryItems
          );
          // this.getList_categoryItems();
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
      // this.isShowUpdate = false;
    },
    getList_categoryItems(row, index, e) {
      console.log(row.categoryItems);
      this.getList();
      this.$refs.refTable.toggleRowExpansion(row);
      this.list_categoryItems = row.categoryItems;
      this.list_categoryItems = this.fastSortItem(this.list_categoryItems);
    },

    uploadPicUrl: function (response) {
      console.log(response);
      this.categoryList.icon = response.data.url;
    },
    onLevelChange: function (value) {
      // if (value === 'L1') {
      //   this.dataForm.pid = 0
      // }
    },
    resetForm() {
      this.categoryList = {
        id: 0,
        index: 0,
        name: "",
        icon: "",
        create_time: null,
        update_time: null,
        deleted: 0,
      };
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
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将创建新类目, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              const { data: res } = await this.$http.post(
                "/admin/category/create",
                this.categoryList
              );
              console.log(res);
              this.getList();
              this.getAllList();
              // this.getList_categoryItems();
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
      this.categoryList = row;
      console.log(row);
    },
    hiddnUpdateForm() {
      this.isShowUpdate = false;
      this.getList();
    },
    handleUpdate(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm("此操作将修改该类目图标, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
            center: true,
          })
            .then(async () => {
              console.log(this.drink);
              const { data: res } = await this.$http.post(
                "/admin/category/updateOne",
                this.categoryList
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
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    uploadPicUrl: function (response) {
      this.categoryList.icon = response.data.url;
    },
    hiddnForm() {
      this.isShowUpdate = false;
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
      this.$confirm("此操作将删除该类目, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/category/delete?id=" + row.id
          );
          this.getList();
          this.getAllList();
          // this.getList_categoryItems();
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

          // this.getList_categoryItems();
        });
      this.getAllList();
      this.getList();
    },

    handleDeleteItem(row, index) {
      console.log(row.id);
      this.$confirm("此操作将删除该类目单项, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      })
        .then(async () => {
          const { data: res } = await this.$http.post(
            "/admin/category/categoryitem/delete?id=" + row.id
          );
          // this.getList_categoryItems();
          console.log(res);
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          this.list_categoryItems.splice(index, 1);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消",
          });
          this.getList();
          // this.getList_categoryItems();
        });
    },
    handleDownload() {
      this.downloadLoading = true;

      import("@/vendor/Export2Excel").then((excel) => {
        const tHeader = ["类目ID", "类目名", "在小程序的位置顺序", "类目图标"];
        const filterVal = ["id", "name", "index", "icon"];
        excel.export_json_to_excel2(
          tHeader,
          this.excelData,
          filterVal,
          "类目信息"
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
          "/admin/category/list?name=" +
            this.listQuery.name +
            "&start=" +
            this.listQuery.start +
            "&end=" +
            this.listQuery.end+"&limit=" +
              this.total
        )
        .catch(() => {
          this.list = [];
          this.total = 0;
          this.listLoading = false;
        });
      this.listAll = res.data.list;
      this.listAll = this.fastSort(this.listAll);
      this.total = res.data.total;
      this.listLoading = false;
      console.log(res.data);



      this.downloadLoading = true;
      import("@/vendor/Export2Excel").then((excel) => {
        const tHeader = ["类目ID", "类目名", "在小程序的位置顺序", "类目图标"];
        const filterVal = ["id", "name", "index", "icon"];
        excel.export_json_to_excel2(tHeader, this.listAll, filterVal, "类目信息");
        this.downloadLoading = false;
      });
    },
  },
};
</script>

<style scoped>
.box {
  display: flex;
  justify-content: space-between;
}
</style>