package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return runner -> {

			//createStudent(studentDAO);

			//createMultipleStudents(studentDAO);

			//readStudent(studentDAO);

			//queryForStudents(studentDAO);

			//queryForStudentsByLastName(studentDAO);

			//updateStudentFirstName(studentDAO);

			//updateMultipleStudents(studentDAO);

			//changeLastNamesBack(studentDAO);

			//deleteStudent(studentDAO);

			//deleteAllStudent(studentDAO);

		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		System.out.println("Deleting all students from the database...");
		System.out.println(studentDAO.deleteAllStudents());
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentID = 3;
		// deleting a student
		System.out.println("Deleting a student with id:" + studentID);
		studentDAO.delete(studentID);
	}

	private void changeLastNamesBack(StudentDAO studentDAO) {
		// changing all students last names back
		System.out.println("Changing all students last names back ");
		Student firstStudent = studentDAO.findById(1);
		firstStudent.setLastName("Doe");
		Student secondStudent = studentDAO.findById(2);
		secondStudent.setLastName("Public");
		Student thirdStudent = studentDAO.findById(3);
		thirdStudent.setLastName("Applebum");
		Student fourthStudent = studentDAO.findById(4);
		fourthStudent.setLastName("Duck");

		// updating all students last name
		studentDAO.update(firstStudent);
		studentDAO.update(secondStudent);
		studentDAO.update(thirdStudent);
		studentDAO.update(fourthStudent);
	}

	private void updateMultipleStudents(StudentDAO studentDAO) {
		// changing all students' last name to "Tester"
		System.out.println("Changing all students' last name");
		studentDAO.updateMultiple();
	}

	private void updateStudentFirstName(StudentDAO studentDAO) {
		// retrieve a student based on the id:primary key
		int studentID = 1;
		System.out.println("Getting student with id:" + studentID);
		Student myStudent = studentDAO.findById(studentID);
		// change first name to "Scooby"
		System.out.println("Updating Student...");
		myStudent.setFirstName("Scooby");
		// update the student
		studentDAO.update(myStudent);
		// display updated student
		System.out.println("Updated student:" + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Doe");
		// display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}

	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student
		System.out.println("Creating a student");
		Student tempStudent = new Student("Daffy","Duck","daffy@luv2code.com");
		// save a new student object
		System.out.println("Saving a student object");
		studentDAO.save(tempStudent);
		// display id of the saved student
		System.out.println("Saved student.Generated id :" + tempStudent.getId());
		// retrieve a student based on the id: primary key
		System.out.println("Retrieving student with id:" + tempStudent.getId());
		Student myStudent = studentDAO.findById(tempStudent.getId());
		// display student
		System.out.println("Found the student:"+myStudent);

	}
	private void createMultipleStudents(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating 3 students objects...");
		Student tempStudent1 = new Student("John","Doe","john@luv2code.com");
		Student tempStudent2 = new Student("Mary","Public","mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita","Applebum","bonita@luv2code.com");
		// saving student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}
	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul","Doe","paul@luv2code.com");
		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);
		// display id the saved student
		System.out.println("Saved student Generated id:" + tempStudent.getId());
	}
}