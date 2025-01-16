package interfaces;

public class Square implements Shape {

	@Override
	public void bentuk() {
		System.out.println("Kotak");
	}

	@Override
	public void jumlahsisi() {
		System.out.println("4");
	}

}
