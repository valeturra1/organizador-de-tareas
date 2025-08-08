package com.example.organizadortareas.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String title;
    private String description;
    private LocalDate date;
    private String hour;
    private String minute;
    private String period;
    private String priority;

    public Task (String title, String description, LocalDate date, String hour, String minute, String period, String priority){
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.period = period;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getMinute() {
        return minute;
    }

    public String getPeriod() {
        return period;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Título: " + title + ", Descripción: " + description + ", Fecha: " + date + ", Hora: " + hour +
                ", Minuto: " + minute + ", Periodo: " + period + ", Prioridad: " + priority;
    }

}
