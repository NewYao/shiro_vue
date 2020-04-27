package cn.jnx.chat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Send {
    /** 消息服务器的连接地址 **/
    public static final String BROKER_URL = "tcp://212.64.5.222:61616";

    public static void main(String[] args) {
        for(int i =0 ;i<100;i++) {
            try {
                Send sender = new Send();
                sender.sendMessage("Hello ActiveMQ."+i);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(String msg) {

        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;

        try {
            // 1.创建一个连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

            // 2.创建一个连接
            connection = connectionFactory.createConnection();

            // 3.创建一个Session
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            // 4.创建消息，此处创建了一个文本消息
            Message message = session.createTextMessage(msg);
            message.setIntProperty("id", 1);
            // 5.创建一个目的地
            Destination destination = session.createQueue("myQueue");
            
            // 6.创建一个消息的生产者（发送者）
            messageProducer = session.createProducer(destination);

            // 7.发送消息
            messageProducer.send(message);

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接释放资源
                if (null != messageProducer) {
                    messageProducer.close();
                }
                if (null != session) {
                    session.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
