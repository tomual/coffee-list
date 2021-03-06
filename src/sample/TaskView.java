package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class TaskView {

    private User user;
    private Task task;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private Text description;

    @FXML
    private JFXCheckBox checkBox;

    @FXML
    void backButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("taskList.fxml"));
            AnchorPane form = loader.load();
            TaskList taskList = loader.getController();
            taskList.setUser(user);
            taskList.initialize();
            anchorPane.getChildren().setAll(form);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void checkTask(ActionEvent event) {

    }

    @FXML
    void deleteButtonClick(ActionEvent event) {
        Database database = new Database();
        database.deleteTask(task);
        backButtonClick(event);
    }

    public void checkboxClick(JFXCheckBox checkBox, Task task) {
        Database database = new Database();
        if(checkBox.isSelected()) {
            database.setTaskComplete(task);
        } else {
            database.setTaskIncomplete(task);
        }
    }

    @FXML
    void initialize() {

        if (task != null) {
            System.out.println("$$$$$$$$$$$$$$$$$$$$");
            System.out.println(task.getLabel());
            checkBox.setText(task.getLabel());
            description.setText(task.getDescripion());
            checkBox.setSelected(task.isComplete());
            checkBox.setOnMouseClicked(event -> checkboxClick(checkBox, task));
        }
    }

    public void setUser(User user) {
        this.user = user;
        System.out.println("Hello, " + user.getUsername() + "!");
    }

    public void setTask(Task task) {
        this.task = task;
        System.out.println("Opened task: " + task.getLabel());
    }
}
