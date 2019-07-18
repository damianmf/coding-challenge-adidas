package search.itinerary;

import org.springframework.stereotype.Service;
import search.dijkstra.AvailabilityGraph;
import search.exception.AdidasApiException;

import java.util.Optional;

@Service
public class SearchValidation {
    public void validate(AvailabilityGraph graph, Long source, Long destiny) throws AdidasApiException {
        if(!Optional.ofNullable(graph.getNodes().get(destiny)).isPresent())
            throw new AdidasApiException(String.format("City not found %d",destiny));

        if(!Optional.ofNullable(graph.getNodes().get(source)).isPresent())
            throw new AdidasApiException(String.format("City not found %d",source));
    }
}
