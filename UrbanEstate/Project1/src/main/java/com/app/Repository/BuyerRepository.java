package com.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entities.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	Optional<Buyer> findByEmail(String name);

}
