package com.example.organizadortareas.controller;

import com.example.organizadortareas.model.Task;
import com.example.organizadortareas.model.TaskManager;
import com.example.organizadortareas.view.OverdueTasksStage;
import com.example.organizadortareas.view.WelcomeStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskController {
    @FXML private Button viewOverdueTasks;
    @FXML private Button exitButton;
    @FXML private VBox taskListVBox;
    @FXML private StackPane taskContainer;
    private TaskManager manager;
    private ArrayList<Task> tasks;

    @FXML
    public void initialize() {
        updateTaskList();
    }

    public void showTask(){
        taskListVBox.getChildren().clear();
        tasks = manager.getTasks();

        for (int i = 0; i < tasks.size(); i++){
            String textFill;
            String priorityColor;

            Task currentTask = tasks.get(i);
            String priority = currentTask.getPriority();

            switch (priority) {
                case "Alta":
                    priorityColor = "#E94E4E";
                    textFill = "#FFFFFF";
                    break;
                case "Media":
                    priorityColor = "#F5A623";
                    textFill = "#000000";
                    break;
                case "Baja":
                    priorityColor = "#B8C5D6";
                    textFill = "#000000";
                    break;
                default:
                    priorityColor = "#000000";
                    textFill = "#FFFFFF";
            }

            printTask(currentTask, priorityColor, textFill);
        }
    }

    public void printTask(Task currentTask, String priorityColor, String textFill){

        Label title = new Label(currentTask.getTitle());
        title.setFont(Font.font("Poppins Bold", 20));
        title.setStyle("-fx-background-color:" + priorityColor + "; -fx-text-fill: " + textFill);
        VBox.setMargin(title, new Insets(10, 0, 10, 0));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setWrapText(true);
        title.setMaxWidth(780);

        Label description = new Label(currentTask.getDescription());
        description.setFont(Font.font("Mozilla Headline ExtraLight", 17));
        description.setStyle("; -fx-text-fill: #000000");
        description.setWrapText(true);
        description.setMaxWidth(780);

        Text prefix = new Text("Fecha y hora límite: ");
        prefix.setFont(Font.font("Poppins Bold", 17));

        Text date = new Text(currentTask.getDate() + " a las " +
                currentTask.getHour() + ":" + currentTask.getMinute() + " " + currentTask.getPeriod());
        date.setFont(Font.font("Mozilla Headline ExtraLight", 17));

        TextFlow dateAndTime = new TextFlow(prefix, date);

        Button setDone = new Button("Marcar como realizado");
        setDone.setFont(Font.font("Mozilla Headline ExtraLight", 14));
        setDone.setStyle("-fx-background-radius: 15;");

        Button delete = new Button("Borrar tarea");
        delete.setFont(Font.font("Mozilla Headline ExtraLight", 14));
        delete.setStyle("-fx-background-radius: 15;");

        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(setDone, delete);

        setDone.setOnAction(event -> {
            title.setStyle("-fx-background-color: #A8E6CF ; -fx-text-fill: #000000");
            title.setText(currentTask.getTitle() + "   |   Realizado");
            buttonHBox.getChildren().remove(setDone);
            manager.deleteTask(currentTask);
        });

        delete.setOnAction(event -> {
            taskListVBox.getChildren().removeAll(title, description, dateAndTime, buttonHBox);
            manager.deleteTask(currentTask);
        });

        buttonHBox.setAlignment(Pos.CENTER);

        taskListVBox.getChildren().addAll(title, description, dateAndTime, buttonHBox);
    }

    @FXML
    private void handleViewOverdueTasks(){
        try{
            OverdueTasksStage.getInstance().show();

            Stage actual = (Stage) viewOverdueTasks.getScene().getWindow();
            actual.hide();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit(){
        try{
            WelcomeStage.getInstance().show();

            Stage actual = (Stage) exitButton.getScene().getWindow();
            actual.hide();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public TaskController getController() {
        return this;
    }

    public void setTaskManager(TaskManager manager) {
        this.manager = manager;
    }

    public void updateTaskList() {
        if (taskListVBox != null && manager != null) {
            Platform.runLater(() -> {
                showTask();
            });
        }
    }
}
