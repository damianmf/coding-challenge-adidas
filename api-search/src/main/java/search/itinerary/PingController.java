package search.itinerary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dami on 08/07/19.
 */

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

}
