package com;

public class Calculator {
	public Calculator() {
		System.out.println("Calculator object created");
	}
	
	public int add(int a, int b) {
		return a + b;
	}
	public int sub(int a, int b) {
		return a - b;
	}
	public int mult(int a, int b) {
		return a * b;
	}
	public int div(int a, int b) {
		if (b == 0) { 
			throw new ArithmeticException();
		}
		return a / b;
	}
	public int mod(int a, int b) {
		return a % b;
	}
	public void checkEven(int a) {
		if (a % 2 == 0) {
			System.out.println("Even");
		} else {
			System.out.println("Odd");
		}
	}
}
