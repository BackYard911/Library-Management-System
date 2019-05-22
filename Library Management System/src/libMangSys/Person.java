package libMangSys;

import java.io.File;
import java.util.Scanner;

public abstract class Person {
    private String password;
    private String username;
    private String name;
    private String date;

    public Person(String name, String username, String password,String date) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }




   public static Person login(String username,String password,String type){

        String src;
        if(type.equals("user")) src="allUsers";
        else src="allLibrarians";
        Scanner file=null;
        try {
            file=new Scanner(new File("src/data/"+src+".txt"));
        }catch (Exception e){
            System.out.println("couldnt be found");
        }

        while(file.hasNext()){
            if(type.equals("user")){
                User user=User.getUser(file);
                if (user.getUsername().equals(username) && user.getPassword().equals(password))
                    return user;
            }
            else if(type.equals("librarian")){
                    Librarian librarian=Librarian.getLibrarian(file);
                    if (librarian.getUsername().equals(username) && librarian.getPassword().equals(password))
                        return librarian;
            }



        }
        return null;
    }



    public String toString(){
        return getName();
    }
}
