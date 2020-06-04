<template>
<view>
    <el-tabs :tab-position="tabPosition" style="height: auto;">
        <el-tab-pane label="管理员">用户管理</el-tab-pane>
        <el-tab-pane label="注册用户">配置管理</el-tab-pane>
        <el-tab-pane label="普通用户">
            <div id="right_content">
                <el-switch v-model="value1" active-text="按月付费" inactive-text="按年付费">
                </el-switch>
                <el-switch style="display: block" v-model="value2" active-color="#13ce66" inactive-color="#ff4949" active-text="按月付费" inactive-text="按年付费">
                </el-switch>
            </div>
        </el-tab-pane>
    </el-tabs>
</view>
</template>

<script>
export default {
    data() {
        return {
            tabPosition: 'left',
            value1: true,
            value2: true
        }
    },
}
</script>

<style scoped>
#right_content {
    width: 100%;
    height: 100px;
}
</style>
