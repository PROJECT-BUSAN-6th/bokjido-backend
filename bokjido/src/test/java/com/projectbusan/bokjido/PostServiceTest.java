package com.projectbusan.bokjido;

import com.projectbusan.bokjido.dto.PostDTO;
import com.projectbusan.bokjido.entity.Post;
import com.projectbusan.bokjido.repository.PostRepository;
import com.projectbusan.bokjido.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    public void 포스팅() {
        //given
        postRepository.save(Post.builder()
                .admin_id(1L)
                .create_date(LocalDateTime.now())
                .title("title")
                .life_cycle("life_cycle")
                .house_situ("getHouse_situ")
                .interest_topic("getInterest_topic")
                .inquiry_station("getInquiry_station")
                .apple_cycle("getApple_cycle")
                .provide_type("getProvide_type")
                .manage_station("getManage_station")
                .apply_target("getApply_target")
                .service_content("getService_content")
                .how_to_apply("getHow_to_apply")
                .addi_info("getAddi_info")
                .build()
        );

        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post post = postList.get(0);
        Assertions.assertEquals("title", post.getTitle());
        System.out.println("post = " + post);
    }
}
