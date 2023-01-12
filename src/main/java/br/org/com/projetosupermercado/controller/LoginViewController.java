/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.com.projetosupermercado.controller;

import br.org.com.projetosupermercado.db.ProdutoDAO;
import br.org.com.projetosupermercado.model.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonEntrar.setOnMouseClicked(((MouseEvent e) -> {
            //verificação
              Produto produto = new Produto();
              ProdutoDAO prod = new ProdutoDAO();
              prod.add(produto);
              
    
        } )); 
       }
}
