package availability.travel;

/**
 * Created by dami on 08/07/19.
 */
public class TravelDto {
    private Long id;
    private Long source;
    private Long destiny;
    private String departureTime;
    private String arrivalTime;
    private Long duration;

    @Override
    public String toString() {
        return "TravelDto{" +
                "id=" + id +
                ", source=" + source +
                ", destiny=" + destiny +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", duration=" + duration +
                '}';
    }

    public Long getDuration() {
        return duration;
    }

    private TravelDto(Builder builder) {
        id = builder.id;
        source = builder.source;
        destiny = builder.destiny;
        departureTime = builder.departureTime;
        arrivalTime = builder.arrivalTime;
        duration = builder.duration;
    }

    public Long getId() {
        return id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getSource() {
        return source;
    }

    public Long getDestiny() {
        return destiny;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public TravelDto() {
    }


    public static final class Builder {
        private Long source;
        private Long destiny;
        private String departureTime;
        private String arrivalTime;
        private Long duration;
        private Long id;

        private Builder() {
        }

        public Builder source(Long val) {
            source = val;
            return this;
        }

        public Builder destiny(Long val) {
            destiny = val;
            return this;
        }

        public Builder departureTime(String val) {
            departureTime = val;
            return this;
        }

        public Builder arrivalTime(String val) {
            arrivalTime = val;
            return this;
        }

        public Builder duration(Long val) {
            duration = val;
            return this;
        }

        public TravelDto build() {
            return new TravelDto(this);
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withSource(Long val) {
            source = val;
            return this;
        }

        public Builder withDestiny(Long val) {
            destiny = val;
            return this;
        }

        public Builder withDepartureTime(String val) {
            departureTime = val;
            return this;
        }

        public Builder withArrivalTime(String val) {
            arrivalTime = val;
            return this;
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }
    }
}