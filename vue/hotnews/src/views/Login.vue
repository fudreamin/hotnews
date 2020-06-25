<!--登录页面-->
<template>
    <div class="container">
        <div class="user_form">
            <div class="login">
                <form>
                    <input type="username" v-model="user.id" placeholder="  账号" id="username"></input><br>
                    <input type="password" v-model="user.password" placeholder="    密码" id="password"></input><br>
                    <button class="login_button" @click="clickLogin()">登录</button>
                    <br>
                    <p>还没有账号？<a href="/register">前往注册</a></p>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                check: false,
                user: {
                    id: '1',
                    name: '张三',
                    password: '123456',
                    age: '18',
                    gender: '男',
                },
                users: [{
                    id: '1',
                    name: '张三',
                    password: '123456',
                    age: '18',
                    gender: '男',
                }],
                count: []
            }
        },
        created() {

            this.$axios.get('http://39.107.143.213:8080/user/findAll').then(response => {
                this.users = response.data;
            });
        },
        methods: {
            clicks(){
                this.$router.push('/');
            },
            clickLogin() {
                let flag=false;
                for (let i=0;i<this.users.length;i++) {
                    // alert(this.users[i].id+" "+this.user.id);
                    // alert(this.users[i].password+" "+this.user.password);
                    if (this.users[i].id==this.user.id &&this.users[i].password==this.user.password) {
                        flag=true;
                        break;
                    }
                }
                alert(flag);
                if(flag===true){
                    this.$router.push('/');
                }else{
                    alert("密码错误");
                }
            }
        },
    }

</script>

<style scoped>
    .container {
        width: 100%;
        height: 100%;
        background: url("../assets/background.jpg") no-repeat;
        background-size: cover;
        margin: 0 auto;
        padding: 0;
        text-align: center;
        text-decoration: none;
    }

    a {
        text-decoration: none;
    }

    input {
        width: 300px;
        height: 40px;
        margin-top: 15px;
        margin-bottom: 15px;
        border: aquamarine solid 1px;
    }

    button {
        width: 150px;
        height: 40px;
        background-color: aliceblue;
        border: aquamarine solid 1px;
        margin-bottom: 20px;
    }

    .user_form {
        position: relative;
        top: 25%;
        width: 30%;
        height: auto;
        border: cyan solid 1px;
        padding-top: 15px;
        padding-bottom: 20px;
    }


</style>