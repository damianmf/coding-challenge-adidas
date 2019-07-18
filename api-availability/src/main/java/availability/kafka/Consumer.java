package availability.kafka;

import availability.travel.TravelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by dami on 10/07/19.
 */
@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
    }

    @KafkaListener(topics = "trav", groupId = "group_id")
    public void consume(TravelDto message){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
    }

}
