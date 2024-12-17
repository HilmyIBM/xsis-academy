public class Human {
    private String name;
    private String gender;
    static long count;

    Human(String _name,String _gender){
        name=_name;
        gender=_gender;
        count++;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
