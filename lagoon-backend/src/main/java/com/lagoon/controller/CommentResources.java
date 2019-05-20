package com.lagoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lagoon.model.Comment;
import com.lagoon.model.Photo;
import com.lagoon.service.CommentService;
import com.lagoon.service.PhotoService;

@RestController
@RequestMapping("/api/auth/")
public class CommentResources {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	public void addComment(@RequestBody Comment comment) {
		Photo photo = photoService.findByPhotoId(comment.getPhotoId());
		comment.setPhoto(photo);
		commentService.save(comment);
	}
}