package orderProcessor;

import jmsTools.JMSConsumer;
import jmsTools.JMSProducer;

public class OrderProcessorConsumer extends JMSConsumer {

	@Override
	protected void processMessage(String msgText) throws Exception {
		String queueName;
		JMSProducer producer = new JMSProducer();
		if (isTrip(msgText)) {
			queueName = "mdw-hw5-new_trips_queue";
			System.out.println("sending to trips queue");
		} else {
			queueName = "mdw-hw5-bookings_queue";
			System.out.println("sending to bookings queue");
		}
		producer.send(queueName, msgText);
	}
	
	private boolean isTrip(String message) {
		return (message.toCharArray()[0] == 't');
	}
	
}
