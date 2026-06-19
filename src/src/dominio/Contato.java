package src.dominio;

import java.util.List;

public abstract class Contato {
	private int id;
	private String nome;
	private List<String> telefones;
	
	public Contato(int id, String nome, List<String> telefones) {
		this.id = id;
		this.nome = nome;
		this.telefones = telefones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
	
	
}
