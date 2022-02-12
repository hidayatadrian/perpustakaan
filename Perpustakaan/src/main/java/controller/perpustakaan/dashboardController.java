package controller.perpustakaan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;
import utils.Helpers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.perpustakaan.DatabaseConnection.getConnect;

public class dashboardController extends Helpers implements Initializable {

    public TableColumn<ModelBook, Integer> col_NO;
    public TableColumn<ModelTable, String> col_Nama;
    public TableColumn<ModelTable, String> col_Kelas;
    public TableColumn<ModelTable, String> col_Jurusan;
    public TableColumn<ModelTable, String> col_Status;
    public TableView<ModelTable> table;

    public TableColumn<ModelBook, Integer> getCol_NO;
    public TableColumn<ModelBook, String> col_nama_buku;
    public TableColumn<ModelBook, String> col_jenis_buku;
    public TableColumn<ModelBook, String> col_status_buku;
    public TableView<ModelBook> tablebuku;

    ObservableList<ModelBook> tablebukus = FXCollections.observableArrayList();

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @FXML
    private void logout(ActionEvent event) {
        changePage(event, "hello-view");
    }

    public  void dataanggota(ActionEvent event){
        changePage(event, "dataanggota");
    }

    public  void dashboard(ActionEvent event){
        changePage(event, "dashboardadmin");
    }
    public  void riwayat(ActionEvent event){
        changePage(event, "riwayatpinjamadmin");
    }
    public  void databuku(ActionEvent event){
        changePage(event, "databukuadmin");
    }
    public  void pinjam(ActionEvent event){
        changePage(event, "pinjambuku");
    }

//Tambah data anggota
    public void tambahanggota(ActionEvent event) throws SQLException {

        PreparedStatement preparedStatement = getConnect().prepareStatement("INSERT INTO tb_anggota (nama,kelas,jurusan,status_anggota) VALUES ( ?,?,?,? )") ;
        preparedStatement.setString(1,namaanggotaField.getText());
        preparedStatement.setString(2,kelasField.getText());
        preparedStatement.setString(3,jurusanField.getText());
        preparedStatement.setString(4,"Siswa");
        if (preparedStatement.executeUpdate() > 0){
            System.out.println("Berhasil tambah data anggota");
            try{
                if (table != null) {
                    oblist.clear();
                    Connection con = DatabaseConnection.getConnect();
                    ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_anggota");
                    int counter = 1;
                    while (rs.next() ){
                        oblist.add(new ModelTable(counter, rs.getString("nama"), rs.getString("kelas"), rs.getString("jurusan"), rs.getString("status_anggota")));
                        counter++;
                    }
                    table.setItems(oblist);
                    col_NO.setCellValueFactory(new PropertyValueFactory<ModelBook, Integer>("no"));
                    col_Nama.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("nama"));
                    col_Kelas.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("kelas"));
                    col_Jurusan.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("jurusan"));
                    col_Status.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("status"));

                    countJumlahAnggota();
                }
            }catch(SQLException | NullPointerException e){
                System.out.println(e.getMessage());
            }

        }else{
            System.out.println("Tidak berhasil");
        }

    }

//Tampil data anggota

    @FXML
    private  TextField namaanggotaField;

    @FXML
    private  TextField kelasField;

    @FXML
    private  TextField jurusanField;

    @FXML
    public void initialize(){
        comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("Option A", "Option B", "Option C");
        comboBox.getSelectionModel().select("Option B");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tampilDataBuku();
        try{
            if (table != null) {
            Connection con = DatabaseConnection.getConnect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM tb_anggota");
            int counter = 1;
            while (rs.next() ){
                oblist.add(new ModelTable(counter, rs.getString("nama"), rs.getString("kelas"), rs.getString("jurusan"), rs.getString("status_anggota")));
                counter++;
            }
            table.setItems(oblist);
                col_NO.setCellValueFactory(new PropertyValueFactory<ModelBook, Integer>("no"));
                col_Nama.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("nama"));
                col_Kelas.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("kelas"));
                col_Jurusan.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("jurusan"));
                col_Status.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("status"));


                countJumlahAnggota();

            }
        }catch(SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
//getrow jumlah anggota
@FXML
private Label jumlah_anggota;
    public void countJumlahAnggota(){
        try{
            Integer size = 0;
            Connection con = DatabaseConnection.getConnect();
            ResultSet rs = con.createStatement().executeQuery("SELECT COUNT(id_anggota) AS rowcount FROM  tb_anggota");
            rs.next();
            size = rs.getInt("rowcount");
            rs.close();
            jumlah_anggota.setText(size.toString());

        }
        catch(Exception e){
            System.out.println("Error");
        }


    }

//    Tambah data buku

    @FXML
    private  TextField namabukuField;

    @FXML
    private  TextField jenisbukuField;

    public  void  tambahDataBuku(ActionEvent event) throws SQLException{
        PreparedStatement preparedStatement = getConnect().prepareStatement("INSERT INTO tb_buku (nama_buku,jenis_buku,status_buku) VALUES ( ?,?,? )") ;
        preparedStatement.setString(1,namabukuField.getText());
        preparedStatement.setString(2,jenisbukuField.getText());
        preparedStatement.setString(3,"Tersedia");
        if (preparedStatement.executeUpdate() > 0){
            System.out.println("Berhasil tambah data buku");
            tablebukus.clear();
            tampilDataBuku();
        }else{
            System.out.println("error tambah data buku");
        }

    }


//tampil data buku

    public void tampilDataBuku() {

        try{
            if (tablebuku != null) {
                Connection con = DatabaseConnection.getConnect();
                ResultSet r = con.createStatement().executeQuery("SELECT * FROM tb_buku");
                int counters = 1;
                while (r.next() ){
                    tablebukus.add(new ModelBook(counters, r.getString("nama_buku"), r.getString("jenis_buku"), r.getString("status_buku") ) );
                    counters++;
                }
                tablebuku.setItems(tablebukus);
                getCol_NO.setCellValueFactory(new PropertyValueFactory<ModelBook, Integer>("no"));
                col_nama_buku.setCellValueFactory(new PropertyValueFactory<ModelBook, String>("nama_buku"));
                col_jenis_buku.setCellValueFactory(new PropertyValueFactory<ModelBook, String>("jenis_buku"));
                col_status_buku.setCellValueFactory(new PropertyValueFactory<ModelBook, String>("status_buku"));

                countJumlahBuku();
                System.out.println("berhasil tampil data buku");

            }
        }catch(SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }



//get row count jumlah data buku
@FXML
private Label jumlah_buku;
    public void countJumlahBuku(){
        try{
            Integer size = 0;
            Connection con = DatabaseConnection.getConnect();
            ResultSet rf = con.createStatement().executeQuery("SELECT COUNT(id_buku) AS rowcount FROM  tb_buku");
            rf.next();
            size = rf.getInt("rowcount");
            rf.close();
            jumlah_buku.setText(size.toString());

        }
        catch(Exception e){
            System.out.println("Error ambil data buku");
        }


    }
//tambah pinjam buku
    @FXML
    private TextField NamaPinjamField;

    @FXML
    private TextField KelasPinjamField;

    @FXML
    private TextField JurusanPinjamField;

    @FXML
    private Text namaBukuPinjamField;
    public  void  tambahPinjamBuku(){

    }


}


