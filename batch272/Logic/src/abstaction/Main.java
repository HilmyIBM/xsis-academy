package abstaction;

public class Main {
	public static void main(String[] args) {
		Universitas fak = new Fakultas();
		fak.fakultas();
		fak.Jurusan();
		fak.nama("UAD");
		
		Universitas jur = new Jurusan();
		jur.fakultas();
		jur.Jurusan();
		jur.nama("Universitas Terbuka");
	}
}
