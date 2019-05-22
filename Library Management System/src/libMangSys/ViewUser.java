package libMangSys;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ViewUser extends BorderPane {
    Stage window=new Stage();
    VBox userDetails = new VBox();
    private VBox setUserDetails(User user){
        Label name = new Label();
        name.setText("Name: "+ user.getName());
        name.setFont(new Font(25));
        Label userName = new Label();
        userName.setText("Usename: "+ user.getUsername());
        userName.setFont(new Font(25));
        Label date = new Label();
        date.setText("Date joined: "+ user.getDate());
        date.setFont(new Font(25));
        userDetails.getChildren().addAll(name,userName,date);
        userDetails.setPadding(new Insets(130,10,10,10));
        userDetails.setSpacing(60);
        return userDetails;
    }
    HBox btns = new HBox();
    private HBox setBtns(){
        Button back =  Mainmenu.createButton("Back");
        back.setOnAction(e->window.close());
        btns.setPadding(new Insets(10,10,35,10));
        btns.getChildren().add(back);
        return btns;
    }
    VBox top = new VBox();
    private VBox topMenu(){
        Label title = new Label();
        title.setText("");

        return top;
    }

    public ViewUser(User user) {
        this.setLeft(setUserDetails(user));
        this.setBottom(setBtns());
        this.setStyle("-fx-background-color: linear-gradient(#c4e5e6,#e5deed);"
        );
        window.setScene(new Scene(this,500,500));
        window.show();
    }
}
