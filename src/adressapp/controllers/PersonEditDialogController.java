package adressapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import adressapp.models.Person;
import adressapp.utils.DateUtil;

public class PersonEditDialogController extends PersonController<TextField> {

    private Stage dialStage;
    private Person person;
    private boolean isOkClicked = false;

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    public void setPerson(Person person) {
        this.person = person;
        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        city.setText(person.getCity());
        street.setText(person.getStreet());
        postalCode.setText(Integer.toString(person.getPostalCode()));
        birthday.setText(DateUtil.format(person.getBirthday()));
        birthday.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() { return isOkClicked; }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstName.getText());
            person.setLastName(lastName.getText());
            person.setStreet(street.getText());
            person.setPostalCode(Integer.parseInt(postalCode.getText()));
            person.setCity(city.getText());
            person.setBirthday(DateUtil.parse(birthday.getText()));
            isOkClicked = true;
            dialStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (firstName.getText() == null || firstName.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastName.getText() == null || lastName.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (street.getText() == null || street.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (postalCode.getText() == null || postalCode.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            try {
                Integer.parseInt(postalCode.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }
        if (city.getText() == null || city.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }
        if (birthday.getText() == null || birthday.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthday.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        if (errorMessage.length() == 0) { return true; }
        else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
