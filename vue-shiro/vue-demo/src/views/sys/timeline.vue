<template>
<div>
    <div class="radio">
        排序：
        <el-radio-group v-model="reverse">
            <el-radio :label="false">倒序</el-radio>
            <el-radio :label="true">正序</el-radio>
        </el-radio-group>
        <span class="radio_right">
            <el-button @click="openWindows" type="primary" size="middle" icon="el-icon-circle-plus-outline" circle></el-button>
        </span>
        <div style="clear:both;"></div>
    </div>
    <el-timeline :reverse="reverse">
        <el-timeline-item v-for="(item,index) in dataList" :key="item.id" :timestamp="item.createtime" placement="top" :type="item.type" size="large">
            <el-card>
                <div class="edition_title">
                    <span class="edition_left">
                        <span class="edition_left_title">版本号：</span>
                        <span class="edition_left_value">{{item.edition_code}}</span>
                    </span>
                    <span class="edition_right">
                        <span class="edition_right_title">提交时间:</span>
                        <span class="edition_right_value">{{item.createtime}}</span>
                    </span>
                    <span class="edition_operation">
                        <span @click="modify_edition(item.id);oper_tag = 2" class="edition_operation_element">
                            <i class="el-icon-edit"></i>
                        </span>
                        <span @click="del_edition(item.id,index)" class="edition_operation_element">
                            <i class="el-icon-delete"></i>
                        </span>
                    </span>
                    <div style="clear:both;"></div>
                    <hr />
                </div>
                <p v-for="(item2,index) in item.details" :key="item2.id">{{index+1}}.&nbsp;&nbsp;{{item2.detail}}</p>
            </el-card>
        </el-timeline-item>
    </el-timeline>
    <el-dialog title="版本信息" :visible.sync="dialogFormVisible" :before-close="handleClose" :close-on-click-modal="false">
        <el-form :model="edition">
            <el-form-item label="版本号" :label-width="formLabelWidth">
                <el-input v-model="edition.edition_code" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="发布时间" :label-width="formLabelWidth">
                <el-date-picker value-format="yyyy-MM-dd HH:mm:ss" v-model="edition.createtime" type="datetime" placeholder="选择日期"></el-date-picker>
            </el-form-item>
            <el-form-item label="版本说明" :label-width="formLabelWidth">
                <el-radio-group v-model="edition.type" @change="typechange" :fill="selectColor">
                    <el-radio-button label="primary">大版本</el-radio-button>
                    <el-radio-button label="success">小版本</el-radio-button>
                    <el-radio-button label="warning">紧急</el-radio-button>
                    <el-radio-button label="info">常规修复</el-radio-button>
                    <el-radio-button label="danger">重大修复</el-radio-button>
                </el-radio-group>
            </el-form-item>
        </el-form>
        <hr />
        <div style="margin:10px 0 10px">
            <el-button @click="add_edition_detail">新增版本详情</el-button>
        </div>
        <div class="detail_list">
            <el-row v-for="(item,index) in detail_list" :key="item.id">
                <el-col :span="1">
                    <div style="margin-top: 8px;">{{index+1}}.</div>
                </el-col>
                <el-col :span="20">
                    <el-input type="textarea" autosize placeholder="请输入内容" v-model="item.detail"></el-input>
                </el-col>
                <el-col :span="3" style="font-size: 20px;padding-left:5px;color: red;">
                    <i @click="del_detail(index)" style="margin-top: 8px;cursor: pointer;" class="el-icon-delete"></i>
                    <span style="position:relative;display: inline-block;">
                        <i @click="moveDetail(1,index)" v-if="index!=0" style="margin-top: 8px;cursor: pointer;float:left;color:#66b1ff;" class="el-icon-top"></i>
                        <i @click="moveDetail(2,index)" v-if="index < detail_list.length -1" style="margin-top: 8px;cursor: pointer;float:left;color:#67c23a" class="el-icon-bottom"></i>
                    </span>
                </el-col>
            </el-row>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button v-if="oper_tag==1" type="primary" @click="addEdition">新增</el-button>
            <el-button v-else type="primary" @click="addEdition">修改</el-button>
        </div>
    </el-dialog>
</div>
</template>

<script>
import {
    Message
} from "element-ui";
export default {
    name: "HelloWorld",
    data() {
        return {
            reverse: false,
            dialogFormVisible: false,
            dataList: [],
            edition: {
                id: "",
                edition_code: "",
                createtime: "",
                type: ""
            },
            formLabelWidth: "120px",
            selectColor: "#409EFF",
            detail_list: [],
            oper_tag: "1" //控制新增修改 1.新增 2.修改
        };
    },
    mounted() {
        this.initList();
    },
    methods: {

        initList() {
            this.postRequest("/edition/editions", "").then(resp => {
                var _this = this;
                if (resp.code == 200) {
                    this.dataList = resp.data;
                }
            });
        },

        //添加版本信息
        addEdition() {
            var params = {
                edition: JSON.stringify(this.edition),
                details: JSON.stringify(this.detail_list)
            };
            var _this = this;
            let url = "/edition/addEdition"; //默认新增
            if (2 == _this.oper_tag) {
                //修改
                url = "/edition/editEdition";
            }
            this.postRequest(url, params).then(resp => {
                if (resp.code == 200) {
                    _this.dialogFormVisible = false;
                    _this.initList();
                    _this.$message({
                        type: "success",
                        message: "添加成功！"
                    });
                }
            });
        },
        typechange() {
            let type = this.edition.type;
            if (type == "primary") {
                this.selectColor = "#409EFF";
            } else if (type == "success") {
                this.selectColor = "#67C23A";
            } else if (type == "warning") {
                this.selectColor = "#E6A23C";
            } else if (type == "danger") {
                this.selectColor = "#F56C6C";
            } else if (type == "info") {
                this.selectColor = "#909399";
            } else {
                this.selectColor = "#409EFF";
            }
        },
        add_edition_detail() {
            this.detail_list.push({
                detail: ""
            });
        },
        del_detail(index) {
            this.detail_list.splice(index, 1);
        },
        modify_edition(id) {
            var that = this;
            that.dialogFormVisible = true;
            that
                .postRequest("/edition/edition", {
                    id: id
                })
                .then(resp => {
                    if (resp.code == 200) {
                        that.edition = resp.data;
                        that.typechange();
                        that.detail_list = resp.data.details;
                    }
                });
        },
        del_edition(id, index) {
            var that = this;
            that
                .$confirm("此操作将删除该版本信息, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                .then(() => {
                    that
                        .postRequest("/edition/delEdition", {
                            id: id
                        })
                        .then(resp => {
                            if (resp.code == 200) {
                                that.$message({
                                    type: "success",
                                    message: "删除成功！"
                                });
                                that.dataList.splice(index, 1);
                            }
                        });
                });
        },
        openWindows() {
            Object.assign(this.$data.edition, this.$options.data().edition);
            this.detail_list = [];
            this.oper_tag = 1;
            this.dialogFormVisible = true;
        },
        handleClose() {
            //模态框关闭时清除数据
            // this.$confirm("是否关闭窗口?", "提示", {
            //     confirmButtonText: "确定",
            //     cancelButtonText: "取消",
            //     type: "warning"
            // }).then(() => {
            //     this.dialogFormVisible = false;
            // });
            this.dialogFormVisible = false;
        },
        moveDetail(tag, index) {
            //版本详情位移
            // console.log(tag,index)
            var that = this;
            if (tag == 1) {
                //上移
                var tagObj = that.detail_list[index - 1];
                //  that.$set(that.detail_list[index - 1] = that.detail_list[index]);
                //  that.$set(that.detail_list[index] = tagObj);
                that.$set(that.detail_list, index - 1, that.detail_list[index]);
                that.$set(that.detail_list, index, tagObj);
            } else {
                //下移
                var tagObj = that.detail_list[index + 1];
                that.$set(that.detail_list, index + 1, that.detail_list[index]); //需要移动的目标
                that.$set(that.detail_list, index, tagObj);

                console.log(index, that.detail_list[index].detail);
                console.log(index + 1, that.detail_list[index + 1].detail);
                console.log(that.detail_list);
            }
        }
    }
};
</script>

<style scoped>
.radio {
    margin-top: 20px;
    margin-bottom: 20px;
}

hr {
    height: 1px;
    background-color: #33333342;
    border: 0;
}
.radio_right .el-button.is-circle{
    padding:5px;
    font-size: 25px;
}
.radio .radio_right {
    float: right;
    margin-right: 15px;
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

.edition_operation {
    float: right;
    font-size: 20px;
}

.edition_operation .edition_operation_element {
    margin-right: 5px;
    cursor: pointer;
}

.edition_operation .edition_operation_element:hover {
    color: rgba(31, 231, 24, 0.85);
}

.edition_title hr {
    margin-top: 7px;
}

.el-card__body p {
    font-size: 14px;
    margin-bottom: 5px;
    font-family: 微软雅黑;
}

.el-dialog form .el-input,
.el-dialog form .el-select {
    width: 300px;
}

.el-radio-group .el-radio-button__inner {
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

.detail_list .el-textarea {
    margin: 0 0 10px 0;
}

.detail_list {
    -webkit-touch-callout: none;
    /* iOS Safari */
    -webkit-user-select: none;
    /* Chrome/Safari/Opera */
    -khtml-user-select: none;
    /* Konqueror */
    -moz-user-select: none;
    /* Firefox */
    -ms-user-select: none;
    /* Internet Explorer/Edge */
    user-select: none;
    /* Non-prefixed version, currently
    not supported by any browser */
}
</style>
