package codecool.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FXController {

    private final Stage stage;
    private final Scene scene;

    @FXML
    private TextField sudoku;

    public FXController(FXMLLoader loader, Stage stage) throws IOException {
        loader.setController(this);
        this.stage = stage;
        this.scene = new Scene(loader.<Parent>load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleOkButton() {
        String sudokuBoard = sudoku.getText();
        System.out.println(sudokuBoard);
    }
}
