package com.example.organizadortareas.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskStage extends Stage {
    public TaskStage() throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/MozillaHeadline-ExtraLight.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 14);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/organizadortareas/task-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        setTitle("Ver tareas");
        setScene(scene);
        setResizable(false);
        show();
    }

    public static TaskStage getInstance() throws IOException {
        if (TaskStage.TaskStageHolder.INSTANCE == null) {
            TaskStage.TaskStageHolder.INSTANCE = new TaskStage();
        }
        return TaskStage.TaskStageHolder.INSTANCE;
    }

    private static class TaskStageHolder {
        private static TaskStage INSTANCE;
    }
}
