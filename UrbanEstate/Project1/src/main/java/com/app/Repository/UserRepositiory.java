package com.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Entities.Role;
import com.app.Entities.User;


@Repository
public interface UserRepositiory extends JpaRepository<User,Long>{
	

	List<User> findByUserRole(Role roleCustomer);

	Optional<User> findByEmail(String email);

	Optional<User> findById(int userId);

	void deleteById(Integer id);

}
