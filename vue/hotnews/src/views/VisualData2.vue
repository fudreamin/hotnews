<template>
    <div class="container">
        <dv-border-box-1>
            <div class="left_box">
                <dv-border-box-10 class="left_box1">
                    <p style="color:#45BEFF">{{new Date().toLocaleDateString()}}</p>
                    <span>{{news.name}}</span>
                </dv-border-box-10>
                <dv-border-box-10 class="left_box2">
                    <span class="context_span">{{news.context}}</span>
                </dv-border-box-10>
            </div>
            <dv-border-box-3 class="medium_box">
                <div class="chart" id="myChart" :style="{width: '80%', height: '90%'}"></div>
                <div class="chart_name">新闻热度走势（万）</div>
            </dv-border-box-3>

            <dv-border-box-3 class="right_box" style="background-color: rgba(0,0,255,0.1)">
                <dv-decoration-1 style="width:60%;height:5%;margin-top: 5%"/>
                <div style="width:90%;height:100%;margin-top: 5%">
                    <dv-conical-column-chart :config="config" style="width:100%;height:85%;"/>
                </div>
                <dv-decoration-2 style="width:200px;height:5px;"/>
            </dv-border-box-3>

            <dv-border-box-6 :color="['red', 'blue']" class="bottom_box">
                <dv-border-box-7 backgroundColor="rgba(0,0,255,0.1)" class="bottom_box_chart">
                    <dv-decoration-7 style="width:50%;height:20%;">被封可能性</dv-decoration-7>
                    <dv-decoration-9 style="width:40%;height:65%;color: aqua">{{news.isBan}}%</dv-decoration-9>
                </dv-border-box-7>
                <dv-border-box-7 backgroundColor="rgba(0,0,255,0.1)" class="bottom_box_chart">
                    <dv-decoration-7 style="width:50%;height:20%;">热度指数</dv-decoration-7>
                    <dv-decoration-9 style="width:40%;height:65%;color: aqua">{{news.isHot}}</dv-decoration-9>
                </dv-border-box-7>
                <dv-border-box-7 backgroundColor="rgba(0,0,255,0.1)" class="bottom_box_chart">
                    <dv-decoration-7 style="width:50%;height:20%;">广告可能性</dv-decoration-7>
                    <dv-decoration-9 style="width:40%;height:65%;color: aqua">{{news.isAdvertisement}}%
                    </dv-decoration-9>
                </dv-border-box-7>
            </dv-border-box-6>
        </dv-border-box-1>
    </div>
</template>

<script>
    export default {
        name: "VisualData2",
        data() {
            return {
                news: {
                    id: 1,
                    name: '国家安全',
                    time: '2020-04-16 12:15:18',
                    heat: '11103',
                    type: '热',
                    address: 'https://element.eleme.cn/#/zh-CN/component/table',

                    context: '现场观众求婚迪丽热巴，不管出于什么原因，这种场合必定失败。不过汪涵反应速度真的很快了，幸好提前挡住了，要不然还不知道会发生什么 ​',
                    comment: 12546,
                    like: 124546,
                    forward: 45651,
                    isAdvertisement: 10,
                    isBan: 80,
                    isHot: 12315,

                },
                config: {
                    data: [
                        {
                            name: '点赞',
                            value: 123123
                        },
                        {
                            name: '评论',
                            value: 123123
                        },
                        {
                            name: '转发',
                            value: 123123
                        },
                        {
                            name: '热度',
                            value: 123123
                        }
                    ],
                    showValue: true,
                },
                heatlist: [1188713, 1146156, 1102219, 1003153, 889266, 577562, 895612, 759862, 156478, 425865],
                message: {},
            }
        },
        created() {
            this.$axios.get("http://39.107.143.213:8080/" + this.$route.query.source + "data" + "/findByName/" + this.$route.query.id).then(response => {
                this.heatlist = response.data;
                // alert(this.heatlist);
            });

        },
        async mounted() {
            await this.getData();
            this.drawLine();
            let data2 = [
                {
                    name: '点赞',
                    value: this.news.like
                },
                {
                    name: '评论',
                    value: this.news.comment
                },
                {
                    name: '转发',
                    value: this.news.forward
                },
                {
                    name: '热度',
                    value: this.news.heat
                }
            ];
            if (this.$route.query.source == "zhihu") {
                data2[2].name = "回答";
            }
            this.$set(this.config, 'data', data2);
            this.config = {...this.config}
        },
        methods: {
            async getData() {
                await this.$axios.get("http://39.107.143.213:8080/" + this.$route.query.source + "/findDataById/" + this.$route.query.id).then(response => {
                    this.news = response.data;
                });
                // await this.$axios.get("http://39.107.143.213:8080/" + this.$route.query.source + "/findDataById/" + this.$route.query.id).then(response => {
                //     this.message = response.data;
                // alert(this.message.conclusion);
                // });
                // alert(this.config.data[0].name);
            },
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
                        data: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
                        //设置轴线的属性
                        axisLine: {
                            lineStyle: {
                                color: '#2bb7ff',
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
                                color: '#2bb7ff',
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
                        data: this.heatlist,
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
            },
        }

    }
</script>

<style scoped>
    * {
        margin: 0 auto;
        padding: 0;
        color: white;
        text-align: center;
    }

    span {
        position: absolute;
        top: 30%;
        left: 50%;
        transform: translateY(-50%);
        transform: translateX(-50%);
        text-align: center;
        /*top:40%;*/
        /*left: 10%;*/
        line-height: 32px;
        color: aqua;
        font-size: 16px;
    }

    .context_span {
        position: absolute;
        top: 10%;
        left: 15%;
        transform: translateY(-10%);
        transform: translateX(-10%);
        text-align: center;
        line-height: 32px;
        color: aqua;
        font-size: 16px;
    }

    .container {
        width: 100%;
        height: 100%;
        background-image: url("../assets/bg.png");
        background-repeat: no-repeat;
        background-size: cover;
    }

    .left_box {
        float: left;
        width: 27%;
        height: 70%;
        margin: 0 auto;
        padding: 0;

    }

    .left_box1 {
        width: 100%;
        height: 50%;
    }

    .left_box2 {
        width: 100%;
        height: 50%;
    }

    .medium_box {
        float: left;
        width: 50%;
        height: 70%;
    }

    .bottom_box {
        float: left;
        width: 77%;
        height: 30%;
    }

    .right_box {
        float: right;
        width: 23%;
        height: 100%;
    }

    .chart_name {
        position: relative;
        bottom: 5%;
        text-align: center;
        color: #45BEFF;
        font-size: 16px;
    }

    .bottom_box_chart {
        float: left;
        width: 31%;
        height: 100%;
        border-right: aliceblue solid 1px;
        margin-left: 1%;
        margin-right: 1%;
    }

</style>