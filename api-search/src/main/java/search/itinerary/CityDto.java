package search.itinerary;

import java.util.Set;

/**
 * Created by dami on 08/07/19.
 */
public class CityDto {

    private String name;
    private Long id;
    private Set<TravelDto> travels;

    public CityDto() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Set<TravelDto> getTravels() {
        return travels;
    }

    private CityDto(Builder builder) {
        name = builder.name;
        id = builder.id;
        travels = builder.travels;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private Long id;
        private Set<TravelDto> travels;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder travels(Set<TravelDto> val) {
            travels = val;
            return this;
        }

        public CityDto build() {
            return new CityDto(this);
        }
    }
}
