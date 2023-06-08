package com.apimasterucao.EgaAPI.repository;

import com.apimasterucao.EgaAPI.entity.Operation;
import com.apimasterucao.EgaAPI.entity.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByCompteIdAndType(Integer compte_id, OperationType type);
}
