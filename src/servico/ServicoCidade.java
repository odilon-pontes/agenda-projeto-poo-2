package servico;

import java.util.List;

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
        RepositorioCidade repo = new RepositorioCidade();
        if (repo.localizarPorNome(nome) != null)
            throw new RuntimeException("Já existe uma cidade com o nome: " + nome.toUpperCase());
        repo.criar(new Cidade(nome));
    }

    public static void atualizarCidade(int id, String nome) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome é obrigatório.");
        RepositorioCidade repo = new RepositorioCidade();
        Cidade cidade = repo.localizar(id);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada para o id: " + id);
        Cidade existente = repo.localizarPorNome(nome);
        if (existente != null && existente.getId() != id)
            throw new RuntimeException("Já existe uma cidade com o nome: " + nome.toUpperCase());
        cidade.setNome(nome);
        repo.atualizar(cidade);
    }

    public static void apagarCidade(int id) {
        RepositorioCidade repo = new RepositorioCidade();
        if (!repo.deletar(id))
            throw new RuntimeException("Cidade não encontrada para o id: " + id);
    }

    public static List<Cidade> listarCidades() {
        return new RepositorioCidade().listarTodas();
    }
}