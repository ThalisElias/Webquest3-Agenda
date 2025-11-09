// Integrantes da Equipe:
// Thalis Elias Da Silva Teixeira
// Polo: Floresta


// Nossa primeira exceção personalizada.
// Ela herda (extends) da classe Exception principal do Java.
public class ContatoExistenteException extends Exception {
    
    // Este é o construtor da nossa exceção
    public ContatoExistenteException(String mensagem) {
        // super(mensagem) significa passe esta mensagem para a classe pai (Exception)
        super(mensagem);
    }
}