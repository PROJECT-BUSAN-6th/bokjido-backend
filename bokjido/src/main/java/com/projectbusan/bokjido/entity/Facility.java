package com.projectbusan.bokjido.entity;

import com.projectbusan.bokjido.dto.FacilityDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String location;

    private String tel;

    private String category;

    private int parking;

    private String service_time;

    private String latitude;

    private String longitude;


    @Builder
    public Facility(Long id, String name, String location, String tel, String category, int parking, String service_time, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.tel = tel;
        this.category = category;
        this.parking = parking;
        this.service_time = service_time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Facility create(FacilityDTO.FacilityDto facilityDto) {
        return Facility.builder()
                .name(facilityDto.getName())
                .location(facilityDto.getLocation())
                .tel(facilityDto.getTel())
                .category(facilityDto.getCategory())
                .parking(facilityDto.getParking())
                .service_time(facilityDto.getService_time())
                .latitude(facilityDto.getLatitude())
                .longitude(facilityDto.getLongitude())
                .build();
    }
}
