package orderProcessor;

import jmsTools.JMSConsumer;

public class OrderProcessor {

	public static void main(String[] args) throws Exception {
		String incomingQueueName = "mdw-hw5-all_orders_queue" ;
	
		JMSConsumer consumer = new OrderProcessorConsumer();
        consumer.receive(incomingQueueName);
	}
}
