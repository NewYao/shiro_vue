<template>
<el-container>
    <el-header>
        <router-link to="/"><span class="header_left">小旭旭的夏日</span></router-link>
        <div class="header_right">
            <el-dropdown @command="handleCommand" trigger="click">
                <span class="el-dropdown-link">
                    <i class="el-icon-arrow-down el-icon-user"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="1">个人中心</el-dropdown-item>
                    <el-dropdown-item command="2">设置</el-dropdown-item>
                    <el-dropdown-item command="3" divided>注销</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </el-header>
    <el-container>
        <el-aside width="180px">
            <div class="aside_top" @click="hide">
                <el-button v-show="!isCollapse" type="info" icon="el-icon-caret-left" circle></el-button>
                <el-button v-show="isCollapse" type="info" icon="el-icon-caret-right" circle></el-button>
            </div>
            <el-menu router unique-opened default-active="1-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span slot="title">导航一</span>
                    </template>
                    <el-menu-item-group>
                        <span slot="title">分组一</span>
                        <el-menu-item index="/per/base">人员列表</el-menu-item>
                        <el-menu-item index="1-2">选项2</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="分组2">
                        <el-menu-item index="1-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="1-4">
                        <span slot="title">选项4</span>
                        <el-menu-item index="1-4-1">选项1</el-menu-item>
                    </el-submenu>
                </el-submenu>
                <el-menu-item index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title">导航二</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                    <i class="el-icon-document"></i>
                    <span slot="title">导航三</span>
                </el-menu-item>

                <el-submenu index="4">
                    <template slot="title">
                        <i class="el-icon-setting"></i>
                        <span slot="title">导航四</span>
                    </template>
                    <el-menu-item index="4-1">选项1</el-menu-item>
                    <el-menu-item index="4-2">选项2</el-menu-item>
                    <el-menu-item-group>
                        <template slot="title">分组6</template>
                        <el-menu-item index="1-7">选项1</el-menu-item>
                        <el-menu-item index="1-8">选项2</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-main>
            <el-breadcrumb separator-class="el-icon-arrow-right" v-if="$router.currentRoute.path!='/index'">
                <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>{{$router.currentRoute.name}}</el-breadcrumb-item>
            </el-breadcrumb>
            <div v-if="$router.currentRoute.path=='/index'">
                <h1>欢迎来到旭旭小镇！</h1>
            </div>
            <router-view class="homeRouterView"></router-view>
        </el-main>
    </el-container>
</el-container>
</template>

<script>
export default {
    name: "HelloWorld",
    data() {
        return {
            msg: "欢迎使用！",
            isCollapse: false
        };
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        hide() {
            var flag = this.isCollapse;
            this.isCollapse = !flag;
        },
        handleCommand(command) {
            this.$message('click on item ' + command);
            var _this = this;
            if (command == '1') {

            } else if (command == '2') {

            } else if (command == '3') {
                _this.postRequest("/logout", '').then(resp => {
                    if (resp.code == 200) {
                        setTimeout(function () {
                            _this.$router.replace('/');
                        }, 500);
                    }
                });
            }

        }
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>
.el-header {
    background-color: #20a0ff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-direction: row;
}

.header_left {
    color: #fff;
    font-size: 22px;
    display: inline;
    margin-left: 8px;
}

.header_right {
    display: flex;
    align-items: center;
    margin-right: 15px;
    float: right;
    cursor: pointer;
}

.header_right .el-dropdown {
    font-size: 25px;
    color: #fff;
}

.el-aside {
    overflow: hidden;
    width: auto !important;
    position: relative;
}

.aside_top {
    position: absolute;
    overflow: hidden;
    top: 5px;
    right: 5px;
    z-index: 1;
}

.el-button.is-circle {
    padding: 1px;
    float: right;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}

.homeRouterView {
    margin-top: 10px;
}
</style>
