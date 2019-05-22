package libMangSys;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person{


    public User(String name,String username, String password,String date) {
        super(name,username,password,date);
    }

    public static void addUser(User user){
        try {
            FileWriter fileWriter = new FileWriter("src/data/"+"allUsers"+".txt", true);
            new File("src/data/"+user.getUsername()+".txt").createNewFile();
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String name=user.getName();
            name=name.replace(" ","-")+" ";
            String userName=user.getUsername()+" ";
            String password =user.getPassword()+" ";
            String  date=user.getDate();
            String textToAppend="\n"+name+userName+password+date;
            printWriter.println(textToAppend);
            printWriter.close();
            new File("src/data/" + user.getUsername()+"Books" + ".txt");
        }
        catch(Exception e){
            System.out.println("error");
        }

    }


    public static ArrayList searchUser(String value) {
        ArrayList<User> list=new ArrayList<>();

        Scanner file=null;
        try {
            file=new Scanner(new File("src/data/"+"allUsers"+".txt"));
        }catch (Exception e){
            System.out.println("couldnt be found");
        }

        while(file.hasNext()){
            User user=getUser(file);
            if(user.getName().contains(value))
                list.add(user);
        }

        return list;
    }


    static  User getUser(Scanner file){
        String name=file.next();
        name=name.replace("-"," ");
        String username=file.next();
        String password=file.next();
        String date=file.next();
        return new User(name, username, password, date) ;

    }
    public static  void removeUser(User user) {
        try {


            String inFile = "src/data/"+"allUsers"+".txt";


            String line =user.getName();
            line=line.replace(" ","-");
            String lineToRemove = line;


            StringBuffer newContent = new StringBuffer();

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            while ((line = br.readLine()) != null) {
                if (!line.trim().contains(lineToRemove)) {
                    newContent.append(line);
                    newContent.append("\n"); // new line

                }
            }
            br.close();

            FileWriter removeLine = new FileWriter(inFile);
            BufferedWriter change = new BufferedWriter(removeLine);
            PrintWriter replace = new PrintWriter(change);
            replace.write(newContent.toString());
            replace.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static ArrayList<User> getAllUsers(String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File("src/data/" + filename + ".txt"));
        } catch (Exception e) {
            System.out.println("couldnt be found");
        }

        ArrayList<User> users = new ArrayList<>();
        while (file.hasNext()) {

            users.add( getUser(file));
        }

        return users;
    }

}
