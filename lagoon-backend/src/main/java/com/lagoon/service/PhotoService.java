package com.lagoon.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lagoon.model.Photo;
import com.lagoon.model.User;

public interface PhotoService {
	List<Photo> findAll() throws IOException;

	List<Photo> findByUser(User user);

	Photo findByPhotoId(Long photoId);
	
	CompletableFuture<List<Photo>> findPhotosAsync();

	List<Photo> findPhotosProducer();
}