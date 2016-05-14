package application.view;

import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBaseController {
    private ObservableList<Person> data;
    private DataSetterController dataSetterController;

    public DataBaseController(DataSetterController dataSetterController){
        data = FXCollections.observableArrayList();

        data.add(new Person("Maks", "Andreevich", "Nikiforovec", "Belarus", "MinskRegion", "Minsk", "Gde-to tam", 0, 0, 0));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", "Belarus", "MinskRegion", "Minsk", "Gde-to tam", 1, 1, 1));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", "Belarus", "MinskRegion", "Minsk", "Gde-to tam", 2, 2, 2));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", "Belarus", "MinskRegion", "Minsk", "Gde-to tam", 3, 3, 3));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", "Belarus", "MinskRegion", "Minsk", "Gde-to tam", 4, 4, 4));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", "Belarus", "MinskRegion", "Minsk", "Gde-to tam", 5, 5, 5));
        data.add(new Person("Nastya", "Aleksandrovna", "Hilko", "Belarus", "MinskRegion", "Baranovichi", "Gde-to tam", 6, 6, 6));
        data.add(new Person("Nastya", "Aleksandrovna", "Hilko", "Belarus", "MinskRegion", "Baranovichi", "Gde-to tam", 7, 7, 7));

        this.dataSetterController = dataSetterController;
        changeDataSet();
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

    public void setData(ObservableList<Person> data){
        this.data = data;
        changeDataSet();
    }

    public ObservableList<Person> getData(){
        return data;
    }

    private void changeDataSet(){
        dataSetterController.setCurrentData(data);
    }

}
