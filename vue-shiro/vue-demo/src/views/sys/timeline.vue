<template>
<div>
    <div class="radio">
        排序：
        <el-radio-group v-model="reverse">
            <el-radio :label="false">倒序</el-radio>
            <el-radio :label="true">正序</el-radio>
        </el-radio-group>
        <span class="radio_right">
            <el-button @click="addEdition" type="primary" icon="el-icon-edit" circle></el-button>
        </span>
    </div>
    <el-timeline :reverse="reverse">

        <el-timeline-item v-for="item in dataList" :key="item.id" :timestamp="item.createtime" placement="top" :type="item.type" size="large">
            <el-card>
                <div class="edition_title">
                    <span class="edition_left">
                        <span class="edition_left_title">版本号：</span>
                        <span class="edition_left_value">{{item.edition_code}}</span>
                    </span>
                    <span class="edition_right">
                        <span class="edition_right_title"> 提交时间:</span>
                        <span class="edition_right_value">{{item.createtime}}</span>
                    </span>
                    <hr />
                </div>
                <p v-for="item2 in item.details" :key="item2.id">{{item2.order}}.&nbsp;&nbsp;{{item2.detail}}</p>
            </el-card>
        </el-timeline-item>

    </el-timeline>
</div>
</template>

<script>
import {
    Message
} from 'element-ui';
export default {
    name: 'HelloWorld',
    data() {
        return {
            reverse: false,
            msg: '时间线！！',
            dataList: []
        }
    },
    mounted() {
        this.initList();
    },
    methods: {
        initList() {
            this.postRequest('/edition/', '').then(resp => {
                var _this = this;
                if (resp.code == 200) {
                    //   console.log(resp.data)
                    this.dataList = resp.data;
                }

            })
        },
        addEdition(){
            this.$message({
                            type: 'info',
                            message: '等待更新！'
                        });
        }
    },
}
</script>

<style scoped>
.radio {
    margin-top: 20px;
    margin-bottom: 20px;
}
.radio .radio_right{
    float:right;
}
.el-card__body .edition_title {
    margin-bottom: 10px;
}

.edition_left_title {
    font-family: serif;
    font-size: 16px;
}

.edition_left_value {
    font-family: serif;
    font-size: 18px;
    font-weight: bolder;
    color: #19dcdc;
}

.edition_right {
    margin-left: 37%;
}

.edition_right_value {
    font-family: 微软雅黑;
    font-size: 15px;
    font-weight: 500;
}

.edition_right_title {
    font-family: serif;
    font-size: 16px;
}

.edition_title hr {
    margin-top: 7px;
}

.el-card__body p {
    font-size: 14px;
    margin-bottom: 5px;
    font-family: 微软雅黑;
}
</style>
