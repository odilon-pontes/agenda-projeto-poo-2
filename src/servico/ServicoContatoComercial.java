package servico;

import java.util.List;

import dominio.Cidade;
import dominio.ContatoComercial;
import repositorio.RepositorioCidade;
import repositorio.RepositorioContatoComercial;

public class ServicoContatoComercial extends ServicoContato {

    // SCRUM-6 — localizarContatoComercial(id)
    public static ContatoComercial localizarContatoComercial(int id) {
        RepositorioContatoComercial repo = new RepositorioContatoComercial();
        ContatoComercial contato = repo.localizarComercialPorId(id);
        if (contato == null)
            throw new RuntimeException("Contato comercial não encontrado para o id: " + id);
        return contato;
    }

    // SCRUM-7 — localizarContatoComercial(nome)
    public static ContatoComercial localizarContatoComercial(String nome) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome não pode ser vazio.");
        RepositorioContatoComercial repo = new RepositorioContatoComercial();
        ContatoComercial contato = repo.localizarComercialPorNome(nome);
        if (contato == null)
            throw new RuntimeException("Contato comercial não encontrado: " + nome);
        return contato;
    }

    // SCRUM-8 — criarContatoComercial(nome, empresa, idcidade)
    public static void criarContatoComercial(String nome, String empresa, int idcidade) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome é obrigatório.");
        if (empresa == null || empresa.isBlank())
            throw new RuntimeException("Empresa é obrigatória.");

        RepositorioContatoComercial repoContato = new RepositorioContatoComercial();

        // Regra 1: nome único
        if (repoContato.localizarComercialPorNome(nome) != null)
            throw new RuntimeException("Já existe um contato com o nome: " + nome.toUpperCase());

        // Regra 3: cidade obrigatória
        RepositorioCidade repoCidade = new RepositorioCidade();
        Cidade cidade = repoCidade.localizar(idcidade);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada para o id: " + idcidade);

        ContatoComercial contato = new