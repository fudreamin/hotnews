<template>
    <div style="margin-top: 8%">
        <el-form style="width: 35%" :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
            <el-form-item label="编号" prop="id">
                <el-input v-model="form.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="内容" prop="name">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="热度" prop="heat">
                <el-input v-model.number="form.heat"  type='number'></el-input>
            </el-form-item>
            <el-form-item label="种类" prop="type">
                <el-input v-model="form.type"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input v-model="form.address"></el-input>
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
        name: "NewUpdate",
        data() {
            return {
                form: {
                    id: '',
                    name: '',
                    heat: '',
                    type: '',
                    address: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入新闻名称', trigger: 'blur'},
                        {min: 1, max: 100, message: '长度在 1 到 100 39.107.143.213字符', trigger: 'blur'}
                    ],
                    heat: [
                        {required: true, message: '请输入热度', trigger: 'blur'},
                    ],
                    type: [
                        {required: false, message: '请输入新闻类别', trigger: 'blur'},
                        {min: 0, max: 2, message: '热/荐/新/无', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '请输入新闻地址', trigger: 'blur'},
                        {min: 5, max: 100, message: '请输入全部完整的网址', trigger: 'blur'}
                    ]
                }
            }
        },
        created() {
            this.$axios.get('http://39.107.143.213:8080/weibo/findById/'+this.$route.query.id).then(response=>{
                this.form=response.data;
            })
        },
        methods: {
            onSubmit(form) {
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        this.$axios.post('http://39.107.143.213:8080/weibo/updateById/',this.form).then(response => {
                            this.$message({
                                showClose: true,
                                message: '更新成功',
                                type: 'success'
                            });
                            this.$router.push("/NewManage");
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