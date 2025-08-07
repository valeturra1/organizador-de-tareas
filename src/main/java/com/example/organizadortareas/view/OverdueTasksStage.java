package com.example.organizadortareas.view;

import com.example.organizadortareas.controller.CreateTaskController;
import com.example.organizadortareas.controller.OverdueTasksController;
import com.example.organizadortareas.controller.TaskController;
import com.example.organizadortareas.model.TaskManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class OverdueTasksStage extends Stage {
    private TaskManager manager;
    private TaskController taskController;

    public OverdueTasksStage(TaskManager manager, TaskController taskController) throws IOException {
        this.manager = manager;
        this.taskController = taskController;

        Font.loadFont(getClass().getResourceAsStream("/fonts/MozillaHeadline-ExtraLight.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 14);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/organizadortareas/overdue-tasks-view.fxml"));
        Parent root;
        try {
            root = loader.load();

            OverdueTasksController controller = loader.getController();
            controller.setTaskManager(manager);
            controller.setTaskController(taskController);

            taskController.startOverdueTaskThread(controller);

        } catch (IOException e) {
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        setTitle("Ver tareas vencidas");
        setScene(scene);
        setResizable(false);
        show();
    }

    public static OverdueTasksStage getInstance(TaskManager manager, TaskController taskController) throws IOException {
        if (OverdueTasksStage.OverdueTasksStageHolder.INSTANCE == null) {
            OverdueTasksStage.OverdueTasksStageHolder.INSTANCE = new OverdueTasksStage(manager, taskController);
        }
        return OverdueTasksStage.OverdueTasksStageHolder.INSTANCE;
    }

    private static class OverdueTasksStageHolder {
        private static OverdueTasksStage INSTANCE;
    }
}
