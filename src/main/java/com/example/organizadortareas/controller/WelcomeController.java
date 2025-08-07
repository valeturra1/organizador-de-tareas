package com.example.organizadortareas.controller;

import com.example.organizadortareas.model.TaskManager;
import com.example.organizadortareas.model.ThreadOverdueTasks;
import com.example.organizadortareas.view.CreateTaskStage;
import com.example.organizadortareas.view.TaskStage;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import java.io.IOException;

public class WelcomeController {
    @FXML private Button newTaskButton;
    @FXML private Button viewTasksButton;
    TaskManager manager = new TaskManager();

    @FXML
    private void handleNewTask(){
        try {
            CreateTaskStage.getInstance(manager).show();

            Stage actual = (Stage) newTaskButton.getScene().getWindow();
            actual.hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewTasks(){
        try {
            TaskStage.getInstance(manager).show();

            Stage actual = (Stage) viewTasksButton.getScene().getWindow();
            actual.hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
