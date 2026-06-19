package app;

import java.util.ArrayList;
import java.util.List;

import dominio.Cidade;
import dominio.ContatoComercial;
import dominio.ContatoPessoal;

public class Main {
	public static void main(String[] args) {
		List<Cidade> cidades = new ArrayList<>();
		List<ContatoPessoal> contatosPessoais = new ArrayList<>();
		List<ContatoComercial> contatosComerciais = new ArrayList<>();
		
		Cidade cidade1 = new Cidade("João Pessoa");
		cidades.add(cidade1);
		Cidade cidade2 = new Cidade("Recife");
		cidades.add(cidade2);

		System.out.println(cidades);
		System.out.println("---------------------");
		
		List<String> telefonesOdilon = new ArrayList<>();
		telefonesOdilon.add("(83)99999-4444");
		telefonesOdilon.add("(83)99999-5555");
		ContatoPessoal cp1 = new ContatoPessoal("Odilon",telefonesOdilon, 3);
		
		List<String> telefonesMatheus = new ArrayList<>();
		telefonesMatheus.add("(83)99999-6666");
		telefonesMatheus.add("(83)99999-7777");
		ContatoPessoal cp2 = new ContatoPessoal("Matheus",telefonesMatheus, 3);
		
		contatosPessoais.add(cp1);
		contatosPessoais.add(cp2);
		
		System.out.println(contatosPessoais);
		System.out.println("---------------------");
		
		List<String> telefonesENB= new ArrayList<>();
		telefonesENB.add("(83)3200-0000");
		telefonesENB.add("(83)3200-1111");
		ContatoComercial cc1 = new ContatoComercial("Odilon",telefonesOdilon, "Escorrega Na Banha");
		contatosComerciais.add(cc1);
		
		List<String> telefonesAR= new ArrayList<>();
		telefonesAR.add("(83)3100-0000");
		telefonesAR.add("(83)3100-1111");
		ContatoComercial cc2 = new ContatoComercial("Matheus",telefonesOdilon, "AssetReserv");
		contatosComerciais.add(cc2);
		
		System.out.println(contatosComerciais);
		
		
		
		
		
	}
}

