import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;
    private Date startTime;
    private Date endTime;
    private String priority;

    public Task(String description, Date startTime, Date endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("Description: %s\nStart Time: %s\nEnd Time: %s\nPriority: %s",
                description, sdf.format(startTime), sdf.format(endTime), priority);
    }
}

class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public void addTask(String description, String startTime, String endTime, String priority) {
        try {
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            Task task = new Task(description, start, end, priority);
            tasks.add(task);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD HH:MM.");
        }
    }

    public void removeTask(String description) {
        tasks.removeIf(task -> task.getDescription().equals(description));
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Task " + (i + 1) + ":");
                System.out.println(tasks.get(i));
                System.out.println("------------------------------");
            }
        }
    }

    public void updateTask(String description, String newDescription, String newStartTime, String newEndTime, String newPriority) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                if (!newDescription.isEmpty()) {
                    task.setDescription(newDescription);
                }
                if (!newStartTime.isEmpty()) {
                    try {
                        Date newStart = sdf.parse(newStartTime);
                        task.setStartTime(newStart);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format for start time.");
                    }
                }
                if (!newEndTime.isEmpty()) {
                    try {
                        Date newEnd = sdf.parse(newEndTime);
                        task.setEndTime(newEnd);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format for end time.");
                    }
                }
                if (!newPriority.isEmpty()) {
                    task.setPriority(newPriority);
                }
                return;
            }
        }
        System.out.println("Task not found.");
    }
}

public class ScheduleManager {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Astronaut Schedule Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Update Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter start time (YYYY-MM-DD HH:MM): ");
                    String startTime = scanner.nextLine();
                    System.out.print("Enter end time (YYYY-MM-DD HH:MM): ");
                    String endTime = scanner.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = scanner.nextLine();
                    manager.addTask(description, startTime, endTime, priority);
                    break;

                case "2":
                    System.out.print("Enter task description to remove: ");
                    String removeDescription = scanner.nextLine();
                    manager.removeTask(removeDescription);
                    break;

                case "3":
                    manager.viewTasks();
                    break;

                case "4":
                    System.out.print("Enter task description to update: ");
                    String updateDescription = scanner.nextLine();
                    System.out.print("Enter new description (leave blank for no change): ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new start time (YYYY-MM-DD HH:MM, leave blank for no change): ");
                    String newStartTime = scanner.nextLine();
                    System.out.print("Enter new end time (YYYY-MM-DD HH:MM, leave blank for no change): ");
                    String newEndTime = scanner.nextLine();
                    System.out.print("Enter new priority (High/Medium/Low, leave blank for no change): ");
                    String newPriority = scanner.nextLine();
                    manager.updateTask(updateDescription, newDescription, newStartTime, newEndTime, newPriority);
                    break;

                case "5":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}