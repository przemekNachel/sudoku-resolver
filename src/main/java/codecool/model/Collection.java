package codecool.model;

import codecool.Field;

import java.util.ArrayList;

/* Kolekcja która bedzie służyć zarówno do przechowywania fieldów w danym rzędzie/kolumnie/kwadracie
 */

public class Collection {

    int idOfColumn;
    ArrayList<Field> listOfFieldsInThisRow;
    private Field lastEmpty;

    public Collection(int idOfColumn){
        this.idOfColumn = idOfColumn;
        listOfFieldsInThisRow = new ArrayList<>();
    }

    public void addField(Field field){
        listOfFieldsInThisRow.add(field);
    }

    public ArrayList<Field> getListOfFieldsInThisRow() {
        return listOfFieldsInThisRow;
    }

    public boolean isLastEmpty() {
        ArrayList<Field> zeros = new ArrayList<>();
        for (Field field : listOfFieldsInThisRow) if (field.getValue() == 0) zeros.add(field);
        if (zeros.size() == 1) {
            lastEmpty = zeros.get(0);
            fillLastEmpty();
            return true;
        }
        return false;
    }

    private void fillLastEmpty() {
        ArrayList<Integer> values = new ArrayList<>();
        for (Field field : listOfFieldsInThisRow) values.add(field.getValue());
        for (int i = 1; i < 10; i ++) {
            if (!values.contains(i)) lastEmpty.setValue(i);
        }
    }

    private Field getFieldByValue(int value) {
        for (Field field : listOfFieldsInThisRow) if (field.getValue() == value) return field;
        return null;
    }

    public Field getLastEmpty() {
        return lastEmpty;
    }
}
