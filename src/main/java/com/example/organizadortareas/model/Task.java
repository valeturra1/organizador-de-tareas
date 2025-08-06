package com.example.organizadortareas.model;

import java.time.LocalDate;

public class Task {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Título: " + title + ", Descripción: " + description + ", Fecha: " + date + ", Hora: " + hour +
                ", Minuto: " + minute + ", Periodo: " + period + ", Prioridad: " + priority;
    }

}
