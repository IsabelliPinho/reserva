package model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private int nivelMinimo;

    public Produto(int id, String nome, double preco, int quantidade, int nivelMinimo) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.nivelMinimo = nivelMinimo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getNivelMinimo() {
        return nivelMinimo;
    }

    @Override
    public String toString() {
        return id + "," + nome + "," + preco + "," + quantidade + "," + nivelMinimo;
    }
}
