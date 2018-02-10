/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.factory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jhonatan
 */
public class Conexao {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bsi";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public Connection criarConexao() {
        Connection con = null;

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (Exception e) {
            System.out.println("Erro ao criar BD");
        }
        return con;
    }

    public void fecharConexao(Connection con) {

        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao fechar BD");
        }
    }
}
