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
import classes.Sorvete;
import java.sql.Date;
import java.util.ArrayList;


public class DAOSorvete {
    //Connection, mantem uma conexão aberta de banco de dados
    private final Connection con;
    //PreparedStatement, gerencia e executa instruções SQL (parametro)
    private PreparedStatement stmt;
    //Armazena o comando SQL
    private String comandoSql;
    //ResultSet, recebe os dados obtidos em uma pesquisa ao banco
    private ResultSet res;
    private ArrayList<Sorvete> lista1 = new ArrayList<Sorvete>();
    
    public DAOSorvete(){
        //Estabelecendo a conexao com o banco de dados por meio da classe ConnectionFactory
        con= ConnectionFactory.getConnection();
    }
    
    
    public void salvarSorvete(Sorvete s) throws SQLException{
        try {
            //Comando SQL
            comandoSql = "insert into sorvete(saborSorvete,valorSorvete) values(?,?)";
            stmt=con.prepareStatement(comandoSql);
            stmt.setString(1, s.getSaborSorvete());
            stmt.setFloat(2, s.getValorSorvete());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Sorvete cadastrado");
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Sorvete"+ ex);
        }
        finally
        { 
            con.close();
            
        }
    }
    
    public ArrayList<Sorvete> retornarSorvete(){
           comandoSql="select * from sorvete";
        try {
            stmt= con.prepareStatement(comandoSql);
            res=stmt.executeQuery();
             while(res.next()){
                 Sorvete s = new Sorvete();
                 s.setIdSorvete(res.getInt("idSorvete"));
                 s.setSaborSorvete(res.getString("saborSorvete"));
                 s.setValorSorvete(res.getFloat("valorSorvete"));
                 lista1.add(s); 
             }
        } catch (SQLException ex) {
            
        }
        return lista1;
    }
}
