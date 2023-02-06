/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.com.projetosupermercado.db;

import br.org.com.projetosupermercado.model.Produto;
import java.util.List;

/**
 *
 * @author Cliente
 */
public interface DAO {
   public void add(Produto produto);
   public void remove(int id);
   public List<Produto> getAll();
   public void update(Produto produto);
   public Produto findById(int id);
}
