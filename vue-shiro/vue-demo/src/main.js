// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vuex from 'vuex'
import { postRequest } from "./utils/http";
import { postKeyValueRequest } from "./utils/http";
import { putRequest } from "./utils/http";
import { deleteRequest } from "./utils/http";
import { getRequest } from "./utils/http";

//rsa加密密码
import JSEncrypt from 'jsencrypt';
Vue.prototype.$getRsaCode = function (str, pubKey) { // 注册方法
  let encryptStr = new JSEncrypt();
  encryptStr.setPublicKey(pubKey); // 设置 加密公钥
  let data = encryptStr.encrypt(str.toString());  // 进行加密
  return data;
}

Vue.prototype.postRequest = postRequest;
Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.getRequest = getRequest;
Vue.use(Vuex)
Vue.use(ElementUI)//全局使用ElementUI

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
