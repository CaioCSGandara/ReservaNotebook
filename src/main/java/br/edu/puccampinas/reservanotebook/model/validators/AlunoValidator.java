package br.edu.puccampinas.reservanotebook.model.validators;
import br.edu.puccampinas.reservanotebook.model.entities.enums.Curso;
import br.edu.puccampinas.reservanotebook.model.exceptions.ValidacaoException;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;


public class AlunoValidator {

    private final static Dotenv dotenv = Dotenv.configure().directory("./").load();
    private final static String DOMINIO = dotenv.get("DOMINIO");

    public static void validarAluno(String nome, String ra, String email, String telefone, Curso curso) {
        ArrayList<String> erros = new ArrayList<>();
        if(!isNomeValido(nome)) erros.add("Nome não pode ser vazio, deve ter sobrenome e não possuir símbolos e nem números");
        if(!isRaValido(ra)) erros.add("RA deve conter 8 dígitos numéricos e não pode ser vazio");
        if(!isEmailValido(email)) erros.add("E-mail não pode ser vazio e deve ser um e-mail acadêmico válido");
        if(!isTelefoneValido(telefone)) erros.add("Telefone não pode ser vazio e deve estar no formato (XX)XXXXX-XXXX");
        if(!isCursoValido(curso)) erros.add("Curso não pode ser vazio e deve estar na lista de cursos do Campus");

        if(!erros.isEmpty()) throw new ValidacaoException("Erro no builder de Aluno: " + String.join(" | ", erros));

    }

    public static boolean isNomeValido(String nome) {
        return(!nome.trim().isEmpty() && nome.split(" ").length>=2 && nome.matches("^[A-Za-zÀ-ÿ'-]+( [A-Za-zÀ-ÿ'-]+)*$"));

    }

    public static boolean isRaValido(String ra) {
        return(!ra.trim().isEmpty() && ra.matches("^\\d{8}$"));
    }

    public static boolean isEmailValido(String email) {
        return (!email.trim().isEmpty() && email.matches("^[\\w.-]+"+DOMINIO+"$"));
    }

    public static boolean isTelefoneValido(String telefone) {
        return(!telefone.trim().isEmpty() && telefone.matches("^\\(\\d{2}\\)\\d{5}-\\d{4}$"));
    }

    public static boolean isCursoValido(Curso curso) {
        return curso!=null;
    }

}
