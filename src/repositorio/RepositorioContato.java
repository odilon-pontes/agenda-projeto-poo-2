package repositorio;

import java.util.List;

import dominio.Contato;
import jakarta.persistence.NoResultException;

public class RepositorioContato extends Repositorio<Contato> {

    public RepositorioContato() {
        super(Contato.class);
    }

    public Contato localizarPorNome(String nome) {
        try {
            return manager.createQuery(
                "SELECT c FROM Contato c WHERE c.nome = :nome", Contato.class)
                .setParameter("nome", nome.toUpperCase())
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Contato> listarTodos() {
        return manager.createQuery("SELECT c FROM Contato c", Contato.class)
                .getResultList();
    }

    public void adicionarTelefone(int idContato, String numero) {
        Contato contato = localizar(idContato);
        if (contato == null) throw new RuntimeException("Contato não encontrado.");
        if (contato.getTelefones().contains(numero)) {
            throw new RuntimeException("Telefone já cadastrado para este contato.");
        }
        manager.getTransaction().begin();
        contato.getTelefones().add(numero);
        manager.getTransaction().commit();
    }

    public void deletar(int id) {
        Contato contato = localizar(id);
        if (contato == null) throw new RuntimeException("Contato não encontrado.");
        manager.getTransaction().begin();
        manager.remove(contato);
        manager.getTransaction().commit();
    }
}