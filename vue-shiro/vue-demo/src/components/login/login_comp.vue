<template>
<div class="table">
    <h2 style="color: white;">
        <div class="login-form">
            <div class="login-space">
                <el-form @submit.native.prevent :label-position="labelPosition" label-width="80px" :model="formLabelAlign" :hide-required-asterisk="true">
                    <el-form-item label="用户名" prop="name">
                        <el-input prefix-icon="el-icon-user" v-model="formLabelAlign.name" clearable autocomplete="off">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="pass">
                        <el-input prefix-icon="el-icon-s-cooperation" type="password"  v-model="formLabelAlign.pass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" class="verifyCodeArea" prop="verifyCode">
                        <el-input type="text" maxlength="4" v-model="formLabelAlign.verifyCode" autocomplete="off">
                        </el-input>
                        <img :src="verifyCodeImgSrc" @click="send_code" class="verifyCodeImg" />
                    </el-form-item>
                    <el-form-item class="form_sub_button">
                        <el-button type="primary" @click="submitForm">登&nbsp;&nbsp;录</el-button>
                        <el-button @click="resetForm">注&nbsp;&nbsp;册</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </h2>
</div>
</template>

<script>
export default {
    data() {
        return {
            labelPosition: 'top',
            formLabelAlign: {
                name: 'guest',
                pass: '123123',
                type: '',
                verifyCode: ''
            },
            abc: '1111',
            rules: {
                name: [{
                    required: true,
                    message: '请输入用户名',
                    trigger: 'blur'
                }, {
                    min: 2,
                    max: 10,
                    message: '长度在 2 到 10 个字符',
                    trigger: 'blur'
                }],
                pass: [{
                    required: true,
                    message: '请输入密码',
                    trigger: 'blur'
                }, {
                    min: 6,
                    max: 16,
                    message: '长度在 6 到 16 个字符',
                    trigger: 'blur'
                }],
                verifyCode: [{
                    required: true,
                    message: '请输入验证码',
                    trigger: 'blur'
                }],
            },
            verifyCodeImgSrc: '', //验证码图片路径
        };
    },
    mounted() {
        this.getVerifyCode();
    },
    created() {
        var that = this;
        document.onkeydown = function (e) {
            var key = window.event.keyCode;
            if (key == 13) {
                that.submitForm();
            }
        }
    },
    beforeDestroy() {
        document.onkeydown = null;
    },
    methods: {
        getVerifyCode() {
            this.postRequest('/pub/valiCode', '').then(resp => {
                this.verifyCodeImgSrc = 'data:image/png;base64,' + resp.data;
            });
        },
        submitForm() {
            var that = this;
            that.postRequest('/pub/pubKey', '').then(resp => {
                var rsaPass = that.$getRsaCode(that.formLabelAlign.pass, resp.data); // ras 加密 密码 
                let params = {
                    name: that.formLabelAlign.name,
                    pass: rsaPass,
                    type: that.formLabelAlign.type,
                    verifyCode: that.formLabelAlign.verifyCode
                };
                that.postRequest('/pub/login', params).then(resp => {
                    if (resp.code == 200) {
                        let param = {
                            "user": '',
                            "roles": '',
                            "permissions": ''
                        };
                        param.user = resp.user;
                        param.roles = resp.roles;
                        param.permissions = resp.permissions;
                        that.$store.commit("setLoginUser", param);
                        that.$message.success('登陆成功！正在跳转。。。');
                        setTimeout(function () {
                            that.$router.replace('index');
                        }, 1500);
                    }
                })
            });
        },
        resetForm() {
            this.$parent.changeComp();
        },
        send_code() {
            this.getVerifyCode();
        }
    },
}
</script>

<style scoped>
.form_sub_button {
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-direction: row;
    width: 100%;
}

.form_sub_button .el-button {
    margin: 0 25px;
    padding: 12px 22px;
    font-size: 15px;
}

.verifyCodeImg {
    height: 40px;
    position: absolute;
    right: 0px;
    top: 0;
    border-radius: 4px;
    cursor: pointer;
}
</style><style>
.login-wrap .w .login-form .login-space .el-form .el-form-item>label {
    padding: 0;
}

.table {
    width: 300px;
    height: 350px;
    /* margin: 0px auto; */
    /* padding: 80px 0; */
}

.table form {
    width: 100%;
}

.table .name {
    width: 280px;
    margin: 20px auto 30px auto;
    display: block;
    height: 30px;
    border-radius: 20px;
    border: none;
    background: rgba(0, 0, 0, 0.2);
    text-indent: 0.5em;
}

.table .btn {
    width: 100px;
    height: 30px;
    background: rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    border: none;
    color: white;
    margin: 0 auto;
    display: block;
}

.login-space .el-form-item__label {
    color: #fff;
}

.verifyCodeArea .el-form-item__content {
    display: flex;
    flex-direction: row;
    /* justify-content: space-between; */
    align-items: center;
}

.verifyCodeArea .el-form-item__content .el-input {
    width: 180px;
}
</style>
