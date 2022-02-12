package controller.perpustakaan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utils.Helpers;
import utils.logout;

public class dashboardControllerUser extends Helpers{
    @FXML
    private void logout(ActionEvent event) {
        changePage(event, "hello-view");
    }

    public  void dataanggota(ActionEvent event){
        changePage(event, "profildiri");
    }

    public  void dashboard(ActionEvent event){
        changePage(event, "dashboarduser");
    }
    public  void riwayat(ActionEvent event){
        changePage(event, "riwayatpinjamadmin");
    }
    public  void databuku(ActionEvent event){
        changePage(event, "databukuadmin");
    }
    
}


