import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSAsyncReceiver implements MessageListener
{
  public JMSAsyncReceiver()
  {
    try
    {
      Connection connection = new ActiveMQConnectionFactory("tcp://localhost:61616").createConnection();
      connection.start();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      Queue queue = session.createQueue("EM_TRADE.Q");
      MessageConsumer consumer = session.createConsumer(queue);
      consumer.setMessageListener(this);
      System.out.println("Waiting for messages");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void onMessage(Message message)
  {
    try
    {
      System.out.println(((TextMessage) message).getText());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args)
  {
    new Thread()
    {
      @Override
      public void run()
      {
        new JMSAsyncReceiver();
      }
    }.start();
  }
}
