class Human{
    String name;
    String gender;
    static long count;
    Human(String name, String gender){
        name = name;
        gender = gender;
        count++; 
    }
    public String getName(){
        return name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}