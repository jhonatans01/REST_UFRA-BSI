/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import restful.factory.Conexao;
import restful.model.Aluno;

/**
 *
 * @author jhonatan
 */
public class AlunoDAO extends Conexao {

    private static AlunoDAO instancia = null;

    public static AlunoDAO getInstance() {
        if (instancia == null) {
            instancia = new AlunoDAO();
        }
        return instancia;
    }

    public boolean inserir(Aluno a) {
        try {
            Connection con = criarConexao();
            String sql = "INSERT INTO aluno VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getMatricula());
            ps.setString(2, a.getNome());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir");
        }
        return false;
    }

    public boolean editar(Aluno a) {
        try {
            Connection con = criarConexao();
            String sql = "UPDATE aluno SET nome = ? WHERE matricula = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getMatricula());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao editar");
        }
        return false;
    }
    
    public boolean deletar(Integer matricula) {
        try {
            Connection con = criarConexao();
            String sql = "DELETE FROM aluno WHERE matricula = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricula);

            int i = ps.executeUpdate();
            if (i > 0) {
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar");
        }
        return false;
    }

    public ArrayList<Aluno> listarTodos() {
        Connection con = null;
        Statement st = null;
        ResultSet res = null;
        ArrayList<Aluno> lista = new ArrayList<Aluno>();
        String sql = "SELECT * FROM aluno ORDER BY matricula ASC";

        con = criarConexao();

        try {
            st = con.createStatement();
            res = st.executeQuery(sql);
            while (res.next()) {
                Aluno a = new Aluno();
                a.setMatricula(res.getInt("matricula"));
                a.setNome(res.getString("nome"));
                lista.add(a);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar");
        }
        return lista;
    }
}
