package availability.kafka;

import availability.travel.TravelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by dami on 10/07/19.
 */
@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC_USERS = "users";
    private static final String TOPIC_TRAV = "trav";

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final KafkaTemplate<String, TravelDto> kafkaTemplateTravel;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate,
                    @Qualifier("travelTemplate") KafkaTemplate<String, TravelDto> kafkaTemplateTravel) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateTravel = kafkaTemplateTravel;
    }

    public void sendMessage(String message){
        logger.info(String.format("$$ -> Producing message --> %s",message));
        this.kafkaTemplate.send(TOPIC_USERS,message);
    }

    public void sendMessage(TravelDto message){
        logger.info(String.format("$$ -> Producing message --> %s",message));
        Message<TravelDto> m = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, "trav")
                .build();

        this.kafkaTemplateTravel.send(m);
    }
}
