package com.example.organizadortareas.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {

    public WelcomeStage() throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/fonts/MozillaHeadline-ExtraLight.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Bold.ttf"), 14);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/organizadortareas/welcome-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new IOException("Error while loading FXML file", e);
        }

        Scene scene = new Scene(root);

        setTitle("Mis Tareas");
        setScene(scene);
        setResizable(false);
        show();
    }

    public static WelcomeStage getInstance() throws IOException {
        if (WelcomeStageHolder.INSTANCE == null) {
            WelcomeStageHolder.INSTANCE = new WelcomeStage();
        }
        return WelcomeStageHolder.INSTANCE;
    }

    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

}
