package repositorio;

import java.util.List;

import dominio.ContatoPessoal;
import jakarta.persistence.NoResultException;

public class RepositorioContatoPessoal extends RepositorioContato {

    public ContatoPessoal localizarPessoalPorId(int id) {
        return manager.find(ContatoPessoal.class, id);
    }

    public ContatoPessoal localizarPessoalPorNome(String nome) {
        try {
            return manager.createQuery(
                "SELECT c FROM ContatoPessoal c WHERE c.nome = :nome", ContatoPessoal.class)
                .setParameter("nome", nome.toUpperCase())
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ContatoPessoal> listarTodosPessoais() {
        return manager.createQuery(
            "SELECT c FROM ContatoPessoal c", ContatoPessoal.class)
                .getResultList();
    }

    public boolean criarPessoal(ContatoPessoal contato) {
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

    public boolean atualizarPessoal(ContatoPessoal contato) {
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