package search.itinerary;

import java.util.List;

/**
 * Created by dami on 09/07/19.
 */
public class SearchItinerary {

    private Long nodeSource;
    private Long nodeDestiny;
    private String nodeSourceName;
    private String nodeDestinyName;

    private List<CityDto> connections;
    private List<CityDto> durations;

    public SearchItinerary(Long nodeSource, Long nodeDestiny, List<CityDto> connections, List<CityDto> durations) {
        this.nodeSource = nodeSource;
        this.nodeDestiny = nodeDestiny;
        this.connections = connections;
        this.durations = durations;
    }

    private SearchItinerary(Builder builder) {
        nodeSource = builder.nodeSource;
        nodeDestiny = builder.nodeDestiny;
        nodeSourceName = builder.nodeSourceName;
        nodeDestinyName = builder.nodeDestinyName;
        connections = builder.connections;
        durations = builder.durations;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getNodeSource() {
        return nodeSource;
    }

    public Long getNodeDestiny() {
        return nodeDestiny;
    }

    public String getNodeSourceName() {
        return nodeSourceName;
    }

    public String getNodeDestinyName() {
        return nodeDestinyName;
    }


    public List<CityDto> getConnections() {
        return connections;
    }

    public List<CityDto> getDurations() {
        return durations;
    }

    public static final class Builder {
        private Long nodeSource;
        private Long nodeDestiny;
        private String nodeSourceName;
        private String nodeDestinyName;
        private List<CityDto> connections;
        private List<CityDto> durations;

        private Builder() {
        }

        public Builder nodeSource(Long val) {
            nodeSource = val;
            return this;
        }

        public Builder nodeDestiny(Long val) {
            nodeDestiny = val;
            return this;
        }

        public Builder nodeSourceName(String val) {
            nodeSourceName = val;
            return this;
        }

        public Builder nodeDestinyName(String val) {
            nodeDestinyName = val;
            return this;
        }

        public Builder connections(List<CityDto> val) {
            connections = val;
            return this;
        }

        public Builder durations(List<CityDto> val) {
            durations = val;
            return this;
        }

        public SearchItinerary build() {
            return new SearchItinerary(this);
        }
    }
}
