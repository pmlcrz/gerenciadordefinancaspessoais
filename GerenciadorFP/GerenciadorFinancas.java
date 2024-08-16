import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorFinancas {
    private List<Transacao> transacoes;

    public GerenciadorFinancas() {
        transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(String descricao, double valor, LocalDate data, String categoria) {
        transacoes.add(new Transacao(descricao, valor, data, categoria));
        System.out.println("Transação adicionada com sucesso!");
    }

    public void listarTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
        } else {
            for (Transacao transacao : transacoes) {
                System.out.println(transacao);
            }
        }
    }

    public void calcularSaldo() {
        double saldo = transacoes.stream().mapToDouble(Transacao::getValor).sum();
        System.out.println(String.format("Saldo total: R$ %.2f", saldo));
    }

    public void relatorioDespesas() {
        Map<String, Double> despesasPorCategoria = transacoes.stream()
                .filter(t -> t.getValor() < 0)
                .collect(Collectors.groupingBy(
                        Transacao::getCategoria,
                        Collectors.summingDouble(Transacao::getValor)
                ));

        if (despesasPorCategoria.isEmpty()) {
            System.out.println("Nenhuma despesa registrada.");
        } else {
            despesasPorCategoria.forEach((categoria, valor) ->
                    System.out.println(String.format("Categoria: %s - Total: R$ %.2f", categoria, valor)));
        }
    }

    public void salvarTransacoes(String arquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Transacao transacao : transacoes) {
                writer.write(String.format("%s,%f,%s,%s%n",
                        transacao.getDescricao(),
                        transacao.getValor(),
                        transacao.getData(),
                        transacao.getCategoria()));
            }
            System.out.println("Transações salvas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar as transações.");
            e.printStackTrace();
        }
    }

    public void carregarTransacoes(String arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String descricao = partes[0];
                double valor = Double.parseDouble(partes[1]);
                LocalDate data = LocalDate.parse(partes[2]);
                String categoria = partes[3];
                transacoes.add(new Transacao(descricao, valor, data, categoria));
            }
            System.out.println("Transações carregadas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar as transações.");
            e.printStackTrace();
        }
    }
}
