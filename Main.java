import java.util.List;
import java.util.Scanner;

public class Main {
    private static Biblioteca biblio = new Biblioteca();

    public static void main(String[] args) {
        Scanner lerTeclado = new Scanner(System.in);
        int opcao;

        do {
            opcao = inputNumerico(lerTeclado, """
                ====== MENU ======
                Escolha uma das opções abaixo:
                1 - Adicionar novo livro
                2 - Pesquisar livro por título
                3 - Listar todos os livros
                4 - Remover livro por título
                5 - Listar livros por ano de publicação
                0 - Sair
                """);


            limparTela();


            switch (opcao) {
                case 1 -> adicionar(lerTeclado);
                case 2 -> pesquisarPorTitulo(lerTeclado);
                case 3 -> listarTodos();
                case 4 -> removerPorTitulo(lerTeclado);
                case 5 -> listarLivrosPorAno(lerTeclado);  // Novo caso para listar livros por ano
                case 0 -> System.out.println("Encerrando programa...");
                default -> System.out.println("Opção inválida. Tente novamente.");

                
            }
            limparTela();
        } while (opcao != 0);

        lerTeclado.close();
    }

    private static void adicionar(Scanner lerTeclado) {
        System.out.println("Digite o título do livro:");
        String titulo = lerTeclado.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = lerTeclado.nextLine();
        int anoPublicacao = inputNumerico(lerTeclado, "Digite o ano da publicação:");
        int numeroPaginas = inputNumerico(lerTeclado, "Digite o número de páginas:");

        Livro novoLivro;
        int tipoLivro = inputNumerico(lerTeclado, "Qual o tipo do livro: 1 - Físico, 2 - Digital");

        if (tipoLivro == 1) {
            LivroFisico livroFisico = new LivroFisico();
            System.out.println("Digite as dimensões do livro:");
            livroFisico.setDimensoes(lerTeclado.nextLine());
            livroFisico.setNumeroExemplares(inputNumerico(lerTeclado, "Digite o número de exemplares:"));
            novoLivro = livroFisico;
        } else {
            LivroDigital livroDigital = new LivroDigital();
            System.out.println("Digite o formato do arquivo:");
            livroDigital.setFormatoArquivo(lerTeclado.nextLine());
            novoLivro = livroDigital;
        }

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setNumeroPaginas(numeroPaginas);

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void pesquisarPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite o título a ser pesquisado:");
        String titulo = lerTeclado.nextLine();
        List<Livro> livrosEncontrados = biblio.pesquisarPorTitulo(titulo);

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : livrosEncontrados) {
                System.out.println(livro);
            }
        }
    }

    private static void listarTodos() {
        List<Livro> livros = biblio.pesquisarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("Livros cadastrados:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void removerPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite o título do livro a ser removido:");
        String titulo = lerTeclado.nextLine();

        if (biblio.removerLivro(titulo)) {
            System.out.println("Livro removido com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void listarLivrosPorAno(Scanner lerTeclado) {
        int anoDesejado = inputNumerico(lerTeclado, "Digite o ano de publicação desejado:");

        List<Livro> livrosPorAno = biblio.pesquisarPorAno(anoDesejado);

        if (livrosPorAno.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o ano " + anoDesejado + ".");
        } else {
            System.out.println("Livros publicados em " + anoDesejado + ":");
            for (Livro livro : livrosPorAno) {
                System.out.println(livro);
            }
        }
    }

    private static int inputNumerico(Scanner lerTeclado, String mensagem) {
        int valor;
        while (true) {
            System.out.println(mensagem);
            String input = lerTeclado.nextLine();
            try {
                valor = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
        return valor;
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}



        