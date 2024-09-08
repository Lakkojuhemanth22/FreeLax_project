package com.freelax.back_end.Repository;


import com.freelax.back_end.Entity.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {

}