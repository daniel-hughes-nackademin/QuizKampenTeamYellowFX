package ClientProgram.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChooseSubject {

    @FXML
    private Label label1;

    @FXML
    private Button bluebutton;

    @FXML
    private Button orangeubtton;

    @FXML
    private Button redbutton;

    @FXML
    void bluebutton(ActionEvent event) throws Exception{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ingameeea.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();


    }

    @FXML
    void orangebutton(ActionEvent event) {

    }

    @FXML
    void redbutton(ActionEvent event) {

    }
}
