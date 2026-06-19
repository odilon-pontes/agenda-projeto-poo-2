package repositorio;

import dominio.CRUDInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class Repositorio<T> implements CRUDInterface<T>{
	protected static EntityManagerFactory emf;
	
	protected EntityManager manager;
	
	static {
        try {
            emf = Persistence.createEntityManagerFactory("agenda-pu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public Repositorio() {
        this.manager = emf.createEntityManager(); 
    }
	
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
