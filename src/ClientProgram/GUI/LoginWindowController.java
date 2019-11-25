package ClientProgram.GUI;

import com.sun.glass.events.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LoginWindowController {

    public static String name = "";

    public static int rounds = 0;

    public static int questionsPerRound = 0;

    @FXML
    private VBox firstwindow;

    @FXML
    public TextField username;

    @FXML
    private Button button1;

    @FXML
    private Label label;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private Button b1;

    @FXML
    private Button howtoPlaybutton;


    @FXML
    private TextArea howtoPlayTextARea;

    /*
    @FXML
    private TextField rounds;

    @FXML
    private TextField questionsPerRound;

     */

    @FXML
    private Label questionsPerRoundLabel;

    @FXML
    private Label roundsLabel;

    @FXML
    void settings(ActionEvent event) {
        //questionsPerRoundLabel.setVisible(true);
        //roundsLabel.setVisible(true);
        //rounds.setVisible(true);
        //questionsPerRound.setVisible(true);

        Stage settingsWindow = new Stage();

        GridPane settingsRoot = new GridPane();
        Scene settingsScene = new Scene(settingsRoot);

        settingsWindow.setTitle("Settings");
        settingsWindow.setHeight(200);
        settingsWindow.setWidth(300);
        settingsWindow.initModality(Modality.APPLICATION_MODAL);

        Label nextToPlayerName = new Label("Save userName");
        Label nextToPlayerRounds = new Label("Rounds");
        Label nextToPlayerQuestPerRound = new Label("Questions Per Round");
        TextField playerName = new TextField(Main.name);
        TextField playerRounds = new TextField(String.valueOf(Main.rounds));
        TextField playerQuestionsPerRound = new TextField(
                String.valueOf(Main.questionsPerRound)
        );

        playerRounds.setLayoutY(40);
        playerQuestionsPerRound.setLayoutY(80);

        Button applySettingsButton = new Button("Apply");
        applySettingsButton.setLayoutY(100);
        applySettingsButton.setLayoutX(200);
        applySettingsButton.setDisable(true);

        settingsRoot.addRow(0, nextToPlayerName, playerName);
        settingsRoot.addRow(1, nextToPlayerRounds, playerRounds);
        settingsRoot.addRow(2, nextToPlayerQuestPerRound, playerQuestionsPerRound);
        settingsRoot.addRow(3, applySettingsButton);
        settingsWindow.setScene(settingsScene);
        settingsWindow.show();

        playerName.textProperty().addListener((observable, oldValue, newValue) -> {
            applySettingsButton.setDisable(false);
        });
        playerRounds.textProperty().addListener((observable, oldValue, newValue) -> {

            applySettingsButton.setDisable(false);

        });
        playerQuestionsPerRound.textProperty().addListener((observable, oldValue, newValue) -> {


            applySettingsButton.setDisable(false);

        });
        applySettingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                try {
                    int test = Integer.parseInt(playerQuestionsPerRound.getText());
                } catch (NumberFormatException e) {

                    playerQuestionsPerRound.setText("3");
                    JOptionPane.showMessageDialog(null,
                            "Rounds Have To Be A Number!");
                    return;
                }
                try {
                    int test = Integer.parseInt(playerRounds.getText());
                } catch (NumberFormatException e) {
                    playerRounds.setText("3");
                    JOptionPane.showMessageDialog(null,
                            "Questions Per Round Have To Be A Number!");
                    return;
                }
                if (Integer.parseInt(playerQuestionsPerRound.getText()) < 3 ||
                        Integer.parseInt(playerQuestionsPerRound.getText()) > 9
                ) {
                    JOptionPane.showMessageDialog(null,
                            "Questions per round has to be between 3 and 9!");
                    playerQuestionsPerRound.setText("3");
                    return;
                }

                if (Integer.parseInt(playerRounds.getText()) < 1 ||
                        Integer.parseInt(playerRounds.getText()) > 9
                ) {
                    JOptionPane.showMessageDialog(null,
                            "Rounds have to be between 1 and 9!");
                    playerRounds.setText("3");
                    return;
                }

                writeToProperties(playerName.getText(),
                        playerRounds.getText(), playerQuestionsPerRound.getText());
                settingsWindow.close();
                readProperties();


            }
        });
    }

    public static void readProperties() {


        Path p = Paths.get("src\\ClientProgram\\Properties.txt");

        try (BufferedReader br = Files.newBufferedReader(p)) {
            br.readLine();
            Main.name = br.readLine();
            br.readLine();
            br.readLine();
            Main.rounds = Integer.parseInt(br.readLine());
            br.readLine();
            br.readLine();
            Main.questionsPerRound = Integer.parseInt(br.readLine());

        } catch (IOException io) {

            io.printStackTrace();
        }

    }

    public static void writeToProperties(
            String name, String rounds, String questionsPerRound) {

        Path p = Paths.get("src\\sample\\Properties.txt");

        try (BufferedWriter bw = Files.newBufferedWriter(p)) {

            bw.write("Player Name:");
            bw.newLine();
            bw.write(name);
            bw.newLine();
            bw.newLine();
            bw.write("Rounds:");
            bw.newLine();
            bw.write(rounds);
            bw.newLine();
            bw.newLine();
            bw.write("Questions Per Round:");
            bw.newLine();
            bw.write(questionsPerRound);

        } catch (IOException io) {

            io.printStackTrace();
        }

    }

    @FXML
    void howtoPlay(ActionEvent event) {
        howtoPlayTextARea.setVisible(true);

    }

    @FXML
    void ConnectToServer(ActionEvent event) throws InterruptedException {
        label.setVisible(true);
        progress.setVisible(true);
        label.setText("Connected as " + username.getText());


        Thread.sleep(2000);
        b1.setVisible(true);
    }

    @FXML
    void writeToProperties(ActionEvent event) {
        Path p = Paths.get("src\\sample\\Properties.txt");

        try (BufferedWriter bw = Files.newBufferedWriter(p)) {

            bw.write("Player Name:");
            bw.newLine();
            bw.write(name);
            bw.newLine();
            bw.newLine();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listen(ActionEvent event) throws Exception {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("WinnerLobby.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }
}

