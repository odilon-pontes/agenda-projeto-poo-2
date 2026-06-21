package dominio;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PESSOAL")
public class ContatoPessoal extends Contato {

    @Column(name = "grau_proximidade")
    private int grauProximidade;

    public ContatoPessoal() {}

    public ContatoPessoal(String nome, Cidade cidade, int grauProximidade) {
        super(nome, cidade);
        this.grauProximidade = grauProximidade;
    }

    public int getGrauProximidade() { return grauProximidade; }
    public void setGrauProximidade(int grauProximidade) { this.grauProximidade = grauProximidade; }

    @Override
    public String toString() {
        return "ContatoPessoal [id=" + id + ", nome=" + nome + ", cidade=" + cidade
                + ", telefones=" + telefones + ", grauProximidade=" + grauProximidade + "]";
    }
}
