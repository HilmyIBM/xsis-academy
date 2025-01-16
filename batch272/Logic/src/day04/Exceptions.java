package day04;

public class Exceptions {

	public static void main(String[] args) {

		int[] A = {1,3,5};
		int x = 0;
		System.out.println(A[2]);
		
		try {
			System.out.println(A[4]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			x = A[2];
		} finally {
			x += 2;
			System.out.println(x);
		}
		
		//check(15);
		check(19);
		
		try {
			fun();
		} catch (Exception e) {
			System.out.println("Catch.." + e.getMessage());
		}
		
	}
	
	static void check(int age) {
		if(age < 18) {
			throw new ArithmeticException("Access denied - harus diatas 18 tahun");
		} else {
			System.out.println("Access granted");
		}
	}
	
	static void fun() throws IllegalAccessException {
		System.out.println("Inside fun()");
		throw new IllegalAccessException("test");
	}

}
