package br.edu.puccampinas.reservanotebook.model.conversor;

import br.edu.puccampinas.reservanotebook.model.entities.Aluno;
import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;
import br.edu.puccampinas.reservanotebook.utils.DateUtils;
import org.bson.Document;

import java.util.Date;

public class AlunoConversor {

    public static Document alunoToDocument(Aluno aluno) {

        if(aluno==null) throw new IllegalArgumentException("Parâmetro 'aluno' não pode ser nulo (alunoToDocument)");

        return new Document()
                .append("nome", aluno.getNome())
                .append("ra", aluno.getRa())
                .append("email", aluno.getEmail())
                .append("telefone", aluno.getTelefone())
                .append("curso", aluno.getCurso().getNomeFormatado())
                .append("ultimoLogin", aluno.getUltimoLogin())
                .append("atualizadoEm", aluno.getAtualizadoEm());
    }

    public static Aluno documentToAluno(Document document) {

        if(document==null) throw new IllegalArgumentException("Parâmetro 'document' não pode ser nulo (documentToAluno).");

        return new Aluno(
                (String)document.get("nome"),
                (String)document.get("ra"),
                (String)document.get("email"),
                (String)document.get("telefone"),
                Curso.valueOf((String)document.get("curso")),
                DateUtils.dateToLocalDateTime(((Date)document.get("ultimoLogin"))),
                DateUtils.dateToLocalDateTime(((Date)document.get("atualizadoEm"))));
    }
}
