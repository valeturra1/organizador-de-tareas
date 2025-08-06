package com.example.organizadortareas.model;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager (){
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void deleteTask(Task task){
        taskList.remove(task);
    }

    public ArrayList<Task> getTasks(){
        return taskList;
    }
}
