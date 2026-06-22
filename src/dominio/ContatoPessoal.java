package dominio;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PESSOAL")
public class ContatoPessoal extends Contato {

    public static final int GRAU_BAIXO = 1;
    public static final int GRAU_MEDIO = 2;
    public static final int GRAU_ALTO = 3;

    @Column(name = "grau_proximidade")
    private int grauProximidade;

    public ContatoPessoal() {}

    public ContatoPessoal(String nome, Cidade cidade, int grauProximidade) {
        super(nome, cidade);
        setGrauProximidade(grauProximidade);
    }

    public int getGrauProximidade() { return grauProximidade; }

    public void setGrauProximidade(int grauProximidade) {
        if (grauProximidade < GRAU_BAIXO || grauProximidade > GRAU_ALTO) {
            throw new RuntimeException("Grau de proximidade deve ser 1 (baixo), 2 (médio) ou 3 (alto).");
        }
        this.grauProximidade = grauProximidade;
    }

    @Override
    public String toString() {
        return "ContatoPessoal [id=" + id + ", nome=" + nome + ", cidade=" + cidade
                + ", telefones=" + telefones + ", grauProximidade=" + grauProximidade + "]";
    }
}
