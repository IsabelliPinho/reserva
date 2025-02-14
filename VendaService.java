package service;

import model.Produto;
import model.Venda;
import repository.VendaRepository;

import java.util.List;

public class VendaService {
    private VendaRepository vendaRepository = new VendaRepository();
    private List<Venda> vendas;

    public VendaService() {
        vendas = vendaRepository.carregar();
    }

    public void registrarVenda(Produto produto, int quantidade) {
        if (produto.removerEstoque(quantidade)) {
            double total = produto.getPreco() * quantidade;
            vendas.add(new Venda(produto.getNome(), quantidade, total));
            vendaRepository.salvar(vendas);
            System.out.println("Venda registrada com sucesso!");
        } else {
            System.out.println("Estoque insuficiente!");
        }
    }

    public List<Venda> listarVendas() {
        return vendas;
    }
}
