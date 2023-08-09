package com.projectbusan.bokjido.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "Post DTO")
public class PostDTO {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostDto {
        @Schema(description = "관리자 아이디", example = "1")
        private Long admin_id;

        @Schema(description = "제목", example = "제목")
        private String title;

        @Schema(description = "카테고리", example = "복지시설")
        private String category;

        @Schema(description = "생애주기", example = "생애주기")
        private String life_cycle;

        @Schema(description = "가구상황", example = "가구상황")
        private String house_situ;

        @Schema(description = "관심주제", example = "관심주제")
        private String interest_topic;

        @Schema(description = "문의처", example = "문의처")
        private String inquiry_station;

        @Schema(description = "지원주기", example = "지원주기")
        private String apple_cycle;

        @Schema(description = "제공유형", example = "제공유형")
        private String provide_type;

        @Schema(description = "담당부처", example = "담당부처")
        private String manage_station;

        @Schema(description = "지원대상", example = "지원대상")
        private String apply_target;

        @Schema(description = "서비스 내용", example = "서비스 내용")
        private String service_content;

        @Schema(description = "신청방법", example = "신청방법")
        private String how_to_apply;

        @Schema(description = "추가정보", example = "추가정보")
        private String addi_info;


        @Builder
        public PostDto(Long admin_id, String title, String category, String life_cycle, String house_situ, String interest_topic, String inquiry_station, String apple_cycle, String provide_type, String manage_station, String apply_target, String service_content, String how_to_apply, String addi_info) {
            this.admin_id = admin_id;
            this.title = title;
            this.category = category;
            this.life_cycle = life_cycle;
            this.house_situ = house_situ;
            this.interest_topic = interest_topic;
            this.inquiry_station = inquiry_station;
            this.apple_cycle = apple_cycle;
            this.provide_type = provide_type;
            this.manage_station = manage_station;
            this.apply_target = apply_target;
            this.service_content = service_content;
            this.how_to_apply = how_to_apply;
            this.addi_info = addi_info;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoadDto {
        @Schema(description = "글 아이디")
        private Long id;

        @Schema(description = "관리자 아이디", example = "1")
        private Long admin_id;

        @Schema(description = "제목", example = "제목")
        private String title;

        @Schema(description = "생성일")
        private LocalDateTime create_date;

        @Schema(description = "카테고리", example = "복지시설")
        private String category;

        @Schema(description = "생애주기", example = "생애주기")
        private String life_cycle;

        @Schema(description = "가구상황", example = "가구상황")
        private String house_situ;

        @Schema(description = "관심주제", example = "관심주제")
        private String interest_topic;

        @Schema(description = "문의처", example = "문의처")
        private String inquiry_station;

        @Schema(description = "지원주기", example = "지원주기")
        private String apple_cycle;

        @Schema(description = "제공유형", example = "제공유형")
        private String provide_type;

        @Schema(description = "담당부처", example = "담당부처")
        private String manage_station;

        @Schema(description = "지원대상", example = "지원대상")
        private String apply_target;

        @Schema(description = "서비스 내용", example = "서비스 내용")
        private String service_content;

        @Schema(description = "신청방법", example = "신청방법")
        private String how_to_apply;

        @Schema(description = "추가정보", example = "추가정보")
        private String addi_info;


        @Builder
        public LoadDto(Long id,Long admin_id, String title, LocalDateTime create_date,String category, String life_cycle, String house_situ, String interest_topic, String inquiry_station, String apple_cycle, String provide_type, String manage_station, String apply_target, String service_content, String how_to_apply, String addi_info) {
            this.id = id;
            this.admin_id = admin_id;
            this.title = title;
            this.create_date = create_date;
            this.category = category;
            this.life_cycle = life_cycle;
            this.house_situ = house_situ;
            this.interest_topic = interest_topic;
            this.inquiry_station = inquiry_station;
            this.apple_cycle = apple_cycle;
            this.provide_type = provide_type;
            this.manage_station = manage_station;
            this.apply_target = apply_target;
            this.service_content = service_content;
            this.how_to_apply = how_to_apply;
            this.addi_info = addi_info;
        }

    }
}
