package availability.travel;

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
public class TravelProducer {
    private static final Logger logger = LoggerFactory.getLogger(TravelProducer.class);
    private static final String TOPIC = "travels";

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final KafkaTemplate<String, TravelDto> kafkaTemplateTravel;

    @Autowired
    public TravelProducer(@Qualifier("stringTemplate") KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, TravelDto> kafkaTemplateTravel) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateTravel = kafkaTemplateTravel;
    }

    public void sendMessage(TravelDto message){
        logger.info(String.format("$$ -> Producing message --> %s",message));
        Message<TravelDto> m = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();

        this.kafkaTemplateTravel.send(m);
    }
}
