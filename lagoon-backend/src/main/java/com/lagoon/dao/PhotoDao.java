package com.lagoon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lagoon.model.Photo;
import com.lagoon.model.User;

@Repository
public interface PhotoDao extends JpaRepository<Photo, Long> {

	List<Photo> findAll();

	List<Photo> findByUser(User user);

	Photo findByPhotoId(Long photoId);
}