package codecool;

import codecool.model.Field;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class FXController {

    private final Stage stage;
    private final Scene scene;
    private int textIndex = 0;

    @FXML
    private TextField sudoku;
    @FXML
    private GridPane table;
    @FXML
    private TextArea textArea;

    public FXController(FXMLLoader loader, Stage stage) throws IOException {
        loader.setController(this);
        this.stage = stage;
        this.scene = new Scene(loader.<Parent>load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleOkButton() throws InterruptedException {
        String sudokuBoard = sudoku.getText();
        int[][] sudoku = Tools.stringToArray(sudokuBoard);
        Resolver.startTime = System.currentTimeMillis();
        new Resolver(sudoku).run();
        ArrayList<Field> solvedArray = Tools.getResultSolvedBoard();
        while (solvedArray == null){
            solvedArray = Tools.getResultSolvedBoard();
            sleep(100);
        }
        textArea.insertText(textIndex,"Solved in: " + Tools.getResultStopTimeMilli() + " milliseconds\n" +
                "Using: " + Tools.getResolverThreads().size() + " threads\n\n");
        Tools.resetThreadCount();
        int [][] solvedBoard = Tools.fieldsToArray(solvedArray);
        insertTableValues(solvedBoard);
    }

    public void insertTableValues(int[][] solvedBoard){
        for(int rowNo=0; rowNo<9; rowNo++){
            for(int columnNo=0; columnNo<9; columnNo++){
                Label field = new Label(String.valueOf(solvedBoard[rowNo][columnNo]));
                field.setMaxWidth(Double.MAX_VALUE);
                field.setAlignment(Pos.CENTER);
                table.add(field, columnNo, rowNo);
            }
        }
    }
}
