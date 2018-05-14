package com.example.lab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FieldsAndMethodsController implements Initializable {


    @FXML
    private TableView<FieldInfo> fieldTable;

    @FXML
    private ChoiceBox<MethodInfo> methodChoiceBox;

    @FXML
    private Button callMethodButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button changeValueButton;

    // wywolanie metody
    @FXML
    void callMethodButtonPressed(ActionEvent event) {
        if(!methodChoiceBox.getSelectionModel().isEmpty()){
            Method m = methodChoiceBox.getValue().getMethod();

            try {
                m.invoke(ClassLoader.sampleObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void changeValuePressed(ActionEvent event) {
        FieldInfo selectedItem = fieldTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            ValuePicker vp = new ValuePicker(selectedItem);
            vp.showWindow(this);
        }
    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // dodaj pola do tabeli
        addFieldsToTable(ClassLoader.loadedClass);

        // dodaj metody do rozwijanej listy
        addMethodsToChoiceBox(ClassLoader.loadedClass);

        fieldTable.getSelectionModel().selectedItemProperty().addListener((obs, old, nw) -> {
            if(nw != null){
                // sprawdz, czy dla zaznaczonego itemu jest dostepny setter
                boolean state = false;

                String name = nw.getName();
                String type = nw.getType().toString();

                // znajdowanie settera
                for (Method m : ClassLoader.methods){
                    String temp = "set" + name.toLowerCase();
                    if(temp.equals(m.getName().toLowerCase())){
                        state = true;
                        break;
                    }
                }

                if(state){
                    changeValueButton.setDisable(false);
                }
                else{
                    changeValueButton.setDisable(true);
                }
            }
        });
    }

    public void refreshTable(){
        // lista pól
        List<FieldInfo> fieldList = new ArrayList<FieldInfo>();

        // dodawanie pol do tabeli
        for(Field f : ClassLoader.fields){
            String name = f.getName();
            String type = f.getType().toString();

            // pokaz tylko te pola, ktore maja gettery
            for (Method m : ClassLoader.methods){
                String temp = "get" + name.toLowerCase();
                if(temp.equals(m.getName().toLowerCase())){
                    // jezeli pole ma gettera
                    try {
                        f.setAccessible(true);
                        fieldList.add(new FieldInfo(f, name, type, f.get(ClassLoader.sampleObject).toString()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        ObservableList<FieldInfo> list = FXCollections.observableArrayList(fieldList);
        fieldTable.setItems(list);
    }

    private void addMethodsToChoiceBox(Class<?> cl){

        for (Method m : ClassLoader.methods){
            if(m.getParameterCount() == 0){
                m.setAccessible(true);
                methodChoiceBox.getItems().add(new MethodInfo(m, m.getName() + "()"));
            }
        }
    }

    private void addFieldsToTable(Class<?> cl){

        // ustawienia kolumn
        TableColumn<FieldInfo, String> nameColumn = new TableColumn<>("Name");
        TableColumn<FieldInfo, String> typeColumn = new TableColumn<>("Type");
        TableColumn<FieldInfo, String> valueColumn = new TableColumn<>("Value");

        // rozmiary kolumn
        nameColumn.prefWidthProperty().bind(fieldTable.widthProperty().divide(3));
        typeColumn.prefWidthProperty().bind(fieldTable.widthProperty().divide(3));
        valueColumn.prefWidthProperty().bind(fieldTable.widthProperty().divide(3));

        // przypisanie odpowiednich wartosci do kolumn
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        valueColumn.setCellValueFactory(
                new PropertyValueFactory<>("value")
        );

        fieldTable.getColumns().addAll(nameColumn, typeColumn, valueColumn);

        // lista pól
        List<FieldInfo> fieldList = new ArrayList<FieldInfo>();

        // dodawanie pol do tabeli
        refreshTable();
    }

}
