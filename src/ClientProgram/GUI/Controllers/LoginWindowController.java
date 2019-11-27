package ClientProgram.GUI.Controllers;

import ClientProgram.ClientConnection;
import ClientProgram.GUI.GUI_Control;
import ClientProgram.GUI.Main;
import ServerUtilities.ClientRequest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

import static ClientProgram.GUI.Main.clientConnection;

public class LoginWindowController {

    @FXML
    public TextField usernameField;
    public Button ConnectButton;
    public Label waitingforPlayerlabel;
    @FXML
    private VBox firstwindow;

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

        settingsWindow.setTitle("Inställningar");
        settingsWindow.setHeight(200);
        settingsWindow.setWidth(300);
        settingsWindow.initModality(Modality.APPLICATION_MODAL);

        Label nextToPlayerName = new Label("Spara användarnamn");
        Label nextToPlayerRounds = new Label("Antal rundor");
        Label nextToPlayerQuestPerRound = new Label("Frågor per runda");
        TextField playerName = new TextField(Main.name);
        TextField playerRounds = new TextField(String.valueOf(Main.rounds));
        TextField playerQuestionsPerRound = new TextField(
                String.valueOf(Main.questionsPerRound)
        );

        playerRounds.setLayoutY(40);
        playerQuestionsPerRound.setLayoutY(80);

        Button applySettingsButton = new Button("Ändra");
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
                            "Rundor måste vara en siffra!");
                    return;
                }
                try {
                    int test = Integer.parseInt(playerRounds.getText());
                } catch (NumberFormatException e) {
                    playerRounds.setText("3");
                    JOptionPane.showMessageDialog(null,
                            "Frågor per runda måste vara en siffra!");
                    return;
                }
                if(Integer.parseInt(playerQuestionsPerRound.getText()) < 3||
                        Integer.parseInt(playerQuestionsPerRound.getText()) > 9
                ){
                    JOptionPane.showMessageDialog(null,
                            "Frågor per runda måste vara mellan 3 och 9!");
                    playerQuestionsPerRound.setText("3");
                    return;
                }

                if(Integer.parseInt(playerRounds.getText()) < 1||
                        Integer.parseInt(playerRounds.getText()) > 9
                ){
                    JOptionPane.showMessageDialog(null,
                            "Rundor måste vara mellan 1 och 9!");
                    playerRounds.setText("3");
                    return;
                }

//                Controller.writeToProperties(playerName.getText(),
//                        playerRounds.getText(), playerQuestionsPerRound.getText());
                settingsWindow.close();
//                Controller.readProperties();


            }
        });
    }
    public static void readProperties() {


//        Path p = Paths.get("src\\sample\\Properties.txt");
//
//        try (BufferedReader br = Files.newBufferedReader(p)) {
//            br.readLine();
//            Main.name = br.readLine();
//            br.readLine(); br.readLine();
//            Main.rounds = Integer.parseInt(br.readLine());
//            br.readLine(); br.readLine();
//            Main.questionsPerRound = Integer.parseInt(br.readLine());
//
//        } catch (IOException io) {
//
//            io.printStackTrace();
//        }

    }

    public static void writeToProperties(
            String name, String rounds, String questionsPerRound) {

//        Path p = Paths.get("src\\sample\\Properties.txt");
//
//        try (BufferedWriter bw = Files.newBufferedWriter(p)) {
//
//            bw.write("Player Name:");
//            bw.newLine();
//            bw.write(name);
//            bw.newLine(); bw.newLine();
//            bw.write("Rounds:");
//            bw.newLine();
//            bw.write(rounds);
//            bw.newLine(); bw.newLine();
//            bw.write("Questions Per Round:");
//            bw.newLine();
//            bw.write(questionsPerRound);
//
//        } catch (IOException io) {
//
//            io.printStackTrace();
//        }

    }

    @FXML
    void howtoPlay(ActionEvent event) {
        howtoPlayTextARea.setVisible(true);

    }

    @FXML
    void notifyReady(ActionEvent event) throws Exception {
        progress.setVisible(true);
        waitingforPlayerlabel.setVisible(true);
        b1.setVisible(false);
        clientConnection.sendObjectToServer(new ClientRequest(ClientRequest.TYPE.NOTIFY_READY, null));
    }

    public void initializeConnection(String username){
        //String hostName = "85.24.133.169";
        String hostName = "127.0.0.1";
       // int portNr = 54322;
        int portNr = 54322;

        //Static import of Main.clientConnection so we can access it from anywhere
        clientConnection = new ClientConnection(hostName, portNr);
        System.out.println("Username = " + username);
        clientConnection.sendObjectToServer(new ClientRequest(ClientRequest.TYPE.SET_USERNAME, username));
        System.out.println("Object sent to server");
    }

    @FXML
    public void writeToProperties(ActionEvent actionEvent) {
    }

    @FXML
    public void ConnectToServer(ActionEvent actionEvent) {

        if (usernameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Du måste ange ett användarnamn!", "Felmeddelande", JOptionPane.WARNING_MESSAGE);
            return;
        }

        initializeConnection(usernameField.getText());
        label.setText("Ansluten som " + usernameField.getText());
        label.setVisible(true);

        
        b1.setVisible(true);
    }
}
