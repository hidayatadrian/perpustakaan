package controller.perpustakaan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static controller.perpustakaan.DatabaseConnection.getConnect;




public class dataanggotaController {
    static void TambahAnggota(ActionEvent event) throws SQLException {
        PreparedStatement preparedStatement = getConnect().prepareStatement("INSERT INTO 'tb_anggota'('nama','kelas','jurusan','status_anggota') VALUES = ( ?,?,?,? )") ;
        preparedStatement.setString(1,namaanggotaField.getText());
        preparedStatement.setString(2,kelasField.getText());
        preparedStatement.setString(3,jurusanField.getText());

        preparedStatement.executeUpdate();

    }

    @FXML
    private static TextField namaanggotaField;

    @FXML
    private static TextField kelasField;

    @FXML
    private static TextField jurusanField;
}
