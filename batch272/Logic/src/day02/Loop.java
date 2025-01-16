package day02;

public class Loop {
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			System.out.println(i);
		}
		for(int i=9; i>=0; i--) {
			System.out.println(i);
		}
		for(int i=0; i<10; i+=2) { // i = i + 2
			System.out.println(i);
		}
		
		int x = 0;
		for(int i=0; i<10; i++) {
			System.out.println(x);
			x=x+2;
		}
		
		int i = 2;
		int temp = 0;
		while(i<=12) {
			System.out.println(i + " : " + temp + ", ");
			temp+=i;
			i+=2;
		}
		//System.out.println(temp);
		
		int y = 0;
		for(int z=2; z<=12; z+=2) {
			System.out.println(z + " : " + y + ", ");
			y += z;
		}
		
		int f = 1;
		for(int z=1; z<=7; z++) {
			System.out.println((z-1) + " = " + f);
			f*=z;
		}
		
	}
}
