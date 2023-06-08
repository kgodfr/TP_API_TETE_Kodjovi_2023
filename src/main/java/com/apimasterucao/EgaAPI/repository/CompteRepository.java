package com.apimasterucao.EgaAPI.repository;

import com.apimasterucao.EgaAPI.entity.Compte;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    List<Compte> findByClientId(Integer client_id);
}
