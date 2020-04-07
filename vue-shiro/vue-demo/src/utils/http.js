import axios from 'axios';
import { Message } from 'element-ui';

//请求超时时间
axios.defaults.timeout = 10000;
//默认请求头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
//默认携带缓存头
axios.defaults.withCredentials = true
// Set config defaults when creating the instance

let base = 'http://127.0.0.1';


// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    // console.log('before--ok');
    // console.log(config);
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    // console.log('before--err');
    // return Promise.reject(error);
  });


// 添加响应拦截器
axios.interceptors.response.use(success => {
    // console.log('after--ok');
    // console.log(success)
    if(success.status && success.status == 200 && success.data.code == 500){
        Message.error({message:error.data.msg})
        return;
    }
    if (success.data.msg) {
        Message.success({message: success.data.msg})
    }
    return success.data;
}, error => {
    console.log('after--err');
    console.log(error);
    return;
});

export const postRequest = (url, params) => {
    return axios({
        method: 'POST',
        url: `${base}${url}`,
        data: params
    })
}

export const putRequest = (url, params) => {
    return axios({
        method: 'PUT',
        url: `${base}${url}`,
        data: params
    })
}

export const getRequest = (url, params) => {
    return axios({
        method: 'GET',
        url: `${base}${url}`,
        data: params
    })
}

export const deleteRequest = (url, params) => {
    return axios({
        method: 'DELETE',
        url: `${base}${url}`,
        data: params
    })
}


/**
 * 用于处理账号密码加密
 * @param {*} url 
 * @param {*} params 
 */
export const postKeyValueRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        transformRequest: [function (data) {
            let ret = '';
            for (let i in data) {
                ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
            }
            return ret;
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
}