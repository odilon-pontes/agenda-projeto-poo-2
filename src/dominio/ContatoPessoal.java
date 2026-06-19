package dominio;

import java.util.List;

public class ContatoPessoal extends Contato {
	private int grauProximidade;

	public ContatoPessoal(String nome, List<String> telefones, int grauProximidade) {
		super(nome, telefones);
		this.grauProximidade = grauProximidade;
	}

	public int getGrauProximidade() {
		return grauProximidade;
	}

	public void setGrauProximidade(int grauProximidade) {
		this.grauProximidade = grauProximidade;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", telefones=" + telefones + ", grauProximidade=" + grauProximidade + "]";
	}
	
	

}
