package com.example.organizadortareas.controller;

import com.example.organizadortareas.model.Task;
import com.example.organizadortareas.model.TaskManager;
import com.example.organizadortareas.model.ThreadOverdueTasks;
import com.example.organizadortareas.view.TaskStage;
import com.example.organizadortareas.view.WelcomeStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class OverdueTasksController {
    @FXML private Button viewTasks;
    @FXML private Button exitButton;
    @FXML private VBox overdueTaskListVBox;

    private ArrayList<Task> tasks;
    private ThreadOverdueTasks threadOverdueTasks;
    private TaskController taskController;
    private TaskManager manager;

    @FXML
    private void initialize(){
        updateOverdueTaskList();
    }

    public void showOverdueTasks(){
        overdueTaskListVBox.getChildren().clear();
        tasks = manager.getOverdueTasks();

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

            printOverdueTask(currentTask, priorityColor, textFill);
        }
    }

    public void printOverdueTask(Task currentTask, String priorityColor, String textFill){

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

        Button delete = new Button("Borrar tarea");
        delete.setFont(Font.font("Mozilla Headline ExtraLight", 14));
        delete.setStyle("-fx-background-radius: 15;");

        HBox buttonHBox = new HBox(10);
        buttonHBox.getChildren().add(delete);
        buttonHBox.setAlignment(Pos.CENTER);

        delete.setOnAction(event -> {
            overdueTaskListVBox.getChildren().removeAll(title, description, dateAndTime, buttonHBox);
            manager.deleteOverdueTask(currentTask);
            showOverdueTasks();
        });

        overdueTaskListVBox.getChildren().addAll(title, description, dateAndTime, buttonHBox);
    }

    @FXML
    private void handleViewTasks(){
        try{
            TaskStage.getInstance(manager).show();

            Stage actual = (Stage) viewTasks.getScene().getWindow();
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

    public void updateOverdueTaskList() {
        if (overdueTaskListVBox != null && manager != null) {
            Platform.runLater(() -> {
                showOverdueTasks();
            });
        }
    }

    public void setTaskManager(TaskManager manager) {
        this.manager = manager;
    }

    public OverdueTasksController getController(){
        return this;
    }

    public void setTaskController(TaskController taskController) {
        this.taskController = taskController;
    }
}
