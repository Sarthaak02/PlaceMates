package com.placemates.dao.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "FEEDBACK")
@Data
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FB_ID")
    private Integer feedBackId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = true)
    private User user;

    @Column(name = "RATE_COUNT")
    private Integer rateCount;

    @Column(name = "CONTENT")
    private String content;
}
