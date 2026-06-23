package repositorio;

import java.util.List;

import dominio.Cidade;
import jakarta.persistence.NoResultException;

public class RepositorioCidade extends Repositorio<Cidade> {

    public RepositorioCidade() {
        super(Cidade.class);
    }

    public Cidade localizarPorNome(String nome) {
        try {
            return manager.createQuery(
                "SELECT c FROM Cidade c WHERE c.nome = :nome", Cidade.class)
                .setParameter("nome", nome.toUpperCase())
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Cidade> listarTodas() {
        return manager.createQuery(
            "SELECT c FROM Cidade c ORDER BY c.nome", Cidade.class)
                .getResultList();
    }
}
