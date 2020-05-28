<template>
    <div class="container">
        <div class="user_form">
            <div class="login">
                <form>
                    <input type="username" v-model="user.id" placeholder="  账号" id="username"></input><br>
                    <input type="password" v-model="user.password" placeholder="    密码" id="password"></input><br>
                    <button class="login_button" @click="login()">登录</button>
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
                    id: '',
                    name: '',
                    password: '',
                    age: '',
                    gender: '',
                },
                count: []
            }
        },
        methods: {
            login() {
                this.$router.push("/");
            },

        },
    }

</script>

<style scoped>
    .container {
        width: 100%;
        height: 100%;
        background: url("../assets/background.png") no-repeat;
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