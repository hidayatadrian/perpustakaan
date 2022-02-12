package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class logout {
    protected void changePage(ActionEvent Event, String page){

        Node node = (Node) Event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;

        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource( page + ".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        
        stage.setScene(new Scene(root, 1366, 768));
        stage.show();
    }
}
