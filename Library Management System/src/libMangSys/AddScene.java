package libMangSys;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddScene extends BorderPane {

    Stage add = new Stage();


    HBox h6 = new HBox();
    VBox v1 = new VBox();
    VBox v2 = new VBox();
    VBox v3 = new VBox();

    Button b1 = Mainmenu.createButton("Cancel");
    Button b2 = Mainmenu.createButton("Add Book");
    Button b3 = Mainmenu.createButton("Add User");

    public  void addbook()
    {
        add.setTitle("Add Book");
        add.setMinHeight(500);
        add.setMinHeight(500);
        Scene scene = new Scene(this);
        add.setScene(scene);


        // name and summary >> h1
        Label l1 = new Label("Name :");
        l1.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t1 = new TextField();
        t1.setStyle("-fx-background-radius: 30;");
        Label l2 = new Label("Summary :");
        l2.setFont(Font.font("Arial", FontWeight.BOLD,16));

        // author and summary textfield
        Label l3 = new Label("Author :");
        l3.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t2 = new TextField();
        t2.setStyle("-fx-background-radius: 30;");
        TextArea t6 = new TextArea();
        t6.setWrapText(true);
        t6.setPrefWidth(200);
        t6.setPrefHeight(200);


        // Release date
        Label l4 = new Label("Release date :");
        l4.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t3 = new TextField();


        // Number of units
        Label l5 = new Label("Number of units :");
        l5.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t4 = new TextField();
        t4.setStyle("-fx-background-radius: 30;");

        // Image URL
        Label l6 = new Label("Image URL :");
        l6.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t5 = new TextField();
        t5.setStyle("-fx-background-radius: 30;");


        b1.setOnAction(e-> add.close());
        b2.setOnAction(e-> {

                    String name = t1.getText();
                    String Author = t2.getText();
                    String ReleaseDate =t3.getText();
                    int NumberOfUnits = Integer.parseInt( t4.getText());
                    String ImageUrl = t5.getText();
                    String Summary = t6.getText();
                    Book.addBook(new Book(name,Author,ReleaseDate,NumberOfUnits,Summary,ImageUrl),"allbooks");
                    AlertBox.display("Success message","Book added successfully !");
                    add.close();
                }
        );
        h6.setPadding(new Insets(100, 12, 15, 12));
        h6.setSpacing(290);
        h6.getChildren().addAll(b1,b2);


        v1.setPadding(new Insets(100, 12, 15, 12));
        v2.setPadding(new Insets(100, 12, 15, 12));
        v3.setPadding(new Insets(100, 12, 15, 12));
        v1.setSpacing(50);
        v2.setSpacing(45);
        v3.setSpacing(25);
        v1.getChildren().addAll(l1,l4,l3,l5,l6);
        v2.getChildren().addAll(t1,t2,t3,t4,t5);
        v3.getChildren().addAll(l2,t6);

        this.setLeft(v1);
        this.setCenter(v2);
        this.setRight(v3);
        this.setBottom(h6);
        add.showAndWait();
    }
    public  void adduser() {
        add.setTitle("Add User");
        add.setMinHeight(500);
        add.setMinHeight(500);
        Scene scene = new Scene(this);
        add.setScene(scene);


        //name textfield
        Label l1 = new Label("Name :");
        l1.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t1 = new TextField();
        t1.setPrefWidth(400);
        t1.setStyle("-fx-background-radius: 30;");


        //username textfield
        Label l3 = new Label("Username :");
        l3.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t2 = new TextField();
        t2.setPrefWidth(400);
        t2.setStyle("-fx-background-radius: 30;");


        // password field
        Label l4 = new Label("Password :");
        l4.setFont(Font.font("Arial", FontWeight.BOLD,16));
        PasswordField t3 = new PasswordField();
        t3.setPrefWidth(400);
        t3.setStyle("-fx-background-radius: 30;");


        // date field
        Label l5 = new Label("Date :");
        l5.setFont(Font.font("Arial", FontWeight.BOLD,16));
        TextField t4 = new TextField();
        t4.setPrefWidth(400);




        b1.setOnAction(e-> add.close());
        b3.setOnAction(e-> {
                    if (validate(t3, t2))
                    {
                        String name = t1.getText();
                        String UserName = t2.getText();
                        String Password = t3.getText();
                        String Date = t4.getText();
                        User.addUser(new User(name,UserName,Password,Date));
                        AlertBox.display("Success message","User added successfully !");
                        add.close();
                    }
                    else
                    {
                        AlertBox.display("invalid Password or Username",
                                "-The Password you entered may be more than 8 characters" + "\n" +
                                        "-The Username you entered can not contain spaces");
                    }

                }

        );
        h6.setPadding(new Insets(100, 12, 15, 12));
        h6.setSpacing(290);
        h6.getChildren().addAll(b1,b3);

        v1.setPadding(new Insets(100, 12, 15, 12));
        v2.setPadding(new Insets(100, 12, 15, 12));

        v1.setSpacing(50);
        v2.setSpacing(45);

        v1.getChildren().addAll(l1,l3,l4,l5);
        v2.getChildren().addAll(t1,t2,t3,t4);

        this.setLeft(v1);
        this.setCenter(v2);
        this.setBottom(h6);
        add.initModality(Modality.APPLICATION_MODAL);
        add.showAndWait();

    }

    private Boolean validate(PasswordField password, TextField username)
    {
        boolean check = true;
        if(password.getText().length() < 8)
        {
            check = false;
        }
        if(username.getText().contains(" "))
        {
            check = false;
        }
        return check;
    }
    public AddScene(String utype) {
        this.setStyle("-fx-background-color: linear-gradient(#c4e5e6,#e5deed);"
        );
        if(utype.equals("adduser"))
        {
            adduser();
        }
        else
        {
            addbook();
        }
    }

}
