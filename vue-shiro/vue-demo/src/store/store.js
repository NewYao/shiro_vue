// state.js
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)//全局使用vuex

export const store = new Vuex.Store({
  strict: true,//严格模式，只允许mutations中方法修改state
  state: {
    loginUser: {
      id: '',
      fullname: '',
      email: '',
      createtime: '',
      phone: '',
      state: ''
    },
    role: [],
    permissions: []
  }, getters: {//获取方法-----------this.$store.getters.saleProducts
    getloginUser: (state) => {//获取用户信息
      return state.loginUser;
    },
    getRole: (state, payload) => {//获取角色是否有
      //遍历List
      for (var index in state.role) {
        if (state.role[index] == payload) {
          return true;
        }
        return false;
      }
    },
    getPermissions: (state, payload) => {//获取权限是否有
      //遍历List
      for (var index in state.permissions) {
        if (state.permissions[index] == payload) {
          return true;
        }
        return false;
      }
    },
  },
  mutations: {//自定义方法--------this.$store.commit("setLoginUser", param);
    setLoginUser: (state, payload) => {//设置用户基础信息
      state.loginUser = payload.user;
      state.role = payload.roles;
      state.permissions = payload.permissions;
    },
    clearLogin: state => {//清除登录状态
      state.loginUser = {
        id: '',
        fullname: '',
        email: '',
        createtime: '',
        phone: '',
        state: ''
      }
      state.role = [];
      state.permissions = [];
      
    }
  },
  activated() {
    
  },
}
)