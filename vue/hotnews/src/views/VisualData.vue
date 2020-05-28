<template>
    <div class="main">
        <div id="contain">
            <header>{{title}}</header>
            <div id="text">{{name}}
                <div id="picture">新闻可视化分析</div>
<!--                <br/><br/><br/><br/><br/><br/><br/>-->
<!--                <br/><br/><br/><br/><br/><br/><br/>-->
<!--                <br/><br/><br/><br/><br/><br/><br/>-->
<!--                <br/><br/><br/><br/><br/><br/>-->
            </div>
            <div class="chart" id="myChart" :style="{width: '600px', height: '400px'}"></div>
            <div id="subtitle2">新闻热度走势实时</div>
            <div></div>
        </div>
        <!--        <div class="chart" id="myChart" :style="{width: '600px', height: '400px'}"></div>-->
    </div>
</template>
<script>
    export default {
        name: 'VisualData',
        data() {
            return {
                msg: 'Welcome to Your Vue.js App',
                title:'微博热搜',
                name:'蔡卓宜节目中的造型是自己完成的',
            }
        },
        mounted() {
            this.drawLine();
        },
        methods: {
            drawLine() {
                // 基于准备好的dom，初始化echarts实例
                let myChart = this.$echarts.init(document.getElementById('myChart'), 'light')
                // 绘制图表
                myChart.setOption({
                    title: {text: '新闻走势'},
                    tooltip: {},
                    xAxis: {
                        data: ["05-17", "05-18", "05-19", "05-20", "05-21", "05-22"]
                    },
                    legend: {
                        data: ['热度(万)']
                    },
                    yAxis: {},
                    series: [{
                        name: '热度(万)',
                        type: 'line',
                        data: [1188713, 1146156, 1102219, 1003153, 889266, 577562]
                    }]
                });
            }
        }
    }
</script>

<style scoped>
    /*.main {*/
    /*    width: 100%;*/
    /*    height: 100%;*/
    /*    background-color: snow;*/
    /*}*/

    /*.chart {*/
    /*    top: 20%;*/
    /*}*/

    * {
        margin: 0;
        border: 0;
        padding: 0;
    }

    #contain {
        width: 90%;
        margin: 0 auto;
    }

    header {
        height: 80px;
        /*background-color: brown;*/
        width: 100%;
        font-size: 30px;
        color: lightskyblue;
        line-height: 80px;
    }

    #text {
        width: 100%;
        height: auto;
        color: #333333;
        column-count: 2;
        column-width: 45%;
        column-gap: 5%;
        column-rule: 2px dashed black;
        /*background-color: blue;*/
        /*宽度，线型，颜色；---栏与栏的间隔线
类似border，solid | dotted | dashed   实线 | 点线 | 虚线 */
    }

    #subtitle2 {
        width: 400px;
        text-align: center;
        margin: 20px auto;
        font-size: 20px;
        line-height: 20px;
    }

    .chart {
        /*top:150px;*/
        /*width: 60%;*/
        /*height: 400px;*/
        margin: 20px auto;
        /*background-color: burlywood;*/
    }
</style>