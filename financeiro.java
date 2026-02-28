public class financeiro {

public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        java.util.List<String> pagamentos = new java.util.ArrayList<>();

        System.out.print("BEM VINDO AO FINANCEIRO - DIGITE O NOME DO CLIENTE: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Digite sua renda mensal: R$ ");
        double rendaMensal = 0;
        try {
            rendaMensal = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (Exception e) {
            rendaMensal = 0;
        }

        System.out.print("Digite suas despesas fixas mensais: R$ ");
        double despesasFixas = 0;
        try {
            despesasFixas = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (Exception e) {
            despesasFixas = 0;
        }

        System.out.println();
        System.out.println("Cadastro concluído para: " + nomeCliente);
        System.out.println("Renda: R$ " + String.format("%.2f", rendaMensal));
        System.out.println("Despesas Fixas: R$ " + String.format("%.2f", despesasFixas));
        System.out.println("Saldo Disponível: R$ " + String.format("%.2f", rendaMensal - despesasFixas));
        System.out.println("(Use o menu abaixo para gerenciar sua gestão financeira)");

        int opcao = 0;
        do {
            System.out.println();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║  SISTEMA DE GESTÃO FINANCEIRA          ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("Saldo Disponível: R$ " + String.format("%.2f", rendaMensal - despesasFixas));
            System.out.println();
            System.out.println("1 - Registrar Gasto");
            System.out.println("2 - Ver Histórico de Gastos");
            System.out.println("3 - Remover Gasto");
            System.out.println("4 - Atualizar Renda");
            System.out.println("5 - Atualizar Despesas Fixas");
            System.out.println("6 - Sair");
            System.out.print("Digite o número da opção desejada: ");
            String linha = scanner.nextLine();
            try {
                opcao = Integer.parseInt(linha.trim());
            } catch (Exception e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    System.out.println("Opção 1 selecionada: Registrar Gasto");
                    System.out.print("Digite a descrição do gasto: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Digite o valor do gasto: R$ ");
                    double valor = 0;
                    try {
                        valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    } catch (Exception e) {
                        valor = 0;
                    }
                    System.out.print("Digite a data do gasto (dd/mm/yyyy): ");
                    String data = scanner.nextLine();
                    String pagamento = "Descrição: " + descricao + " | Valor: R$ " + String.format("%.2f", valor) + " | Data: " + data;
                    pagamentos.add(pagamento);
                    System.out.println("Gasto registrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Opção 2 selecionada: Histórico de Gastos");
                    if (pagamentos.isEmpty()) {
                        System.out.println("Nenhum gasto registrado.");
                    } else {
                        double totalGastos = 0;
                        for (int i = 0; i < pagamentos.size(); i++) {
                            System.out.println((i + 1) + " - " + pagamentos.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Opção 3 selecionada: Remover Gasto");
                    if (pagamentos.isEmpty()) {
                        System.out.println("Nenhum gasto registrado para remover.");
                    } else {
                        System.out.print("Digite o número do gasto a ser removido (1-" + pagamentos.size() + "): ");
                        String linhaCancelar = scanner.nextLine();
                        int numeroCancelar;
                        try {
                            numeroCancelar = Integer.parseInt(linhaCancelar.trim());
                            if (numeroCancelar >= 1 && numeroCancelar <= pagamentos.size()) {
                                pagamentos.remove(numeroCancelar - 1);
                                System.out.println("Gasto removido com sucesso!");
                            } else {
                                System.out.println("Número inválido. Nenhum gasto removido.");
                            }
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Nenhum gasto removido.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Opção 4 selecionada: Atualizar Renda");
                    System.out.print("Digite sua nova renda mensal: R$ ");
                    try {
                        rendaMensal = Double.parseDouble(scanner.nextLine().replace(",", "."));
                        System.out.println("Renda atualizada com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Valor inválido.");
                    }
                    break;
                case 5:
                    System.out.println("Opção 5 selecionada: Atualizar Despesas Fixas");
                    System.out.print("Digite suas novas despesas fixas mensais: R$ ");
                    try {
                        despesasFixas = Double.parseDouble(scanner.nextLine().replace(",", "."));
                        System.out.println("Despesas fixas atualizadas com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Valor inválido.");
                    }
                    break;
                case 6:
                    System.out.println("Opção 6 selecionada: Sair");
                    System.out.println("Obrigado por usar o sistema de gestão financeira. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}
