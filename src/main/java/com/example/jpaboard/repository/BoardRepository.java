package com.example.jpaboard.repository;

import com.example.jpaboard.entity.JpaBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JpaRepository<JpaBoard, Long> {
}
