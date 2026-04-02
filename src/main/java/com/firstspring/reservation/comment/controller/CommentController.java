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
@Tag(name = "Comment API", description = "寃뚯떆湲 ?볤? 愿??API")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "?볤? ?묒꽦", description = "?뱀젙 寃뚯떆湲???덈줈???볤????묒꽦?⑸땲??")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long ReservationId,
            @RequestBody CommentDto commentDto) {

        commentDto.setReservationId(ReservationId); // URL?먯꽌 諛쏆? 寃뚯떆湲 ID瑜?DTO???명똿
        CommentDto savedComment = commentService.saveComment(commentDto);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping
    @Operation(summary = "?볤? 紐⑸줉 議고쉶", description = "?뱀젙 寃뚯떆湲???щ┛ 紐⑤뱺 ?볤???議고쉶?⑸땲??")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long ReservationId) {
        List<CommentDto> comments = commentService.getCommentsByreservationId(ReservationId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "?볤? ??젣", description = "?뱀젙 ?볤?????젣?⑸땲??")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long ReservationId,
            @PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
