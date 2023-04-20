package com.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.Entities.Property;
import com.app.Entities.PropertyFor;
import com.app.Entities.Status;
import com.app.Entities.Type;

@Repository
public interface propertyRepository extends JpaRepository<Property, Long> {

	List<Property> findByAddressCity(String city);

	List<Property> findByPropType(Type type);

	List<Property> findByPropertyFor(PropertyFor propfor);
	
	@Query("select p from Property p join fetch p.images i where p.id=?1")
	Property getPropertyById(Long propId);

	List<Property> findByStatus(Status pending);



	
}
