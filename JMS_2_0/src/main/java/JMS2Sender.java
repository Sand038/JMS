import com.sun.messaging.ConnectionFactory;

import javax.jms.DeliveryMode;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

public class JMS2Sender
{
  public static void main(String[] args)
  {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    try (JMSContext context = connectionFactory.createContext();)
    {
//      Queue queue = context.createQueue("EM_JMS2_TRADE.Q");
      Topic topic = context.createTopic("EM_JMS2_TRADE.T");
      context
        .createProducer()
        .setProperty("TraderName", "Sand")
        .setDeliveryMode(DeliveryMode.NON_PERSISTENT)
        .send(topic, "SELL APPLE 1500 SHARES");
      System.out.println("Message sent!");
    }
  }
}
