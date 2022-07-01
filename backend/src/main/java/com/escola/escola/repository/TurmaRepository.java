package com.escola.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escola.escola.model.CTurma;

@Repository
public interface TurmaRepository  extends JpaRepository<CTurma, Long>{

}
