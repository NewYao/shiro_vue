<template>
<div id="app">
    <router-view />
</div>
</template>

<script>
export default {
    name: 'App',
    mounted() {
        // 创建cnzz统计js
        const script = document.createElement('script')
        script.src = 'https://v1.cnzz.com/z_stat.php?id=1278839330&web_id=1278839330'
        script.language = 'JavaScript'
        document.body.appendChild(script)
    },
    watch: {
        '$route': {
            handler(to, from) {
                setTimeout(() => { //避免首次获取不到window._czc
                    if (window._czc) {
                        let location = window.location;
                        let contentUrl = location.pathname + location.hash;
                        let refererUrl = '/';
                        // 用于发送某个URL的PV统计请求，
                        window._czc.push(['_trackPageview', contentUrl, refererUrl])
                        window._czc.push(["_setAutoPageview", false]);
                    }
                }, 300)
            },
            immediate: true // 首次进入页面即执行
        }
    }
}
</script>

<style>
* {
    margin: 0;
    padding: 0;
}

html {
    /* overflow: hidden; */
    height: 100%;
}

body {
    height: 100%;
}

a {
    text-decoration: none;
}

#app {
    height: 100%;
    /* filter: grayscale(100%); */
}

.el-header .el-select,
.form_input,
.el-button,
.el-date-editor {
    margin: 5px 5px 0px 0;
    margin-left: 0 !important;
}

.el-header .el-select {
    width: 150px;
}

table .el-button--mini {
    padding: 3px 4px;
    margin: 2px;
}
</style>
