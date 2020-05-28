<template>
    <div>
        <p>test</p>
        <div class="chart" id="main" :style="{width: '600px', height: '400px'}"></div>
    </div>
</template>

<script>
    export default {
        name: "Test",
        data() {
            return {
               datetime: [],
            }
        },
        mounted() {
            let echarts = require('echarts');
            let year =2020 || '2017';
            let date = +echarts.number.parseDate(year + '-01-01');
            let end = +echarts.number.parseDate(year + '-12-31');
            let dayTime = 3600 * 24 * 1000;
            for (let time = date; time <= end; time += dayTime) {
                this.datetime.push([
                    echarts.format.formatTime('yyyy-MM-dd', time),
                    Math.floor(Math.random() * 10000)
                ]);
            }
            this.drawLine();
        },
        methods: {
            drawLine() {
                // 基于准备好的dom，初始化echarts实例
                let echarts = require('echarts');
                let myChart = echarts.init(document.getElementById('main'));
                // 绘制图表
                myChart.setOption({
                    visualMap: {
                        show: false,
                        min: 0,
                        max: 10000
                    },
                    calendar: {
                        range: '2017'
                    },
                    series: {
                        type: 'heatmap',
                        coordinateSystem: 'calendar',
                        data: this.datetime
                    }
                });
            }
        }
    }

</script>

<style scoped>

</style>