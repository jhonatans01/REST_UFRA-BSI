/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.resource;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import restful.dao.AlunoDAO;
import restful.model.Aluno;

/**
 *
 * @author jhonatan
 */
@Path("/aluno")
public class AlunoResource {

    @GET
    @Path("/listarTodos")
    @Produces("application/xml")
    public ArrayList<Aluno> obterTodos() {
        return AlunoDAO.getInstance().listarTodos();
    }

    @GET
    @Path("/inserir/{mat}/{nome}")
    public String inserir(@PathParam("mat") int mat, @PathParam("nome") String nome) {
        Aluno a = new Aluno();
        a.setMatricula(mat);
        a.setNome(nome);
        if (AlunoDAO.getInstance().inserir(a)) {
            return "Salvo com sucesso!";
        } else {
            return "Erro ao salvar";
        }
    }

    @GET
    @Path("/editar/{mat}/{nome}")
    public String editar(@PathParam("mat") int mat,
            @PathParam("nome") String nome) {
        Aluno a = new Aluno();
        a.setMatricula(mat);
        a.setNome(nome);
        if (AlunoDAO.getInstance().editar(a)) {
            return "Editado com sucesso";
        } else {
            return "Erro ao editar";
        }
    }

    @GET
    @Path("/deletar/{mat}")
    public String deletar(@PathParam("mat") int mat) {
        if (AlunoDAO.getInstance().deletar(mat)) {
            return "Excluído com sucesso";
        } else {
            return "Erro ao excluir";
        }

    }
}
