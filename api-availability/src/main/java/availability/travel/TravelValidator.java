package availability.travel;

import availability.city.CityRepository;
import availability.exception.AdidasApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TravelValidator {

    private final CityRepository repository;

    @Autowired
    public TravelValidator(CityRepository repository) {
        this.repository = repository;
    }

    public void validate(Long id, TravelDto travel) throws AdidasApiException {

        if(!Optional.ofNullable(travel.getSource()).isPresent()
                || !Optional.ofNullable(travel.getDestiny()).isPresent())
            throw new AdidasApiException("Invalid data");

        if(travel.getSource() != id)
            throw new AdidasApiException("Inconsistent data");

        if(!repository.existsById(travel.getSource()))
            throw new AdidasApiException(String.format("Source %d does not exist", travel.getSource()));

        if(!repository.existsById(travel.getDestiny()))
            throw new AdidasApiException(String.format("Destiny %d does not exist", travel.getDestiny()));

    }
}
