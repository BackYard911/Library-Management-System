package libMangSys;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;

        LoginMenu root=new LoginMenu();

        window.setTitle("Library Management System");
        window.setScene(new Scene(root));
        window.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
