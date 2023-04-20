package com.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.Entities.Owner;

@Repository
public interface ownerRepository extends JpaRepository<Owner, Long> {

	@Query(value = "select o from Owner o join fetch o.properties p")
	List<Owner> getAllOwners();

	Optional<Owner> findByEmail(String name);

	
}
