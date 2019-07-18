package search.dijkstra;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import search.itinerary.TravelDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by dami on 09/07/19.
 */
@Service
public class AvailabilityGraph {

    private final ObjectMapper objectMapper;

    private HashMap<Long,Vertex> nodes;
    private List<Edge> edges;
    private Graph graph;
//    TODO: Initial version, matrix structure saved on the same node, ideally persist on redis/memcached in order to
//     have full integrity between nodes synchronization
    private HashMap<Long,Set<Edge>> edgesMap;

    @Autowired
    public AvailabilityGraph(ObjectMapper objectMapper) {
        nodes = new HashMap<>();
        edges = new ArrayList<Edge>();
        graph = new Graph();
        edgesMap = new HashMap<>();
        this.objectMapper = objectMapper;
    }

    public HashMap<Long, Set<Edge>> getEdgesMap() {
        return edgesMap;
    }

    public Graph getGraph() {
        return graph;
    }

    public HashMap<Long,Vertex> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        edges.add(lane);
    }

    public void updateGraph(List<TravelDto> result) {
        List<TravelDto> resultConverted = objectMapper.convertValue(result,new TypeReference<List<TravelDto>>(){});
        resultConverted.stream().forEach(this::acceptTravel);
    }

    private void updateEdge(TravelDto travel, Long source) {
        Edge lane = new Edge(source.toString().concat("-").concat(travel.getDestiny().toString()),
                nodes.get(source), nodes.get(travel.getDestiny()), Math.toIntExact(travel.getDuration()));
        edges.add(lane);
        Set<Edge> edges = edgesMap.get(source);
        edges.add(lane);
    }

    public void acceptTravel(TravelDto travel) {
        if (nodes.containsKey(travel.getSource())) {
            if (!nodes.containsKey(travel.getDestiny())) {
                Vertex location = new Vertex(travel.getDestiny(), travel.getDestiny().toString());
                nodes.put(travel.getDestiny(), location);
                edgesMap.put(travel.getDestiny(), Sets.newHashSet());
            }

            updateEdge(travel, travel.getSource());
        } else {
            Vertex location = new Vertex(travel.getSource(), travel.getSource().toString());
            nodes.put(travel.getSource(), location);

            edgesMap.put(travel.getSource(), Sets.newHashSet());

            if (!nodes.containsKey(travel.getDestiny())) {
                Vertex d = new Vertex(travel.getDestiny(), travel.getDestiny().toString());
                nodes.put(travel.getDestiny(), d);
                edgesMap.put(travel.getDestiny(), Sets.newHashSet());
            }
            updateEdge(travel, travel.getSource());
        }
    }
}
