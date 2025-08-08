package com.example.organizadortareas.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskManager implements Serializable {
    private ArrayList<Task> taskList;
    private ArrayList<Task> overdueTaskList;

    public TaskManager (){
        this.taskList = new ArrayList<>();
        this.overdueTaskList = new ArrayList<>();
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void addOverdueTask(Task task){
        overdueTaskList.add(task);
    }

    public void deleteTask(Task task){
        taskList.remove(task);
    }

    public void deleteOverdueTask(Task task){
        overdueTaskList.remove(task);
    }

    public ArrayList<Task> getTasks(){
        return taskList;
    }

    public ArrayList<Task> getOverdueTasks(){
        return overdueTaskList;
    }
}