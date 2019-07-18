package availability.city;

/**
 * Created by dami on 08/07/19.
 */
public class CityDto {

    private String name;
    private Long id;

    public String getName() {
        return name;
    }

    public CityDto() {
    }

    public Long getId() {
        return id;
    }

    public CityDto(String name) {
        this.name = name;
    }

    private CityDto(Builder builder) {
        name = builder.name;
        id = builder.id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private Long id;

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

        public CityDto build() {
            return new CityDto(this);
        }
    }
}
