package sample;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signupFirstName;

    @FXML
    private TextField signupUsername;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private Button signupRegisterButton;

    @FXML
    void initialize() {
        signupRegisterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createUser();
            }
        });
    }

    private void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = signupFirstName.getText();
        String username = signupUsername.getText();
        String password = signupPassword.getText();

        User user = new User(name, username, password);

        databaseHandler.signUpUser(user);
    }

}
