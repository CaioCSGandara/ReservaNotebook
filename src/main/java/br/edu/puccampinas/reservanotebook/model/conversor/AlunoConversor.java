package br.edu.puccampinas.reservanotebook.model.conversor;

import br.edu.puccampinas.reservanotebook.model.entities.Aluno;
import br.edu.puccampinas.reservanotebook.utils.DateUtils;
import org.bson.Document;
import java.util.Date;

public class AlunoConversor {

    public static Document alunoToDocument(Aluno aluno) {

        return new Document()
                .append("nome", aluno.getNome())
                .append("ra", aluno.getRa())
                .append("email", aluno.getEmail())
                .append("telefone", aluno.getTelefone())
                .append("curso", aluno.getCurso())
                .append("qtdReservas", aluno.getQtdReservas())
                .append("ultimoLogin", aluno.getUltimoLogin())
                .append("atualizadoEm", aluno.getAtualizadoEm())
        ;
    }

    public static Aluno documentToAluno(Document document) {
        try {
            return new Aluno(
                    (String)document.get("nome"),
                    (String)document.get("ra"),
                    (String)document.get("email"),
                    (String)document.get("telefone"),
                    (String)document.get("curso"),
                    (Integer)document.get("qtdReservas"),
                    DateUtils.dateToLocalDateTime(((Date)document.get("ultimoLogin"))),
                    DateUtils.dateToLocalDateTime(((Date)document.get("atualizadoEm"))));
        }
        catch (NullPointerException e) {
            throw e;
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
