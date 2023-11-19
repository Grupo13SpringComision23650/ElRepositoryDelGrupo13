package com.homebanking.grupo13.repositories;

import com.homebanking.grupo13.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
