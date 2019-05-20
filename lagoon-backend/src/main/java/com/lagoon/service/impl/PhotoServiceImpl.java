package com.lagoon.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lagoon.dao.PhotoDao;
import com.lagoon.model.Photo;
import com.lagoon.model.User;
import com.lagoon.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDao photoDao;

	@Override
	public List<Photo> findAll() throws IOException {
		List<Photo> photos = photoDao.findAll();
		List<Photo> allPhotos = new ArrayList<Photo>();
		for(Photo photo : photos) {
			File img = new File("src/main/resources/static/images/"+photo.getImageName());
			byte[] image = Files.readAllBytes(img.toPath());
			byte[] encodeBase64 = Base64.getEncoder().encode(image);
	        String base64Encoded = new String(encodeBase64, "UTF-8");
			Photo p = new Photo();
			p.setCommentList(photo.getCommentList());
			p.setCreated(photo.getCreated());
			p.setDescription(photo.getDescription());
			p.setImageName(base64Encoded);
			p.setLikes(photo.getLikes());
			p.setPhotoId(photo.getPhotoId());
			p.setPhotoName(photo.getPhotoName());
			p.setTitle(photo.getTitle());
			p.setUser(photo.getUser());
			allPhotos.add(p);
		}
		return allPhotos;
	}

	@Override
	public List<Photo> findByUser(User user) {
		return photoDao.findByUser(user);
	}

	@Override
	public Photo findByPhotoId(Long photoId) {
		return photoDao.findByPhotoId(photoId);
	}
}
