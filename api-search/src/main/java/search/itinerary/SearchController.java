package search.itinerary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import search.config.StartUpService;
import search.dijkstra.AvailabilityGraph;

/**
 * Created by dami on 08/07/19.
 */

@RestController
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchService service;
    private final StartUpService startUpService;

    @Autowired
    public SearchController(SearchService service, StartUpService startUpService) {
        this.service = service;
        this.startUpService = startUpService;
    }

    @GetMapping("/search/{source}/{destiny}")
    @ResponseBody
    public SearchItinerary searchDijkstra(@PathVariable String source, @PathVariable String destiny) throws Exception {
        logger.info("Fetch source {} destiny {}", source,destiny);
        return service.resolveItinerary(source, destiny);
    }

    @GetMapping("/itineraries")
    @ResponseBody
    public AvailabilityGraph itinerary() {
        logger.info("Fetch all itineraries");
        return service.allItineraries();
    }

    @GetMapping("/refresh-cache")
    public void refresh() {
        logger.info("Refresh dijkstra structure cache");
        startUpService.clear();
        startUpService.setUpCache();
    }

}
