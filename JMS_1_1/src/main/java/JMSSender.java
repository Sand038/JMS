import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSSender
{
  public static void main(String[] args) throws JMSException, NamingException
  {
    
    //    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    //    Connection connection = connectionFactory.createConnection();
    //    connection.start();
    //    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    //    Queue queue = session.createQueue("EM_TRADE.Q");
    //    MessageProducer producer = session.createProducer(queue);
    
    InitialContext context = new InitialContext();
    Connection connection = ((ConnectionFactory) context.lookup("ConnectionFactory")).createConnection();
    connection.start();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    Queue queue = (Queue) context.lookup("EM_TRADE.Q");
    MessageProducer producer = session.createProducer(queue);
    
    TextMessage message = session.createTextMessage("BUY APPLE 1000 SHARES");
    message.setStringProperty("TraderName", "Sand");
    producer.send(message);
    System.out.println("Message sent!");
    connection.close();
  }
}
