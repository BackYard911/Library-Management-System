package libMangSys;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ViewBook extends BorderPane {

    Stage window=new Stage();
    VBox bookDetails = new VBox();
    private VBox setBookDetails(Book book){
        Label bookName = new Label();
        bookName.setText("Book Name: "+ book.getName());
        bookName.setFont(new Font(14));
        Label authorName = new Label();
        authorName.setText("Author: " + book.getAuthor());
        authorName.setFont(new Font(14));
        Label releaseDate = new Label();
        releaseDate.setText("Release Date: " + book.getRelease() );
        releaseDate.setFont(new Font(14));
        Label summary = new Label();
        summary.setText("Summary: " + book.getSummary());
        summary.setFont(new Font(14));
        summary.setMaxWidth(300);
        summary.prefHeight(300);
        summary.setWrapText(true);
        bookDetails.getChildren().addAll(bookName,authorName,releaseDate,summary);
        bookDetails.setPadding(new Insets(10,10,10,10));
        bookDetails.setSpacing(30);
        return bookDetails;
    }
    HBox bookImage = new HBox();
    private HBox  BookImage(Book book){
        ImageView coverPage = new ImageView(new Image (getClass().getResource(book.getImage()).toString()));
        coverPage.setFitWidth(150);
        coverPage.setFitHeight(250);
        bookImage.getChildren().add(coverPage);
        bookImage.setPadding(new Insets(20,20,30,10));
        return bookImage;
    }
    HBox buttons = new HBox();
    private HBox btns(Book book, Person type){

        ArrayList list =new ArrayList();
        Button btnBack = Mainmenu.createButton("Back");
        Button btnBorrow = Mainmenu.createButton("Borrow Book");
        btnBack.setOnAction(e-> window.close());
        //Back Scene Here
        if (type instanceof Librarian){
            btnBorrow.setDisable(true);
        }
        else{
            User user=(User)type;
            list =Book.searchBook(book.getName(),user.getUsername());


            if(list.isEmpty()) {


                if(!book.isAvaliable()) btnBorrow.setDisable(true);
                // Borrwed Success and add this book to added books
                btnBorrow.setOnAction(e->{
                    Book.addBook(book,user.getUsername());
                    window.close();
                    AlertBox.display("success message","book borrowed");
                });
            }
            else {
                btnBorrow= Mainmenu.createButton("Return Book");
                //return success and remove book from added books
                btnBorrow.setOnAction(e->{
                    Book.removeBook(book,user.getUsername());
                    window.close();
                    AlertBox.display("sucess message","book returned");
                });
            }
        }

        buttons.getChildren().addAll(btnBack,btnBorrow);
        buttons.setSpacing(250);
        buttons.setPadding(new Insets(10,10,35,10));
        return buttons;
    }
    VBox top = new VBox();
    private VBox topMenu(){
        Label title = new Label();
        title.setText("");

        return top;
    }

    public ViewBook(Book book,Person user){
        this.setLeft(setBookDetails(book));
        this.setRight(BookImage(book));
        this.setBottom(btns(book,user));
        this.setStyle("-fx-background-color: linear-gradient(#c4e5e6,#e5deed);"
        );



        window.setScene(new Scene(this,520,550));
        window.show();
    }
}


