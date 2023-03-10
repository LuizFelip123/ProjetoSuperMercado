/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.com.projetosupermercado.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Cliente
 */
public class LoginViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField campoUser;
    @FXML
    private Button buttonEntrar;
    
    @FXML
    private PasswordField campoSenha;
    
    private double x  =0, y =0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonEntrar.setOnMouseClicked(((MouseEvent e) -> {
            //verificação
           if(campoUser.getText().equals("admin") && campoSenha.getText().equals("123")){
               
               try {
                   Stage stage  = new Stage();  
                   Parent root = FXMLLoader.load(getClass().getResource("/br/org/com/projetosupermercado/view/TelaPrincipalView.fxml"));
                   styleRoot(root, stage);
                   buttonEntrar.getScene().getWindow().hide();
                   stage.initStyle(StageStyle.TRANSPARENT);
                   Scene scene = new Scene(root);
                
                   stage.setScene(scene);
                  
                   stage.show();
               } catch (IOException ex) {
                   Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
               }
             
           }else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("UserName ou Senha está incorreta!");
               alert.show();
           }
              
    
        } )); 
       }
    
    @FXML
    void fecharWindow(MouseEvent event) {
        System.exit(0);
    }

    private void styleRoot(Parent root, Stage stage ) {
        
           root.setOnMousePressed((MouseEvent e)->{
                x = e.getSceneX();
                y = e.getSceneY();
                
            });
            
            root.setOnMouseDragged((MouseEvent e)->{
                
                stage.setX(e.getScreenX()-x);
                stage.setY(e.getScreenY()-y);
                stage.setOpacity(0.4);
            });
            
            root.setOnMouseReleased((MouseEvent e )->{
                stage.setOpacity(1);
            });
    }
}
