package dominio;

import java.util.List;

public abstract class Contato {
	private static int proximoId = 1;
	protected int id;
	protected String nome;
	protected List<String> telefones;
	
	
	public Contato( String nome, List<String> telefones) {
		this.nome = nome;
		this.telefones = telefones;
		this.id = proximoId++;
	}

	
	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", telefones=" + telefones + "]";
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
