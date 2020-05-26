<template>
<div class="table">
    <h2 style="color: white;">
        <div class="login-form">
            <div class="login-space">
                <el-form @submit.native.prevent :label-position="labelPosition" label-width="80px" :model="formLabelAlign" :hide-required-asterisk="true">
                    <el-form-item label="登录名" prop="username">
                        <el-input prefix-icon="el-icon-s-platform" v-model="formLabelAlign.username" clearable autocomplete="off">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="昵称" prop="fullname">
                        <el-input prefix-icon="el-icon-user" v-model="formLabelAlign.fullname" clearable autocomplete="off">
                        </el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input prefix-icon="el-icon-s-cooperation" type="password" show-password v-model="formLabelAlign.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                        <el-input prefix-icon="el-icon-s-comment" v-model="formLabelAlign.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="phone">
                        <el-input prefix-icon="el-icon-phone" v-model="formLabelAlign.phone" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" class="verifyCodeArea" prop="verifyCode">
                        <el-input type="text" style="width:50%" maxlength="4" v-model="formLabelAlign.verifyCode" autocomplete="off">
                        </el-input>
                        <img :src="verifyCodeImgSrc" @click="send_code" class="verifyCodeImg" />
                    </el-form-item>
                    <el-form-item class="form_sub_button">
                        <el-button type="success" @click="submitForm">注&nbsp;册</el-button>
                        <el-button @click="resetForm">返&nbsp;回</el-button>
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
            isLogin: true,
            labelPosition: 'left',
            formLabelAlign: {
                username: '',
                fullname: '',
                password: '',
                email: '',
                phone: '',
                verifyCode: ''
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
    methods: {
        getVerifyCode() {
            this.postRequest('/pub/valiCode', '').then(resp => {
                this.verifyCodeImgSrc = 'data:image/png;base64,' + resp.data;
            });
        },
        submitForm() {
            this.postRequest('/pub/pubKey', '').then(resp => {
                var rsaPass = this.$getRsaCode(this.formLabelAlign.password, resp.data); // ras 加密 密码 
                let params = {
                    username: this.formLabelAlign.username,
                    fullname: this.formLabelAlign.fullname,
                    password: rsaPass,
                    email: this.formLabelAlign.email,
                    phone: this.formLabelAlign.phone,
                    verifyCode: this.formLabelAlign.verifyCode
                };
                this.postRequest('/pub/register', params).then(resp => {
                    if (resp.code == 200) {
                        var _this = this;
                        this.$message.success('注册成功！');
                        setTimeout(function () {
                             this.$parent.changeComp();
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
