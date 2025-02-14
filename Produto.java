package model;

public class Produto {
    private String nome;
    private double preco;
    private int quantidade;
    private int nivelMinimo;

    public Produto(String nome, double preco, int quantidade, int nivelMinimo) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.nivelMinimo = nivelMinimo;
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

    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public boolean removerEstoque(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            if (this.quantidade < nivelMinimo) {
                System.out.println("ALERTA: Estoque de " + nome + " abaixo do nível mínimo!");
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nome + "," + preco + "," + quantidade + "," + nivelMinimo;
    }
}
