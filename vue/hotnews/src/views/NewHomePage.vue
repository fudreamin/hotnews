<template>
    <div>
        <div class="banner">
            <div class="banner-info">
                <div class="container">
                    <nav class="navbar navbar-default">

                        <!-- 热点新闻标志 -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <div class="logo">
                                <a class="navbar-brand" href="/#"><span>H</span> 热点新闻</a>
                            </div>
                        </div>

                        <!-- 查找和登录按钮 -->
                        <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">

                            <div class="search">
                                <form>
                                    <input type="text" class="select_box"></input>
                                    <button type="submit" class="select_button">查找</button>
                                </form>
                            </div>
                            <div class="login"><a href="/login"><font size="4">登录</font></a></div>
                        </div>

                    </nav>


                    <!--图片上滚动的新闻标题-->

                    <div id="top" class="callbacks_container">
<!--                        <ul class="rslides" id="slider3">-->
                            <li>
                                <div class="banner-info-slider">
                                    <ul>
                                        <li></li>
                                    </ul>
                                    <h3>{{news[0].name}}</h3>
                                    <p><span>来自
                                        <i v-if="source=='weibo'">微博热搜</i>
                                        <i v-if="source=='zhihu'">知乎热搜</i>
                                        <i v-if="source=='baidu'">百度热搜</i>
                                    </span></p>
                                    <div class="more">
                                        <a @click="view_data(news[0])" href="javascript:void(0);" class="type-1">   <!--新闻地址-->
                                            <span> 查看详情 </span>
                                            <span> 查看详情 </span>
                                        </a>
                                    </div>
                                </div>
                            </li>
<!--                            <li>-->
<!--                                <div class="banner-info-slider">-->
<!--                                    <ul>-->
<!--                                        <li></li>-->
<!--                                    </ul>-->

<!--                                    <h3>北京新增 36 例新冠肺炎确诊病例</h3>-->
<!--                                    <p><span>来自 <i>知乎</i> <i></i></span></p>-->
<!--                                    <div class="more">-->
<!--                                        <a href="https://www.zhihu.com/question/401285020" class="type-1">  &lt;!&ndash;新闻地址&ndash;&gt;-->
<!--                                            <span>查看详情 </span>-->
<!--                                            <span> 查看详情 </span>-->
<!--                                        </a>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </li>-->
<!--                            <li>-->
<!--                                <div class="banner-info-slider">-->
<!--                                    <ul>-->
<!--                                        <li></li>-->
<!--                                    </ul>-->
<!--                                    <h3>桂林暴雨</h3>-->
<!--                                    <p><span>来自 <i> 百度 </i>  <i></i></span></p>-->
<!--                                    <div class="more">-->
<!--                                        <a href="http://www.baidu.com/baidu?cl=3&tn=SE_baiduhomet8_jmjb7mjw&rsv_dl=fyb_top&fr=top1000&wd=%B9%F0%C1%D6%B1%A9%D3%EA" class="type-1">  &lt;!&ndash;新闻地址&ndash;&gt;-->
<!--                                            <span> 查看详情 </span>-->
<!--                                            <span> 查看详情 </span>-->
<!--                                        </a>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </li>-->
<!--                            <li>-->
<!--                                <div class="banner-info-slider">-->
<!--                                    <ul>-->
<!--                                        <li></li>-->
<!--                                    </ul>-->
<!--                                    <h3>新闻标题4</h3>-->
<!--                                    <p><span>来自 <i>新浪微博</i> 和 <i>知乎</i></span></p>-->
<!--                                    <div class="more">-->
<!--                                        <a href="single.html" class="type-1">  &lt;!&ndash;新闻地址&ndash;&gt;-->
<!--                                            <span>查看详情 </span>-->
<!--                                            <span> 查看详情</span>-->
<!--                                        </a>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </li>-->
<!--                            <li>-->
<!--                                <div class="banner-info-slider">-->
<!--                                    <ul>-->
<!--                                        <li></li>-->
<!--                                    </ul>-->
<!--                                    <h3>新闻标题5</h3>-->
<!--                                    <p><span>来自 <i>新浪微博</i> 和 <i>知乎</i></span></p>-->
<!--                                    <div class="more">-->
<!--                                        <a href="single.html" class="type-1">  &lt;!&ndash;新闻地址&ndash;&gt;-->
<!--                                            <span> 查看详情 </span>-->
<!--                                            <span> 查看详情</span>-->
<!--                                        </a>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </li>-->
<!--                        </ul>-->
                    </div>

                </div>
            </div>
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
                            <div class="form_id" v-if="item.id<=3" style="color:red;size:2">{{item.id}}</div>
                            <div class="form_id" v-else style="color: #B3C0D1">{{item.id}}</div>
                            <div class="form_name"><a :href="item.address">{{item.name}}</a></div>
                            <div class="form_heat">{{item.heat}}万</div>
                            <el-button v-if="source=='weibo'||source=='zhihu'" type="primary" size="mini" plain class="view_button" @click="view_data(item)">可视化
                            </el-button>
                        </div>

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
        <div class="footer"><span><font size="4" color="white">新闻热点甄别系统</font></span></div>
    </div>
</template>

<script>
    export default {
        name: "NewHomePage",
        created() {
            this.$axios.get("http://39.107.143.213:8080/weibo/findAll").then(response => {
                this.news = response.data;
            });
            this.$axios.get("http://39.107.143.213:8080/one/getUrl").then(response => {
                this.urlAdress = response.data;
            });
            this.$axios.get("http://39.107.143.213:8080/one/getImage").then(response => {
                this.one_image = response.data;
            })
            this.$axios.get("http://39.107.143.213:8080/one/getSentence").then(response => {
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
                urlAdress: "http://wufazhuce.com/one/2797",
                source: 'weibo'

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
            // alert(data)
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
                this.$axios.get("http://39.107.143.213:8080/weibo/findAll").then(response => {
                    this.news = response.data;
                    this.source = 'weibo';
                });
            },
            changeToZhihu() {
                this.$axios.get("http://39.107.143.213:8080/zhihu/findAll").then(response => {
                    this.news = response.data;
                    this.source = 'zhihu';
                });
            },
            changeToBaidu() {
                this.$axios.get("http://39.107.143.213:8080/baidu/findAll").then(response => {
                    this.news = response.data;
                    this.source = 'baidu';
                });
            },
            view_data(item) {
                this.$router.push({
                    path: "/VisualData2",
                    query: {
                        id: item.id,
                        adress: item.address,
                        source: this.source
                    }
                })
            }

        }

    }
</script>
<!--<script>-->
<!--    export default {-->
<!--        name: "NewHomePage",-->
<!--        created() {-->
<!--            this.$axios.get("http://39.107.143.213:8080/weibo/findAll").then(response => {-->
<!--                this.news = response.data;-->
<!--            });-->
<!--            this.$axios.get("http://39.107.143.213:8080/one/getUrl").then(response => {-->
<!--                this.urlAdress = response.data;-->
<!--            });-->
<!--            this.$axios.get("http://39.107.143.213:8080/one/getImage").then(response => {-->
<!--                this.one_image = response.data;-->
<!--            })-->
<!--            this.$axios.get("http://39.107.143.213:8080/one/getSentence").then(response => {-->
<!--                this.one_sentence = "ONE日推：" + response.data;-->
<!--            })-->
<!--        },-->
<!--        data() {-->
<!--            return {-->
<!--                news: [{-->
<!--                    id: 1,-->
<!--                    name: '国家安全',-->
<!--                    time: '2020-04-16 12:15:18',-->
<!--                    heat: '11103',-->
<!--                    type: '热',-->
<!--                    address: 'https://element.eleme.cn/#/zh-CN/component/table'-->
<!--                }],-->
<!--                one_image: "http://image.wufazhuce.com/FpNcpQ9V8MN6RullPL7x6vjfowXS",-->
<!--                one_sentence: "ONE日推：永远年轻，永远热泪盈眶。",-->
<!--                urlAdress: "http://wufazhuce.com/one/2797",-->
<!--                source: 'weibo'-->

<!--            }-->
<!--        },-->
<!--        mounted() {-->
<!--            let myChart = this.$echarts.init(document.getElementById('myChart'), 'light')-->
<!--            let year = year || '2017';-->
<!--            let date = +echarts.number.parseDate(year + '-01-01');-->
<!--            let end = +echarts.number.parseDate(year + '-12-31');-->
<!--            let dayTime = 3600 * 24 * 1000;-->
<!--            let data = [];-->
<!--            for (let time = date; time <= end; time += dayTime) {-->
<!--                data.push([-->
<!--                    echarts.format.formatTime('yyyy-MM-dd', time),-->
<!--                    Math.floor(Math.random() * 10000)-->
<!--                ]);-->
<!--            }-->
<!--            alert(data)-->
<!--            myChart.setOption({-->
<!--                visualMap: {-->
<!--                    show: false,-->
<!--                    min: 0,-->
<!--                    max: 10000-->
<!--                },-->
<!--                calendar: {-->
<!--                    range: '2017'-->
<!--                },-->
<!--                series: {-->
<!--                    type: 'heatmap',-->
<!--                    coordinateSystem: 'calendar',-->
<!--                    data: data-->
<!--                }-->
<!--            })-->
<!--        },-->
<!--        methods: {-->
<!--            changeToWeibo() {-->
<!--                this.$axios.get("http://39.107.143.213:8080/weibo/findAll").then(response => {-->
<!--                    this.news = response.data;-->
<!--                    this.source = 'weibo';-->
<!--                });-->
<!--            },-->
<!--            changeToZhihu() {-->
<!--                this.$axios.get("http://39.107.143.213:8080/zhihu/findAll").then(response => {-->
<!--                    this.news = response.data;-->
<!--                    this.source = 'zhihu';-->
<!--                });-->
<!--            },-->
<!--            changeToBaidu() {-->
<!--                this.$axios.get("http://39.107.143.213:8080/baidu/findAll").then(response => {-->
<!--                    this.news = response.data;-->
<!--                    this.source = 'baidu';-->
<!--                });-->
<!--            },-->
<!--            view_data(item) {-->
<!--                this.$router.push({-->
<!--                    path: "/VisualData2",-->
<!--                    query: {-->
<!--                        id: item.id,-->
<!--                        adress: item.address,-->
<!--                        source: this.source-->
<!--                    }-->
<!--                })-->
<!--            }-->

<!--        }-->

<!--    }-->
<!--</script>-->

<style scoped>

    @import "../css/style.css";

    .search {
        margin-left: 200px;
        float: left;
        width: 550px;
        height: 60px;
        line-height: 60px;
    }

    .login {
        float: right;
        width: 60px;
        height: 60px;
        line-height: 60px;
    }

    .login > a {
        color: rgb(255, 245, 245);
    }

    .login>a:hover{
        color: #1ABC9C;
        text-decoration: none;
    }

    .select_box {
        float: left;
        margin-top: 15px;
        height: 30px;
        width: 60%;
        opacity:0.9;
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
        opacity: 0.9;
        border: cornflowerblue solid 1px;
    }

    .select_button:hover{
        background-color: #1ABC9C;
        color: white;
    }

    .main {
        width: 80%;
        height: 1100px;
        text-align: center;
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
        /*text-align: left;*/
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

    .view_button {
        position: relative;
        top: 20%;
        right: 35%;
    }

    .main_news_daily {
        float: right;
        width: 36%;
        height: 850px;
        /*border: #B3C0D1 solid 1px;*/
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
        /*border: aqua solid 1px;*/
        height: 400px;
        width: 100%;
    }

    .footer {
        width: 100%;
        height: 10px;
        text-align: center;
        line-height: 10px;
        background-color: rgb(18, 29, 39);
    }


</style>