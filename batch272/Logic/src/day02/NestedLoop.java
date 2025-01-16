package day02;

public class NestedLoop {

	public static void main(String[] args) {
		int n=5;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
					System.out.print(i + "" + j + " ");
			}
			System.out.println();
		}
		System.out.println();

		int m = n+1;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if((i+j) == m) {
					System.out.print(" X ");
				} else {
					System.out.print(" 0 ");
				}
			}
			System.out.println();
		}

	}

}
