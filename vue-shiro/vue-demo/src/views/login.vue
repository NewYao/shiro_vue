<template>
  <el-container>
    <el-header>
      <div class="header_main">
        <span>马小旭的春天</span>
      </div>
    </el-header>
    <el-main>
      <div class="tips-wrap"></div>
      <div class="login-wrap">
        <div class="w">
          <div class="login-form">
            <div class="login-space">
              <el-form @submit.native.prevent :label-position="labelPosition" label-width="80px"
                :model="formLabelAlign">
                <el-form-item label="用户名" prop="name">
                  <el-input prefix-icon="el-icon-user" v-model="formLabelAlign.name" clearable>
                  </el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                  <el-input prefix-icon="el-icon-s-cooperation" type="password" clearable
                    show-password v-model="formLabelAlign.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="验证码">
                  <el-input type="text" maxlength="4" v-model="formLabelAlign.type" clearable>
                    <el-button slot="append" @click="send_code">发送</el-button>
                  </el-input>
                </el-form-item>
                <el-form-item class="form_sub_button">
                  <el-button type="primary" @click="submitForm">登&nbsp;&nbsp;录</el-button>
                  <el-button @click="resetForm">注&nbsp;&nbsp;册</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
        </div>
      </div>
      <div class="footer_data">
        <el-progress type="circle" :percentage="0"></el-progress>
        <el-progress type="circle" :percentage="25"></el-progress>
        <el-progress type="circle" :percentage="100" status="success"></el-progress>
        <el-progress type="circle" :percentage="50" status="exception"></el-progress>
      </div>
      <div class="footer_data_down">
        <span>1</span>
        <span>2</span>
        <span>3</span>
        <span>4</span>
      </div>
    </el-main>
    <el-footer class="login_footer">
      版权所有&copy;2020-2030
    </el-footer>
  </el-container>
</template>

<script>
import axios from 'axios';
import Qs from 'qs';
export default {
  data() {
    return {
      labelPosition: 'top',
      formLabelAlign: {
        name: 'admin1',
        pass: '111111',
        type: ''
      },
      abc: '1111',
    };
  },
  methods: {
    submitForm() {
      this.postRequest('/pubKey', '').then(resp => {
        var rsaPass = this.$getRsaCode(this.formLabelAlign.pass, resp.data); // ras 加密 密码 
        let params = {
            name: this.formLabelAlign.name,
            pass: rsaPass,
            type: this.formLabelAlign.type
        };
        this.postRequest('/login', params).then(resp => {
          if (resp.code == 200) {
            var _this = this;
            this.$message.success('登陆成功！正在跳转。。。');
            setTimeout(function () {
              _this.$router.replace('index');
            }, 1500);
          }
        })
      });

    },
    resetForm() {
      console.log('resetForm!');
    },
    send_code() {
      console.log('send_code!');
      this.$message.error('错了哦，这是一条错误消息');
    }
  }
};
</script>

<style scoped>
.el-header {
  background: rgb(62, 94, 199);
}

.el-header .header_main {
  width: 1200px;
  margin: 0 auto;
  height: 100%;
  vertical-align: middle;
}

.el-header .header_main span {
  line-height: 60px;
  height: 60px;
  font-size: 20px;
  color: #fff;
}

.tips-wrap {
  background-image: url("/static/login_bg.jpg");
  height: 450px;
}

.el-main {
  padding: 0;
  position: relative;
}

.login-wrap {
  position: absolute;
  height: 450px;
  z-index: 5;
  top: 0;
  left: 50%;
  transform: translate(-50%, 0);
}

.login-wrap .w {
  width: 1200px;
  margin: 0 auto;
  position: relative;
}

.login-wrap .w .login-form {
  position: relative;
  z-index: 4;
  background: #fff;
  width: 346px;
  float: right;
  top: 20px;
}

.login-wrap .w .login-form .login-space {
  padding: 10px 20px 10px 20px;
  position: relative;
  overflow: hidden;
}

.login-wrap .w .login-form .login-space .el-form .el-form-item {
  margin-bottom: 15px;
}

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

.footer_data {
  margin: 0 auto;
  width: 1200px;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
  margin-top: 20px;
}

.footer_data_down {
  margin: 0 auto;
  width: 1200px;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
  align-items: center;
  margin-top: 20px;
}

.login_footer {
  position: absolute;
  bottom: 0;
  background-color: rgb(146, 138, 138);
  width: 100%;
  text-align: center;
  line-height: 40px;
  height: 40px !important;
}
</style>
<style>
.login-wrap .w .login-form .login-space .el-form .el-form-item > label {
  padding: 0;
}
</style>
