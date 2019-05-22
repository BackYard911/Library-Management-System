package libMangSys;

import java.util.Scanner;

public class Librarian extends Person {

    public Librarian(String name, String username, String password,String date) {
        super(name, username, password,date);
    }

    static  Librarian getLibrarian(Scanner file){
        String name=file.next();
        name=name.replace("-"," ");
        String username=file.next();
        String password=file.next();
        String date=file.next();
        return new Librarian(name, username, password, date) ;

    }
}
