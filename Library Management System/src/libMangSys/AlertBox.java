package libMangSys;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

        public static void display (String title,String msg){
            Stage window =new Stage();
            //window.setResizable(false);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setMinWidth(300);
            window.setMinHeight(120);
            window.setTitle(title);
            Label label=new Label(msg);
            label.setStyle("-fx-font-size:16px;");
            Button btclose= Mainmenu.createButton("close");
            btclose.setOnAction(e->window.close());
            VBox layout =new VBox(10);
            layout.getChildren().addAll(label,btclose);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(10,10,10,10));
            Scene scene =new Scene(layout);
            window.setScene(scene);
            window.showAndWait();


        }
}
