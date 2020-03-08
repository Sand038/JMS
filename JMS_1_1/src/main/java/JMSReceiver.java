import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSReceiver
{
  public static void main(String[] args) throws JMSException
  {
    Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
    connection.start();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Queue queue = session.createQueue("EM_TRADE.Q");
    MessageConsumer consumer = session.createConsumer(queue);
    
    TextMessage message = (TextMessage) consumer.receive(); //receiveNull
    
    System.out.println(message.getText() + ", Trader = " + message.getStringProperty("TraderName"));
    connection.close();
  }
}
