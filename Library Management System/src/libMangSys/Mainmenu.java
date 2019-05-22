package libMangSys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Mainmenu extends BorderPane {
//comment


    public static Button createButton(String name){
        Button btn=new Button(name);
        String btStyle="-fx-background-radius: 30;" +
                " -fx-background-insets: 0;" +
                "-fx-text-fill: #ebebf5;" +
                "-fx-cursor: hand;";

        btn.setStyle(" -fx-background-color: linear-gradient(#4bb0be,#a1d0cf);" +btStyle);
        btn.setOnMouseExited(e->{
            btn.setStyle(" -fx-background-color: linear-gradient(#4bb0be,#a1d0cf);" +btStyle);
        });
        btn.setOnMouseEntered(e->btn.setStyle(" -fx-background-color: linear-gradient(#2d8b8e,#a1d0cf);"+btStyle));
        btn.setPrefWidth(190);
        btn.setPrefHeight(30);
        btn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD,16));

        return btn;
    }
    public void updateAllBooks(){
        ObservableList<Book> allBooks =FXCollections.observableArrayList(Book.getAllBooks("allbooks"));
        list.setItems(allBooks);
    }
    public void updateAllUsers(){
        ObservableList<User> allUsers =FXCollections.observableArrayList(User.getAllUsers("allUsers"));
        list.setItems(allUsers);
    }

    private HBox srchbar =new HBox();
    VBox btnsBox =new VBox();
    StackPane viewPane=new StackPane();
    ListView list=new ListView();
    HBox logoutBar =new HBox();
    TextField search=new TextField();
    VBox top=new VBox();
    boolean flagDisplayList=true;

    private VBox setBtnsMenu(Person type) {
        Button btViewAllBooks =createButton("View all books");

        if(type instanceof User) {
            User user=(User)type;
            btViewAllBooks.setOnAction(e ->{
                flagDisplayList=true;
                updateAllBooks();

            });
            Button btViewBor = createButton("View borrowed books");
            btViewBor.setOnAction(e -> {
                ObservableList<Book> allBooks =FXCollections.observableArrayList(Book.getAllBooks(user.getUsername()));
                list.setItems(allBooks);
            });

            Button btSub = createButton("My subscription");
            btSub.setOnAction(e -> {
                new Subscription(user);

            });

            btnsBox.getChildren().addAll(btViewAllBooks, btViewBor, btSub);


        }
        else{
            Button btAddBook=createButton("Add book");
            Button btRemoveBook =createButton("Remove Book");
            Button btAddUser=createButton("Add user");
            Button btRemoveUser=createButton("Remove User");

            btRemoveBook.setOnAction(e->{
                if(list.getSelectionModel().getSelectedItem()==null) {
                        AlertBox.display("Error","Please select a book!");
                }
                else {
                    Book book = (Book) list.getSelectionModel().getSelectedItem();
                    Book.removeBook(book,"allbooks");
                    updateAllBooks();
                }
            });

            btAddUser.setDisable(true);
            btRemoveUser.setDisable(true);
            Button btViewAllUsers =createButton("View all users");
            btViewAllBooks.setOnAction(e ->{
                flagDisplayList=true;
                updateAllBooks();
                btAddUser.setDisable(true);
                btRemoveUser.setDisable(true);
                btAddBook.setDisable(false);
                btRemoveBook.setDisable(false);


            });
            btViewAllUsers.setOnAction(e->{
                updateAllUsers();
                flagDisplayList=false;
                btAddBook.setDisable(true);
                btRemoveBook.setDisable(true);
                btAddUser.setDisable(false);
                btRemoveUser.setDisable(false);
            });
            btRemoveUser.setOnAction(e->{
                if(list.getSelectionModel().getSelectedItem()==null) {
                    AlertBox.display("Error","Please select a user!");
                }
                else {
                    User user = (User) list.getSelectionModel().getSelectedItem();
                    User.removeUser(user);
                    updateAllUsers();
                }

            });

            btAddUser.setOnAction(e->new AddScene("adduser"));
            btAddBook.setOnAction(e->new AddScene("addbook"));

            btnsBox.getChildren().addAll(btViewAllBooks,btViewAllUsers,btAddBook,btRemoveBook,btAddUser,btRemoveUser);

        }

        btnsBox.setSpacing(10);
        btnsBox.setPadding(new Insets(10, 10, 10, 10));
        return btnsBox;
    }

    private HBox setLogOut(){
        Button btLgOut=createButton("Log Out");
        btLgOut.setPrefWidth(100);
        btLgOut.setOnAction(e->{
            Main.window.setScene(new Scene(new LoginMenu()));
        });
        logoutBar.getChildren().add(btLgOut);
        logoutBar.setPadding(new Insets(10,10,25,10));

        return logoutBar;
    }

    private VBox setTop(Person type){

        search.setStyle("-fx-background-radius:30px;");
        search.setPrefWidth(300);
        search.setPrefHeight(30);
        search.setPromptText("Book name...");
        Button btSrch=createButton("Search");
        btSrch.setFont(Font.font("Arial",FontWeight.findByWeight(650),13));
        btSrch.setPrefWidth(70);
        btSrch.setPrefHeight(30);
        btSrch.setOnAction(e->{
            String value=search.getText();
            if (flagDisplayList) {


                if (!(value.equals(""))) {
                    if (!Book.searchBook(value,"allbooks").isEmpty()) {
                        ObservableList<Book> listfound = FXCollections.observableArrayList(Book.searchBook(value,"allbooks"));
                        list.setItems(listfound);
                    } else {
                        ObservableList<String> listfound = FXCollections.observableArrayList("Book Not Found!");
                        list.setItems(listfound);
                    }

                }
            }
            else{
                if (!(value.equals(""))) {
                    if (!User.searchUser(value).isEmpty()) {
                        ObservableList<User> listfound = FXCollections.observableArrayList(User.searchUser(value));
                        list.setItems(listfound);
                    } else {
                        ObservableList<String> listfound = FXCollections.observableArrayList("User Not Found!");
                        list.setItems(listfound);
                    }

                }
            }
        });

        srchbar.getChildren().addAll(search,btSrch);
        srchbar.setSpacing(20);
        srchbar.setPadding(new Insets(10,10,10,10));
        Label id=new Label("username : "+ type.getName());
        id.setPadding(new Insets(10,10,10,10));
        Label logo=new Label("Library Mangement System ");
        logo.setStyle("-fx-font-size:28px;"+
                "-fx-text-fill:#287d80;"+
                "-fx-text-stroke:#084e50;"+
                "-fx-font-weight:700;");
            HBox hbox= new HBox(logo,id);
            hbox.setSpacing(100);
            hbox.setPadding(new Insets(0,10,10,10));
        top.getChildren().addAll(hbox,srchbar);
        top.setSpacing(10);
        top.setPadding(new Insets(20,10,10,10));
        return top;
    }

    private StackPane setList(Person type){
        list.setStyle("-fx-font-size:18px;");
        viewPane.setPadding(new Insets(10, 10, 25, 10));
        updateAllBooks();
        list.setOnMouseClicked(e->{
            if(e.getClickCount()==2){
                Object object =list.getSelectionModel().getSelectedItem();
                if (object instanceof Book){
                    new ViewBook((Book)object,type);
                }
                else if (object instanceof User){
                    new ViewUser((User)object);
                }
            }

        });

            viewPane.getChildren().add(list);
        return  viewPane;
    }

   public Mainmenu(Person type){

        this.setRight(setBtnsMenu(type));
        this.setBottom(setLogOut());
        this.setTop(setTop(type));
        this.setCenter(setList(type));
        this.setStyle("-fx-background-color: linear-gradient(#c4e5e6,#e5deed);"
        );
    }






















}
