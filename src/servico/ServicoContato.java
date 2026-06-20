package servico;

import repositorio.RepositorioContato;

public abstract class ServicoContato extends Servico {

    // SCRUM-14 — adicionarTelefoneContato(numero, id)
    public static void adicionarTelefoneContato(String numero, int id) {
        if (numero == null || numero.isBlank())
            throw new RuntimeException("Número de telefone é obrigatório.");
        // Regra 6: duplicidade verificada no repositório
        RepositorioContato repo = new RepositorioContato();
        repo.adicionarTelefone(id, numero);
    }

    // SCRUM-15 — apagarContato(id)
    public static void apagarContato(int id) {
        // Regra 7: cidade não é apagada junto com o contato
        RepositorioContato repo = new RepositorioContato();
        repo.deletar(id);
    }
}