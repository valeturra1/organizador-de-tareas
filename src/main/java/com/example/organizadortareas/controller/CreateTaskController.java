package com.example.organizadortareas.controller;

import com.example.organizadortareas.model.Task;
import com.example.organizadortareas.model.TaskManager;
import com.example.organizadortareas.view.TaskStage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CreateTaskController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> hourBox;
    @FXML private ComboBox<String> minuteBox;
    @FXML private ComboBox<String> periodBox;
    @FXML private Button lowButton;
    @FXML private Button midButton;
    @FXML private Button highButton;
    @FXML private Button taskButton;
    @FXML private Label messageLabel;

    private String title;
    private String description;
    private LocalDate date;
    private String hour;
    private String minute;
    private String period;
    private String priority;

    private TaskManager manager;
    private TaskController taskController;


    @FXML
    public void initialize() {
        titleField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > 80) {
                titleField.setText(oldValue);
            }
        });

        descriptionArea.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > 300) {
                descriptionArea.setText(oldValue);
            }
        });

        for (int hour = 1; hour <= 12; hour++) {
            hourBox.getItems().add(String.format("%02d", hour));
        }

        for (int minute = 0; minute < 60; minute++) {
            minuteBox.getItems().add(String.format("%02d", minute));
        }

        periodBox.getItems().add("AM");
        periodBox.getItems().add("PM");
    }

    public void createTask(){
        title = titleField.getText();
        description = descriptionArea.getText();
        date = datePicker.getValue();
        hour = hourBox.getValue();
        minute = minuteBox.getValue();
        period = periodBox.getValue();

        Task task = new Task(title, description, date, hour, minute, period, priority);
        manager.addTask(task);

        try {
            TaskController taskController = TaskStage.getInstance(manager).getController();
            taskController.updateTaskList();

        } catch (IOException e){
            e.printStackTrace();
        }

        clearFields();
    }

    public boolean checkValues() {
        LocalDate today = LocalDate.now();
        LocalTime actualHour = LocalTime.now();

        if (titleField.getText().isEmpty()) {
            showMessage("El título no puede estar vacío");
            return false;
        }

        if (descriptionArea.getText().isEmpty()) {
            showMessage("La descripción no puede estar vacía");
            return false;
        }

        if (datePicker.getValue() == null) {
            showMessage("Escoja una fecha");
            return false;
        }

        if (datePicker.getValue().isBefore(today)) {
            showMessage("Escoja una fecha válida, la fecha es anterior al día de hoy");
            return false;
        }

        if (hourBox.getValue() == null || minuteBox.getValue() == null || periodBox.getValue() == null) {
            showMessage("Escoja una hora válida");
            return false;
        }

        int selectedHour = Integer.parseInt(hourBox.getValue());
        int selectedMinute = Integer.parseInt(minuteBox.getValue());
        String selectedPeriod = periodBox.getValue();

        if ("PM".equals(selectedPeriod) && selectedHour != 12) {
            selectedHour += 12;
        } else if ("AM".equals(selectedPeriod) && selectedHour == 12) {
            selectedHour = 0;
        }

        LocalTime selectedTime = LocalTime.of(selectedHour, selectedMinute);

        if (datePicker.getValue().isEqual(today) && selectedTime.isBefore(actualHour)) {
            showMessage("Escoja una hora válida, la hora escogida ya pasó");
            return false;
        }

        if (priority == null || priority.isEmpty()) {
            showMessage("Escoja una prioridad");
            return false;
        }

        hideMessage();
        return true;
    }

    private void clearFields(){
        titleField.clear();
        titleField.setPromptText("Ingrese un título");

        descriptionArea.clear();
        descriptionArea.setPromptText("Ingrese una descripción de la tarea");

        datePicker.setValue(null);
        datePicker.setPromptText("Seleccione una fecha");

        hourBox.getSelectionModel().clearSelection();
        hourBox.setPromptText("Seleccione hora");

        minuteBox.getSelectionModel().clearSelection();
        minuteBox.setPromptText("Seleccione minuto");

        periodBox.getSelectionModel().clearSelection();
        periodBox.setPromptText("AM / PM");

        lowButton.setStyle("-fx-background-color: #B8C5D6; -fx-text-fill: black; -fx-font-family: 'Mozilla Headline ExtraLight'; -fx-font-size: 14; -fx-background-radius: 10;");
        midButton.setStyle("-fx-background-color: #F5A623; -fx-text-fill: black; -fx-font-family: 'Mozilla Headline ExtraLight'; -fx-font-size: 14; -fx-background-radius: 10;");
        highButton.setStyle("-fx-background-color: #E94E4E; -fx-text-fill: white; -fx-font-family: 'Mozilla Headline ExtraLight'; -fx-font-size: 14; -fx-background-radius: 10;");
    }

    public void showMessage(String text) {
        messageLabel.setText(text);
        messageLabel.setVisible(true);
        messageLabel.setManaged(true);
    }

    public void hideMessage() {
        messageLabel.setVisible(false);
        messageLabel.setManaged(false);
    }

    @FXML
    public void handleCreateTask() {
        if (!checkValues()){
            return;
        }

        createTask();

        ArrayList<Task> tasks = manager.getTasks();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(task.toString());
        }

        try {
            TaskStage.getInstance(manager).show();

            Stage actual = (Stage) taskButton.getScene().getWindow();
            actual.hide();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLowPriority() {
        selectPriorityButton(lowButton, "Baja");
    }

    @FXML
    private void handleMidPriority() {
        selectPriorityButton(midButton, "Media");
    }

    @FXML
    private void handleHighPriority() {
        selectPriorityButton(highButton, "Alta");
    }

    private void selectPriorityButton(Button selectedButton, String selectedPriority) {
        lowButton.setStyle("-fx-background-color: #B8C5D6; -fx-text-fill: black; -fx-font-family: 'Mozilla Headline ExtraLight'; -fx-font-size: 14; -fx-background-radius: 10;");
        midButton.setStyle("-fx-background-color: #F5A623; -fx-text-fill: black; -fx-font-family: 'Mozilla Headline ExtraLight'; -fx-font-size: 14; -fx-background-radius: 10;");
        highButton.setStyle("-fx-background-color: #E94E4E; -fx-text-fill: white; -fx-font-family: 'Mozilla Headline ExtraLight'; -fx-font-size: 14; -fx-background-radius: 10;");

        selectedButton.setStyle(selectedButton.getStyle() + " -fx-border-color: green; -fx-border-width: 3px; -fx-border-radius: 10;");

        priority = selectedPriority;
    }


    public void setTaskManager(TaskManager manager) {
        this.manager = manager;
    }

    public CreateTaskController getController() {
        return this;
    }
}
