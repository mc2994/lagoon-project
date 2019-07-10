package com.lagoon.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lagoon.email.components.EmailService;
import com.lagoon.email.components.EmailServiceImpl;
import com.lagoon.email.components.Mail;
import com.lagoon.model.Photo;
import com.lagoon.reports.util.ExcelBuilder;
import com.lagoon.reports.util.ExcelDataInput;
import com.lagoon.reports.util.PDFBuilder;
import com.lagoon.reports.util.PDFDataInput;
import com.lagoon.service.PhotoService;

@RestController
@RequestMapping("/api/auth")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	@Autowired
	private EmailService emailService;

	public final Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@RequestMapping("/allPhotos")
	public ResponseEntity<List<Photo>> getAllPhotos() throws IOException {
		List<Photo> photos = photoService.findAll();
		return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
	}

	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download() throws IOException {
		HttpHeaders headers = null;
		ByteArrayResource resource = null;
		PDFDataInput input = null;
		try {

			HashMap<String, String> meta = new LinkedHashMap<String, String>();

			/* metadata for generated info */
			Date dateNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yy hh:mm a");
			SimpleDateFormat fileNameDate = new SimpleDateFormat("MMddyy_hhmm_a");

			input = new PDFDataInput();
			meta.put("Host Name: ", "testHost");
			meta.put("Generated By: ", "Mc Kinley Tolentino");
			meta.put("Generated Date and Time: ", ft.format(dateNow));
			input.setMetadata(meta);

			/* Column header */
			String[] columnHeaders = { "Full Name", "Email Address", "User Type", "User Status" };
			input.initDataInput(columnHeaders);

			/* row content */
//		    for (MasterUserDTO item : masterUserDTO) {
//                input.addDataToRow(0, item.getUserFullName());
//                input.addDataToRow(1, item.getEmailAddress());
//                input.addDataToRow(2, item.getMasterUserType().getUserTypeDescription());
//                input.addDataToRow(3, item.getMasterStatus().getStatusDescription());
//            }

			PDFBuilder pdf = new PDFBuilder();
			pdf.build(input);

			headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gghj.pdf");
			resource = new ByteArrayResource(input.getExportedFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentLength(input.getExportedFile().length)
				.contentType(MediaType.parseMediaType("application/pdf")).headers(headers).body(resource);
	}

	@RequestMapping(value = "/excel", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> downloadExcelFile() {
		ExcelDataInput input = null;
		HttpHeaders headers = null;
		ByteArrayResource resource = null;
		try {

			input = new ExcelDataInput();
			HashMap<String, String> meta = new LinkedHashMap<String, String>();

			/* metadata for generated info */
			Date dateNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yy hh:mm a");
			SimpleDateFormat fileNameDate = new SimpleDateFormat("MMddyy_hhmm_a");

			meta.put("Host Name: ", "testHost");
			meta.put("Generated By: ", "Mc Kinley Tolentino");
			meta.put("Generated Date and Time: ", ft.format(dateNow));
			input.setMetadata(meta);

			/* Column header */
			String[] columnHeaders = { "Full Name", "Primary Email Address", "User Type", "User Status" };
			input.initDataInput(columnHeaders);

			/* row content */

//	            for (MasterUserDTO item : masterUserDTO) {
//	                input.addDataToRow(0, item.getUserFullName());
//	                input.addDataToRow(1, item.getEmailAddress());
//	                input.addDataToRow(2, item.getMasterUserType().getUserTypeDescription());
//	                input.addDataToRow(3, item.getMasterStatus().getStatusDescription());
//	            }

			ExcelBuilder excel = new ExcelBuilder();
			excel.build(input);

			headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment;filename=user-management-template.xls");
			resource = new ByteArrayResource(input.getExportedFile());

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ResponseEntity.ok().contentLength(input.getExportedFile().length)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).headers(headers).body(resource);
	}

	private void sendWorkflowEmail() {
		try {
//			for (CaseUser user : workflowUserList) {
			Map<String, Object> model = new HashMap<String, Object>();
			final Mail mail = new Mail();
			model.put("workflowName", "workflow namen");
			model.put("caseUserName", "Mc Kinley Tolentino");
			model.put("folderName", "music folder");
			mail.setMailFrom("test.@email.com");
			mail.setMailTo("tolentinomckinley@yahoo.com");
			mail.setMailSubject("Workflow Email Notification");
			mail.setContentType("email");
			mail.setModel(model);
			mail.setEmailTemp(Mail.EmailTemplate.WORKFLOW_EMAIL);
			emailService.sendEmail(mail);
			logger.info("sending email to workflowUserList : ");
//			}
		} catch (MailSendException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}