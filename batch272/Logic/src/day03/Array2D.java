package day03;

public class Array2D {
	
	public static void readArray2D(int[][] X) {
		for(int i=0; i<X.length; i++) {
			for(int j=0; j<X[i].length; j++) {
				System.out.print(X[i][j] + ",");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		int[][] A = new int[3][3];
		int[][] B = {{1,2,3},{4,5,6},{7,8,9}};
		
		A[0][0] = 5;
		A[1][0] = 7;
		
		System.out.println(A[1][0]);
		
		readArray2D(A);
		readArray2D(B);
	}

}
