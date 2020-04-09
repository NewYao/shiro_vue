<template>
<el-container>
    <el-header>{{msg}}</el-header>
    <el-main>
        <h2>{{timer}}</h2>
    </el-main>
</el-container>
</template>

<script>
export default {
    name: 'HelloWorld',
    data() {
        return {
            msg: '欢迎使用！！',
            timer: '',
            timeTool:''
        }
    },
    mounted() {
        var that = this;
        this.timeTool = setInterval(function () {
            var date = new Date(); //实例一个时间对象；
            var year = date.getFullYear(); //获取系统的年；
            var month = date.getMonth() + 1; //获取系统月份，由于月份是从0开始计算，所以要加1
            var day = date.getDate();
            //获取系统日
            var hour = date.getHours(); //获取系统时间
            var minute = date.getMinutes(); //分
            var second = date.getSeconds(); //秒
            var time = year + '年' + month + '月' + day + '日 ' + hour + ':' + minute + ':' + second;
            // console.log(time)
            that.timer = time;
        }, 1000)
    },
    beforeDestroy() {
        var that = this;
        if (that.timeTool) {
            clearInterval(that.timeTool); // 在Vue实例销毁前，清除我们的定时器
            that.timeTool = '';
        }
    }
}
</script>
