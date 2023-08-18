package com.projectbusan.bokjido.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema (description = "시설 DTO")
public class FacilityDTO {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FacilityDto {
        @Schema(description = "건물 이름", example = "전포1동주민센터")
        private String name;

        @Schema(description = "주소", example = "부산광역시 부산진구 서전로68번길 75 전포1동주민센터")
        private String location;

        @Schema(description = "전화번호", example = "051-605-4908")
        private String tel;

        @Schema(description = "카테고리", example = "행정복지센터")
        private String category;

        @Schema(description = "주차장", example = "10")
        private int parking;

        @Schema(description = "운영시간", example = "09:00-18:00,12:00-13:00")
        private String service_time;

        @Schema(description = "위도", example = "129.06388252068075")
        private String latitude;

        @Schema(description = "경도", example = "35.164077276830795")
        private String longitude;


        @Builder
        public FacilityDto(String name, String location, String tel, String category, int parking, String service_time, String latitude, String longitude) {
            this.name = name;
            this.location = location;
            this.tel = tel;
            this.category = category;
            this.parking = parking;
            this.service_time = service_time;
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
