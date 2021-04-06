package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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

public class LoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginSignupButton;

    @FXML
    void initialize() {
        DatabaseHandler databaseHandler = new DatabaseHandler();


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String loginText = loginUsername.getText().trim();
                String loginPwd = loginPassword.getText().trim();

                User user = new User();
                user.setUserName(loginText);
                user.setPassword(loginPwd);

                ResultSet userRow = databaseHandler.getUser(user);

                int counter = 0;

                try {
                    while (userRow.next()) {
                        counter++;
                    }

                    if (counter == 1) {
                        System.out.println("Login successful!");
                        showAddItemScreen();
                    } else {
                        Shaker shUsername = new Shaker(loginUsername);
                        shUsername.shake();
                        Shaker shPassword = new Shaker(loginPassword);
                        shPassword.shake();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        loginSignupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("ToDo");
                    stage.show();
                    // Hide this current window (if this is what you want)
                    loginSignupButton.getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void showAddItemScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addItem.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("ToDo");
            stage.show();
            // Hide this current window (if this is what you want)
            loginButton.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
