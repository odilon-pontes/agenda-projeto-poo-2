package dominio;

public class Cidade {
	private static int proximoId = 1;
	private int id = 1;
	private String nome;
	
	public Cidade(String nome) {
		this.nome = nome;
		this.id = proximoId++;
	}
	

	@Override
	public String toString() {
		return "Cidade [id="+ id + ", nome=" + nome + "]";
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
	
}
