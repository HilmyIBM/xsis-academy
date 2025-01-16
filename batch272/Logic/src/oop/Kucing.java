package oop;

public class Kucing extends Hewan {
	
	protected String ras;
	
	public void kucing() {
		this.name = "Kucing";
		this.sound = "Meow";
		System.out.println(this.name);
		System.out.println(this.sound);
	}
	
}
