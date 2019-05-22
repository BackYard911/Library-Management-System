package libMangSys;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LoginMenu extends BorderPane {

        private  TextField username;
        private PasswordField pass;
    private void Action(String s) {

        Label l1 = new Label(s+" ID: ");   // Nodes
       username = new TextField();
        username.setStyle("-fx-background-radius:30px;");
        username.setPromptText("enter your id");

        Label l2 = new Label("Password: ");
       pass = new PasswordField();
        pass.setStyle("-fx-background-radius:30px;");
        pass.setPromptText("enter your password");

        GridPane pane = new GridPane();       //Pane
        pane.setHgap(10);
        pane.setVgap(10);
        pane.add(l1,3,3);
        pane.add(username,4,3);
        pane.add(l2,3,4);
        pane.add(pass,4,4);
        pane.setAlignment(Pos.CENTER);


        this.setCenter(pane);
    }
    public LoginMenu() {

        Button lgnbtn = Mainmenu.createButton("LOGIN");
        lgnbtn.setDisable(true);

        Button userbtn = Mainmenu.createButton("User");            //NODES
        userbtn.setOnAction(e ->{
            lgnbtn.setDisable(false);
            Action("User");
            lgnbtn.setOnAction(d->{
                String userId=username.getText();
                String password=pass.getText();
               Person user= Person.login(userId,password,"user");

               if(user==null) AlertBox.display("Login Error","user not found!");
                else {
                    Mainmenu mainmenu=new Mainmenu(user);
                   Main.window.setScene(new Scene(mainmenu));
               }

            });
        });


        Button libbtn = Mainmenu.createButton("Librarian");
        libbtn.setOnAction(e ->{

            lgnbtn.setDisable(false);
            Action("Librarian");

            lgnbtn.setOnAction(d->{
                String userId=username.getText();
                String password=pass.getText();
                Person.login(userId,password,"librarian");

                Person librarian=Person.login(userId,password,"librarian");
                if(librarian==null) AlertBox.display("Login Error","librarian not found!");
                else {
                    Mainmenu mainmenu=new Mainmenu(librarian);
                    Main.window.setScene(new Scene(mainmenu));
                }
            });

        });


        VBox p = new VBox();
        p.getChildren().addAll(userbtn,libbtn);
        p.setSpacing(10);
        p.setPadding(new Insets(10,10,10,10));
        p.setAlignment(Pos.CENTER);
        this.setTop(p);

        HBox h = new HBox();
        h.getChildren().add(lgnbtn);
        h.setPadding(new Insets(10,10,30,10));
        h.setAlignment(Pos.CENTER);
        this.setBottom(h);

        this.setStyle("-fx-background-color: linear-gradient(#c4e5e6,#e5deed);");

        this.setPrefSize(500,300);
    }





}