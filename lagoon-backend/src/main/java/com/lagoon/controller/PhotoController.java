package com.lagoon.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lagoon.model.Photo;
import com.lagoon.service.PhotoService;

@RestController
@RequestMapping("/api/auth")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@RequestMapping("/allPhotos")
	public ResponseEntity<List<Photo>> getAllPhotos() throws IOException {
		List<Photo> photos = photoService.findAll();
		return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
	}
}