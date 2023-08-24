package com.projectbusan.bokjido.service;


import com.projectbusan.bokjido.dto.QnaCreateRequestDTO;
import com.projectbusan.bokjido.dto.QnaDetailsResponseDTO;
import com.projectbusan.bokjido.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QnaService {
    Long createQna(User user, QnaCreateRequestDTO qnaCreateRequestDTO);
    Page<QnaDetailsResponseDTO> getQnaAll(Pageable pageable);
    QnaDetailsResponseDTO getQnaById(Long qnaId);
}
