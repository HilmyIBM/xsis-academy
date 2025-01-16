package bangundatar;

public class Lingkaran extends BangunDatar {
	
	public float luas() {
		return (3.14f * this.sisi * this.sisi);
	}
	
	public float keliling() {
		return (2 * 3.14f * this.sisi);
	}

}
