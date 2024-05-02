package Library;

import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class TableCellValidation extends TableCell<Book, String> {
        private final TextField textField = new TextField();
        private final String validPattern = "^[A-Za-z ,]+$";

        public TableCellValidation() {
            textField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    if (textField.getText().matches(validPattern)) {
                        commitEdit(textField.getText());
                    } else {
                        cancelEdit();
                        showAlert("WARNING! Invalid Input", "Only letters and spaces are allowed.");
                    }
                } else if (event.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });
        }

        public void startEdit() {
            super.startEdit();
            textField.setText(getItem());
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            textField.requestFocus();
        }

        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem());
            setGraphic(null);
        }

        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    textField.setText(getItem());
                    setGraphic(textField);
                } else {
                    setText(getItem());
                    setGraphic(null);
                }
            }
        }

        private void showAlert(String input, String content) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(input);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }

    }

