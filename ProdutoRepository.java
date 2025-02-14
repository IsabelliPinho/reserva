package repository;

import model.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private static final String ARQUIVO = "estoque.txt";

    public void salvar(List<Produto> produtos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Produto produto : produtos) {
                writer.write(produto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public List<Produto> carregar() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                produtos.add(new Produto(dados[0], Double.parseDouble(dados[1]), Integer.parseInt(dados[2]), Integer.parseInt(dados[3])));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
        return produtos;
    }
}
