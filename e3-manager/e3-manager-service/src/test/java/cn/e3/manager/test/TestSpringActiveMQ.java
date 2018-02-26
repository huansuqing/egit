package cn.e3.manager.test;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TestSpringActiveMQ {

	@Test
	public void testSpringActiveMQ(){
		//初始化容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/activeMQ.xml");
		//获取jmsTemplate对象
		JmsTemplate jmsTemplate = ac.getBean(JmsTemplate.class);
		//获取具体的destination对象根据id进行取值 即消息对列对象 destnation是queue和topic的顶层父级接口
		Destination destination = (Destination) ac.getBean("test-queue");
		//获取到消息对列以后 用模板进行消息的发送 (往哪个对列发送什么消息)
		jmsTemplate.send(destination, new MessageCreator() {
			//进行消息的发送 返回值是消息
			@Override
			public Message createMessage(Session session) throws JMSException {
				//创建一个消息文本对象(使用session进行创建)
				TextMessage textMessage = session.createTextMessage("这是spring整合activeMq的queue消息");
				//将消息进行返回
				return textMessage;
			}
		});
	}
	
	@Test
	public void testSpringActiveMQ1(){
		//初始化容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/activeMQ.xml");
		//获取jmsTemplate对象
		JmsTemplate jmsTemplate = ac.getBean(JmsTemplate.class);
		//获取具体的destination对象根据id进行取值 即消息对列对象 destnation是queue和topic的顶层父级接口
		Destination destination = (Destination) ac.getBean("test-queue");
		//获取到消息对列以后 用模板进行消息的发送 (往哪个对列发送什么消息)
		jmsTemplate.send(destination, new MessageCreator() {
			//进行消息的发送 返回值是消息
			@Override
			public Message createMessage(Session session) throws JMSException {
				//创建一个消息文本对象(使用session进行创建)
				TextMessage textMessage = session.createTextMessage("这是spring整合activeMq的Topic消息");
				//将消息进行返回
				return textMessage;
			}
		});
	}
}
