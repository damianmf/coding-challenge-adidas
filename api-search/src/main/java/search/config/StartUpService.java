package search.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import search.dijkstra.AvailabilityGraph;

import java.net.URI;
import java.util.List;

@Service
public class StartUpService {

    private static final String PAGE = "page";
    private final AvailabilityGraph graph;
    private final RestTemplate restTemplate;
    @Value("${adidas.challenge.city.server}")
    private String availabilityUrl;

    private Logger logger = LoggerFactory.getLogger(StartUpService.class);

    public StartUpService(AvailabilityGraph graph, RestTemplate restTemplate) {
        this.graph = graph;
        this.restTemplate = restTemplate;
    }

    public void setUpCache() {
        try {
            int page = 0;
            URI targetUrl = getUri(page);
            List result = restTemplate.getForObject(targetUrl, List.class);
            graph.updateGraph(result);
            while (!result.isEmpty()) {
                page++;
                targetUrl= getUri(page);
                result = restTemplate.getForObject(targetUrl, List.class);
                graph.updateGraph(result);
            }
        }catch (Exception ex){
            logger.error("Cache initialization could not be completed, view fallback", ex);
        }finally {
            logger.info("End process");
        }
    }

    private URI getUri(int page) {
        return UriComponentsBuilder.fromUriString("http://"+availabilityUrl+"/travels")
                        .queryParam(PAGE, page)
                        .build()
                        .encode()
                        .toUri();
    }

    public void clear() {
        graph.getNodes().clear();
        graph.getEdges().clear();
        graph.getEdgesMap().clear();
    }
}
