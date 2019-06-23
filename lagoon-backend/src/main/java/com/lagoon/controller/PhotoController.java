package com.lagoon.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download() throws IOException {
		ClassPathResource pdfFile = new ClassPathResource("static/images/gghj.pdf");
		HttpHeaders headers = null;
		ByteArrayResource resource = null;
		try {
			headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gghj.pdf");
			resource = new ByteArrayResource(Files.readAllBytes(pdfFile.getFile().toPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentLength(pdfFile.getFile().length())
				.contentType(MediaType.parseMediaType("application/pdf")).headers(headers).body(resource);
	}
}