import com.sun.messaging.ConnectionFactory;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

public class JMS2Receiver
{
  public static void main(String[] args)
  {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    try (JMSContext context = connectionFactory.createContext();)
    {
      Queue queue = context.createQueue("EM_JMS2_TRADE.T");
      Message message = context.createConsumer(queue).receive();
      System.out.println(message.getBody(String.class) + ", Trader name = " + message.getStringProperty("TraderName"));
    }
    catch (JMSException e)
    {
      e.printStackTrace();
    }
  }
}
