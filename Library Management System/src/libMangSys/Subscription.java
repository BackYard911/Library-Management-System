package libMangSys;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class Subscription extends BorderPane {
    Stage window =new Stage();

    public Subscription( User user)
    {
        VBox v2 = new VBox();
        v2.setSpacing(10);
        v2.setPadding(new Insets(200, 30, 0, 50));
        Text c_txt = new Text(4, 50, "My Subscription :");

        c_txt.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 35));
        this.setTop(c_txt);

        Text c_txt1 = new Text(4, 235, "Name :");

        c_txt1.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 20));
        v2.getChildren().add(c_txt1);

        Text c_txt2 = new Text(4, 285, "Days to renew :");

        c_txt2.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 20));
        v2.getChildren().add(c_txt2);

        Text c_txt3 = new Text(4, 335, "Subscription date :");

        c_txt3.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.REGULAR, 20));
        v2.getChildren().add(c_txt3);


        VBox v1 = new VBox();
        v1.setSpacing(5);
        v1.setPadding(new Insets(200, 30, 0, 20));


        Label lbl = new Label(user.getName());

        lbl.setFont(Font.font(20));
        v1.getChildren().add(lbl);


        Label lb2 = new Label("29");

        lb2.setFont(Font.font(20));
        v1.getChildren().add(lb2);

        Label lb3 = new Label(user.getDate());

        lb3.setFont(Font.font(20));
        v1.getChildren().add(lb3);
        HBox h1 =new HBox();
        h1.getChildren().addAll(v2,v1);
        this.setCenter(h1);

        VBox v3 =new VBox();
        v3.setSpacing(5);
        v3.setPadding(new Insets(250, 0, 0, 0));
        Button bt1 = Mainmenu.createButton("Renew Subscription");
        bt1.setOnAction(e->{
            AlertBox.display("Subscription","subscription renewed");
            lb2.setText("30");
        });

        v3.getChildren().add(bt1);

        Button bt2 = Mainmenu.createButton("Cancel Subscription");
        bt2.setOnAction(e->{
            AlertBox.display("Subscription","subscription canceled");
            lb2.setText("0");
        });
        v3.getChildren().add(bt2);
        this.setRight(v3);

        Button bt3 = Mainmenu.createButton("Back");
        bt3.setOnAction(e->window.close());

        this.setBottom(bt3);

            this.setStyle("-fx-background-color: linear-gradient(#c4e5e6,#e5deed);"
            );
        window.setTitle("My Subscription");
        window.setScene(new Scene(this,650,500));
        window.show();


    }





}