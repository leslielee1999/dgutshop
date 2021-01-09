<template>
  <div class="Echarts">
    <div id="main" style="width: 100%; height: 800px"></div>
  </div>
</template>
<script>
export default {
  name: "Echarts",
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
              show: true,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "制作中",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "待取货",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "派送中",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [],
          },
          {
            name: "已完成",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [],
          },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
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
};
</script>

<style>
</style>