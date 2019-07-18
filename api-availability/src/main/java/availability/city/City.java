package availability.city;

import availability.travel.Travel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dami on 08/07/19.
 */
@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "source")
    private Set<Travel> travels;

    public City() {
    }

    private City(Builder builder) {
        id = builder.id;
        name = builder.name;
        travels = builder.travels;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Travel> getTravels() {
        return travels;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private Set<Travel> travels;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder travels(Set<Travel> val) {
            travels = val;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }
}
