<template>
  <div class="dashboard-editor-container">

    <el-row :gutter="40" class="panel-group">
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
          <div class="card-panel-icon-wrapper icon-people">
            <img src="../../assets/img/用户.png" width="60" />
            <!-- <svg-icon icon-class="peoples" class-name="card-panel-icon" /> -->
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">用户数量</div>
            <count-to :start-val="0" :end-val="userTotal" :duration="2600" class="card-panel-num"/>
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('messages')">
          <div class="card-panel-icon-wrapper icon-message">
            <img src="../../assets/img/奶茶.png" width="60" />
            <!-- <svg-icon icon-class="message" class-name="card-panel-icon" /> -->
          </div>
          <div class="card-panel-description">
            <div class="card-panel-text">奶茶数量</div>
            <count-to :start-val="0" :end-val="goodsTotal" :duration="3000" class="card-panel-num"/>
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('purchases')">
          <div class="card-panel-icon-wrapper icon-money">
            <img src="../../assets/img/珍珠.png" width="60" />
            <!-- <svg-icon icon-class="message" class-name="card-panel-icon" /> -->
          </div>
          <div class="card-panel-description">
            
            <div class="card-panel-text">小料数量</div>
            <count-to :start-val="0" :end-val="toppingsTotal" :duration="3200" class="card-panel-num"/>
          </div>
        </div>
      </el-col>
      <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
        <div class="card-panel" @click="handleSetLineChartData('shoppings')">
          <div class="card-panel-icon-wrapper icon-shoppingCard">
            <img src="../../assets/img/订单.png" width="60" />
            <!-- <svg-icon icon-class="money" class-name="card-panel-icon" /> -->
          </div>
          <div class="card-panel-description">
            
            <div class="card-panel-text">订单数量</div>
            <count-to :start-val="0" :end-val="orderTotal" :duration="3600" class="card-panel-num"/>
          </div>
        </div>
      </el-col>
    </el-row>
<br/>
    <div id="main" style="width: 100%; height: 600px"></div>
  </div>
</template>

<script>
// import { info } from '@/api/dashboard'
import CountTo from 'vue-count-to'

export default {
    components: {
    CountTo
  },
  data() {
    return {
            list: [null],
      option: {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
          },
        },
        legend: {
          data: ["待支付", "制作中", "待取货", "派送中", "已完成"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "value",
        },
        yAxis: {
          type: "category",
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
        },
        series: [
          {
            name: "待支付",
            type: "bar",
            stack: "总量",
            label: {
              show: false,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "制作中",
            type: "bar",
            stack: "总量",
            label: {
              show: false,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "待取货",
            type: "bar",
            stack: "总量",
            label: {
              show: false,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "派送中",
            type: "bar",
            stack: "总量",
            label: {
              show: false,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "已完成",
            type: "bar",
            stack: "总量",
            label: {
              show: false,
              position: "insideRight",
            },
            data: [],
          },
        ],
      },
      userTotal: 0,
      goodsTotal: 0,
      toppingsTotal: 0,
      orderTotal: 0
    }
  },
  created() {
    this.getList();
    // console.log(this.$store.state.user.roleId)
    // info().then(response => {
    // })
  },
  // methods: {
  //   // handleSetLineChartData(type) {
  //   //   this.$emit('handleSetLineChartData', type)
  //   // }
  // }
    methods: {
    async getList() {
      const resGetNum = await this.$http.get("/admin/stat/getNum");
      console.log(resGetNum)
      this.userTotal = resGetNum.data.data[0]
      this.goodsTotal = resGetNum.data.data[1]
      this.toppingsTotal = resGetNum.data.data[2]
      this.orderTotal = resGetNum.data.data[3]

      const res = await this.$http.get("/admin/stat/list").catch(() => {});
      this.list = res.data.data;
      for (var i = 0; i < this.option.series.length; i++) {
          for(var j=0;j<this.list.length;j++){
            //   console.log(this.option.series[i].data[j])
              this.option.series[i].data[j] = this.list[j][i]
          }
      }
      
      console.log(this.list);
      console.log(this.option.series);
      this.myEcharts()
    },
    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("main"));
      
      // 指定图表的配置项和数据
      // 使用刚指定的配置项和数据显示图表。
    //   console.log(this.option.series)
      myChart.setOption(this.option);
    },
  },
  watch: {
      //观察option的变化
      option: {
        handler(newVal, oldVal) {
          if (this.myChart) {
            if (newVal) {
              this.myChart.setOption(newVal);
            } else {
              this.myChart.setOption(oldVal);
            }
          } else {
            this.myEcharts();
          }
        },
        deep: true //对象内部属性的监听，关键。
      }
    },
  mounted() {
    //   this.getList();
    this.myEcharts();
  },
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

.panel-group {
  margin-top: 18px;

  .card-panel-col{
    margin-bottom: 32px;
  }
  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);
    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }
      .icon-people {
         background: #40c9c6;
      }
      .icon-message {
        background: #36a3f7;
      }
      .icon-money {
        background: #f4516c;
      }
      .icon-shoppingCard {
        background: #34bfa3
      }
    }
    .icon-people {
      color: #40c9c6;
    }
    .icon-message {
      color: #36a3f7;
    }
    .icon-money {
      color: #f4516c;
    }
    .icon-shoppingCard {
      color: #34bfa3
    }
    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }
    .card-panel-icon {
      float: left;
      font-size: 48px;
    }
    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;
      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }
      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}
</style>
