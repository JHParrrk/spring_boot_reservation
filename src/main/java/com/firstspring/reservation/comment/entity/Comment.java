package com.firstspring.reservation.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private Long ReservationId; // ?대뼡 寃뚯떆湲???щ┛ ?볤??몄? ?앸퀎
    private String content;
    private String author;
}
