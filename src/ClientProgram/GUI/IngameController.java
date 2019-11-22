package ClientProgram.GUI;

import ClientProgram.ClientConnection;
import ServerUtilities.ClientRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IngameController{
    int counter = 0;
    String input = "";

    ClientConnection clientConnection;

    @FXML
    private AnchorPane question;


    @FXML
    public Button answer1;

    @FXML
    public Button answer2;

    @FXML
    public Button answer3;

    @FXML
    public Button answer4;

    @FXML
    private Label pointslabel;

    @FXML
    private Label labelpoints;

    @FXML
    public TextArea questionArea;

    @FXML
    private ImageView imageView;

    @FXML
    private Button continueButton;


    @FXML
    private Button chatwindowButton;

    @FXML
    private Pane chatpane;

    @FXML
    public TextArea chatWindow;

    @FXML
    public Button sendmsgButton;

    @FXML
    public TextField msgTextField;


    public void initializeConnection(String username){
        String hostName = "127.0.0.1";
        int portNr = 13377;
        this.clientConnection = new ClientConnection(hostName, portNr, this);
        System.out.println("Username = " + username);
        clientConnection.sendObjectToServer(new ClientRequest(ClientRequest.TYPE.SEND_USERNAME, username));
        System.out.println("Object sent to server");
        //FIX ABILITY TO PASS USERNAME FROM LOGIN WINDOW TO IN-GAME WINDOW
    }


    @FXML
    void continuetoWinninglobby(ActionEvent event) throws Exception{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("WinnerLobby.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();


    }


    @FXML
    void answer1(ActionEvent event) {
        counter++;
        labelpoints.setVisible(true);
        labelpoints.setText(String.valueOf(counter));


    }

    @FXML
    void answer2(ActionEvent event) {

    }

    @FXML
    void answer3(ActionEvent event) {

    }

    @FXML
    void answer4(ActionEvent event) {

    }

    @FXML
    void sendMsg(ActionEvent event) {
            String messageToServer;
            if (!msgTextField.getText().isEmpty()) {
                messageToServer = msgTextField.getText();
                msgTextField.setText("");

                chatWindow.appendText("[Du] " + messageToServer + "\n");
                ClientRequest messageRequest = new ClientRequest(ClientRequest.TYPE.MESSAGE_TO_ALL, messageToServer);
                clientConnection.sendObjectToServer(messageRequest);
            }
        }

    @FXML
    void openchatWindow(ActionEvent event) {
        chatpane.setVisible(true);
        chatWindow.setVisible(true);
        sendmsgButton.setVisible(true);
        msgTextField.setVisible(true);

    }

    public void initialize() {
        //If we want to do stuff right after the scene is loaded
    }
}


