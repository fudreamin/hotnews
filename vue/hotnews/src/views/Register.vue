<template>
    <div class="container">
        <div class="user_form">
            <div class="register" >
                <form>
                    <input type="text" v-model="user.name" placeholder="    用户名"></input><br>
                    <input type="password" v-model="user.password" placeholder="    密码"></input><br>
                    <input type="text" v-model="user.age" placeholder="    年龄"></input><br>
                    <input type="text" v-model="user.gender" placeholder="    性别"></input><br>
                    <button class="login_button" @click="register_check()">注册</button><br>
                    <p>已有账号？<a href="/login">前往登录</a></p>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data() {
            return {
                user: {
                    id: '',
                    name: '',
                    password: '',
                    age: '',
                    gender: '',
                }
            }
        },
        methods: {
            register_check(){
                this.$axios.post('http://39.107.143.213:8080/user/add/', this.user).then(response => {
                    this.$message({
                        showClose: true,
                        message: '注册成功',
                        type: 'success'
                    });
                });
            }
        }
    }

</script>

<style scoped>
    .container{
        width: 100%;
        height: 100%;
        background: url("../assets/background.jpg") no-repeat;
        background-size: cover;
        margin: 0 auto;
        padding: 0;
        text-align: center;
        text-decoration: none;
    }
    a{
        text-decoration: none;
    }
    input{
        width: 300px;
        height: 40px;
        margin-top: 15px;
        margin-bottom: 15px;
        border: aquamarine solid 1px ;
    }
    button{
        width: 150px;
        height: 40px;
        background-color: aliceblue;
        border: aquamarine solid 1px ;
        margin-bottom: 20px;
    }
    .user_form{
        position: relative;
        top:20%;
        width: 30%;
        height: auto;
        border: cyan solid 1px;
        padding-top: 15px;
        padding-bottom: 20px;
    }
</style>