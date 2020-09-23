package com.example.RatingJournal.repository;

import com.example.RatingJournal.model.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface LearnerRepository extends JpaRepository<Student, Integer> {
    //SELECT x.* FROM public.learner x WHERE x.rating IN (5)
    @Query(value = "SELECT x.* FROM public.learner x WHERE x.rating = :rating", nativeQuery = true)
    List<Student> countAllStudentByRating(@Param("rating") Integer rating);
}