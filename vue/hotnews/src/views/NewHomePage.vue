<template>
    <div class="container">
        <div class="head">
            <div class="logo"></div>
            <div class="head_main"><a href="#">首页</a></div>
            <div class="head_more"><a href="#">更多</a></div>
            <div class="search">
                <form>
                    <input type="text" class="select_box"></input>
                    <button type="submit" class="select_button">查找</button>
                </form>
            </div>
            <div class="login"><a href="/login">登录</a></div>
        </div>
        <div class="main">
            <a href="/Special">
                <div class="main_specialColumn"></div>
            </a>
            <div class="main_news">
                <div class="main_news_rank">
                    <div>
                        <el-button class="news_select" @click="changeToWeibo()">微博</el-button>
                        <el-button class="news_select" @click="changeToZhihu()">知乎</el-button>
                        <el-button class="news_select" @click="changeToBaidu()">百度</el-button>
                    </div>
                    <div class="news_form">
                        <div v-for="item in news" class="news_row">
                            <div class="form_id" v-if="item.id<=3" style="color:red">{{item.id}}</div>
                            <div class="form_id" v-else style="color: #B3C0D1">{{item.id}}</div>
                            <div class="form_name"><a :href="item.address">{{item.name}}</a></div>
                            <div class="form_heat">{{item.heat}}万</div>
                        </div>
                        <el-button type="primary" >主要按钮</el-button>
                    </div>
                </div>
                <div class="main_news_daily">
                    <div class="daily_recommended">
                        <a v-bind:href="urlAdress">
                            <img class="daily_img" v-bind:src="one_image">
                        </a>
                    </div>
                    <div class="daily_sentence">
                        <p>{{one_sentence}}</p>
                    </div>
                    <div class="calendar" id="myCalendar">

                    </div>
                </div>
            </div>
        </div>
        <div class="footer"><span>新闻热点甄别系统</span></div>
    </div>
</template>

<script>
    export default {
        name: "NewHomePage",
        created() {
            this.$axios.get("http://localhost:8080/weibo/findAll").then(response => {
                this.news = response.data;
            });
            this.$axios.get("http://localhost:8080/one/getUrl").then(response => {
                this.urlAdress = response.data;
            });
            this.$axios.get("http://localhost:8080/one/getImage").then(response => {
                this.one_image = response.data;
            })
            this.$axios.get("http://localhost:8080/one/getSentence").then(response => {
                this.one_sentence = "ONE日推：" + response.data;
            })
        },
        data() {
            return {
                news: [{
                    id: 1,
                    name: '国家安全',
                    time: '2020-04-16 12:15:18',
                    heat: '11103',
                    type: '热',
                    address: 'https://element.eleme.cn/#/zh-CN/component/table'
                }],
                one_image: "http://image.wufazhuce.com/FpNcpQ9V8MN6RullPL7x6vjfowXS",
                one_sentence: "ONE日推：永远年轻，永远热泪盈眶。",
                urlAdress: "http://wufazhuce.com/one/2797"

            }
        },
        mounted() {
            let myChart = this.$echarts.init(document.getElementById('myChart'), 'light')
            let year = year || '2017';
            let date = +echarts.number.parseDate(year + '-01-01');
            let end = +echarts.number.parseDate(year + '-12-31');
            let dayTime = 3600 * 24 * 1000;
            let data = [];
            for (let time = date; time <= end; time += dayTime) {
                data.push([
                    echarts.format.formatTime('yyyy-MM-dd', time),
                    Math.floor(Math.random() * 10000)
                ]);
            }
            alert(data)
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
                    data: data
                }
            })
        },
        methods: {
            changeToWeibo() {
                this.$axios.get("http://localhost:8080/weibo/findAll").then(response => {
                    this.news = response.data;
                });
            },
            changeToZhihu() {
                this.$axios.get("http://localhost:8080/zhihu/findAll").then(response => {
                    this.news = response.data;
                });
            },
            changeToBaidu() {
                this.$axios.get("http://localhost:8080/baidu/findAll").then(response => {
                    this.news = response.data;
                });
            }

        }

    }
</script>

<style scoped>
    * {
        margin: 0 auto;
        padding: 0;
        text-align: center;
        text-decoration: none;
    }

    .head {
        width: 80%;
        margin-left: 10%;
        height: 60px;
        top: 0;
        min-width: 1200px;
        border-bottom: #eeeeee solid 1px;
        box-shadow: #B3C0D1 2px 1px 2px 1px;
    }

    .logo {
        float: left;
        width: 120px;
        height: 60px;
        background: url("../assets/logo.png") no-repeat;
        background-size: cover;
        overflow: hidden;
    }

    .head_main {
        float: left;
        margin-left: 50px;
        width: 120px;
        height: 60px;
        line-height: 60px;
    }

    .head_main > a {
        font-size: 25px;
        color: silver;
    }


    .head_more > a {
        font-size: 25px;
        color: silver;
    }

    .head_more {
        float: left;
        margin-left: 50px;
        width: 120px;
        height: 60px;
        line-height: 60px;
        font-size: 25px;
        color: silver;
    }

    .search {
        margin-left: 50px;
        float: left;
        width: 500px;
        height: 60px;
        line-height: 60px;
        border: #eeeeee solid 1px;
    }

    .select_box {
        float: left;
        margin-top: 15px;
        height: 30px;
        width: 60%;
    }

    .select_button {
        float: left;
        margin-top: 10px;
        height: 30px;
        width: 15%;
        margin-left: 10%;
        line-height: 30px;
        margin-top: 15px;
        background-color: #eeeeee;
        border: cornflowerblue solid 1px;
    }

    .login {
        float: right;
        width: 100px;
        height: 60px;
        line-height: 60px;
    }

    .login > a {
        color: silver;
    }

    .main {
        width: 80%;
        height: 1100px;
    }


    .main_specialColumn {
        width: 100%;
        height: 120px;
        margin-top: 40px;
        background: url("../assets/1.jpg") no-repeat;
        background-size: 100% 100%;
    }

    .main_news {
        width: 100%;
        height: 850px;
        margin-top: 50px;
    }

    .main_news_rank {
        float: left;
        width: 60%;
        height: 850px;
        border: #B3C0D1 solid 1px;
        text-align: left;
    }

    .news_select {
        width: 30%;
        margin-top: 20px;
        margin-bottom: 20px;
    }

    .news_form {
        width: 100%;
        height: 90%;
        overflow-y: auto;
    }

    .news_form::-webkit-scrollbar {
        display: none;
    }

    .news_row {
        width: 100%;
        height: 10%;
    }

    .form_id {
        width: 5%;
        float: left;
    }

    .form_name {
        width: 75%;
        padding-left: 5%;
        float: left;
        text-align: left;
    }

    .form_name > a {
        color: black;
    }

    .form_heat {
        width: 15%;
        float: left;
        text-align: left;
        color: orangered;
    }

    .form_heat > a {
        color: orangered;
    }


    .main_news_daily {
        float: right;
        width: 36%;
        height: 850px;
        border: #B3C0D1 solid 1px;
    }

    .daily_recommended {
        width: 100%;
        height: auto;
    }

    .daily_img {
        width: 100%;
    }

    .daily_sentence {
        /*margin-top: 10px;*/
        height: auto;
        line-height: 30px;
        font-size: 13px;
        background-color: antiquewhite;
    }

    .calendar {
        border: aqua solid 1px;
        height: 400px;
        width: 100%;
    }

    .footer {
        width: 100%;
        height: 20px;
        line-height: 20px;
        background-color: darkgray;
    }


</style>