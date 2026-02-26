public class hospital {
        public static void main(String[] args) {


        java.util.Scanner scanner = new java.util.Scanner(System.in);
        java.util.List<String> consultas = new java.util.ArrayList<>();

        System.out.print("BEM VINDO AO HOSPITAL - DIGITE O NOME DO PACIENTE: ");
        String nomePaciente = scanner.nextLine();

        System.out.print("Digite o seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o seu RG: ");
        String rg = scanner.nextLine();

        System.out.print("Digite o seu endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Digite o seu telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite a sua data de nascimento (dd/mm/yyyy): ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Digite o seu sexo (M/F): ");
        String sexo = scanner.nextLine();

        System.out.print("Digite o seu plano de saúde (se tiver): ");
        String plano = scanner.nextLine();

        System.out.println();
        System.out.println("Cadastro concluído para: " + nomePaciente);
        System.out.println("(Use o menu abaixo para gerenciar consultas)");

        int opcao = 0;
        do {
            System.out.println();
            System.out.println("Olá, " + nomePaciente + "! Escolha uma das opções abaixo para gerenciar suas consultas:");
            System.out.println("1 - Marcar Consulta");
            System.out.println("2 - Ver Consultas");
            System.out.println("3 - Cancelar Consulta");
            System.out.println("4 - Sair");
            System.out.print("Digite o número da opção desejada: ");
            String linha = scanner.nextLine();
            try {
                opcao = Integer.parseInt(linha.trim());
            } catch (Exception e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    System.out.println("Opção 1 selecionada: Marcar Consulta");
                    System.out.print("Digite a data da consulta (dd/mm/yyyy): ");
                    String dataConsulta = scanner.nextLine();
                    System.out.print("Digite o horário da consulta (hh:mm): ");
                    String horarioConsulta = scanner.nextLine();
                    String consulta = dataConsulta + " às " + horarioConsulta;
                    consultas.add(consulta);
                    System.out.println("Consulta marcada para " + consulta + ".");
                    break;
                case 2:
                    System.out.println("Opção 2 selecionada: Ver Consultas");
                    if (consultas.isEmpty()) {
                        System.out.println("Nenhuma consulta marcada para o paciente " + nomePaciente + ".");
                    } else {
                        System.out.println("Consultas marcadas para o paciente " + nomePaciente + ":");
                        for (int i = 0; i < consultas.size(); i++) {
                            System.out.println((i + 1) + " - " + consultas.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Opção 3 selecionada: Cancelar Consulta");
                    System.out.print("Digite a data da consulta a ser cancelada (dd/mm/yyyy): ");
                    String dataCancelamento = scanner.nextLine();
                    System.out.print("Digite o horário da consulta a ser cancelada (hh:mm): ");
                    String horarioCancelamento = scanner.nextLine();
                    String chave = dataCancelamento + " às " + horarioCancelamento;
                    boolean removido = consultas.remove(chave);
                    if (removido) {
                        System.out.println("Consulta cancelada para " + chave + ".");
                    } else {
                        System.out.println("Nenhuma consulta encontrada nessa data/horário.");
                    }
                    break;
                case 4:
                    System.out.println("Saindo. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 4);

        scanner.close();
        }
}
