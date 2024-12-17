
class Human {
    private String _name;
    private String _gender;
    static long count;

    Human(String name, String gender) {
        _name = name;
        _gender = gender;
        count++;
    }

    Human() {
        count++;
    }

    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
        _name = name;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String gender) {
        _gender = gender;
    }
}
