package src.dominio;

import java.util.List;

public class ContatoPessoal extends Contato {
	private int grauProximidade;

	public ContatoPessoal(int id, String nome, List<String> telefones) {
		super(id, nome, telefones);
	}

	public int getGrauProximidade() {
		return grauProximidade;
	}

	public void setGrauProximidade(int grauProximidade) {
		this.grauProximidade = grauProximidade;
	}

}
