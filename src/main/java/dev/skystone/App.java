package dev.skystone;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {
        D100Loader.load();
        scene = new Scene(loadFXML("mainScene"), 1080, 720);
        stage.setTitle("Skystone");
        stage.setScene(scene);
        stage.show();
    }

    public static void setTitle(String title) {
    if (primaryStage != null) {
        primaryStage.setTitle(title);
    }
}

    static void trySetRoot(String fxml) {
        try {
            setRoot(fxml);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            // e.printStackTrace();
        }
    }

    private static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}