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
        <div class="aside_top" @click="hide">
            <el-button v-show="!isCollapse" type="info" icon="el-icon-s-fold" class="menu-controller"></el-button>
            <el-button v-show="isCollapse" type="info" icon="el-icon-s-unfold" class="menu-controller"></el-button>
        </div>
    </el-header>
    <el-container>
        <el-aside width="180px">
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
                        <el-menu-item index="/chat/chat">聊天</el-menu-item>
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
                        <span slot="title">系统</span>
                    </template>
                    <el-menu-item index="/sys/timeline">时间线</el-menu-item>
                    <el-menu-item index="/sys/uploadFile">文件上传</el-menu-item>
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
    <el-backtop target=".el-container"></el-backtop>
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
    mounted() {
        ! function () {
            function n(n, e, t) {
                return n.getAttribute(e) || t
            }

            function e(n) {
                return document.getElementsByTagName(n)
            }

            function t() {
                var t = e("script"),
                    o = t.length,
                    i = t[o - 1];
                return {
                    l: o,
                    z: n(i, "zIndex", -1),
                    o: n(i, "opacity", .5),
                    c: n(i, "color", "0,0,0"),
                    n: n(i, "count", 99)
                }
            }

            function o() {
                a = m.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
                    c = m.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
            }

            function i() {
                r.clearRect(0, 0, a, c);
                var n, e, t, o, m, l;
                s.forEach(function (i, x) {
                        for (i.x += i.xa, i.y += i.ya, i.xa *= i.x > a || i.x < 0 ? -1 : 1, i.ya *= i.y > c || i.y < 0 ? -1 : 1, r.fillRect(i.x - .5, i.y - .5, 1, 1), e = x + 1; e < u.length; e++) n = u[e],
                            null !== n.x && null !== n.y && (o = i.x - n.x, m = i.y - n.y,
                                l = o * o + m * m, l < n.max && (n === y && l >= n.max / 2 && (i.x -= .03 * o, i.y -= .03 * m),
                                    t = (n.max - l) / n.max, r.beginPath(), r.lineWidth = t / 2, r.strokeStyle = "rgba(" + d.c + "," + (t + .2) + ")", r.moveTo(i.x, i.y), r.lineTo(n.x, n.y), r.stroke()))
                    }),
                    x(i)
            }
            var a, c, u, m = document.createElement("canvas"),
                d = t(),
                l = "c_n" + d.l,
                r = m.getContext("2d"),
                x = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
                function (n) {
                    window.setTimeout(n, 1e3 / 45)
                },
                w = Math.random,
                y = {
                    x: null,
                    y: null,
                    max: 2e4
                };
            m.id = l, m.style.cssText = "position:fixed;top:0;left:0;z-index:" + d.z + ";opacity:" + d.o, e("body")[0].appendChild(m), o(), window.οnresize = o,
                window.onmousemove = function (n) {
                    n = n || window.event, y.x = n.clientX, y.y = n.clientY
                },
                window.onmouseout = function () {
                    y.x = null, y.y = null
                };
            for (var s = [], f = 0; d.n > f; f++) {
                var h = w() * a,
                    g = w() * c,
                    v = 2 * w() - 1,
                    p = 2 * w() - 1;
                s.push({
                    x: h,
                    y: g,
                    xa: v,
                    ya: p,
                    max: 6e3
                })
            }
            u = s.concat([y]),
                setTimeout(function () {
                    i()
                }, 100)
        }();
    },
    methods: {
        //鼠标吸附线条
        mouseLine() {

        },
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
    position: relative;
}
.menu-controller{
    padding: 0;
    margin: 0;
}
.aside_top button{
  border: 0;
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
    position: relative;
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
    bottom: 0px;
    left: 0px;
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
