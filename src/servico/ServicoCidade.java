package servico;

import dominio.Cidade;
import repositorio.RepositorioCidade;

public class ServicoCidade extends Servico {

    public static Cidade localizarCidade(int id) {
        RepositorioCidade repo = new RepositorioCidade();
        Cidade cidade = repo.localizar(id);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada para o id: " + id);
        return cidade;
    }

    public static Cidade localizarCidade(String nome) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome não pode ser vazio.");
        RepositorioCidade repo = new RepositorioCidade();
        Cidade cidade = repo.localizarPorNome(nome);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada: " + nome);
        return cidade;
    }

    public static void criarCidade(String nome) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome é obrigatório.");

        nome = nome.trim();
        if (nome.length() > 50)
            throw new RuntimeException("Nome da cidade deve ter no máximo 50 caracteres.");

        RepositorioCidade repo = new RepositorioCidade();
        if (repo.localizarPorNome(nome) != null)
            throw new RuntimeException("Já existe uma cidade com o nome: " + nome.toUpperCase());
        repo.criar(new Cidade(nome));
    }
}