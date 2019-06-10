package com.lagoon.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lagoon.dao.PhotoDao;
import com.lagoon.model.Photo;
import com.lagoon.model.User;
import com.lagoon.util.LagoonUtility;

@RestController
@RequestMapping("/api/auth")
public class PhotoResource {

	private String imageName;

	@Autowired
	private PhotoDao photoService;
	
	@Autowired
	RestTemplate restTemplate; //temp

	@RequestMapping(value = "/photo/upload", method = RequestMethod.POST)
	public String upload(HttpServletResponse response, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> it = multipartRequest.getFileNames();
		MultipartFile multipartFile = multipartRequest.getFile(it.next());
		String fileName = multipartFile.getOriginalFilename();
		imageName = fileName;

		String path = new File("src/main/resources/static/images").getAbsolutePath() + "\\" + fileName;
		try {
			multipartFile.transferTo(new File(path));
			System.out.println(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Upload Success!";
	}

	@RequestMapping(value = "/photo/add", method = RequestMethod.POST)
	public ResponseEntity<Photo> addPhoto(@RequestBody Photo photo) {
		photo.setImageName(imageName);
		photoService.save(photo);
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);
	}

	@RequestMapping(value = "/photo/user", method = RequestMethod.POST)
	public ResponseEntity<List<Photo>> getPhotosByUser(@RequestBody User user){
		List<Photo> photos = photoService.findByUser(user);
		photos.forEach(photo -> 
 				photo.setImageName(
 						LagoonUtility.imageToByte(photo.getImageName())
 				));
	return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK) ;
	}

	@RequestMapping(value = "/photo/photoId", method = RequestMethod.POST)
	public ResponseEntity<Photo> getPhotoByPhotoId(@RequestBody Long photoId) {
		Photo photo = photoService.findByPhotoId(photoId);
		photo.setImageName(LagoonUtility.imageToByte(photo.getImageName()));
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<User>(headers);
		
		User photoaa =  restTemplate.exchange("http://localhost:8080/user/getAllUsers",HttpMethod.GET,entity, User.class).getBody();
		System.out.println(">>>>>>>>>>> "+photoaa.getFirstName());
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);
	}

	@RequestMapping(value = "/photo/update", method = RequestMethod.POST)
	public void updatePhoto(@RequestBody Photo photo) {
		Photo currentPhoto = photoService.findByPhotoId(photo.getPhotoId());
		currentPhoto.setLikes(photo.getLikes());
		photoService.save(currentPhoto);
	}
}