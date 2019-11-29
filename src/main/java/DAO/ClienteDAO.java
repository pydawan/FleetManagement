/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import MODEL.Carro;
import MODEL.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {
        this.connection = new ConnectionFactory().getConnection();

    }

    public void adiciona(Cliente cliente) {
        String sql = "insert into cliente" + "(cpf,cnpj,nome,razao_social,endereco,fone)"
                + "values(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getCnpj());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getRazao_social());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getFone());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }

    }

    public void altera(Cliente cliente) {
        String sql = "update cliente set cpf=?, cnpj=?, nome=?, razao_social=?, endereco=?, fone=?, where id_cliente=?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getCnpj());
            stmt.setString(3, cliente.getNome());
            stmt.setString(4, cliente.getRazao_social());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getFone());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void remove(Cliente cliente) {
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from cliente where id_cliente=?");

            stmt.setInt(1, cliente.getId_cliente());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public List<Cliente> getLista() {
        try {

            PreparedStatement stmt = this.connection.prepareStatement("select * from cliente");
            ResultSet rs = stmt.executeQuery();
            List<Cliente> clientes = new ArrayList<Cliente>();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setCpf(rs.getString("Cpf"));
                cliente.setCnpj(rs.getString("Cnpj"));
                cliente.setRazao_social(rs.getString("Razão Social"));
                cliente.setEndereco(rs.getString("Endereço"));
                cliente.setFone(rs.getString("Fone"));

                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            return clientes;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

  public List<Cliente> buscaProdutoPorCpf(String cpf) {

        try {

            List<Cliente> lista = new ArrayList<>();
            String sql = "select * from cliente where cpf like ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setCpf(rs.getString("Cpf"));
                cliente.setCnpj(rs.getString("Cnpj"));
                cliente.setRazao_social(rs.getString("Razão Social"));
                cliente.setEndereco(rs.getString("Endereço"));
                cliente.setFone(rs.getString("Fone"));

                lista.add(cliente);

            }

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
  
  public List<Cliente> buscaProdutoPorCnpj(String cnpj) {

        try {

            List<Cliente> lista = new ArrayList<>();
            String sql = "select * from cliente where cnpj like ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, cnpj);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setCpf(rs.getString("Cpf"));
                cliente.setCnpj(rs.getString("Cnpj"));
                cliente.setRazao_social(rs.getString("Razão Social"));
                cliente.setEndereco(rs.getString("Endereço"));
                cliente.setFone(rs.getString("Fone"));

                lista.add(cliente);

            }

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
}
