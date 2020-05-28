<template>
    <div style="margin-top: 8%">
        <el-form style="width: 35%" :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model.number="form.age"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-select v-model="form.gender">
                    <el-option value="男"></el-option>
                    <el-option value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">提交</el-button>
                <el-button @click="resetForm('form')">重置</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
    export default {
        name: "AddUser",
        data() {
            return {
                form: {
                    id: 1,
                    name: '',
                    password:'',
                    age:'',
                    gender:''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur'}
                    ],
                    age: [
                        {required: true, message: '请输入年龄', trigger: 'blur'},
                        { type: 'number', message: '年龄必须为数字值'}
                    ],
                    gender: [
                        {required: true, message: '请输入性别', trigger: 'blur'},
                    ]
                }
            }
        },
        methods: {

            onSubmit(form) {
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        this.$axios.post('http://localhost:8080/user/add/', this.form).then(response => {
                            this.$message({
                                showClose: true,
                                message: '添加成功',
                                type: 'success'
                            });
                        });
                    }
                });
            },
            resetForm(form) {
                this.$refs[form].resetFields();
            }
        }
    }
</script>

<style scoped>

</style>