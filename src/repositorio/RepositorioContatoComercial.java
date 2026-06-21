package repositorio;

import java.util.List;

import dominio.ContatoComercial;
import jakarta.persistence.NoResultException;

public class RepositorioContatoComercial extends RepositorioContato {

    public ContatoComercial localizarComercialPorId(int id) {
        return manager.find(ContatoComercial.class, id);
    }

    public ContatoComercial localizarComercialPorNome(String nome) {
        try {
            return manager.createQuery(
                "SELECT c FROM ContatoComercial c WHERE c.nome = :nome", ContatoComercial.class)
                .setParameter("nome", nome.toUpperCase())
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ContatoComercial> listarTodosComerciais() {
        return manager.createQuery(
            "SELECT c FROM ContatoComercial c", ContatoComercial.class)
                .getResultList();
    }

    public boolean criarComercial(ContatoComercial contato) {
        try {
            manager.getTransaction().begin();
            manager.persist(contato);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        }
    }

    public boolean atualizarComercial(ContatoComercial contato) {
        try {
            manager.getTransaction().begin();
            manager.merge(contato);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false;
        }
    }
}