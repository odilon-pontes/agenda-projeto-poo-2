package servico;

import repositorio.RepositorioContato;

public abstract class ServicoContato extends Servico {

    public static void adicionarTelefoneContato(String numero, int id) {
        if (numero == null || numero.isBlank())
            throw new RuntimeException("Número de telefone é obrigatório.");
        RepositorioContato repo = new RepositorioContato();
        repo.adicionarTelefone(id, numero);
    }

    public static void apagarContato(int id) {
        RepositorioContato repo = new RepositorioContato();
        repo.deletar(id);
    }
}