package edu.integricert.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import edu.integricert.model.Employee;
import edu.integricert.model.Student;

@Service
public class BarcodeService {

	public byte[] generateBarcode(String data) throws Exception {
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, 300, 100);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "PNG", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new Exception("Failed to create barcode: " + e.getMessage());
		}
	}

	public byte[] generateQRCode(String data) throws Exception {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "PNG", baos);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new Exception("Failed to create QR code: " + e.getMessage());
		}
	}

	public String decodeBarcodeOrQRCode(MultipartFile file) throws Exception {
		try {
			String fileName = file.getOriginalFilename();
			if (fileName == null) {
				throw new Exception("Invalid file.");
			}
			String fileType = FilenameUtils.getExtension(fileName);

			BufferedImage image = null;
			if (fileType.equals("pdf")) {
				image = extractImageFromPDF(file);
			} else if (fileType.equals("png") || fileType.equals("jpg") || fileType.equals("jpeg")) {
				image = ImageIO.read(file.getInputStream());
			} else {
				throw new Exception("Unsupported file format. Please upload PNG, JPG, or PDF.");
			}

			if (image == null) {
				throw new Exception("Failed to extract an image for barcode/QR decoding.");
			}
			return decodeQRCode(image);
		} catch (Exception e) {
			throw new Exception("Failed to decode barcode/QR code: " + e.getMessage());
		}
	}

	private BufferedImage extractImageFromPDF(MultipartFile file) throws IOException {
		try (PDDocument document = PDDocument.load(file.getInputStream())) {
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			return pdfRenderer.renderImageWithDPI(0, 300); // Render first page at 300 DPI
		}
	}

	public String decodeQRCode(BufferedImage image) throws Exception {
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
		Result result = new MultiFormatReader().decode(bitmap);
		return result.getText();
	}

	public String getStudentValue(Student student) {
		StringBuilder sb = new StringBuilder();
		sb.append(student.getId());
		sb.append(student.getName());
		sb.append(student.getAcademicYear());
		sb.append(student.getCourse());
		sb.append(student.getCollege().getName());
		String input = sb.toString();
		return DigestUtils.sha256Hex(input);
	}

	public String getEmployeeValue(Employee employee) {
		StringBuilder sb = new StringBuilder();
		sb.append(employee.getId());
		sb.append(employee.getName());
		sb.append(employee.getDesignation());
		sb.append(employee.getDepartment());
		sb.append(employee.getCompany().getName());
		String input = sb.toString();
		return DigestUtils.sha256Hex(input);
	}

	public byte[] generateStudentCertificate(Student student, String value) throws Exception {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Title
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
		contentStream.beginText();
		contentStream.newLineAtOffset(180, 700);
		contentStream.showText("Course Completion Certificate");
		contentStream.endText();

		// Subtitle
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.newLineAtOffset(230, 670);
		contentStream.showText("This is to certify that");
		contentStream.endText();

		// Name
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.beginText();
		contentStream.newLineAtOffset(260, 650);
		contentStream.showText(student.getName());
		contentStream.endText();

		// Course details
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.newLineAtOffset(150, 630);
		contentStream.showText("has successfully completed the " + student.getCourse() + " program");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(200, 610);
		contentStream.showText("at " + student.getCollege().getName() + " during the academic year " + student.getAcademicYear() + ".");
		contentStream.endText();

		// Result
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		contentStream.setNonStrokingColor(0, 128, 0); // Green color
		contentStream.beginText();
		contentStream.newLineAtOffset(260, 580);
		contentStream.showText("Result: " + student.getCourseResult());
		contentStream.endText();

		// Date
		contentStream.setNonStrokingColor(0, 0, 0); // Reset to black
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.newLineAtOffset(450, 520);
		contentStream.showText("Date: 20-03-2025");
		contentStream.endText();

		// Signatures
		contentStream.beginText();
		contentStream.newLineAtOffset(100, 450);
		contentStream.showText("______________________");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(400, 450);
		contentStream.showText("______________________");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(130, 430);
		contentStream.showText("Course Coordinator");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(430, 430);
		contentStream.showText("Head of Department");
		contentStream.endText();

		// Signature names
		contentStream.beginText();
		contentStream.newLineAtOffset(130, 410);
		contentStream.showText("John Doe");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(430, 410);
		contentStream.showText("John Doe");
		contentStream.endText();

		// Add official stamp image
		PDImageXObject stamp = loadStamp(document);
		contentStream.drawImage(stamp, 250, 400, 80, 80);

		// Add Barcode Image
		byte[] barcodeBytes = generateBarcode(value);
		if (barcodeBytes != null && barcodeBytes.length > 0) {
			PDImageXObject barcodeImage = PDImageXObject.createFromByteArray(document, barcodeBytes, "barcode.png");
			contentStream.drawImage(barcodeImage, 100, 250, 200, 50);
		}

		// Add QR Code Image
		byte[] qrCodeBytes = generateQRCode(value);
		if (qrCodeBytes != null && qrCodeBytes.length > 0) {
			PDImageXObject qrCodeImage = PDImageXObject.createFromByteArray(document, qrCodeBytes, "qrcode.png");
			contentStream.drawImage(qrCodeImage, 350, 230, 100, 100);
		}

		contentStream.close();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		document.save(outputStream);
		document.close();

		return outputStream.toByteArray();
	}

	public byte[] generateEmployeeCertificate(Employee employee, String value) throws Exception {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Title
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
		contentStream.beginText();
		contentStream.newLineAtOffset(180, 700);
		contentStream.showText("Experience Certificate");
		contentStream.endText();

		// Subtitle
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.newLineAtOffset(230, 670);
		contentStream.showText("This is to certify that");
		contentStream.endText();

		// Employee Name
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
		contentStream.beginText();
		contentStream.newLineAtOffset(260, 650);
		contentStream.showText(employee.getName());
		contentStream.endText();

		// Work Details
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.newLineAtOffset(150, 630);
		contentStream.showText("was employed at " + employee.getCompany().getName() + " Corporation from " + employee.getJoiningDate() + " to " + "31-Dec-2024");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(200, 610);
		contentStream.showText("as a " + employee.getDesignation() + ".");
		contentStream.endText();

		// Performance Comment
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		contentStream.setNonStrokingColor(0, 128, 0); // Green color
		contentStream.beginText();
		contentStream.newLineAtOffset(100, 580);
		contentStream.showText("During his tenure, he exhibited excellent technical skills and professionalism.");
		contentStream.endText();

		// Date
		contentStream.setNonStrokingColor(0, 0, 0); // Reset to black
		contentStream.setFont(PDType1Font.HELVETICA, 12);
		contentStream.beginText();
		contentStream.newLineAtOffset(450, 520);
		contentStream.showText("Date: 20-03-2025");
		contentStream.endText();

		// Signatures
		contentStream.beginText();
		contentStream.newLineAtOffset(100, 450);
		contentStream.showText("______________________");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(400, 450);
		contentStream.showText("______________________");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(130, 430);
		contentStream.showText("HR Manager");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(430, 430);
		contentStream.showText("Managing Director");
		contentStream.endText();

		// Signature Names
		contentStream.beginText();
		contentStream.newLineAtOffset(130, 410);
		contentStream.showText("Alice Johnson");
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(430, 410);
		contentStream.showText("Robert Smith");
		contentStream.endText();

		// Add official stamp image
		PDImageXObject stamp = loadStamp(document);
		contentStream.drawImage(stamp, 250, 400, 80, 80);

		// Add Barcode Image
		byte[] barcodeBytes = generateBarcode(value);
		if (barcodeBytes != null && barcodeBytes.length > 0) {
			PDImageXObject barcodeImage = PDImageXObject.createFromByteArray(document, barcodeBytes, "barcode.png");
			contentStream.drawImage(barcodeImage, 100, 250, 200, 50);
		}

		// Add QR Code Image
		byte[] qrCodeBytes = generateQRCode(value);
		if (qrCodeBytes != null && qrCodeBytes.length > 0) {
			PDImageXObject qrCodeImage = PDImageXObject.createFromByteArray(document, qrCodeBytes, "qrcode.png");
			contentStream.drawImage(qrCodeImage, 350, 230, 100, 100);
		}

		contentStream.close();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		document.save(outputStream);
		document.close();

		return outputStream.toByteArray();

	}

	public static PDImageXObject loadStamp(PDDocument document) throws IOException {
		// Load the image from the resources folder
		ClassPathResource resource = new ClassPathResource("stamp.png");

		try (InputStream inputStream = resource.getInputStream()) {
			return PDImageXObject.createFromByteArray(document, inputStream.readAllBytes(), "stamp.png");
		}
	}

}
