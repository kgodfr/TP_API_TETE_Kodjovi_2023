package com.apimasterucao.EgaAPI.repository;

import com.apimasterucao.EgaAPI.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author TETE Kodjovi
 *
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
