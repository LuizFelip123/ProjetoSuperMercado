/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.com.projetosupermercado.db;

import br.org.com.projetosupermercado.model.Produto;
import com.google.protobuf.Internal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Cliente
 */
public class ProdutoDAO implements DAO {

    private PreparedStatement statement;

    @Override
    public void add(Produto produto) {

        try {
            String sql = "INSERT INTO produto (`cod`, `nome`, `marca`, `quantidade`,  `preco`, `descricao`) VALUES (?,?,?,?,?, ?);";
            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
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
    public void remove(int id) {
        try {
            String sql = "DELETE FROM produto WHERE cod= ?;";
            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        ResultSet resposta;
        try {
            String sql = "SELECT * FROM produto Order by cod asc;";

            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
            resposta = statement.executeQuery();

            while (resposta.next()) {
                Produto produto = new Produto();
                produto.setId(resposta.getInt("cod"));
                produto.setMarca(resposta.getString("marca"));
                produto.setQuantidade(resposta.getInt("quantidade"));
                produto.setPreco(resposta.getDouble("preco"));
                produto.setNome(resposta.getString("nome"));
                produto.setDescricao(resposta.getString("descricao"));
                produtos.add(produto);

            }
        } catch (SQLException exececaoSQL) {
            exececaoSQL.getMessage();
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, exececaoSQL);

        } finally {

            try {
                statement.close();

            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return produtos;
    }

    @Override
    public void update(Produto produto) {
        try {
            String sql = "UPDATE produto SET nome = ?, marca = ?, quantidade = ?,  preco =?, descricao =?  WHERE cod = ?;";
            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getMarca());
            statement.setInt(3, produto.getQuantidade());
            statement.setDouble(4, produto.getPreco());
            statement.setString(5, produto.getDescricao());
            statement.setInt(6, produto.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Produto findById(int id) {
        Produto produto = new Produto();
        try {
            String sql = "SELECT * FROM produto WHERE cod = ?;";
            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            produto.setId(result.getInt("cod"));
            produto.setMarca(result.getString("marca"));
            produto.setQuantidade(result.getInt("quantidade"));
            produto.setPreco(result.getDouble("preco"));
            produto.setNome(result.getString("nome"));
            produto.setDescricao(result.getString("descricao"));

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produto;
    }

    public boolean existsProduto(int codigo) {
        try {
            String sql = "SELECT * FROM produto where cod = ?";
            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
            statement.setInt(1, codigo);
            if (statement.execute()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void findProduto(int cod, int quantidade, List<Produto> listProduto) {
        Produto produto = new Produto();
        try {
            String sql = "SELECT * FROM produto WHERE cod = ? ;";
            statement = ConnectionDataBase.getConnection().prepareStatement(sql);
            statement.setInt(1, cod);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                produto.setId(result.getInt("cod"));
                produto.setMarca(result.getString("marca"));
                produto.setQuantidade(result.getInt("quantidade"));
                produto.setPreco(result.getDouble("preco"));
                produto.setNome(result.getString("nome"));
                produto.setDescricao(result.getString("descricao"));

            }
            if (produto.getQuantidade()< quantidade) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("A quantidade solicitada");
                alert.setContentText("A quantidade do Produto " + produto.getNome() + " em estoque é menor do que foi solicitado!. "+ quantidade);
                alert.show();
            } else {

                for (int i = 0; i < produto.getQuantidade(); i++) {
                    Produto prod = new Produto();
                    prod.setId(produto.getId());
                    prod.setDescricao(produto.getDescricao());
                    prod.setMarca(produto.getMarca());
                    prod.setNome(produto.getNome());
                    prod.setPreco(produto.getPreco());
                    prod.setQuantidade(1);
                    listProduto.add(prod);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
