package cn.jnx.model;

import java.util.Date;

public class ChatTemplate {
    // 消息id
    private Integer msgId;
    // 发送人id
    private Integer fromId;
    // 接收人id
    private Integer toId;
    // 发送消息主体
    private String sendText;
    // 发送时间
    private Date sendTime;
    // 消息类型
    private Integer type;
    // 消息状态
    private Integer msg_state;
    // 消息更新时间
    private Date updateTime;

    @Override
    public String toString() {
        return "ChatTemplate [msgId=" + msgId + ", fromId=" + fromId + ", toId=" + toId + ", sendText=" + sendText
                + ", sendTime=" + sendTime + ", type=" + type + ", msg_state=" + msg_state + ", updateTime="
                + updateTime + "]";
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getSendText() {
        return sendText;
    }

    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMsg_state() {
        return msg_state;
    }

    public void setMsg_state(Integer msg_state) {
        this.msg_state = msg_state;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
