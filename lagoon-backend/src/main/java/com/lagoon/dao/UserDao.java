package com.lagoon.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lagoon.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	Page<User> findAll(Pageable pageable);

	User findByUserName(String userName);

	User findByUserId(Long userId);
}