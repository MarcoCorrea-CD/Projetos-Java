
import java.util.Scanner;


public class listaTarefas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BEM VINDO A LISTA DIGITAL DE TAREFAS - DIGITE O NOME DO USUÁRIO: ");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Olá, " + nomeUsuario + "! Escolha uma das opções abaixo para gerenciar suas tarefas: 1 - Adicionar Tarefa, 2 - Listar Tarefas, 3 - Marcar Tarefa como Concluída, 4 - Excluir Tarefa, 5 - Sair");
        
        java.util.List<String> tarefas = new java.util.ArrayList<>();
        int opcao;
        do {
            System.out.println();
            System.out.println("Olá, " + nomeUsuario + "! Escolha uma das opções abaixo para gerenciar suas tarefas:");
            System.out.println("1 - Adicionar Tarefa");
            System.out.println("2 - Listar Tarefas");
            System.out.println("3 - Marcar Tarefa como Concluída");
            System.out.println("4 - Excluir Tarefa");
            System.out.println("5 - Sair");
            System.out.print("Digite o número da opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcao) {
                case 1:
                    System.out.println("Opção 1 selecionada: Adicionar Tarefa");
                    System.out.print("Digite a descrição da tarefa: ");
                    String descricaoTarefa = scanner.nextLine();
                    tarefas.add(descricaoTarefa);
                    System.out.println("Tarefa adicionada: " + descricaoTarefa);
                    break;
                case 2:
                    System.out.println("Opção 2 selecionada: Listar Tarefas");
                    if (tarefas.isEmpty()) {
                        System.out.println("Nenhuma tarefa cadastrada.");
                    } else {
                        for (int i = 0; i < tarefas.size(); i++) {
                            System.out.println((i+1) + ". " + tarefas.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Opção 3 selecionada: Marcar Tarefa como Concluída");
                    System.out.print("Digite o número da tarefa a ser marcada como concluída: ");
                    int numeroTarefa = scanner.nextInt();
                    scanner.nextLine();
                    if (numeroTarefa >= 1 && numeroTarefa <= tarefas.size()) {
                        String concluida = tarefas.remove(numeroTarefa - 1);
                        System.out.println("Tarefa " + numeroTarefa + " (" + concluida + ") marcada como concluída e removida da lista.");
                    } else {
                        System.out.println("Número de tarefa inválido.");
                    }
                    break;
                case 4:
                    System.out.println("Opção 4 selecionada: Excluir Tarefa");
                    System.out.print("Digite o número da tarefa a ser excluída: ");
                    int numeroTarefaExcluir = scanner.nextInt();
                    scanner.nextLine();
                    if (numeroTarefaExcluir >= 1 && numeroTarefaExcluir <= tarefas.size()) {
                        String excluida = tarefas.remove(numeroTarefaExcluir - 1);
                        System.out.println("Tarefa " + numeroTarefaExcluir + " (" + excluida + ") excluída.");
                    } else {
                        System.out.println("Número de tarefa inválido.");
                    }
                    break;
                case 5:
                    System.out.println("Opção 5 selecionada: Sair");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 5.");
            }
        } while (opcao != 5);
    }

}