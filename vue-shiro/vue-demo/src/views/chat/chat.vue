<template>
  <div>
    <span>我的ID：</span>
    <input v-model="userid" placeholder="目标用户id" />
    <button @click="initSocket">建立websocket连接</button>
    <div>
      <span>发送给：</span>
      <input v-model="toUserId" placeholder="目标用户id" />
    </div>
    <div class="chatMainArea">
      <div class="toSendUser">{{toUserId}}</div>
      <div class="chatMsgArea">
        <!-- <textarea v-model="receiveMsg"></textarea> -->
      </div>
      <div class="sendMsgInputArea">
        <input v-model="contentText" placeholder="消息" class="msgInput" />
        <button @click="sendMsg" class="sendBtn">发送消息</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {

  data() {
    return {
      webSocket: null,
      url: '192.168.0.69',
      types: 'websocket',
      userid: '',
      toUserId: '',
      contentText: '',
      receiveMsg: ''
    }
  },
  methods: {
    sendMsg() {
      let sendMsgTemplate = [{
        "toUserId": this.toUserId,
        "contentText": this.contentText,
      }]
      //给后台发送数据
      this.webSocket.send(JSON.stringify(sendMsgTemplate));
      var chatMsgArea = document.querySelectorAll('.chatMsgArea')[0];
      var msgDiv = document.createElement('div');
      msgDiv.className = "sendMsgDiv_right";
      msgDiv.innerHTML = "<span class='msgSpan'>" + this.contentText + "</span>";
      chatMsgArea.appendChild(msgDiv);
    },
    // 建立连接
    initSocket() {
      // 有参数的情况下：
      let url = `ws://${this.url}/${this.types}/${this.userid}`
      // 没有参数的情况:接口
      //  let url1 = 'ws://192.168.0.69'
      this.webSocket = new WebSocket(url)
      this.webSocket.onopen = this.webSocketOnOpen
      this.webSocket.onclose = this.webSocketOnClose
      this.webSocket.onmessage = this.webSocketOnMessage
      this.webSocket.onerror = this.webSocketOnError

    },
    // 建立连接成功后的状态
    webSocketOnOpen() {
      console.log('websocket连接成功');
    },
    // 获取到后台消息的事件，操作数据的代码在onmessage中书写
    webSocketOnMessage(res) {
      // res就是后台实时传过来的数据
      console.log("接收消息", res);
      var chatMsgArea = document.querySelectorAll('.chatMsgArea')[0];
      var msgDiv = document.createElement('div');
      msgDiv.className = "sendMsgDiv_left";
      msgDiv.innerHTML = "<span class='msgSpan'>" + JSON.parse(res.data).contentText + "</span>";
      chatMsgArea.appendChild(msgDiv);
      chatMsgArea.scrollTop = chatMsgArea.scrollHeight;
      //给后台发送数据
      //this.webSocket.send(sendMsgTemplate);
    },
    // 关闭连接
    webSocketOnClose() {
      this.webSocket.close()
      console.log('websocket连接已关闭');
    },
    //连接失败的事件
    webSocketOnError(res) {
      console.log('websocket连接失败');
      // 打印失败的数据
      console.log(res);
    }
  },
  created() {
    // 页面打开就建立连接，根据业务需要
    //this.initSocket()
  },
  destroyed() {
    // 页面销毁关闭连接
    this.webSocket.close()
  },
}
</script>

<style scoped>
.toSendUser{
  height: 20px;
  text-align: center;
}
.chatMainArea {
  margin: 20px auto;
  width: 400px;
  border: 1px solid #000;
  padding: 10px;
  height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.sendMsgInputArea {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}
.sendBtn {
  width: 70px;
  padding: 5px 0;
  border-radius: 8px;
  background-color: skyblue;
  color: #fff;
  border: 0;
}
.msgInput {
  width: calc(100% - 90px);
  padding: 5px 5px;
}
.chatMsgArea {
  width: 100%;
  height: calc(100% - 70px);
  overflow-y: auto;
}
/* 不同浏览器隐藏滚动条 */
.chatMsgArea::-webkit-scrollbar { width: 0 !important }
.chatMsgArea { -ms-overflow-style: none; }
.chatMsgArea { overflow: -moz-scrollbars-none; }
/*  */

</style>
<style>
.sendMsgDiv_right {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  margin: 10px 0;
}
.sendMsgDiv_left {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  margin: 10px 0;
}
.msgSpan{
  border: 1px solid grey;
  border-radius: 8px;
  padding: 5px 20px;
  max-width: 200px;
  word-break: break-all;
}
</style>
