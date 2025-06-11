package Visual;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;


public class Main extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Visual/PantallaPrincipal.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("WordFamae");
        primaryStage.setScene(new Scene(root));
        
        
        Rectangle2D pantalla = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(pantalla.getMinX());
        primaryStage.setY(pantalla.getMinY());
        //primaryStage.setWidth(pantalla.getWidth());
        //primaryStage.setHeight(pantalla.getHeight());
        //primaryStage.setMaximized(true);
        primaryStage.centerOnScreen();
        primaryStage.show();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/imagenes/Logo.png")));
    }
    public static void main(String[] args) {
         launch(args);
    }
    
}