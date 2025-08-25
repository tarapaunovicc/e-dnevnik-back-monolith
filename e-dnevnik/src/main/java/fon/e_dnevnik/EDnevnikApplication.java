package fon.e_dnevnik;

import fon.e_dnevnik.dto.GradeDTO;
import fon.e_dnevnik.dto.StudentDTO;
import fon.e_dnevnik.entity.Student;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.stream.Collectors;

@SpringBootApplication
public class EDnevnikApplication {

	/*@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Configure modelMapper for collections
		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

		// Converter for Student to StudentDTO
		modelMapper.typeMap(Student.class, StudentDTO.class).addMappings(mapper -> {
			mapper.skip(StudentDTO::setGrades);
		}).setPostConverter(context -> {
			Student source = context.getSource();
			StudentDTO destination = context.getDestination();
			destination.setGrades(source.getGrades().stream()
					.map(grade -> modelMapper.map(grade, GradeDTO.class))
					.collect(Collectors.toList()));
			return context.getDestination();
		});
		return modelMapper;
	}
*/
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration()
				.setFieldMatchingEnabled(true)
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

		return modelMapper;
	}
	public static void main(String[] args) {

		SpringApplication.run(EDnevnikApplication.class, args);

	}

}
