package com.example.jpaboard.repository;

import com.example.jpaboard.entity.JpaFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileRepository extends JpaRepository<JpaFile, Long> {



}
