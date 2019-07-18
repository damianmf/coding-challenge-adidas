package availability.travel;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by dami on 08/07/19.
 */
@RepositoryRestResource
public interface TravelRepository extends PagingAndSortingRepository<Travel, Long> {
}
