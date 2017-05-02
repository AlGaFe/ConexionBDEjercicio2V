package Datos;

import Modelo.Comunidad;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BDA {

    private ResultSet rs;
    private Connection conn;
    private PreparedStatement ps;
    ObservableList listaComunidades = FXCollections.observableArrayList();

    public BDA() {
    }

    public boolean conexion() {

        boolean resuesta;
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/parques", "root", "root");
            if (conn != null) {

            }
            resuesta = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            resuesta = false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            resuesta = false;
        }
        return resuesta;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public Connection getConn() {
        return conn;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public boolean insertarParque(int id, String nombre, String extension, String comunidad) {
        boolean respuesta;
        try {
            try {
                String sql = "INSERT INTO parque VALUES (?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, nombre);
                if (extension == null) {
                    ps.setNull(3, java.sql.Types.DOUBLE);
                } else {
                    ps.setString(3, extension);
                }
                ps.setString(4, comunidad);
                ps.executeUpdate();

                respuesta = true;
            } catch (SQLException ex) {
                respuesta = false;
            }
        } catch (Exception e) {
            respuesta = false;
        }
        return respuesta;
    }

    public ObservableList rellenarComboBox() {
        PreparedStatement ps;
        Comunidad com = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM comunidad");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                com = new Comunidad(rs.getInt("id"), rs.getString("nombre"));
                listaComunidades.add(com);
            }

        } catch (SQLException ex) {

        }
        return listaComunidades;
    }
}
