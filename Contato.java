// Integrantes da Equipe:
// Thalis Elias Da Silva Teixeira
// Polo: Floresta

public class Contato implements Comparable<Contato> {
    // Atributos privados para garantir o encapsulamento
    private String nome;
    private String telefone;
    private String email;

    // Construtor: Usado para criar um novo objeto Contato
    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // --- Get (para ler os valores) ---
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    // --- Set (para alterar os valores) ---
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString(): Para imprimir o objeto de forma legível
    @Override
    public String toString() {
        return "Nome: " + nome + " | Telefone: " + telefone + " | Email: " + email;
    }
}