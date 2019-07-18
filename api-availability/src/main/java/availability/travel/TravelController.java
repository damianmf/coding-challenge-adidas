package availability.travel;

import availability.common.PageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TravelController {

    private Logger logger = LoggerFactory.getLogger(TravelController.class);

    private final TravelProducer travelProducer;
    private final TravelService service;

    public TravelController(TravelProducer travelProducer, TravelService service) {
        this.travelProducer = travelProducer;
        this.service = service;
    }

    @PostMapping("/cities/{id}/travels")
    public TravelDto saveTravel(@PathVariable Long id, @RequestBody TravelDto travel) throws Exception {
        logger.debug("Save travel: {}",travel);
        return service.addTravel(id, travel);
    }

    @GetMapping("travels")
    public List<TravelDto> fetchAll(PageDto page) throws Exception {
        logger.debug("Fetch all travels: {}",page);
        return service.fetchTravels(page);
    }

}
