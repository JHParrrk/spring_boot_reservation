package com.firstspring.reservation.comment.repository;

import com.firstspring.reservation.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    private final List<Comment> database = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            Comment newComment = new Comment(
                    sequence.getAndIncrement(),
                    comment.getReservationId(),
                    comment.getContent(),
                    comment.getAuthor());
            database.add(newComment);
            return newComment;
        } else {
            return comment;
        }
    }

    // ?뱀젙 寃뚯떆湲(ReservationId)???щ┛ ?볤?留?議고쉶
    public List<Comment> findByReservationId(Long ReservationId) {
        return database.stream()
                .filter(comment -> comment.getReservationId().equals(ReservationId))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        database.removeIf(comment -> comment.getId().equals(id));
    }
}
