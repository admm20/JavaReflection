package com.example.lab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

// Okno ktore pokazuje sie, gdy chcemy zmienic wartosc pola danego obiektu
public class ValuePicker {

    private FieldInfo fieldInfo;
    private ValueType valueType;
    private Stage stage = null;
    private FieldsAndMethodsController ref;

    public ValuePicker(FieldInfo fieldInfo){
        this.fieldInfo = fieldInfo;
    }

    // pokazanie okna w ktorym mozna zmieniac wartosc
    public void showWindow(FieldsAndMethodsController ref){
        this.ref = ref; // referencja do obiektu z tabela (potrzebna do odswiezania tabeli)
        stage = new Stage();
        stage.setTitle("Insert value");

        Class<?> type = fieldInfo.getField().getType();

        if(type.toString().equals("class java.lang.String")){
            System.out.println("STRING");
            modifyString();
        }
        else if(type.isEnum()){
            System.out.println("ENUM");
            modifyEnum();
        }
        else if(type.toString().equals("double")){
            System.out.println("DOUBLE");
            modifyDouble();
        }
        else if(type.toString().equals("int")){
            System.out.println("INT");
            modifyInt();
        }
        else if(type.toString().equals("class java.util.Date")){
            System.out.println("DATE");
            modifyDate();
        }
        else{
            System.out.println("not supported");
        }
    }

    // okno z modyfikacja integera
    private void modifyInt(){
        VBox vbox = new VBox();
        TextField textField = new TextField();
        Button ok = new Button("Ok");

        vbox.getChildren().addAll(textField, ok);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);


        stage.setScene(new Scene(vbox));
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();

        // klikniecie przycisku
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // pobierz zmienna z pola
                int input = Integer.parseInt(textField.getText());

                // zmien wartosci obiektu
                try {
                    fieldInfo.getField().set(ClassLoader.sampleObject, input);
                    fieldInfo.setValue("" + input); // heh. zamiana inta na string
                    ref.refreshTable();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // zamknij okno
                Stage stage = (Stage) ok.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void modifyDouble(){
        VBox vbox = new VBox();
        TextField textField = new TextField();
        Button ok = new Button("Ok");

        vbox.getChildren().addAll(textField, ok);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);


        stage.setScene(new Scene(vbox));
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();

        // klikniecie przycisku
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // pobierz zmienna z pola
                double input = Double.parseDouble(textField.getText());

                // zmien wartosci obiektu
                try {
                    fieldInfo.getField().set(ClassLoader.sampleObject, input);
                    fieldInfo.setValue("" + input); // heh. zamiana double na string
                    ref.refreshTable();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // zamknij okno
                Stage stage = (Stage) ok.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void modifyString(){
        VBox vbox = new VBox();
        TextField textField = new TextField();
        Button ok = new Button("Ok");

        vbox.getChildren().addAll(textField, ok);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);


        stage.setScene(new Scene(vbox));
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();

        // klikniecie przycisku
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // pobierz zmienna z pola
                String input = textField.getText();

                // zmien wartosci obiektu
                try {
                    fieldInfo.getField().set(ClassLoader.sampleObject, input);
                    fieldInfo.setValue(input);
                    ref.refreshTable();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // zamknij okno
                Stage stage = (Stage) ok.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void modifyDate(){
        VBox vbox = new VBox();
        DatePicker datePicker = new DatePicker();
        Button ok = new Button("Ok");

        vbox.getChildren().addAll(datePicker, ok);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);


        stage.setScene(new Scene(vbox));
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();

        // klikniecie przycisku
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // pobierz zmienna z pola
                String input = datePicker.getValue().toString();
                Date date = null;
                // set date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    System.out.println("COSTAM: " + input);
                    date = dateFormat.parse(input);
                } catch (ParseException e) {
                    System.out.println("Cannot parse date");
                }


                // zmien wartosci obiektu
                try {
                    fieldInfo.getField().set(ClassLoader.sampleObject, date);
                    fieldInfo.setValue(date.toString());
                    ref.refreshTable();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // zamknij okno
                Stage stage = (Stage) ok.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void modifyEnum(){
        VBox vbox = new VBox();
        ChoiceBox<EnumSelection> choiceBox = new ChoiceBox<>();
        Button ok = new Button("Ok");

        vbox.getChildren().addAll(choiceBox, ok);
        vbox.setPadding(new Insets(20,20,20,20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        // dodaj elementy do listy wyborow:
        for(Object o : fieldInfo.getField().getType().getEnumConstants()){
            choiceBox.getItems().add(new EnumSelection(o, o.toString()));
        }


        stage.setScene(new Scene(vbox));
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();

        // klikniecie przycisku
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // pobierz zmienna z pola
                String input = choiceBox.getValue().toString();
                Object obj = choiceBox.getValue().getEnumObj();

                // zmien wartosci obiektu
                try {
                    fieldInfo.getField().set(ClassLoader.sampleObject, obj);
                    fieldInfo.setValue(input);
                    ref.refreshTable();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // zamknij okno
                Stage stage = (Stage) ok.getScene().getWindow();
                stage.close();
            }
        });
    }
}
