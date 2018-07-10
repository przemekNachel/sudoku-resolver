package codecool.model;

import java.util.ArrayList;

public class Column {

    int idOfColumn;
    ArrayList<Integer> listOfFieldsInThisRow;

    public Column(int idOfColumn){
        this.idOfColumn = idOfColumn;
        listOfFieldsInThisRow = new ArrayList<>();
    }

    public void addField(int field){
        listOfFieldsInThisRow.add(field);
    }
}
