package model;

public class Venda {
    private String nomeProduto;
    private int quantidade;
    private double total;

    public Venda(String nomeProduto, int quantidade, double total) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.total = total;
    }

    @Override
    public String toString() {
        return nomeProduto + "," + quantidade + "," + total;
    }
}
