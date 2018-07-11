package codecool.model;

import codecool.Field;

import java.util.ArrayList;

/* Kolekcja która bedzie służyć zarówno do przechowywania fieldów w danym rzędzie/kolumnie/kwadracie
 */

public class Collection {

    ArrayList<Field> listOfFieldsInThisRow;

    public Collection(){
        listOfFieldsInThisRow = new ArrayList<>();
    }

    public void addField(Field field){
        listOfFieldsInThisRow.add(field);
    }

    public ArrayList<Field> getListOfFieldsInThisRow() {
        return listOfFieldsInThisRow;
    }
}
