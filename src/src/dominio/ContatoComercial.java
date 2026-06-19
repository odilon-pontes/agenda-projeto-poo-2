package src.dominio;

import java.util.List;

public class ContatoComercial extends Contato{
	private String empresa;

	public ContatoComercial(int id, String nome, List<String> telefones) {
		super(id, nome, telefones);
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
}
