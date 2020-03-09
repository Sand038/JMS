import com.sun.messaging.ConnectionFactory;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

public class JMS2SharedSubscriber implements MessageListener
{
  
  public JMS2SharedSubscriber()
  {
    try
    {
      ConnectionFactory connectionFactory = new ConnectionFactory();
      JMSContext context = connectionFactory.createContext();
      Topic topic = context.createTopic("EM_JMS2_TRADE.T");
      
      JMSConsumer consumer = context.createSharedConsumer(topic, "sub:3e");
      
      consumer.setMessageListener(this);
      System.out.println("Waiting On Messages");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  @Override
  public void onMessage(Message message)
  {
    try
    {
      System.out.println(message.getBody(String.class) + ", Trader name = " + message.getStringProperty("TraderName"));
    }
    catch (JMSException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args)
  {
    new Thread(JMS2SharedSubscriber::new).start();
  }
}
