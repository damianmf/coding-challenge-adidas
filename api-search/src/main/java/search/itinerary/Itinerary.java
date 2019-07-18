package search.itinerary;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dami on 08/07/19.
 */
public class Itinerary implements Serializable {

    private String name;
    private List<Connection> connections;

    public String getName() {
        return name;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    private Itinerary(Builder builder) {
        name = builder.name;
        connections = builder.connections;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private List<Connection> connections;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder connections(List<Connection> val) {
            connections = val;
            return this;
        }

        public Itinerary build() {
            return new Itinerary(this);
        }
    }
}
