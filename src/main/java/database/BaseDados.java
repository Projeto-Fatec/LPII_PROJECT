package database;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.InstituicaoFinanceira;
import model.Leilao;
import model.Produto;
import jakarta.inject.Singleton;

@Singleton
public class BaseDados {

    private List<Cliente> clientes;
    private List<InstituicaoFinanceira> instituicoesFinanceiras;
    private List<Leilao> leiloes;

    private Long leilaoSequence;

    public BaseDados(){
        clientes = new ArrayList<Cliente>();
        instituicoesFinanceiras = new ArrayList<InstituicaoFinanceira>();
        leiloes = new ArrayList<Leilao>();

        leilaoSequence = 0l;
    }

    //Listar
    public List<Cliente> findAllClientes(){
        return clientes;
    }

    public List<InstituicaoFinanceira> findAllInstituicoesFinanceiras(){
        return instituicoesFinanceiras;
    }

    public List<Leilao> findAllLeiloes(){
        return leiloes;
    }

    //Encontrar
    public Cliente findClienteByCpf(String cpf){
        for(Cliente c: clientes){

            if(c.getCpf().equals(cpf))
                return c;
        }

        return null;
    }

    public InstituicaoFinanceira findInstituicaoFinanceiraByCnpj(String cnpj){
        for(InstituicaoFinanceira i: instituicoesFinanceiras){
            if(i.getCnpj().equals(cnpj))
                return i;
        }

        return null;
    }

    public Leilao findLeilaoById(Long id){
        for(Leilao l: leiloes){
            if(l.getId().equals(id))
                return l;
        }

        return null;
    }

    //Persistir
    public Cliente saveCliente(Cliente cliente){
        Cliente persistedCliente = findClienteByCpf(cliente.getCpf());

        if(persistedCliente == null){
            clientes.add(cliente);
            persistedCliente = cliente;
        } else {
            persistedCliente.setEmail(cliente.getEmail());
            persistedCliente.setNome(cliente.getNome());
        }

        return persistedCliente;
    }

    public InstituicaoFinanceira saveInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        InstituicaoFinanceira persistedInstituicaoFinanceira = findInstituicaoFinanceiraByCnpj(instituicaoFinanceira.getCnpj());

        if(persistedInstituicaoFinanceira == null){
            instituicoesFinanceiras.add(instituicaoFinanceira);
            persistedInstituicaoFinanceira = instituicaoFinanceira;
        } else {
            persistedInstituicaoFinanceira.setNomeFantasia(instituicaoFinanceira.getNomeFantasia());
            persistedInstituicaoFinanceira.setRazaoSocial(instituicaoFinanceira.getRazaoSocial());
        }

        return persistedInstituicaoFinanceira;
    }

    public Leilao saveLeilao(Leilao leilao){
        Leilao persistedLeilao = findLeilaoById(leilao.getId());

        if(persistedLeilao == null){
            leilao.setProdutos(new ArrayList<Produto>());
            leilao.setId(++leilaoSequence);
            leiloes.add(leilao);
            persistedLeilao = leilao;
        } else {
            persistedLeilao.setDataInicial(leilao.getDataInicial());
            persistedLeilao.setDataFinal(leilao.getDataFinal());
            persistedLeilao.setDescricao(leilao.getDescricao());
            persistedLeilao.setLeilaoStatus(leilao.getLeilaoStatus());
            persistedLeilao.setLocal(leilao.getLocal());
        }

        return persistedLeilao;
    }

    //Deletar
    public Cliente deleteClienteByCpf(String cpf){
        Cliente persitedCliente = findClienteByCpf(cpf);
        clientes.remove(persitedCliente);
        return persitedCliente;
    }

    public InstituicaoFinanceira deleteInstituicaoFinanceiraByCnpj(String cnpj){
        InstituicaoFinanceira persistedInstituicaoFinanceira = findInstituicaoFinanceiraByCnpj(cnpj);
        instituicoesFinanceiras.remove(persistedInstituicaoFinanceira);
        return persistedInstituicaoFinanceira;
    }

    public Leilao deleteLeilaoById(Long id){
        Leilao persistedLeilao = findLeilaoById(id);
        leiloes.remove(persistedLeilao);
        return persistedLeilao;
    }
}