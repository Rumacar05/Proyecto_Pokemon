package service;

import javafx.scene.control.Alert;

public class AlertService {
    public static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
