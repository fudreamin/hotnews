import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "../views/Index";
import NewManage from "../views/NewManage";
import AddNew from "../views/AddNew";
import NewUpdate from "../views/NewUpdate";
import UserManage from "../views/UserManage";
import AddUser from "../views/AddUser";
import UserUpdate from "../views/UserUpdate";
import NewHomePage from "../views/NewHomePage";
import Login from "../views/Login";
import Register from "../views/Register";
import VisualData from "../views/VisualData";
import Test from "../views/Test";
import SpecialColumn from "../views/SpecialColumn";

Vue.use(VueRouter)

const routes = [
    {
        path:'/',
        name:'新闻首页',
        component: NewHomePage
    },
    {
        path: '/manage',
        name: '新闻模块',
        component: Index,
        show: true,
        redirect: '/NewManage',
        children: [
            {
                path: '/NewManage',
                name: '新闻管理',
                component: NewManage
            },
            {
                path: '/AddNew',
                name: '添加新闻',
                component: AddNew
            }
        ]
    },
    {
        path: 'navigation',
        name: '修改栏',
        component: Index,
        show: false,
        children: [
            {
                path: '/NewUpdate',
                name: '修改新闻',
                component: NewUpdate
            }
        ]
    },
    {
        path: 'navigation2',
        name: '修改栏',
        component: Index,
        show: false,
        children: [
            {
                path: '/UserUpdate',
                name: '修改用户',
                component: UserUpdate
            }
        ]
    },
    {
        path: 'navigation',
        name: '用户模块',
        component: Index,
        show: true,
        children: [
            {
                path: '/UserManage',
                name: '用户管理',
                component: UserManage
            },
            {
                path: '/AddUser',
                name: '添加用户',
                component: AddUser
            }
        ]
    },
    {
        path:'/login',
        name:'登录模块',
        component: Login
    },
    {
        path:'/register',
        name:'注册模块',
        component: Register
    },
    {
        path:'/VisualData',
        name:'可视化数据',
        component: VisualData
    },
    {
        path:'/test',
        name:'测试',
        component:Test
    },
    {
        path:'/Special',
        name:'专栏',
        component:SpecialColumn
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
