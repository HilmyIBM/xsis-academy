public class Human {
    private String name;
    private String gender;
    static long count = 0;

    Human(String name, String gender) {
        this.name = name;
        this.gender = gender;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
