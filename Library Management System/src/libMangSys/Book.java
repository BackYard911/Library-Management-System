package libMangSys;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Book {
    private String name;
    private String author;
    private String release;
    private int numberOfUnits;
    private String summary;
   private String image;
    private boolean avaliable=true;

    public Book(String name, String author, String release, int numberOfUnits, String summary, String image) {
        this.name = name;
        this.author = author;
        this.release = release;
        this.numberOfUnits = numberOfUnits;
        this.summary = summary;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getRelease() {
        return release;
    }

    public String getSummary() {
        return summary;
    }

    public String getImage() {
        return image;
    }

    public boolean isAvaliable() {
        return avaliable;
    }
    public void updateAvaliable(){
        if(numberOfUnits<=0) avaliable=false;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }
    public String toString() {
        return getName();
    }



    public static Book getBook(Scanner file){
        String name=file.next();
        name=name.replace("-"," ");
        String author=file.next();
        author=author.replace("-"," ");
        String date=file.next();
        int units=Integer.parseInt(file.next());
        String sum=file.next();
        sum=sum.replace("-"," ");
        String img=file.next();
        return new Book(name,author,date,units,sum,img);


    }

    public static ArrayList<Book> getAllBooks(String filename){
        Scanner file=null;
        try {
            file=new Scanner(new File("src/data/"+filename+".txt"));
        }catch (Exception e){
            System.out.println("couldnt be found");
        }

        ArrayList<Book> books=new ArrayList<>();
        while(file.hasNext()){

            books.add(getBook(file));
        }



        return books;
    }

    public static ArrayList searchBook(String value,String filename) {
        ArrayList<Book> list=new ArrayList<>();

        Scanner file=null;
        try {
            file=new Scanner(new File("src/data/"+filename+".txt"));
        }catch (Exception e){
            System.out.println("couldnt be found");
        }

        while(file.hasNext()){
            Book book=getBook(file);
            if(book.getName().contains(value))
                list.add(book);
        }

        return list;
    }

    public static void addBook(Book book,String file){


         try {
             FileWriter fileWriter = new FileWriter("src/data/"+file+".txt", true);
             PrintWriter printWriter = new PrintWriter(fileWriter);
             String name=book.name;
             name=name.replace(" ","-")+" ";
             String author=book.author;
             author=author.replace(" ","-")+ " ";
             String date=book.release+" ";
             String units=""+book.numberOfUnits+ " ";
             String sum=book.summary;
             sum=sum.replace(" ","-")+ " ";
             String img=book.image;
             String textToAppend="\n"+name+author+date+units+sum+img;
             printWriter.println(textToAppend);  //New line
             printWriter.close();
         }
         catch(Exception e){
             System.out.println("error");
         }






    }

    public static  void removeBook(Book book,String file){


        try {


            String inFile = "src/data/"+file+".txt";


            String line =book.name;
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

}
