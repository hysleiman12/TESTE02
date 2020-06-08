package br.com.faculdadedelta.exercicio03hysleiman.modelo;

import java.io.Serializable;
import java.util.Objects;

public class EstoqueHysleiman implements Serializable {

    private Long id;
    private String produto;
    private String fornecedor;
    private double valor;

    public EstoqueHysleiman() {
    }

    public EstoqueHysleiman(Long id, String produto, String fornecedor, double valor) {
        this.id = id;
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueHysleiman that = (EstoqueHysleiman) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
