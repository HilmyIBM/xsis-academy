package Day03;
class Human {
    private String _name;
    private String _gender;
    static long count;

    Human(String name, String gender){
        _name = name;
        _gender = gender;
        count++;
    }

    public String getName() {
        return _name;
    }

    public String getGender() {
        return _gender;
    }
}
