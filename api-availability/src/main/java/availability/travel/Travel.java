package availability.travel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by dami on 08/07/19.
 */
@Entity
public class Travel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Long source;
    @Column
    private Long destiny;
    @Column
    private LocalDateTime departureTime;
    @Column
    private LocalDateTime arrivalTime;
    @Column
    private Long duration;


    public Long getDuration() {
        return duration;
    }

    public Long getId() {
        return id;
    }

    public Long getSource() {
        return source;
    }

    public Long getDestiny() {
        return destiny;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    private Travel(Builder builder) {
        id = builder.id;
        source = builder.source;
        destiny = builder.destiny;
        departureTime = builder.departureTime;
        arrivalTime = builder.arrivalTime;
        duration = builder.duration;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Travel() {
    }

    public static final class Builder {
        private Long source;
        private Long destiny;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
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

        public Builder departureTime(LocalDateTime val) {
            departureTime = val;
            return this;
        }

        public Builder arrivalTime(LocalDateTime val) {
            arrivalTime = val;
            return this;
        }

        public Builder duration(Long val) {
            duration = val;
            return this;
        }

        public Travel build() {
            return new Travel(this);
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

        public Builder withDepartureTime(LocalDateTime val) {
            departureTime = val;
            return this;
        }

        public Builder withArrivalTime(LocalDateTime val) {
            arrivalTime = val;
            return this;
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }
    }
}
