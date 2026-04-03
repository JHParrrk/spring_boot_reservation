package com.firstspring.reservation.comment.controller;

import com.firstspring.reservation.comment.dto.CommentDto;
import com.firstspring.reservation.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations/{ReservationId}/comments")
@RequiredArgsConstructor
@Tag(name = "Comment API", description = "예약 게시글 댓글 관련 API")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "댓글 작성", description = "특정 예약글에 새로운 댓글을 작성합니다.")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long ReservationId,
            @RequestBody CommentDto commentDto) {

        commentDto.setReservationId(ReservationId); // URL에서 받은 게시글 ID를 DTO에 세팅
        CommentDto savedComment = commentService.saveComment(commentDto);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping
    @Operation(summary = "댓글 목록 조회", description = "특정 예약글에 달린 모든 댓글 정보를 조회합니다.")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long ReservationId) {
        List<CommentDto> comments = commentService.getCommentsByreservationId(ReservationId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제", description = "ID를 통해 특정 댓글을 삭제합니다.")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long ReservationId,
            @PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
