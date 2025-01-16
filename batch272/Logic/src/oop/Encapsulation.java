package oop;

public class Encapsulation {
	
	public String name;
	private int age;
	
	public void getName() {
		System.out.println(this.name);
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}
