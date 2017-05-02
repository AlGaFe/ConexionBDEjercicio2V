package conexionbdejercicios;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private int numFilas;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
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

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/parques", "root", "root");
            if (conn != null) {
                this.LabeDesconectado.setText("");
                rellenarComboBox();

            }

        } catch (SQLException ex) {
            this.LabelConectado.setText("");
            System.out.println(ex.getMessage());

        } catch (Exception e) {
            this.LabelConectado.setText("");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void ActionButtonGuardar(ActionEvent event) throws SQLException {
        try {
            try {
                String sql ="INSERT INTO parque VALUES (?,?,?,?)";
                ps = conn.prepareStatement(sql);
                 ps.setInt(1, Integer.valueOf(this.TextFliedID.getText()));
                ps.setString(2, this.TextFliedNombre.getText());
                if (this.TextFliedExtension.getText().isEmpty()) {
                    ps.setNull(3, java.sql.Types.DOUBLE);
                } else {
                    ps.setString(3, this.TextFliedExtension.getText());
                }
                ps.setString(4, String.valueOf(this.ComboBoxComunidades.getValue().getId()));
                ps.executeUpdate();

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Registro actualizado satisfactoriamente");
                alert.setContentText("EL parque se ha añadido a nuestra base de datos");
                alert.showAndWait();
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error al registrar");
                alert.setHeaderText(ex.getSQLState());
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error al insertar");
                alert.setHeaderText("Datos erroneos");
                alert.showAndWait();
            }

       
        }

    public void rellenarComboBox() {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("SELECT * FROM comunidad");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comunidad com = new Comunidad(rs.getInt("id"), rs.getString("nombre"));
                listaComunidades.add(com);
            }
            ComboBoxComunidades.setItems(listaComunidades);
        } catch (SQLException ex) {
           
}
    }
    @FXML
    private void ActionComboBoxComunidades(ActionEvent event) {
    }

    }
