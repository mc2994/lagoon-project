package com.lagoon.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lagoon.dao.PhotoDao;
import com.lagoon.model.Photo;
import com.lagoon.model.User;
import com.lagoon.util.LagoonUtility;
import com.lagoon.util.PhotoDTOConverter;

@RestController
@PostAuthorize("hasRole('ROLE_PM') or hasRole('ROLE_ADMIN')")
@RequestMapping("/api/auth")
public class PhotoResource {

	@Autowired
	private PhotoDao photoService;

	@RequestMapping(value = "/photo/add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Photo> addPhoto(@RequestPart("photo") Photo photo,
			@RequestPart("file") List<MultipartFile> file) {
		System.out.println(photo.getUser().getUserName());
		for (MultipartFile upload : file) {
			Photo p = PhotoDTOConverter.convertDTOToModel(photo);
			p.setImageName(upload.getOriginalFilename());
			
			String path = new File("src/main/resources/static/images").getAbsolutePath() + "\\"
					+ upload.getOriginalFilename();
			try {
				photoService.save(p);
				upload.transferTo(new File(path));
				System.out.println(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);
	}

	@RequestMapping(value = "/photo/user", method = RequestMethod.POST)
	public ResponseEntity<List<Photo>> getPhotosByUser(@RequestBody User user) {
		List<Photo> photos = photoService.findByUser(user);
		photos.forEach(photo -> photo.setImageName(LagoonUtility.imageToByte(photo.getImageName())));
		return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
	}

	@RequestMapping(value = "/photo/photoId", method = RequestMethod.POST)
	public ResponseEntity<Photo> getPhotoByPhotoId(@RequestBody Long photoId) {
		Photo photo = photoService.findByPhotoId(photoId);
		photo.setImageName(LagoonUtility.imageToByte(photo.getImageName()));
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);
	}

	@RequestMapping(value = "/photo/update", method = RequestMethod.POST)
	public void updatePhoto(@RequestBody Photo photo) {
		Photo currentPhoto = photoService.findByPhotoId(photo.getPhotoId());
		currentPhoto.setLikes(photo.getLikes());
		photoService.save(currentPhoto);
	}
}