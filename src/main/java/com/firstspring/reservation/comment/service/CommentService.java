package com.firstspring.reservation.comment.service;

import com.firstspring.reservation.comment.dto.CommentDto;
import com.firstspring.reservation.comment.entity.Comment;
import com.firstspring.reservation.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // ?볤? ?묒꽦
    public CommentDto saveComment(CommentDto dto) {
        Comment comment = new Comment(null, dto.getReservationId(), dto.getContent(), dto.getAuthor());
        Comment savedComment = commentRepository.save(comment);

        return new CommentDto(
                savedComment.getId(),
                savedComment.getReservationId(),
                savedComment.getContent(),
                savedComment.getAuthor());
    }

    // ?뱀젙 寃뚯떆湲???볤? 紐⑸줉 議고쉶
    public List<CommentDto> getCommentsByreservationId(Long ReservationId) {
        return commentRepository.findByReservationId(ReservationId).stream()
                .map(comment -> new CommentDto(
                        comment.getId(),
                        comment.getReservationId(),
                        comment.getContent(),
                        comment.getAuthor()))
                .collect(Collectors.toList());
    }

    // ?볤? ??젣
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
