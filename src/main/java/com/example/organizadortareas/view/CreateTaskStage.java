package com.example.organizadortareas.view;

import com.example.organizadortareas.controller.CreateTaskController;
import com.example.organizadortareas.model.TaskManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateTaskStage extends Stage {
    private TaskManager manager;

    public CreateTaskStage(TaskManager manager) throws IOException {
        this.manager = manager;

        Font.loadFont(getClass().getResourceAsStream("/fonts/MozillaHeadline-ExtraLight.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 14);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/organizadortareas/create-task-view.fxml"));
        Parent root;
        try {
            root = loader.load();

            CreateTaskController controller = loader.getController();
            controller.setTaskManager(manager);

        } catch (IOException e) {
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        setTitle("Crear tarea");
        setScene(scene);
        setResizable(false);
        show();
    }

    public static CreateTaskStage getInstance(TaskManager manager) throws IOException {
        if (CreateTaskStage.CreateTaskStageHolder.INSTANCE == null) {
            CreateTaskStage.CreateTaskStageHolder.INSTANCE = new CreateTaskStage(manager);
        }
        return CreateTaskStage.CreateTaskStageHolder.INSTANCE;
    }

    private static class CreateTaskStageHolder {
        private static CreateTaskStage INSTANCE;
    }
}
