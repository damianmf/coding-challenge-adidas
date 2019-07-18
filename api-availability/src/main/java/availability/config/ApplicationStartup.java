package availability.config;

import availability.city.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by dami on 09/07/19.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    private final CityService service;

    public ApplicationStartup(CityService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
//            service.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}