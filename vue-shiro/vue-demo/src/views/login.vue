<template>

  <div class="bodyArea">
    <div class="stars"></div>

    <div class="table">
      <h2 style="color: white;">
        <div class="login-form">
          <div class="login-space">
            <el-form @submit.native.prevent :label-position="labelPosition" label-width="80px"
              :model="formLabelAlign" :rules="rules" :hide-required-asterisk="true">
              <el-form-item label="用户名" prop="name">
                <el-input prefix-icon="el-icon-user" v-model="formLabelAlign.name" clearable autocomplete="off">
                </el-input>
              </el-form-item>
              <el-form-item label="密码" prop="pass">
                <el-input prefix-icon="el-icon-s-cooperation" type="password"
                  show-password v-model="formLabelAlign.pass" autocomplete="off"></el-input>
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
      <div
        style="width: 100%;height: 200px;background: url(image/1000141.jpg) no-repeat;background-size: 100%;opacity: 0.5;">
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Qs from 'qs';
export default {
  data() {
    return {
      labelPosition: 'top',
      formLabelAlign: {
        name: 'admin',
        pass: '111111',
        type: '',
        verifyCode: ''
      },
      abc: '1111',
      rules: {
        name: [{ required: true, message: '请输入用户名', trigger: 'blur' }, { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }],
        pass: [{ required: true, message: '请输入密码', trigger: 'blur' }, { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }],
        verifyCode: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
      },
      verifyCodeImgSrc: '',//验证码图片路径
    };
  },
  mounted() {
    this.initStars();
    this.getVerifyCode();
  },
  methods: {
    initStars() {
      var stars = 800; /*星星的密集程度，数字越大越多*/
      var $stars = $(".stars");
      var r = 800; /*星星的看起来的距离,值越大越远,可自行调制到自己满意的样子*/
      for (var i = 0; i < stars; i++) {
        var $star = $("<div/>").addClass("star");
        $stars.append($star);
      }
      $(".star").each(function () {
        var cur = $(this);
        var s = 0.2 + (Math.random() * 1);
        var curR = r + (Math.random() * 300);
        cur.css({
          transformOrigin: "0 0 " + curR + "px",
          transform: " translate3d(0,0,-" + curR + "px) rotateY(" + (Math.random() *
            360) + "deg) rotateX(" + (Math.random() * -50) + "deg) scale(" + s + "," +
            s + ")"
        })
      })
    },
    getVerifyCode() {
      this.postRequest('/valiCode', '').then(resp => {
        this.verifyCodeImgSrc = 'data:image/png;base64,' + resp.data;
      });
    },
    submitForm() {
      this.postRequest('/pubKey', '').then(resp => {
        var rsaPass = this.$getRsaCode(this.formLabelAlign.pass, resp.data); // ras 加密 密码 
        let params = {
          name: this.formLabelAlign.name,
          pass: rsaPass,
          type: this.formLabelAlign.type,
          verifyCode: this.formLabelAlign.verifyCode
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
      this.getVerifyCode();
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

.verifyCodeImg {
  height: 40px;
  position: absolute;
  right: 0px;
  top: 0;
  border-radius: 4px;
  cursor: pointer;
}
</style>
<style>
.login-wrap .w .login-form .login-space .el-form .el-form-item > label {
  padding: 0;
}

.bodyArea {
  width:100%;
  height: 100%;
  background: radial-gradient(
    200% 100% at bottom center,
    #f7f7b6,
    #e96f92,
    #75517d,
    #1b2947
  );
  background: radial-gradient(
    220% 105% at top center,
    #1b2947 10%,
    #75517d 40%,
    #e96f92 65%,
    #f7f7b6
  );
  background-attachment: fixed;
  overflow: hidden;
  position: relative;
}

.stars {
  transform: perspective(500px);
  transform-style: preserve-3d;
  position: absolute;
  bottom: 0;
  perspective-origin: 50% 100%;
  left: 50%;
  animation: rotate 90s infinite linear;
}

.star {
  width: 2px;
  height: 2px;
  background: #f7f7b6;
  position: absolute;
  top: 0;
  left: 0;
  transform-origin: 0 0 -300px;
  transform: translate3d(0, 0, -300px);
  backface-visibility: hidden;
}
.table {
  width: 300px;
  height: 350px;
  margin: 0px auto;
  padding: 80px 0;
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
@keyframes rotate {
  0% {
    transform: perspective(400px) rotateZ(20deg) rotateX(-40deg) rotateY(0);
  }
  100% {
    transform: perspective(400px) rotateZ(20deg) rotateX(-40deg)
      rotateY(-360deg);
  }
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
