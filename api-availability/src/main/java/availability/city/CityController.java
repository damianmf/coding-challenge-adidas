package availability.city;

import availability.common.PageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dami on 08/07/19.
 */
@RestController
public class CityController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping("/city")
    public List<CityDto> fetchAll(PageDto page) {
        logger.debug("Fetch all cities {}", page);
        return service.get(page);
    }

    @GetMapping("/city/{id}")
    public CityDto fetch(@PathVariable Long id) throws Exception {
        logger.debug("Fetch all city with id {}", id);
        return service.get(id);
    }

//    TODO: ideally this is not restfull/ this name should be unique
    @GetMapping("/city-name/{id}")
    public CityDto fetchByName(@PathVariable String id) throws Exception {
        logger.debug("Fetch all city with name {}", id);
        return service.getByName(id);
    }

    @PostMapping("/city")
    public CityDto save(@RequestBody CityDto city) {
        logger.debug("Save city {}", city);
        CityDto persisted = service.save(city);
        return persisted;
    }

    @GetMapping("/city/init")
    public void init() throws Exception {
        logger.debug("Initialize db with mock data");
        service.init();
    }
}