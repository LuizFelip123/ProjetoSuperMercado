/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.com.projetosupermercado.db;

import br.org.com.projetosupermercado.model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Cliente
 */
public class ProdutoDAO implements DAO{
       private PreparedStatement statement;
    @Override
    public void add(Produto produto) {
       produto.setId(1);
       produto.setDescricao("Nao sei");
       produto.setMarca("descr");
       produto.setPreco(3.3);
       produto.setNome("vitarela");
       produto.setQuantidade(2);
           try {
               String sql = "INSERT INTO produto (`cod`, `nome`, `marca`, `quantidade`,  `preco`, `descricao`) VALUES (?,?,?,?,?);";
               ConnectionDataBase.getConnection().prepareStatement(sql);
               statement.setInt(1, produto.getId());
               statement.setString(2, produto.getNome());
               statement.setString(3, produto.getMarca());
               statement.setInt(4, produto.getQuantidade());
               statement.setDouble(5, produto.getPreco());
               statement.setString(6, produto.getDescricao());
               statement.execute();
           } catch (SQLException ex) {
               Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
           }

    }

    @Override
    public void remove(Produto produto) {
      try {
            String sql = "DELETE FROM produto WHERE cod= ?;";
            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
            statement.setInt(1, produto.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produto> getAll() {
    List<Produto> produtos = new ArrayList<>();
       Connection conexao =null;
        ResultSet resposta;
        PreparedStatement statement = null;
        try{
             String  sql ="SELECT * FROM produto Order by cod asc;";

         statement = ConnectionDataBase.getConnection().prepareStatement(sql);
         resposta = statement.executeQuery();


         while(resposta.next()){
                Produto produto = new Produto();
                produto.setId(resposta.getInt("cod"));
                produto.setMarca(resposta.getString("marca"));
                produto.setQuantidade(resposta.getInt("quant"));
                
                produto.setPreco(resposta.getDouble("valor"));
                
                produtos.add(produto);
                
            }
        }catch(SQLException exececaoSQL){
                      exececaoSQL.getMessage();
          Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null, exececaoSQL);

        }finally{
        
            try {
                statement.close();
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
       return produtos;
   }

    }

    @Override
    public void update(Produto produto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
