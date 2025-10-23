package com.bookmydoctor.bookMyDoctor;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyDoctorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyDoctorApplication.class, args);
	}

	CommandLineRunner init(DoctorRepository repo){
		return args -> {
			Doctor d = new Doctor();
			d.setName("Dr. Ravi Kumar");
			d.setAge(40);
			d.setEmail("ravi@example.com");
			d.setSex("MALE");
			d.setPhoneNumber("9876543210");
			d.setSpeciality("Cardiology");
			repo.save(d);
			System.out.println("✅ Doctor saved!");
		};
	}

}
