package search.config.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import search.config.StartUpService;

/**
 * Created by dami on 09/07/19.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    private StartUpService service;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        logger.info("Setup cache");
        service.setUpCache();
    }

}