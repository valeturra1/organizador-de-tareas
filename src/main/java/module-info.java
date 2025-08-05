module com.example.organizadortareas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.organizadortareas.controller to javafx.fxml;
    exports com.example.organizadortareas;
}