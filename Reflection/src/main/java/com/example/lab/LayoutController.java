
package com.example.lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LayoutController {

    @FXML
    private TextField classNameTextField;

    @FXML
    private Button runButton;

    @FXML
    private Button exitButton;

    @FXML
    void exitButtonPressed(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void runButtonPressed(ActionEvent event) {
        ClassLoader cl = new ClassLoader(classNameTextField.getText());
        if (cl.isOk()) {

            // jeżeli podano poprawną nazwę pakietu, to utwórz nowe okno
            Stage st = new Stage();

            VBox vBox = null;
            try {
                vBox = FXMLLoader.load(getClass().getResource("/FieldsAndMethods.fxml"));
                st.setScene(new Scene(vBox));
                st.setTitle("Object fields and methods");
                st.show();
            } catch (IOException e) {
                System.out.println("Cannot create new window");
                e.printStackTrace();
            }


            // zamknij to okno
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
    }

}
