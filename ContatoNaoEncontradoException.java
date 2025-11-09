// Integrantes da Equipe:
// Thalis Elias Da Silva Teixeira
// Polo: Floresta
// Segunda exceção personalizada.
public class ContatoNaoEncontradoException extends Exception {
    
    // Construtor que passa a mensagem de erro para a classe "pai"
    public ContatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}