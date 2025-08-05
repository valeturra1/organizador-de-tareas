package com.example.organizadortareas.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class OverdueTasksStage extends Stage {
    public OverdueTasksStage() throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/MozillaHeadline-ExtraLight.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 14);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/organizadortareas/overdue-tasks-view.fxml"));
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

    public static OverdueTasksStage getInstance() throws IOException {
        if (OverdueTasksStage.OverdueTasksStageHolder.INSTANCE == null) {
            OverdueTasksStage.OverdueTasksStageHolder.INSTANCE = new OverdueTasksStage();
        }
        return OverdueTasksStage.OverdueTasksStageHolder.INSTANCE;
    }

    private static class OverdueTasksStageHolder {
        private static OverdueTasksStage INSTANCE;
    }
}
