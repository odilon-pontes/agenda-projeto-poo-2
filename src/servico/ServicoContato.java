package servico;

import java.util.regex.Pattern;

import repositorio.RepositorioContato;

public abstract class ServicoContato extends Servico {
	
	private static final Pattern PADRAO_TELEFONE = Pattern.compile("^\\d{10,11}$"); 

    public static void adicionarTelefoneContato(String numero, int id) {
        if (numero == null || numero.isBlank())
            throw new RuntimeException("Número de telefone é obrigatório.");
        
        numero = numero.replaceAll("[^0-9]", "");
        
        if (!PADRAO_TELEFONE.matcher(numero).matches())
            throw new RuntimeException("Telefone inválido. Use DDD + número, somente dígitos (10 ou 11 dígitos).");
        
        RepositorioContato repo = new RepositorioContato();
        repo.adicionarTelefone(id, numero);
    }

    public static void apagarContato(int id) {
        RepositorioContato repo = new RepositorioContato();
        repo.deletar(id);
    }
}