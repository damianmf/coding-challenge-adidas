package availability.kafka;

import availability.travel.TravelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dami on 10/07/19.
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final Producer producer;
    @Autowired
    public KafkaController(Producer producer) {
        this.producer = producer;
    }
    @GetMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);
    }


    @GetMapping(value = "/publish-travel")
    public void sendMessageToKafkaTopic2(){
        this.producer.sendMessage(TravelDto.newBuilder().source(1l).destiny(2l).build());
    }
}

