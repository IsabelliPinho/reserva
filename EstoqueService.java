package service;

import model.Produto;
import repository.ProdutoRepository;

import java.util.List;

public class EstoqueService {
    private ProdutoRepository produtoRepository = new ProdutoRepository();
    private List<Produto> produtos;

    public EstoqueService() {
        produtos = produtoRepository.carregar();
    }

    public void adicionarProduto(String nome, double preco, int quantidade, int nivelMinimo) {
        produtos.add(new Produto(nome, preco, quantidade, nivelMinimo));
        produtoRepository.salvar(produtos);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }
}
