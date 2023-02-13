/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.com.projetosupermercado.controller;

import br.org.com.projetosupermercado.db.ProdutoDAO;
import br.org.com.projetosupermercado.model.Produto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author CLIENTE
 */
public class VendaViewController implements Initializable {

    @FXML
    private Button botaoBuscar;

    @FXML
    private Button botaoCalcularTroco;

    @FXML
    private TextField campoCodigo;

    @FXML
    private TextField campoPago;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private TextField campoTroco;

    @FXML
    private TextField campoValor;

    @FXML
    private Text textValor;
    @FXML
    private TableColumn<Produto, Integer> columnCodigo;

    @FXML
    private TableColumn<Produto, String> columnMarca;

    @FXML
    private TableColumn<Produto, String> columnNome;

    @FXML
    private TableColumn<Produto, Double> columnPreco;

    @FXML
    private TableView<Produto> tableProduto;

    private ObservableList<Produto> observableProduto;
    private List<Produto> listaProduto = new ArrayList<>();
    private Produto produto;
    private double x =0, y =0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
         tableProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->selecionarItem(newValue));
    }

    private void carregarTabela() {
        //Mudar via de dados

        //------------------------------------------
        columnCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        observableProduto = FXCollections.observableArrayList(listaProduto);
        tableProduto.setItems(observableProduto);

    }

  

    private void calcularPreco() {
        double valor = 0;
        for (Produto produto : listaProduto) {
            valor += produto.getPreco();
        }
        textValor.setText("R$ " + String.valueOf(valor));
        campoValor.setText("R$ " + String.valueOf(valor));
    }

    @FXML
    private void calcularTroco(MouseEvent event) {
        Double valor = Double.parseDouble(campoValor.getText().replace("R$", ""));
        Double pago = Double.parseDouble(campoPago.getText());
        Double total = pago - valor;
         
        campoTroco.setText("R$ " + String.valueOf(total));

    }

    @FXML
    void buscarProduto(MouseEvent event) {
        int cod = Integer.parseInt(campoCodigo.getText());
        ProdutoDAO dao = new ProdutoDAO();
        if (dao.existsProduto(cod)) {
            if (campoQuantidade.getText().isEmpty()) {
                //verificar o campo quantidade
                //realizar pesquisa no banco (Produto)

                dao.findProduto(cod, 1, listaProduto);
            } else {
                int quantidade = Integer.parseInt(campoQuantidade.getText());
                dao.findProduto(cod, quantidade, listaProduto);
            }
            carregarTabela();
            calcularPreco();
        }
    }

    @FXML
    void voltar(MouseEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/br/org/com/projetosupermercado/view/TelaPrincipalView.fxml"));
            botaoBuscar.getScene().getWindow().hide();
                
            stage.initStyle(StageStyle.TRANSPARENT);
            styleRoot(root, stage);
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void selecionarItem(Produto produto){
      
      this.produto = produto;  
    } 
     
    @FXML
   public void venderProdutos(MouseEvent event) {
       
    }
   
     @FXML
   public void removerItem(MouseEvent event) {
      if(produto != null){
          listaProduto.remove(produto);
          
      }
      
      carregarTabela();
       calcularPreco();
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
