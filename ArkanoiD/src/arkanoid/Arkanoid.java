package arkanoid;

import arkanoid.GamePane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Arkanoid extends Application {
    
    @Override
    public void start(Stage primaryStage) {
               
        GamePane root = new GamePane();
        
        Scene scene = new Scene(root, 800, 680);
       
        primaryStage.setTitle("ArkanoiD");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
