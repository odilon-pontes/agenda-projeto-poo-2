package servico;

import java.util.List;

import dominio.Cidade;
import dominio.ContatoComercial;
import repositorio.RepositorioCidade;
import repositorio.RepositorioContatoComercial;

public class ServicoContatoComercial extends ServicoContato {

    public static ContatoComercial localizarContatoComercial(int id) {
        RepositorioContatoComercial repo = new RepositorioContatoComercial();
        ContatoComercial contato = repo.localizarComercialPorId(id);
        if (contato == null)
            throw new RuntimeException("Contato comercial não encontrado para o id: " + id);
        return contato;
    }

    public static ContatoComercial localizarContatoComercial(String nome) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome não pode ser vazio.");
        RepositorioContatoComercial repo = new RepositorioContatoComercial();
        ContatoComercial contato = repo.localizarComercialPorNome(nome);
        if (contato == null)
            throw new RuntimeException("Contato comercial não encontrado: " + nome);
        return contato;
    }

    public static void criarContatoComercial(String nome, String empresa, int idcidade) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome é obrigatório.");
        if (empresa == null || empresa.isBlank())
            throw new RuntimeException("Empresa é obrigatória.");

        RepositorioContatoComercial repoContato = new RepositorioContatoComercial();

        if (repoContato.localizarComercialPorNome(nome) != null)
            throw new RuntimeException("Já existe um contato com o nome: " + nome.toUpperCase());

        RepositorioCidade repoCidade = new RepositorioCidade();
        Cidade cidade = repoCidade.localizar(idcidade);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada para o id: " + idcidade);

        ContatoComercial contato = new ContatoComercial(nome, cidade, empresa);
        repoContato.criarComercial(contato);
    }

    public static void alterarContatoComercial(String nome, String empresa, int idcidade) {
        if (nome == null || nome.isBlank())
            throw new RuntimeException("Nome é obrigatório.");
        if (empresa == null || empresa.isBlank())
            throw new RuntimeException("Empresa é obrigatória.");

        RepositorioContatoComercial repoContato = new RepositorioContatoComercial();

        ContatoComercial contato = repoContato.localizarComercialPorNome(nome);
        if (contato == null)
            throw new RuntimeException("Contato comercial não encontrado: " + nome.toUpperCase());

        RepositorioCidade repoCidade = new RepositorioCidade();
        Cidade cidade = repoCidade.localizar(idcidade);
        if (cidade == null)
            throw new RuntimeException("Cidade não encontrada para o id: " + idcidade);

        contato.setEmpresa(empresa);
        contato.setCidade(cidade);
        repoContato.atualizarComercial(contato);
    }

    public static List<ContatoComercial> listarContatosEmpresa() {
        RepositorioContatoComercial repo = new RepositorioContatoComercial();
        return repo.listarTodosComerciais();
    }
}