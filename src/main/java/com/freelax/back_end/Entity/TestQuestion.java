package com.freelax.back_end.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "test_question")
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private SkillTest skillTest;

    private String questionText;
    private String options; // JSON formatted options
    private String correctOption;
}
