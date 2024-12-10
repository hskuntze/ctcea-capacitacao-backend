package br.com.sad2.capacitacao.servicos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import br.com.sad2.capacitacao.servicos.excecoes.EmailException;
import br.com.sad2.capacitacao.servicos.excecoes.FormatacaoException;

@Service
public class EmailServico {
	private SpringTemplateEngine templateEngine;
	private JavaMailSender mailSender;

	private static final String ENCODING = "UTF-8";
	private static final Path TMP_PATH = Paths.get("").resolve("capacitacao/tmp");
	
	public EmailServico(@Qualifier("mailSender") final JavaMailSender jms, final SpringTemplateEngine ste) {
		this.mailSender = jms;
		this.templateEngine = ste;
	}
	
	/**
	 * Função que envia um e-mail usando template gerenciado pelo Thymeleaf 
	 * @param template
	 * @param to
	 * @param subject
	 * @param variables
	 * @return String "Sucesso"; em caso de falha EmailException
	 */
	public String enviarEmailHtmlComThymeleaf(String template, String to, String subject, Map<String, Object> variables) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, ENCODING);

			helper.setTo(to);
			helper.setSubject(subject);

			Context context = new Context();
			context.setVariables(variables);

			String content = templateEngine.process(template + ".html", context);
			helper.setText(content, true);
			
			FileSystemResource logo = new FileSystemResource(new File("src/main/resources/static/images/logo_SGC_v_positiva.png"));
			helper.addInline("logo", logo);

			mailSender.send(message);
			return "Sucesso";
		} catch (MessagingException e) {
			throw new EmailException(e.getMessage(), e);
		}
	}
	
	/**
	 * Função que envia e-mail com anexos
	 * @param to
	 * @param subject
	 * @param file
	 * @param text
	 * @param fileName
	 * @return String "Sucesso"; em caso de falha EmailException
	 */
	public String enviarEmailComAnexo(String to, String subject, byte[] file, String text, String fileName) {
		try {
			File pdfFile = formatarBytesParaArquivo(file, fileName);

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, ENCODING);

			helper.setTo(to);
			helper.setSubject(subject);

			Context context = new Context();
			context.setVariable("texto", text);

			String content = templateEngine.process("email-attachment.html", context);
			helper.setText(content, true);
			//helper.addInline("attachment.png", resourceFile);
			helper.addAttachment(pdfFile.getName(), pdfFile);

			mailSender.send(message);

			Files.delete(pdfFile.toPath());

			return "Sucesso";
		} catch (MessagingException | IOException e) {
			throw new EmailException(e.getMessage(), e);
		}
	}

	private File formatarBytesParaArquivo(byte[] file, String fileName) {
		Path tempFile = null;
		try {
			tempFile = Files.createTempFile(TMP_PATH, fileName, ".pdf");
			Files.write(tempFile, file);
		} catch (IOException e) {
			throw new FormatacaoException(e.getMessage());
		}
		return tempFile.toFile();
	}
}
