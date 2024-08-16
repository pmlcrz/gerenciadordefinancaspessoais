
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorFinancas gerenciador = new GerenciadorFinancas();

        while (true) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Valor (use - para despesas): ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.print("Data (YYYY-MM-DD): ");
                    LocalDate data = LocalDate.parse(scanner.nextLine());
                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();
                    gerenciador.adicionarTransacao(descricao, valor, data, categoria);
                    break;
                case 2:
                    gerenciador.listarTransacoes();
                    break;
                case 3:
                    gerenciador.calcularSaldo();
                    break;
                case 4:
                    gerenciador.relatorioDespesas();
                    break;
                case 5:
                    System.out.print("Nome do arquivo para salvar: ");
                    String salvarArquivo = scanner.nextLine();
                    gerenciador.salvarTransacoes(salvarArquivo);
                    break;
                case 6:
                    System.out.print("Nome do arquivo para carregar: ");
                    String carregarArquivo = scanner.nextLine();
                    gerenciador.carregarTransacoes(carregarArquivo);
                    break;
                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nGerenciador de Finanças Pessoais");
        System.out.println("1. Adicionar Transação");
        System.out.println("2. Listar Transações");
        System.out.println("3. Calcular Saldo");
        System.out.println("4. Relatório de Despesas");
        System.out.println("5. Salvar Transações");
        System.out.println("6. Carregar Transações");
        System.out.println("7. Sair");
    }
}
