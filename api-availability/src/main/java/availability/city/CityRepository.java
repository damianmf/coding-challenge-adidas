package availability.city;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by dami on 08/07/19.
 */
@RepositoryRestResource
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    Optional<City> findByName(String id);
}
