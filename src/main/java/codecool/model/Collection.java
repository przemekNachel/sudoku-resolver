package codecool.model;

import codecool.Field;

import java.util.ArrayList;

/* Kolekcja która bedzie służyć zarówno do przechowywania fieldów w danym rzędzie/kolumnie/kwadracie
 */

public class Collection {

    int idOfColumn;
    ArrayList<Field> listOfFieldsInThisRow;

    public Collection(int idOfColumn){
        this.idOfColumn = idOfColumn;
        listOfFieldsInThisRow = new ArrayList<>();
    }

    public void addField(Field field){
        listOfFieldsInThisRow.add(field);
    }
}
