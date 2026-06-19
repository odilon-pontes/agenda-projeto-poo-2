package dominio;

public interface CRUDInterface<T> {
    boolean criar(T objeto);
    T localizar(int id);
    boolean atualizar(T objeto);
    boolean deletar(int id);
}
