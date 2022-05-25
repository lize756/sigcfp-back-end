package com.edu.icesi.sigcfp.sigcfpbackendbusiness.auth.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtility {
	public static String saveFile(MultipartFile multiPart, String route) {
		// It get the original name of the file.
		String originalName = multiPart.getOriginalFilename();
		try {
			// It form the name of the file to save it on the hard drive.
			File imageFile = new File(route + originalName);
			System.out.println("Archive: " + imageFile.getAbsolutePath());
			// It physically save the file in HD.
			multiPart.transferTo(imageFile);
			return originalName;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}

}
