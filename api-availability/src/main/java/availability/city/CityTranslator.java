package availability.city;

import org.springframework.stereotype.Service;

@Service
public class CityTranslator {

    public CityDto toDto(City domain){
        return CityDto.newBuilder().name(domain.getName()).id(domain.getId()).build();
    }

    public City toDomain(CityDto dto){
        return City.newBuilder().name(dto.getName()).id(dto.getId()).build();
    }

}
