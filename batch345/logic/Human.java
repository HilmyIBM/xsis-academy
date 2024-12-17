public class Human {
    private String _name;
    private String _gender;
    static long count;

    Human(String name, String gender){
        _name = name;
        _gender = gender;
        count++;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
