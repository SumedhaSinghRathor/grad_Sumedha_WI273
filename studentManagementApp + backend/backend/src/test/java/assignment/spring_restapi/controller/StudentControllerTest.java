package assignment.spring_restapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import assignment.spring_restapi.repo.StudentRepo;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
	@Mock
	StudentRepo repo;

	@Test
	void test() {
		System.out.println("Is this working?");
//		StudentRepo repo = new StudentRepo();
		
	}

}
