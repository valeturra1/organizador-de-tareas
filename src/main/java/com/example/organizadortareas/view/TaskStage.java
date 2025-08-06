package com.example.organizadortareas.view;

import com.example.organizadortareas.controller.CreateTaskController;
import com.example.organizadortareas.controller.TaskController;
import com.example.organizadortareas.model.TaskManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskStage extends Stage {
    private TaskManager manager;
    private TaskController controller;

    public TaskStage(TaskManager manager) throws IOException {
        this.manager = manager;
        Font.loadFont(getClass().getResourceAsStream("/fonts/MozillaHeadline-ExtraLight.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 14);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/organizadortareas/task-view.fxml"));

        Parent root;
        try {
            root = loader.load();

            controller = loader.getController();
            controller.setTaskManager(manager);
            controller.updateTaskList();

        } catch (IOException e) {
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        setTitle("Ver tareas");
        setScene(scene);
        setResizable(false);
        show();
    }

    public static TaskStage getInstance(TaskManager manager) throws IOException {
        if (TaskStage.TaskStageHolder.INSTANCE == null) {
            TaskStage.TaskStageHolder.INSTANCE = new TaskStage(manager);
        }
        return TaskStage.TaskStageHolder.INSTANCE;
    }

    private static class TaskStageHolder {
        private static TaskStage INSTANCE;
    }

    public TaskController getController() {
        return controller;
    }
}
