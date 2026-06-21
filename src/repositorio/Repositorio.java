package repositorio;

import dominio.CRUDInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class Repositorio<T> implements CRUDInterface<T> {

    protected static EntityManagerFactory emf;
    protected EntityManager manager;
    private Class<T> tipo;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("agenda-pu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Repositorio(Class<T> tipo) {
        this.tipo = tipo;
        this.manager = emf.createEntityManager();
    }

    @Override
    public boolean criar(T objeto) {
        try {
            manager.getTransaction().begin();
            manager.persist(objeto);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public T localizar(int id) {
        return manager.find(tipo, id);
    }

    @Override
    public boolean atualizar(T objeto) {
        try {
            manager.getTransaction().begin();
            manager.merge(objeto);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        try {
            T objeto = localizar(id);
            if (objeto == null) return false;
            manager.getTransaction().begin();
            manager.remove(objeto);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        }
    }
}