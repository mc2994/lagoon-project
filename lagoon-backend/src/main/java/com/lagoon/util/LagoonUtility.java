package com.lagoon.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Base64;

public class LagoonUtility {

	public static String imageToByte(String imageName) {
		File img = new File("src/main/resources/static/images/"+imageName);
		byte[] image = new byte[] {};
		try {
			image = Files.readAllBytes(img.toPath());
		} catch (IOException e) {
			System.out.println("Reading image error... ");
			e.printStackTrace();
		}
		byte[] encodeBase64 = Base64.getEncoder().encode(image);
        String base64Encoded = "";
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Base64 encoding error... ");
			e.printStackTrace();
		}
        return base64Encoded;
	}
}
