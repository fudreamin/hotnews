<template>
    <div class="container">
        <dv-border-box-1>
            <div class="left_box">
                <dv-border-box-10  class="left_box1">
                    <span>新闻名</span>
                </dv-border-box-10>
                <dv-border-box-10 class="left_box2">
                    <span>新闻内容</span>
                </dv-border-box-10>
            </div>
            <dv-border-box-3 class="medium_box">
                <div class="chart" id="myChart" :style="{width: '80%', height: '90%'}"></div>
                <div class="chart_name">新闻热度走势（万）</div>
            </dv-border-box-3>
            <dv-border-box-3  class="right_box">
                <span>新闻数据</span>
            </dv-border-box-3>
            <dv-border-box-6 :color="['red', 'blue']" class="bottom_box">
                <dv-border-box-7 backgroundColor="rgba(0,0,255,0.1)"  class="bottom_box_chart">
                    <dv-decoration-7 style="width:50%;height:20%;">当前热度</dv-decoration-7>
                    <dv-decoration-9 style="width:40%;height:65%;color: aqua">65%</dv-decoration-9>
                </dv-border-box-7>
                <dv-border-box-7 backgroundColor="rgba(0,0,255,0.1)"  class="bottom_box_chart">
                    <dv-decoration-7 style="width:50%;height:20%;">广告可能性</dv-decoration-7>
                    <dv-decoration-9 style="width:40%;height:65%;color: aqua">65%</dv-decoration-9>
                </dv-border-box-7>
                <dv-border-box-7 backgroundColor="rgba(0,0,255,0.1)"  class="bottom_box_chart">
                    <dv-decoration-7 style="width:50%;height:20%;">被封可能性</dv-decoration-7>
                    <dv-decoration-9 style="width:40%;height:65%;color: aqua">65%</dv-decoration-9>
                </dv-border-box-7>
            </dv-border-box-6>
        </dv-border-box-1>
    </div>
</template>

<script>
    export default {
        name: "VisualData2",
        // data() {
        //     return {
        //
        //     }
        // },
        mounted() {
            this.drawLine();
        },
        methods: {
            drawLine() {
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myChart'), 'light')
                // 绘制图表
                myChart.setOption({
                    title: {
                        // text: '新闻热度走势',
                    },
                    tooltip: {},
                    xAxis: {
                        data: ["05-17", "05-18", "05-19", "05-20", "05-21", "05-22"],
                        //设置轴线的属性
                        axisLine: {
                            lineStyle: {
                                color: '#00ffff',
                            }
                        }
                    },
                    // legend: {
                    //     data: ['热度(万)']
                    // },
                    yAxis: {
                        //设置轴线的属性
                        axisLine: {
                            lineStyle: {
                                color: '#00ffff',
                            }
                        }

                    },
                    itemStyle: {

                        lineStyle: {        // 系列级个性化折线样式
                            width: 3,
                            type: 'solid',
                            color: '#00ffff'
                        }

                    },
                    series: [{
                        name: '热度(万)',
                        type: 'line',
                        data: [1188713, 1146156, 1102219, 1003153, 889266, 577562],
                        symbolSize: 5,
                        itemStyle: {
                            normal: {
                                borderColor: '#2BFFC2',  // 拐点边框颜色
                                lineStyle: {
                                    width: 2,  // 设置线宽
                                    type: 'solid',  //'dotted'虚线 'solid'实线
                                    color: '#00ffff'
                                }
                            }
                        }

                    }]
                });
            }
        }
    }
</script>

<style scoped>
    *{
        margin: 0 auto;
        color: white;
        text-align: center;
    }

    span{
        position: absolute;
        top:40%;
        left: 30%;
        text-align: center;

    }

    .container {
        width: 100%;
        height: 100%;
        background-image: url("../assets/bg.png");
        background-repeat: no-repeat;
        background-size: cover;
    }

    .left_box{
        float: left;
        width: 27%;
        height: 70%;
        margin: 0 auto;
        padding: 0;
    }

    .left_box1{
        width: 100%;
        height: 50%;
    }

    .left_box2{
        width: 100%;
        height: 50%;
    }

    .medium_box{
        float: left;
        width: 50%;
        height: 70%;
    }

    .bottom_box{
        float: left;
        width: 77%;
        height: 30%;
    }

    .right_box{
        float: right;
        width: 23%;
        height: 100%;
    }

    .chart_name {
        position: relative;
        bottom: 5%;
        text-align: center;
        color: aqua;
        font-size: 16px;
    }

    .bottom_box_chart{
        float: left;
        width: 31%;
        height: 100%;
        border-right: aliceblue solid 1px;
        margin-left: 1%;
        margin-right: 1%;
    }

</style>