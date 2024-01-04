package com.example.demo;

import com.example.demo.datamodel.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class StudentSystemComponent {
    VBox vBox;
    TextArea studentEntry;
    ListView<Student> studentListView;
    ObservableList<Student> students;
    Button saveButton;
    Button saveToFileButton;

    public StudentSystemComponent(){
        students = FXCollections.observableArrayList();
        vBox = new VBox();
        studentEntry = new TextArea();
        studentListView = new ListView<>();
        studentListView.setItems(students);
        studentListView.setCellFactory(param -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.Name == null) {
                    setText(null);
                } else {
                    setText(item.Name);
                }
            }
        });
        saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                var studentName = studentEntry.getText();
                var student = new Student();
                student.Name = studentName;
                student.MatricNumber = String.valueOf(new Random().nextInt(7));
                students.add(student);

            }
        });
        saveToFileButton = new Button();
        saveToFileButton.setOnAction(event ->{
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("./file.txt", true));
                for (int i = 0; i < students.size(); i++) {
                    writer.append("\n" + students.get(i).Name);
                }

                //writer.append("Demonstrating an append mode\n");
                writer.close();
                System.out.println("New itam has added!");

        }
	catch (IOException e){
            Dialog dialog = new Dialog();
            dialog.setTitle(e.getClass().getName());
            dialog.show();

        };});
        vBox.getChildren().add(studentEntry);
        vBox.getChildren().add(studentListView);
        vBox.getChildren().add(saveButton);
        vBox.getChildren().add(saveToFileButton);

    }

    public VBox getView(){
        return this.vBox;
    }

}
