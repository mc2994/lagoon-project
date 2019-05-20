package com.lagoon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lagoon.dao.CommentDao;
import com.lagoon.model.Comment;
import com.lagoon.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> findByPhotoId(Long photoId) {
		return commentDao.findByPhotoId(photoId);
	}

	@Override
	public Comment findOne(Long commentId) {
		return commentDao.findOne(commentId);
	}

	@Override
	public void save(Comment comment) {
		commentDao.save(comment);		
	}
}