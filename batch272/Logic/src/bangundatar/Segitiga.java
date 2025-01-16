package bangundatar;

public class Segitiga extends BangunDatar {

	public float luas() {
		return (0.5f * this.alas * this.tinggi);
	}
	
	public float keliling() {
		return (this.sisi + this.sisi + this.sisi);
	}
}
