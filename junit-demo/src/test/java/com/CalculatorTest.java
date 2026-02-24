package com;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayName("Testing Calculator Class")
// @TestInstance(Lifecycle.PER_CLASS)
class CalculatorTest {
	static Calculator c1;
	private boolean flag = true;
	
	public CalculatorTest() {
		System.out.println("CalculatorTest Object created");
	}
	
	@Test
	@Disabled
	void test() {
		assertTrue(true);
	}
	
	@BeforeAll
	public static void createObject() {
		c1 = new Calculator();
		System.out.println("**** STARTED ****");
	}
	
	@AfterAll
	public static void removeObject() {
		c1 = null;
		System.out.println("OBJECT DELETED");
	}
	
	@BeforeEach
	public void abc() { // setUp() method
		System.out.println("--- Before each test case ---");
	}
	
	@AfterEach
	public void xyz() { // tearDown() method
		System.out.println("--- After each test case --- ");
	}
	
	@Test
	// @AtIgnore - for older versions
	public void testAdd() {
		System.out.println("testAdd()");
		int actual = c1.add(10, 20);
		int expected = 30;
		assertEquals(expected, actual); // actual testing
	}
	
	@Test
	public void testSub() {
		System.out.println("testSub()");
		assertEquals(-10, c1.sub(10, 20));
	}
	
	@Test
	public void testMult() {
		System.out.println("testMult()");
		assertAll(
				() -> assertEquals(200, c1.mult(10, 20)),
				() -> assertEquals(-600, c1.mult(-30, 20)),
				() -> assertEquals(-280, c1.mult(40, -7)),
				() -> assertEquals(100, c1.mult(-10, -10))
		);
	}
	
	@Test
	public void testDiv() {
		System.out.println("testDiv()");
		assertEquals(6, c1.div(30, 5));
		assertThrows(ArithmeticException.class, () -> c1.div(10, 0));
	}
	
	@Test
	@DisplayName("Testing Modulus operation")
	public void testMod() {
		System.out.println("testMod()");
		assertAll(
				() -> assertEquals(0, c1.mod(10, 2)),
				() -> assertEquals(2, c1.mod(20, 3)),
				() -> assertEquals(2, c1.mod(30, 4)),
				() -> assertEquals(0, c1.mod(40, 5)),
				() -> assertEquals(2, c1.mod(50, 6)),
				() -> assertEquals(5, c1.mod(40, 7))
		);
	}

	@Test
	public void testCheckEven() {
		PrintStream mockOut = mock(PrintStream.class);
		PrintStream originalOut = System.out;

		try {
			System.setOut(mockOut);
			c1.checkEven(10);
			c1.checkEven(7);

			verify(mockOut).println("Even");
			verify(mockOut).println("Odd");
		} catch (Exception e) {
			System.setOut(originalOut);
		}
	}
	
	@Test
	@EnabledOnOs(OS.WINDOWS)
	@Tag("windows")
	public void testDll() {
		System.out.println("testDll()");
	}
	
	@Test
	@EnabledOnOs(value = {OS.MAC, OS.LINUX, OS.SOLARIS})
	@Tag("others")
	public void testSh() {
		System.out.println("testSh()");
	}
	
	@Test
//	@EnabledOnJre(JRE.)
	@EnabledForJreRange(min = JRE.JAVA_18)
	public void testLambda() {
		System.out.println("testLambda()");
		assumeTrue(flag); // depends on the flag whether it should be tested
		System.out.println("Just for demo");
	}
}
