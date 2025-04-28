import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo = new ArrayList<>();
    private static final int ANO_PUBLICACAO_MINIMO = 1400;

    public Livro adicionar(Livro novoLivro) throws Exception {
        // Validações
        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty()) {
            throw new Exception("Título não pode estar em branco!");
        }

        if (novoLivro.getAutor() == null || novoLivro.getAutor().isEmpty()) {
            throw new Exception("Autor não pode estar em branco!");
        }

        if (novoLivro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO) {
            throw new Exception("Ano de publicação não pode ser menor que " + ANO_PUBLICACAO_MINIMO);
        }

        if (novoLivro.getNumeroPaginas() <= 0) {
            throw new Exception("Número de páginas não pode ser zero ou negativo!");
        }

        // Verificar se já existe um livro com o mesmo título (ignorando maiúsculas/minúsculas)
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(novoLivro.getTitulo())) {
                throw new Exception("Já existe um livro com o mesmo título!");
            }
        }

        acervo.add(novoLivro);
        return novoLivro;
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> pesquisarTodos() {
        return new ArrayList<>(acervo); // Retorna uma cópia para proteger o acervo
    }

    public boolean removerLivro(String titulo) {
        Iterator<Livro> iterator = acervo.iterator();
        while (iterator.hasNext()) {
            Livro livro = iterator.next();
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void listarLivros() {
        if (acervo.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (Livro livro : acervo) {
                System.out.println(livro);
            }
        }
    }

    public List<Livro> pesquisarPorAno(int ano) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }
}