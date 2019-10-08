package com.lagoon.dto;

import com.lagoon.model.Photo;

public class CommentDTO {
	
	public CommentDTO() {
		this.commentId = 1L;
		this.content = "";
		this.photo = new Photo();
		this.photoId = 1L;
		this.userName = "";
	}

	private Long commentId;
	private String content;
	private Photo photo;
	private Long photoId;
	private String userName;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}