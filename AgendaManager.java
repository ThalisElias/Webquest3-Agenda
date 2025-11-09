// Integrantes da Equipe:
// Thalis Elias Da Silva Teixeira
// Polo: Floresta

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgendaManager implements GerenciadorContatos {

    private Map<String, Contato> contatos;

    public AgendaManager() {
        this.contatos = new HashMap<>();
    }

    @Override
    public void adicionarContato(Contato contato) throws ContatoExistenteException {
        if (contatos.containsKey(contato.getNome())) {
            throw new ContatoExistenteException("Erro: Contato com o nome '" + contato.getNome() + "' já existe.");
        }
        contatos.put(contato.getNome(), contato);
    }

    @Override
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        Contato contato = contatos.get(nome);

        if (contato == null) {
            throw new ContatoNaoEncontradoException("Erro: Contato com o nome '" + nome + "' não foi encontrado.");
        }
        return contato;
    }

    @Override
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        Contato removido = contatos.remove(nome);

        if (removido == null) {
            throw new ContatoNaoEncontradoException("Erro: Contato com o nome '" + nome + "' não foi encontrado.");
        }
    }

    @Override
    public List<Contato> listarTodosContatos() {
        return new ArrayList<>(contatos.values());
    }

    
    @Override
    public void salvarContatosCSV(String nomeArquivo) {
        System.out.println('Salvar em CSV')
    }

    @Override
    public void carregarContatosCSV(String nomeArquivo) {
        System.out.println('Carregar de CSV')
    }

    @Override
    public List<Contato> listarContatosOrdenados() {
        System.out.println('Listar Ordenado')
        return new ArrayList<>();
    }

    @Override
    public List<Contato> buscarPorDominioEmail(String dominio) {
        System.out.println('Buscar por Domínio')
        return new ArrayList<>();
    }
}