package com.example.organizadortareas.model;

import com.example.organizadortareas.controller.OverdueTasksController;
import com.example.organizadortareas.controller.TaskController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ThreadOverdueTasks extends Thread {
    private TaskManager manager;
    private TaskController taskController;
    private OverdueTasksController overdueTaskController;
    private SerializableFileHandler serializableFileHandler;

    public ThreadOverdueTasks(TaskManager manager, OverdueTasksController overdueTasksController, TaskController taskController) {
        this.manager = manager;
        this.overdueTaskController = overdueTasksController;
        this.taskController = taskController;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            checkAndMoveOverdueTasks();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void checkAndMoveOverdueTasks() {
        ArrayList<Task> tasks;
        tasks = manager.getTasks();

        ArrayList<Task> copyTasks = new ArrayList<>(tasks);
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        for (int i = 0; i < copyTasks.size(); i++) {
            Task currentTask = copyTasks.get(i);
            if (isTaskOverdue(currentTask, today, now)) {
                manager.addOverdueTask(currentTask);
                manager.deleteTask(currentTask);

                serializableFileHandler = new SerializableFileHandler();
                serializableFileHandler.serialize("task_manager.ser", manager);

                overdueTaskController.updateOverdueTaskList();
                taskController.updateTaskList();
            }
        }
    }

    public boolean isTaskOverdue(Task task) {
        return isTaskOverdue(task, LocalDate.now(), LocalTime.now());
    }

    private boolean isTaskOverdue(Task task, LocalDate today, LocalTime now) {
        int selectedHour = Integer.parseInt(task.getHour());
        int selectedMinute = Integer.parseInt(task.getMinute());


        if ("PM".equals(task.getPeriod()) && selectedHour != 12) {
            selectedHour += 12;
        } else if ("AM".equals(task.getPeriod()) && selectedHour == 12) {
            selectedHour = 0;
        }

        LocalTime taskTime = LocalTime.of(selectedHour, selectedMinute);
        LocalDate taskDate = task.getDate();

        return taskDate.isBefore(today) || (taskDate.isEqual(today) && taskTime.isBefore(now));
    }
}
