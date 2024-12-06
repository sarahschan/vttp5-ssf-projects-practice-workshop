package sg.edu.nus.iss.vttp5a_practice_workshop;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.vttp5a_practice_workshop.service.FileService;


@SpringBootApplication
public class Vttp5aPracticeWorkshopApplication implements CommandLineRunner{

	@Autowired
	FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(Vttp5aPracticeWorkshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Use FilService to load data into redis
		//	 remember that this can throw a FileNotFound Exception
		try {
			fileService.loadFile();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("todos.txt successfully read to redis");

	}

}
