package br.com.sad2.capacitacao.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {
	private static final String CURRENT_DIR = System.getProperty("user.dir");

	@Override
	public void run(String... args) throws Exception {
        Path currentPath = Paths.get(CURRENT_DIR);
        Path parentPath = currentPath.getParent();
        Path newDirectory = parentPath.resolve("uploads");
		
		if(!Files.exists(newDirectory)) {
			Files.createDirectories(newDirectory);
			System.out.println("Diretório criado " + newDirectory.toAbsolutePath());
		} else {
			System.out.println("Diretório já existe: " + newDirectory.toAbsolutePath());
		}
	}
}