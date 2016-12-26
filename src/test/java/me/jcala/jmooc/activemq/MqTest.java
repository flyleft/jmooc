package me.jcala.jmooc.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;


public class MqTest {
    @Test
    public void obSend() {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer producer;
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.10.5:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("test-queue");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            MessageBean bean = new MessageBean();
            bean.setAge(13);
            for (int i = 0; i < 100; i++) {
                bean.setName("小黄" + i);
                producer.send(session.createObjectMessage(bean));
            }
            producer.close();
            System.out.println("呵呵");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void obGet() {
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.10.5:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            //这个最好还是有事务
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("test-queue");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        MessageBean bean = (MessageBean) ((ObjectMessage) message).getObject();
                        System.out.println(bean);
                        if (null != message) {
                            System.out.println("收到消息" + bean.getName());
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   /* @Test
    public void suSend(){
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer producer;
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.3.159:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            destination = session.createTopic("test-topic");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //优先级不能影响先进先出。。。那这个用处究竟是什么呢呢呢呢
            MessageBean bean = new MessageBean();
            bean.setAge(13);
            for(int i=0;i<100;i++){
                Thread.sleep(1000);
                bean.setName("小黄"+i);
                producer.send(session.createObjectMessage(bean));
            }
            producer.close();
            System.out.println("呵呵");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}