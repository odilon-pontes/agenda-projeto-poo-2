package dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "contato_comercial")
@PrimaryKeyJoinColumn(name = "id_contato")
public class ContatoComercial extends Contato {

    @Column(nullable = false)
    private String empresa;

    public ContatoComercial() {}

    public ContatoComercial(String nome, Cidade cidade, String empresa) {
        super(nome, cidade);
        this.empresa = empresa;
    }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    @Override
    public String toString() {
        return "ContatoComercial [id=" + id + ", nome=" + nome + ", empresa=" + empresa
                + ", cidade=" + cidade + ", telefones=" + telefones + "]";
    }
}