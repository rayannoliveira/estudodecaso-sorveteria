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
import classes.Funcionario;
import java.util.ArrayList;
 
public class DAOFuncionario {
    //Connection, mantem uma conexão aberta de banco de dados
    private final Connection con;
    //PreparedStatement, gerencia e executa instruções SQL (parametro)
    private PreparedStatement stmt;
     //Armazena o comando SQL
    private String comandoSql;
    //ResultSet, recebe os dados obtidos em uma pesquisa ao banco
    private ResultSet res;
    private ArrayList<Funcionario> lista1 = new ArrayList<Funcionario>();
    public DAOFuncionario(){
        //Estabelecendo a conexao com o banco de dados por meio da classe ConnectionFactory
        con= ConnectionFactory.getConnection();
    }
    
    public void salvarFuncionario(Funcionario f) throws SQLException{
        try {
            //Comando SQL
            comandoSql = "insert into funcionario(nome,endereco,dataNascimento) values(?,?,?)";
            stmt=con.prepareStatement(comandoSql);
            stmt.setString(1, f.getNome());
            stmt.setString(2,f.getEndereco());
            stmt.setString(3, f.getData());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionario cadastrado");
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Funcionario"+ ex);
        }
        finally
        { 
            con.close();
            
        }
    }
    
    public ArrayList<Funcionario> retornarFuncionario(){
           comandoSql="select * from  funcionario";
        try {
            stmt= con.prepareStatement(comandoSql);
            res=stmt.executeQuery();
             while(res.next()){
                 Funcionario f = new Funcionario();
                 f.setIdFuncionario(res.getInt("idFuncionario"));
                 f.setNome(res.getString("nome"));
                 f.setEndereco(res.getString("endereco"));
                 f.setData(res.getString("dataNascimento"));
                 lista1.add(f); 
             }
        } catch (SQLException ex) {
            
        }
        return lista1;
    }
}
