<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="id"
                    label="账号"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="用户名"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="password"
                    label="密码"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="age"
                    label="年龄"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="gender"
                    label="性别"
                    width="100">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="medium">修改</el-button>
                    <el-button @click="deleteClick(scope.row)" type="text" size="medium">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="position: relative;bottom: 0"
                       background
                       layout="prev, pager, next"
                       :page-size="pageSize"
                       :total=total
                       @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    import NewUpdate from "./NewUpdate";

    export default {
        name: "UserManage",
        created() {
            this.$axios.get("http://localhost:8080/user/findPage/1/8").then(response => {
                this.tableData = response.data.list;
                this.pageSize=response.data.pageSize;
                this.total=response.data.total;
            })
        },
        data() {
            return {
                pageSize: 0,
                total: 0,
                tableData: [{
                    id: 1,
                    name: '国家安全',
                    password:'123456',
                    age:16,
                    gender:'男'
                }]
            }
        },
        methods: {
            handleClick(row) {
                this.$router.push({
                    path:"/UserUpdate",
                    query:{
                        id:row.id
                    }
                })
            },
            deleteClick(row){
                this.$axios.delete("http://localhost:8080/user/deleteById/"+row.id).then(response=>{
                    this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    });
                    window.location.reload();
                });
            },
            page(data) {
                this.$axios.get("http://localhost:8080/user/findPage/"+data+"/8").then(response => {
                    this.tableData = response.data.list;
                    this.pageSize= response.data.pageSize;
                    this.total=response.data.total;
                });
            }

        },



    }
</script>

<style scoped>

</style>