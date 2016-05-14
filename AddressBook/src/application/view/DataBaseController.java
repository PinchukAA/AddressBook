package application.view;

import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataBaseController {
    private ObservableList<Person> data;
    private TableView view;
    private DataSetterController dataSetterController;

    private DataBaseController(){
        data = FXCollections.observableArrayList();
    }

    public boolean addPerson(Person person){
        boolean isAdded = data.add(person);
        if(isAdded) changeDataSet();
        return  isAdded;
    }

    public boolean deletePerson(Person person){
        boolean isDeleted = data.remove(person);
        if(isDeleted) changeDataSet();
        return isDeleted;
    }


    public boolean deletePerson(ObservableList<Person> persons){
        boolean isDeleted = data.removeAll(persons);
        if(isDeleted) changeDataSet();
        return isDeleted;
    }

    private void changeDataSet(){
        dataSetterController.setCurrentData(data);
    }

}
