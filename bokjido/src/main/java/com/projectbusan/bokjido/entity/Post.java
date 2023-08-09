package com.projectbusan.bokjido.entity;

import com.projectbusan.bokjido.dto.PostDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private Long admin_id;

    private String title;

    private LocalDateTime create_date;

    private String category;

    private String life_cycle;

    private String house_situ;

    private String interest_topic;

    private String inquiry_station;

    private String apple_cycle;

    private String provide_type;

    private String manage_station;

    private String apply_target;

    private String service_content;

    private String how_to_apply;

    private String addi_info;

    @Builder
    public Post(Long id, Long admin_id, String title, LocalDateTime create_date, String category, String life_cycle, String house_situ, String interest_topic, String inquiry_station, String apple_cycle, String provide_type, String manage_station, String apply_target, String service_content, String how_to_apply, String addi_info) {
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


    public static Post register(PostDTO.PostDto postDto) {
        return Post.builder()
                .admin_id(postDto.getAdmin_id())
                .title(postDto.getTitle())
                .create_date(LocalDateTime.now())
                .category(postDto.getCategory())
                .life_cycle(postDto.getLife_cycle())
                .house_situ(postDto.getHouse_situ())
                .interest_topic(postDto.getInterest_topic())
                .inquiry_station(postDto.getInquiry_station())
                .apple_cycle(postDto.getApple_cycle())
                .provide_type(postDto.getProvide_type())
                .manage_station(postDto.getManage_station())
                .apply_target(postDto.getApply_target())
                .service_content(postDto.getService_content())
                .how_to_apply(postDto.getHow_to_apply())
                .addi_info(postDto.getAddi_info())
                .build();
    }
}
