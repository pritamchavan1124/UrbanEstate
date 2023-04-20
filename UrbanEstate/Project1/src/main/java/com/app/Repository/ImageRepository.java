package com.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entities.Image;
import com.app.Entities.Property;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

	List<Image> findByProperty(Property property);

}
