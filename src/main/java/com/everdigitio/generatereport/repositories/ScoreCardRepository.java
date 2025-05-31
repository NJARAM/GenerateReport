package com.everdigitio.generatereport.repositories;

import com.everdigitio.generatereport.entities.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreCardRepository extends JpaRepository<ScoreCard,Integer> {
    List<ScoreCard> findByStudent_RollNumber(Integer rollNumber);
}
