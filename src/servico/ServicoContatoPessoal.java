package servico;

import java.util.List;

import dominio.Cidade;
import dominio.ContatoPessoal;
import repositorio.RepositorioCidade;
import repositorio.RepositorioContatoPessoal;

public class ServicoContatoPessoal extends ServicoContato {

    public static ContatoPessoal localizarContatoPessoal(int id) {
        RepositorioContatoPessoal repo = new RepositorioContatoPessoal();
        ContatoPessoal contato = repo.localizarPessoalPorId(id);
        if (contato == null)
            throw new RuntimeException("Contato pessoal não encontrado para o id: " + id);
        return contato;
    }

    public static ContatoPessoal localizarContatoPessoal(String nome) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome não pode ser vazio.");
        RepositorioContatoPessoal repo = new RepositorioContatoPessoal();
        ContatoPessoal contato = repo.localizarPessoalPorNome(nome);
        if (contato == null)
            throw new RuntimeException("Contato pessoal não encontrado: " + nome);
        return contato;
    }

    public static void criarContatoPessoal(String nome, int grauProximidade, int idcidade) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome é obrigatório.");

        RepositorioContatoPessoal repo = new RepositorioContatoPessoal();

        if (repo.localizarPessoalPorNome(nome) != null)
            throw new RuntimeException("Já existe um contato com o nome: " + nome.toUpperCase());

        RepositorioCidade repoCidade = new RepositorioCidade();
        Cidade cidade = repoCidade.localizar(idcidade);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada para o id: " + idcidade);

        ContatoPessoal contato = new ContatoPessoal(nome, cidade, grauProximidade);
        if (!repo.criarPessoal(contato))
            throw new RuntimeException("Não foi possível criar o contato pessoal.");
    }

    public static void alterarContatoPessoal(int id, String nome, int grauProximidade, int idcidade) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome é obrigatório.");

        RepositorioContatoPessoal repo = new RepositorioContatoPessoal();
        ContatoPessoal existente = repo.localizarPessoalPorId(id);
        if (existente == null)
            throw new RuntimeException("Contato pessoal não encontrado para o id: " + id);

        ContatoPessoal outro = repo.localizarPessoalPorNome(nome);
        if (outro != null && outro.getId() != id)
            throw new RuntimeException("Já existe um contato com o nome: " + nome.toUpperCase());

        RepositorioCidade repoCidade = new RepositorioCidade();
        Cidade cidade = repoCidade.localizar(idcidade);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada para o id: " + idcidade);

        existente.setNome(nome);
        existente.setGrauProximidade(grauProximidade);
        existente.setCidade(cidade);

        if (!repo.atualizarPessoal(existente))
            throw new RuntimeException("Não foi possível atualizar o contato pessoal.");
    }

    public static List<ContatoPessoal> listarContatosPessoais() {
        return new RepositorioContatoPessoal().listarTodosPessoais();
    }
}
