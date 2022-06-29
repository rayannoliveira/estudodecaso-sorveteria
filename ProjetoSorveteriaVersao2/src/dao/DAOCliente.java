/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import utilitarios.ConnectionFactory;
import classes.Cliente;
import java.sql.Date;
import java.util.ArrayList;
public class DAOCliente {
    //Connection, mantem uma conexão aberta de banco de dados 
    private final Connection con;
    //PreparedStatement, gerencia e executa instruções SQL (parametro)
    private PreparedStatement stmt;
    //Armazena o comando SQL
    private String comandoSql;
    //ResultSet, recebe os dados obtidos em uma pesquisa ao banco
    private ResultSet res;
    private ArrayList<Cliente> lista1 = new ArrayList<Cliente>();
    
    //Construtor da classe DAOClinte
    public DAOCliente(){
        //Estabelecendo a conexao com o banco de dados por meio da classe ConnectionFactory
        con= ConnectionFactory.getConnection();
    }
    
    public void salvarCliente(Cliente c) throws SQLException{
        try {
            //comando SQL
            comandoSql = "insert into cliente(nome,endereco,data) values(?,?,?)";
            stmt=con.prepareStatement(comandoSql);
        
            stmt.setString(1,c.getNome());
            stmt.setString(2, c.getEndereco());
            stmt.setString(3, c.getData());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado");
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Cliente"+ ex);
        }
        finally
        { 
            con.close();
            
        }
    }
    
    //Retornar Clientes 
    public ArrayList<Cliente> retornarCliente(){
           comandoSql="select * from  cliente";
        try {
            stmt= con.prepareStatement(comandoSql);
            res=stmt.executeQuery();
             while(res.next()){
                 Cliente c = new Cliente();
                 c.setIdCliente(res.getInt("idCliente"));
                 c.setNome(res.getString("nome"));
                 c.setEndereco(res.getString("endereco"));
                 c.setData(res.getString("data"));
                 lista1.add(c); 
             }
        } catch (SQLException ex) {
            
        }
        return lista1;
    }
    
}
