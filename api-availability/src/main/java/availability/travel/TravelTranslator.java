package availability.travel;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TravelTranslator {

    public TravelDto toDto(Travel domain){
        return TravelDto.newBuilder()
                .destiny(domain.getDestiny())
                .source(domain.getSource())
                .arrivalTime(domain.getArrivalTime().toString())
                .departureTime(domain.getDepartureTime().toString())
                .duration(domain.getDuration())
                .build();
    }

    public Travel toDomain(TravelDto dto){
        return Travel.newBuilder()
                .destiny(dto.getDestiny())
                .source(dto.getSource())
                .arrivalTime(LocalDateTime.parse(dto.getArrivalTime()))
                .departureTime(LocalDateTime.parse(dto.getDepartureTime()))
                .build();
    }
}
