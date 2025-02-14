package repository;

import model.Venda;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    private static final String ARQUIVO = "vendas.txt";

    public void salvar(List<Venda> vendas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Venda venda : vendas) {
                writer.write(venda.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar vendas: " + e.getMessage());
        }
    }

    public List<Venda> carregar() {
        List<Venda> vendas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                vendas.add(new Venda(dados[0], Integer.parseInt(dados[1]), Double.parseDouble(dados[2])));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar vendas: " + e.getMessage());
        }
        return vendas;
    }
}
