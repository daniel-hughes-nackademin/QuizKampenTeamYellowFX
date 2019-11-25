package ClientProgram.GUI;

import ClientProgram.GUI.LoginWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {

    public static String name;
    public static int rounds;
    public static int questionsPerRound;

    public void init() {
        Properties settings = new Properties();
        try{
            settings.load(new FileInputStream("src/ClientProgram/settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = settings.getProperty("name");
        rounds = Integer.parseInt(settings.getProperty("rounds"));
        questionsPerRound = Integer.parseInt(settings.getProperty("questionsPerRound"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println(name);
        System.out.println(rounds);
        System.out.println(questionsPerRound);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginWindow.fxml"));
            loader.setController(new LoginWindowController());
            Pane root = loader.load();
            Scene scene = new Scene(root, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
