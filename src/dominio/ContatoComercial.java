package dominio;

import java.util.List;

public class ContatoComercial extends Contato{
	private String empresa;

	public ContatoComercial(String nome, List<String> telefones, String empresa) {
		super(nome, telefones);
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", telefones=" + telefones + ", empresa=" + empresa +"]";
	}
	
}
