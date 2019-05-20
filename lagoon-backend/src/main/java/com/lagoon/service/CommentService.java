package com.lagoon.service;

import java.util.List;
import com.lagoon.model.Comment;

public interface CommentService {
	List<Comment> findByPhotoId(Long photoId);

	Comment findOne(Long commentId);
	
	void save(Comment comment);
}