package search.itinerary;

import java.io.Serializable;

/**
 * Created by dami on 08/07/19.
 */
public class Connection implements Serializable {

    private String source;
    private String destiny;
    private String time;

    public Connection() {
    }

    public String getSource() {
        return source;
    }

    public String getDestiny() {
        return destiny;
    }

    public String getTime() {
        return time;
    }

    private Connection(Builder builder) {
        source = builder.source;
        destiny = builder.destiny;
        time = builder.time;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String source;
        private String destiny;
        private String time;

        private Builder() {
        }

        public Builder source(String val) {
            source = val;
            return this;
        }

        public Builder destiny(String val) {
            destiny = val;
            return this;
        }

        public Builder time(String val) {
            time = val;
            return this;
        }

        public Connection build() {
            return new Connection(this);
        }
    }
}
