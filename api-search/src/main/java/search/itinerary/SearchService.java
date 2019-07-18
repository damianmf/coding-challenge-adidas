package search.itinerary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import search.dijkstra.AvailabilityGraph;
import search.dijkstra.Dijkstra;
import search.dijkstra.Vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dami on 08/07/19.
 */
@Service
public class SearchService {

    private final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final AvailabilityGraph graph;

    private final RestTemplate restTemplate;
    private final SearchValidation validator;
    private final AvailabilityRepository availabilityRepository;

    public SearchService(AvailabilityGraph graph, RestTemplate restTemplate, SearchValidation validator, AvailabilityRepository availabilityRepository) {
        this.graph = graph;
        this.restTemplate = restTemplate;
        this.validator = validator;
        this.availabilityRepository = availabilityRepository;
    }

    public SearchItinerary resolveItinerary(String source, String destiny) throws Exception {

        CityDto sourceCity = availabilityRepository.getCityByName(source);
        CityDto destinyCity = availabilityRepository.getCityByName(destiny);

        validator.validate(graph, sourceCity.getId(), destinyCity.getId());

//        TODO: incomplete implementation, algorithm, return the combination according conections and time travel,
//         it is missing the logic in order to get a valid next travel (departure time bigger than last arrival time)
        Dijkstra dijkstra = new Dijkstra(graph.getEdgesMap());

//        todo: this could be run only once
        dijkstra.execute(graph.getNodes().get(sourceCity.getId()), true);
        LinkedList<Vertex> itineraryConnections = dijkstra.getPath(graph.getNodes().get(destinyCity.getId()));
        dijkstra.execute(graph.getNodes().get(sourceCity.getId()), false);
        LinkedList<Vertex> itineraryDuration = dijkstra.getPath(graph.getNodes().get(destinyCity.getId()));

        List<CityDto> connections = null, durations = null;
        if(itineraryConnections!=null && !itineraryConnections.isEmpty()){
            connections = itineraryConnections.stream()
                .map(conn -> availabilityRepository.getCityById(conn.getId()))
                .collect(Collectors.toList());
        }

        if(itineraryDuration!=null && !itineraryDuration.isEmpty()){
            durations = itineraryDuration.stream()
                    .map(conn -> availabilityRepository.getCityById(conn.getId()))
                .collect(Collectors.toList());
        }

        return SearchItinerary.newBuilder()
                .nodeSource(sourceCity.getId())
                .nodeDestiny(destinyCity.getId())
                .nodeSourceName(sourceCity.getName())
                .nodeDestinyName(destinyCity.getName())
                .connections(connections)
                .durations(durations)
                .build();
    }

    public AvailabilityGraph allItineraries() {
        return graph;
    }
}
