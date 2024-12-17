public class Day03_OOP {
    public static void main(String[] args) {
        Human jack = new Human("Jack", "Male");
        System.out.println(jack.get_name() + " with gender " + jack.get_gender() + " | " + Human.count);

        Human saitama = new Human("Saitama", "Male");
        System.out.println(saitama.get_name() + " with gender " + saitama.get_gender() + " | " + Human.count);

        Day03_OOP objectTest = new Day03_OOP();
        objectTest.mamaliaObject();
    }

    public void mamaliaObject() {
        Sapi sapiPutih = new Sapi();
        sapiPutih.jumlahKaki = 4;
        sapiPutih.bergerak();
        sapiPutih.bersuara();

        Paus pausOrca = new Paus();
        pausOrca.jumlahKaki = 0;
        pausOrca.bergerak();
        System.out.println("Paus orca menyusui: " + pausOrca.isMenyusui);

        Kuda horse = new Kuda();
        horse.bergerak();
        horse.bersuara();

        Kelelawar bat = new Kelelawar();
        bat.bergerak();
        bat.isMenyusui = false;
        System.out.println("Kelelawar menyusui: " + bat.isMenyusui);
    }
}

class Human {
    // Modifier
    private String _name;
    private String _gender;
    static int count;

    Human(String name, String gender) {
        _name = name;
        _gender = gender;
        count++;
    }

    public String get_name() {
        return _name;
    }

    public String get_gender() {
        return _gender;
    }
}

abstract class Mamalia {
    // Modifier
    protected boolean isMenyusui = true;
    int jumlahKaki;

    // Polymorphism
    void bergerak() {

    }
}

// Inheritance
class Sapi extends Mamalia {
    void bersuara() {
        System.out.println("Mooooo.......");
    }
}

class Paus extends Mamalia {
    // Ini adalah polymorphism
    void bergerak() {
        System.out.println("berenang.....");
    }
}

class Kuda extends Mamalia {
    // Ini adalah polymorphism
    void bergerak() {
        System.out.println("berlari");
    }

    void bersuara() {
        System.out.println("Mieeeeeeee......!!!");
    }
}

class Kelelawar extends Mamalia {
    // Ini adalah polymorphism
    void bergerak() {
        System.out.println("Terbang");
    }
}
