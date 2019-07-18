package search.itinerary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import search.dijkstra.AvailabilityGraph;

/**
 * Created by dami on 10/07/19.
 */
@Service
public class TravelConsumer {

//    todo:externalize in properties
    public static final String TOPIC_TRAVELS = "travels";
    public static final String GROUP_ID = "group_id";
    private final Logger logger = LoggerFactory.getLogger(TravelConsumer.class);
    private final AvailabilityGraph graph;

    public TravelConsumer(AvailabilityGraph graph) {
        this.graph = graph;
    }

    @KafkaListener(topics = TOPIC_TRAVELS, groupId = GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload TravelDto message){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));
        graph.acceptTravel(message);
    }


}
