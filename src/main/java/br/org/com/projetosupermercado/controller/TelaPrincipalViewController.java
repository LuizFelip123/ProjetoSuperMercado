/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.com.projetosupermercado.controller;

import br.org.com.projetosupermercado.db.ProdutoDAO;
import br.org.com.projetosupermercado.model.Produto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;
/**
 * FXML Controller class
 *
 * @author Cliente
 */
public class TelaPrincipalViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Produto> tableProduto;
       @FXML
    private TableColumn<Produto, Integer> columnCodigo;

    @FXML
    private TableColumn<Produto, String> columnDescricao;

    @FXML
    private TableColumn<Produto, String> columnMarca;

    @FXML
    private TableColumn<Produto, String> columnNome;

    @FXML
    private TableColumn<Produto, Double> columnPreco;

    @FXML
    private TableColumn<Produto, Integer> columnQuantidade;

    @FXML
    private AnchorPane paindelCadastro;

    @FXML
    private AnchorPane painelConsulta;

     @FXML
    private Button buttonCadastrar;

    @FXML
    private TextField campoCodigo;

    @FXML
    private TextField campoDescricao;

    @FXML
    private TextField campoMarca;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPreco;

    @FXML
    private TextField campoQuantidade;
    
    @FXML
    private AnchorPane painelCadastro;
    @FXML
    private Button menuAdicionar;
     
    @FXML
    private Button menuEstoque;

    @FXML
    private Button menuVender;
    
    @FXML
    private Button menuSair;
    private ObservableList<Produto> observableProduto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarTabela();
         menuSair.setOnMouseClicked(new EventHandler<MouseEvent>(){
             @Override
             public void handle(MouseEvent t) {
                    
             }   
        });
         menuAdicionar.setOnMouseClicked(new EventHandler<MouseEvent>(){
             @Override
             public void handle(MouseEvent t) {
               painelConsulta.setVisible(false);
               painelCadastro.setVisible(true);
           
             }
         
         });
         menuEstoque.setOnMouseClicked(new EventHandler<MouseEvent>(){
         @Override 
         public void handle(MouseEvent t){
            carregarTabela();
             painelConsulta.setVisible(true);
             painelCadastro.setVisible(false);
         }
         });
    }  
    
     @FXML
    void cadastrarProduto(MouseEvent event) {
       Produto produto = new Produto();
       produto.setId(Integer.parseInt(campoCodigo.getText()));
       produto.setDescricao(campoDescricao.getText());
       produto.setMarca(campoMarca.getText());
       produto.setNome(campoNome.getText());
       produto.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
       produto.setPreco(Double.parseDouble( campoPreco.getText()));
         ProdutoDAO dao = new ProdutoDAO();
         dao.add(produto);
         Alert alert  = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("O Produto Foi cadastrado!!");
         alert.show();
    }
     private void carregarTabela(){
         ProdutoDAO dao = new ProdutoDAO();
         List<Produto> listaProduto =  dao.getAll();
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
   
        
        observableProduto = FXCollections.observableArrayList(listaProduto);
        tableProduto.setItems(observableProduto);

    }
}
