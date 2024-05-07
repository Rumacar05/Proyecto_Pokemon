package service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertService {
    public static ButtonType showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);

        return alert.showAndWait().orElse(null);
    }
}
