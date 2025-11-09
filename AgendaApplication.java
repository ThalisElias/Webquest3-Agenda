// Integrantes da Equipe:
// Thalis Elias Da Silva Teixeira
// Polo: Floresta

import java.util.List;
import java.util.Scanner;

public class AgendaApplication {

    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorContatos agenda = new AgendaManager();
    private static final String NOME_ARQUIVO_PADRAO = "contatos.csv";

    public static void main(String[] args) {
        boolean executando = true;

        agenda.carregarContatosCSV(NOME_ARQUIVO_PADRAO);

        while (executando) {
            exibirMenu();
            int opcao = -1;
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
                continue; 
            }

            switch (opcao) {
                case 1:
                    adicionar();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    remover();
                    break;
                case 4:
                    listarOrdenado(); 
                    break;
                case 5:
                    buscarPorDominio(); 
                    break;
                case 6:
                    salvar();
                    break;
                case 7:
                    carregar();
                    break;
                case 8:
                    executando = false; 
                    System.out.println("Salvando agenda antes de sair...");
                    agenda.salvarContatosCSV(NOME_ARQUIVO_PADRAO);
                    System.out.println("Obrigado por usar a agenda. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            
            if (executando) {
                pressioneEnterParaContinuar();
            }
        }
        scanner.close(); 
    }

    private static void exibirMenu() {
        System.out.println("\n--- AGENDA ELETRÔNICA ---");
        System.out.println("1. Adicionar Contato");
        System.out.println("2. Buscar Contato por Nome");
        System.out.println("3. Remover Contato");
        System.out.println("4. Listar Todos os Contatos (Ordenado)");
        System.out.println("5. Buscar por Domínio de Email (ex: gmail.com)");
        System.out.println("6. Salvar em CSV");
        System.out.println("7. Carregar de CSV");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionar() {
        System.out.println("\n--- Adicionar Contato ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        try {
            agenda.adicionarContato(new Contato(nome, telefone, email));
            System.out.println("Contato adicionado com sucesso!");
        } catch (ContatoExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscar() {
        System.out.println("\n--- Buscar Contato ---");
        System.out.print("Digite o nome para busca: ");
        String nome = scanner.nextLine();

        try {
            Contato contato = agenda.buscarContato(nome);
            System.out.println("Contato encontrado:");
            System.out.println(contato);
        } catch (ContatoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void remover() {
        System.out.println("\n--- Remover Contato ---");
        System.out.print("Digite o nome para remover: ");
        String nome = scanner.nextLine();

        try {
            agenda.removerContato(nome);
            System.out.println("Contato '" + nome + "' removido com sucesso.");
        } catch (ContatoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarOrdenado() {
        System.out.println("\n--- Contatos (Ordenados por Nome) ---");
        List<Contato> contatos = agenda.listarContatosOrdenados(); 
        
        if (contatos.isEmpty()) {
            System.out.println("A agenda está vazia.");
        } else {
            for (Contato contato : contatos) {
                System.out.println(contato);
            }
        }
    }
    
    private static void buscarPorDominio() {
        System.out.println("\n--- Buscar por Domínio de Email ---");
        System.out.print("Digite o domínio (ex: gmail.com): ");
        String dominio = scanner.nextLine();
        
        List<Contato> contatos = agenda.buscarPorDominioEmail(dominio);
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado com o domínio @" + dominio);
        } else {
            System.out.println("Contatos encontrados:");
            for (Contato contato : contatos) {
                System.out.println(contato);
            }
        }
    }

    private static void salvar() {
        System.out.println("\n--- Salvar em CSV ---");
        agenda.salvarContatosCSV(NOME_ARQUIVO_PADRAO);
    }

    private static void carregar() {
        System.out.println("\n--- Carregar de CSV ---");
        agenda.carregarContatosCSV(NOME_ARQUIVO_PADRAO);
    }
    
    private static void pressioneEnterParaContinuar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}