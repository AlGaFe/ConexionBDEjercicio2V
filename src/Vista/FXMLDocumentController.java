package Vista;

import Datos.BDA;
import Modelo.Comunidad;
import com.mysql.jdbc.Connection;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/* @author Álvaro García Fernández */
public class FXMLDocumentController implements Initializable {

    private Connection conn;
    private int numFilas;
    private Label label;
    @FXML
    private Button ButtonGuardar;
    @FXML
    private Label LabelComunidad;
    @FXML
    private Label LabelConectado;
    @FXML
    private Label LabeDesconectado;
    private TextField TextFieldComunidad;
    @FXML
    private TextField TextFliedID;
    @FXML
    private Label LabelID;
    @FXML
    private Label LabelNombre;
    @FXML
    private Label LabelExtension;
    @FXML
    private TextField TextFliedNombre;
    @FXML
    private TextField TextFliedExtension;
    @FXML
    private ComboBox<Comunidad> ComboBoxComunidades;
    ObservableList listaComunidades = FXCollections.observableArrayList();
    BDA bda = new BDA();

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (bda.conexion() == true) {
            this.LabeDesconectado.setText("");
        } else {
            this.LabelConectado.setText("");
        }
        rellenarComboBox();
    }

    @FXML
    private void ActionButtonGuardar(ActionEvent event) {
        String nombre;
        int id;
        String extension;
        String comunidad;
        boolean respuesta;

        id = Integer.valueOf(this.TextFliedID.getText());
        nombre = this.TextFliedNombre.getText();
        extension = this.TextFliedExtension.getText();
        comunidad = this.TextFliedExtension.getText();

        respuesta = this.bda.insertarParque(id, nombre, extension, comunidad);

        if (respuesta = true) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Registro actualizado satisfactoriamente");
            alert.setContentText("EL parque se ha añadido a nuestra base de datos");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.showAndWait();
        }

    }

    public void rellenarComboBox() {
        listaComunidades = bda.rellenarComboBox();
        ComboBoxComunidades.setItems(listaComunidades);
    }

    @FXML
    private void ActionComboBoxComunidades(ActionEvent event) {
    }

}
