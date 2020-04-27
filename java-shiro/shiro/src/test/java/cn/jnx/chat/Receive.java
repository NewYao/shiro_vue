package cn.jnx.chat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receive {
    /** 消息服务器的连接地址 **/
    public static final String BROKER_URL = "tcp://212.64.5.222:61616";

    public static void main(String[] args) {
        Receive receiver = new Receive();
        receiver.receiveMessage();
    }

    /**
     * 接收消息
     *
     */
    public void receiveMessage() {

        Connection connection = null;
        Session session = null;
        MessageConsumer messageConsumer = null;

        try {
            // 1.创建一个连接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

            // 2.创建一个连接
            connection = connectionFactory.createConnection();

            // 3.创建一个Session
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

            // 4.创建一个目的地
            Destination destination = session.createQueue("myQueue");

            // 5.创建一个消息的消费者（接收者）
            // selector即为消息选择器,通过选择需要的标识,过滤消息接受id为10-15之 //间的消息
            // String selector = "id >=10 and id<=15";
            // messageConsumer = session.createConsumer(destination);
            String selector = "id = 1";
            messageConsumer = session.createConsumer(destination, selector);

            // 接收消息之前，需要把连接启动一下
            connection.start();

            // 6.接收消息(同步接收)
//            Message message = messageConsumer.receive();
            // 异步接收，使用监听器接收消息
            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    // 判断消息的类型
                    if (message instanceof TextMessage) { // 判断是否是文本消息
                        String text = null;
                        try {
                            text = ((TextMessage) message).getText();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                        System.out.println("接收到的消息内容是：" + text);
                    }
                }
            });
            // 判断消息的类型
//            if (message instanceof TextMessage) { // 判断是否是文本消息
//                String text = ((TextMessage) message).getText();
//                System.out.println("接收到的消息内容是：" + text);
//            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接释放资源
                if (null != messageConsumer) {
                    messageConsumer.close();
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
