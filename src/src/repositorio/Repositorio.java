package src.repositorio;

import src.dominio.CRUDInterface;

public class Repositorio<T> implements CRUDInterface<T>{

	@Override
	public boolean criar(T objeto) {
		return false;
	}

	@Override
	public T localizar(int id) {
		return null;
	}

	@Override
	public boolean atualizar(T objeto) {
		return false;
	}

	@Override
	public boolean deletar(int id) {
		return false;
	}	
	
}
