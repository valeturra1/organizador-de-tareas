package com.example.organizadortareas.controller;

import com.example.organizadortareas.view.OverdueTasksStage;
import com.example.organizadortareas.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskController {
    @FXML private Button viewOverdueTasks;
    @FXML private Button exitButton;

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
}
