package com.projectbusan.bokjido.service;

import com.projectbusan.bokjido.dto.CommentResponseDTO;
import com.projectbusan.bokjido.dto.QnaCreateRequestDTO;
import com.projectbusan.bokjido.dto.QnaDetailsResponseDTO;
import com.projectbusan.bokjido.entity.*;
import com.projectbusan.bokjido.exception.CustomException;
import com.projectbusan.bokjido.exception.ErrorCode;
import com.projectbusan.bokjido.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService{
    private final QnaRepository qnaRepository;

    @Override
    public Long createQna(User user, QnaCreateRequestDTO qnaCreateRequestDTO) {
        Qna qna = Qna.builder()
                .user(user)
                .title(qnaCreateRequestDTO.getTitle())
                .content(qnaCreateRequestDTO.getContent())
                .password(qnaCreateRequestDTO.getPassword())
                .views(0L)
                .build();

        qnaRepository.save(qna);
        return qna.getId();
    }

    @Override
    public QnaDetailsResponseDTO getQnaById(Long qnaId) {
        Qna qna = findQna(qnaId);
        qna.increaseViews();
        qnaRepository.save(qna);

        List<CommentResponseDTO> commentDTOs = qna.getComments().stream()
                .map(CommentResponseDTO::toDto)
                .collect(Collectors.toList());
        return QnaDetailsResponseDTO.toDto(qna, commentDTOs);
    }

    @Override
    public Page<QnaDetailsResponseDTO> getQnaAll(Pageable pageable) {
        Page<Qna> qnaPage = qnaRepository.findAll(pageable);

        for (Qna qna : qnaPage.getContent()) {
            qna.increaseViews();
        }
        qnaRepository.saveAll(qnaPage.getContent());
        return QnaDetailsResponseDTO.toDtoList(qnaPage);
    }


    private Qna findQna(Long id){
        return qnaRepository.findById(id).orElseThrow(()
                  -> new CustomException(ErrorCode.QNA_NOT_FOUND_ERROR, "해당 질의응답 게시물은 존재하지 않습니다."));
    }
}