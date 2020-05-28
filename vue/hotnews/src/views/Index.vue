<template>
    <el-container style="height: 700px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu  :default-openeds="['0','1','2','3','4']" router>
                <el-submenu v-for="(item,index) in $router.options.routes" :index="String(index)" v-if="item.show">
                    <template slot="title"><i class="el-icon-message"></i>{{item.name}}</template>
                    <el-menu-item-group>
                        <el-menu-item v-for="item2 in item.children" :index="item2.path"
                                      :class="$route.path===item2.path?'is-active':''">{{item2.name}}
                        </el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "Index"
    }
</script>

<style scoped>
    .el-footer{
        border: aqua;
    }
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
</style>