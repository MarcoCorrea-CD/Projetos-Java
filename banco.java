public class banco {
    
    public static void main(String[] args) {
        System.out.println("BEM VINDO AO BANCO DIGITAL - DIGITE O NOME DO USUÁRIO: ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String nomeUsuario = scanner.nextLine();
        System.out.println("Olá, " + nomeUsuario + "! Escolha uma das opções abaixo para gerenciar suas finanças: 1 - Ver Saldo, 2 - Depositar, 3 - Sacar, 4 - Sair");
        
        double saldo = 0.0;
        int opcao;
        do {
            System.out.println();
            System.out.println("Olá, " + nomeUsuario + "! Escolha uma das opções abaixo para gerenciar suas finanças:");
            System.out.println("1 - Ver Saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Sair");
            System.out.print("Digite o número da opção desejada: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Opção 1 selecionada: Ver Saldo");
                    System.out.printf("Seu saldo atual é: R$ %.2f%n", saldo);
                    break;
                case 2:
                    System.out.println("Opção 2 selecionada: Depositar");
                    System.out.print("Digite o valor a ser depositado: R$ ");
                    double valorDeposito = scanner.nextDouble();
                    if (valorDeposito > 0) {
                        saldo += valorDeposito;
                        System.out.printf("Depósito de R$ %.2f realizado com sucesso. Novo saldo: R$ %.2f%n", valorDeposito, saldo);
                    } else {
                        System.out.println("Valor de depósito inválido.");
                    }
                    break;
                case 3:
                    System.out.println("Opção 3 selecionada: Sacar");
                    System.out.print("Digite o valor a ser sacado: R$ ");
                    double valorSaque = scanner.nextDouble();
                    if (valorSaque > 0 && valorSaque <= saldo) {
                        saldo -= valorSaque;
                        System.out.printf("Saque de R$ %.2f realizado com sucesso. Novo saldo: R$ %.2f%n", valorSaque, saldo);
                    } else {
                        System.out.println("Valor de saque inválido ou saldo insuficiente.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 4);
        
        System.out.println("Obrigado por usar o banco digital. Até logo, " + nomeUsuario + "!");
        scanner.close();
    }
}
