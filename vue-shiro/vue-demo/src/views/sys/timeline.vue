<template>
<div>
    <div class="radio">
        排序：
        <el-radio-group v-model="reverse">
            <el-radio :label="false">倒序</el-radio>
            <el-radio :label="true">正序</el-radio>
        </el-radio-group>
    </div>
    <el-timeline :reverse="reverse">
        <el-timeline-item timestamp="2018/4/12" placement="top" type="warning" size="large">
            <el-card>
                <h4>更新 Github 模板</h4>
                <p>王小虎 提交于 2018/4/12 20:46</p>
            </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/3" placement="top" type="success" size="normal">
            <el-card>
                <h4>更新 Github 模板</h4>
                <p>王小虎 提交于 2018/4/3 20:46</p>
            </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/2" placement="top" type="primary">
            <el-card>
                <h4>更新 Github 模板</h4>
                <p>王小虎 提交于 2018/4/2 20:46</p>
            </el-card>
        </el-timeline-item>
    </el-timeline>
</div>
</template>

<script>
export default {
    name: 'HelloWorld',
    data() {
        return {
            reverse: false,
            msg: '时间线！！'
        }
    }
}
</script>
