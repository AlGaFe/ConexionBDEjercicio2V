package conexionbdejercicios;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
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

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/parques", "root", "root");
            if (conn != null) {
                this.LabeDesconectado.setText("");

            }
            String sql ="INSERT INTO"+ "parque(nombre,extension,id,comunidad)"+"VALUES (?,?,?,?)"; 
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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
        ps.setString(1, this.TextFliedNombre.getText());
        ps.setString(2, this.TextFliedExtension.getText());
        ps.setString(3, this.TextFliedID.getText());
        ps.setString(4, this.TextFieldComunidad.getText());
        numFilas = ps.executeUpdate();   
        System.out.println("filas insertadas: "+ numFilas);
    }

}
