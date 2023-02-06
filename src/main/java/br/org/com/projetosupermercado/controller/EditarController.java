/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.com.projetosupermercado.controller;

import br.org.com.projetosupermercado.db.DAO;
import br.org.com.projetosupermercado.db.ProdutoDAO;
import br.org.com.projetosupermercado.model.Produto;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author CLIENTE
 */
public class EditarController  implements Initializable {


    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonEditar;

    @FXML
    private Button buttonExcluir;

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
    
    private Produto produto; 

    public void setProduto(Produto produto) {
        this.produto = produto;
         campoCodigo.setText(String.valueOf(produto.getId()));
        campoDescricao.setText(produto.getDescricao());
        campoMarca.setText(produto.getMarca());
        campoNome.setText(produto.getNome());
        campoQuantidade.setText(String.valueOf(produto.getQuantidade()));
        campoPreco.setText(String.valueOf(produto.getPreco()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    @FXML
   public void cancelar(MouseEvent event) {
      
      
    }

    @FXML
   public void excluir(MouseEvent event) {
        ProdutoDAO dao =  new ProdutoDAO();
        int id  = Integer.parseInt(campoCodigo.getText());
        dao.remove(id);
        buttonExcluir.getScene().getWindow().hide();
    }

    @FXML
   public void salvar(MouseEvent event) {
        campoCodigo.getText();
        campoDescricao.getText();
        campoMarca.getText();
        campoNome.getText();
        campoQuantidade.getText();
        campoPreco.getText();
        ProdutoDAO dao = new ProdutoDAO();
        produto.setDescricao(campoDescricao.getText());
        produto.setMarca(campoMarca.getText());
        produto.setNome(campoNome.getText());
        produto.setPreco(Double.parseDouble(campoPreco.getText()));
        produto.setQuantidade(Integer.parseInt(campoQuantidade.getText()));
        dao.update(produto);
        buttonEditar.getScene().getWindow().hide();
    }
}
