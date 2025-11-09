// Integrantes da Equipe:
// Thalis Elias Da Silva Teixeira
// Polo: Floresta

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Contato contato : contatos.values()) {
                String linha = contato.getNome() + ";" + contato.getTelefone() + ";" + contato.getEmail();
                writer.write(linha);
                writer.newLine();
            }
            System.out.println("Contatos salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo CSV: " + e.getMessage());
        }
    }

    @Override
    public void carregarContatosCSV(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            contatos.clear();
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(";");
                if (campos.length == 3) {
                    Contato contato = new Contato(campos[0], campos[1], campos[2]);
                    contatos.put(contato.getNome(), contato);
                }
            }
            System.out.println("Contatos carregados com sucesso de " + nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo CSV: " + e.getMessage());
        }
    }

    @Override
    public List<Contato> listarContatosOrdenados() {
        List<Contato> lista = new ArrayList<>(contatos.values());
        Collections.sort(lista);
        return lista;
    }

    @Override
    public List<Contato> buscarPorDominioEmail(String dominio) {
        List<Contato> encontrados = new ArrayList<>();
        String dominioBusca = "@" + dominio; 
        
        for (Contato contato : contatos.values()) {
            if (contato.getEmail().endsWith(dominioBusca)) {
                encontrados.add(contato);
            }
        }
        return encontrados;
    }
}