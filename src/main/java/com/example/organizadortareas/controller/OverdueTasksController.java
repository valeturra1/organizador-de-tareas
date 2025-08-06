package com.example.organizadortareas.controller;

import com.example.organizadortareas.model.TaskManager;
import com.example.organizadortareas.view.OverdueTasksStage;
import com.example.organizadortareas.view.TaskStage;
import com.example.organizadortareas.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class OverdueTasksController {
    @FXML private Button viewTasks;
    @FXML private Button exitButton;
    TaskManager manager = new TaskManager();

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
}
