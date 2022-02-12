package controller.perpustakaan;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Helpers;

import javax.xml.transform.Result;

import static controller.perpustakaan.DatabaseConnection.getConnect;

public class HelloController extends Helpers {
   
    
    @FXML
    private TextField UsernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label labelMessageLabel;
    
    public  void TestLogin(ActionEvent event){
        labelMessageLabel.setText("Anda mencoba untuk login!");
    }

    @FXML
    private void onHelloButtonClick(ActionEvent event){
        
        if (UsernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            
//            labelMessageLabel.setText("USERNAME ATAU PASSWORD SALAH");
            try{

                PreparedStatement preparedStatement = getConnect().prepareStatement("SELECT * FROM login_user WHERE nama = ?");
                preparedStatement.setString(1,UsernameTextField.getText());
                ResultSet resultSet = preparedStatement.executeQuery();

                    if(resultSet.next()){
                        String retrievedPassword = resultSet.getString("password");
                        String retrievedStatus = resultSet.getString("status");
                        if(retrievedPassword.equals(passwordPasswordField.getText())){
                            if(retrievedStatus.equals("Admin")){
                                System.out.println("sukses");
                                changePage(event,"dashboardadmin");
                            }else{
                                labelMessageLabel.setText("Akun anda belum bukan Admin!");
                            }
                        }else{

                        }
                    } else {
                        labelMessageLabel.setText("Akun anda belum terdaftar");
                    }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            labelMessageLabel.setText("Isi Username dan Password sebelum login");
        }
    }

   

    
    @FXML
    private Button closeButton;
    @FXML
    public void CancelButtonClick(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
   
    


}