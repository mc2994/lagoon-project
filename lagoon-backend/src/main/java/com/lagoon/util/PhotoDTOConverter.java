package com.lagoon.util;

import com.lagoon.model.Photo;

public class PhotoDTOConverter {

	public PhotoDTOConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public static Photo convertDTOToModel(Photo photo) {
		Photo p = new Photo();		
		p.setCommentList(photo.getCommentList());
		p.setCreated(photo.getCreated());
		p.setDescription(photo.getDescription());
		p.setLikes(photo.getLikes());
		p.setPhotoName(photo.getPhotoName());
		p.setTitle(photo.getTitle());
		p.setUser(photo.getUser());
		return p;
	}

}
