package arkanoid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Arkanoid extends Application {
    
    private Alert wl;
    @Override
    public void start(Stage primaryStage) {
               
        Game root = new Game();
        Scene scene = new Scene(root, 800, 680);
       
        primaryStage.setTitle("ArkanoiD");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
