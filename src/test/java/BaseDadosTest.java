import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import database.BaseDados;
import model.Cliente;
import model.InstituicaoFinanceira;

public class BaseDadosTest {

    @Test
    public void baseDadosPersistCliente_thenRecover(){
        Cliente cliente = new Cliente();
        cliente.setCpf("123");
        cliente.setEmail("teste@teste.com");
        cliente.setNome("Testando da Silva");

        BaseDados baseDados = new BaseDados();

        baseDados.saveCliente(cliente);

        Cliente recovered = baseDados.findClienteByCpf(cliente.getCpf());

        assertEquals(cliente.getCpf(), recovered.getCpf());
        assertEquals(cliente.getNome(), recovered.getNome());
        assertEquals(cliente.getEmail(), recovered.getEmail());
    }

    @Test
    public void baseDadosPersistCliente_thenDelete(){
        Cliente cliente = new Cliente();
        cliente.setCpf("123");
        cliente.setEmail("teste@teste.com");
        cliente.setNome("Testando da Silva");

        BaseDados baseDados = new BaseDados();

        baseDados.saveCliente(cliente);

        Cliente recovered = baseDados.deleteClienteByCpf(cliente.getCpf());

        assertEquals(cliente.getCpf(), recovered.getCpf());
        assertEquals(cliente.getNome(), recovered.getNome());
        assertEquals(cliente.getEmail(), recovered.getEmail());
        assertNull(baseDados.findClienteByCpf(cliente.getCpf()));
    }

    @Test
    public void baseDadosPersistCliente_thenUpdate(){
        Cliente cliente = new Cliente();
        BaseDados baseDados = new BaseDados();
        Cliente recovered = new Cliente();

        cliente.setCpf("123");
        cliente.setEmail("teste@teste.com");
        cliente.setNome("Testando da Silva");

        baseDados.saveCliente(cliente);
        recovered = baseDados.findClienteByCpf(cliente.getCpf());

        assertEquals(cliente.getNome(), recovered.getNome());

        cliente.setNome("Testandinho");
        baseDados.saveCliente(cliente);
        recovered = baseDados.findClienteByCpf(cliente.getCpf());

        assertEquals(cliente.getNome(), recovered.getNome());
    }

    @Test
    public void baseDadosPersistInstituicaoFinanceira_thenRecover(){
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira();
        InstituicaoFinanceira recovered = new InstituicaoFinanceira();
        BaseDados baseDados = new BaseDados();

        instituicaoFinanceira.setCnpj("123");
        instituicaoFinanceira.setNomeFantasia("Fanstástica fábrica de titãs");
        instituicaoFinanceira.setRazaoSocial("Gigantosma SA");

        baseDados.saveInstituicaoFinanceira(instituicaoFinanceira);
        recovered = baseDados.findInstituicaoFinanceiraByCnpj(instituicaoFinanceira.getCnpj());

        assertEquals(instituicaoFinanceira.getCnpj(), recovered.getCnpj());
        assertEquals(instituicaoFinanceira.getNomeFantasia(), recovered.getNomeFantasia());
        assertEquals(instituicaoFinanceira.getRazaoSocial(), recovered.getRazaoSocial());
    }

    @Test
    public void baseDadosPersistInstituicaoFinanceira_thenDelete(){
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira();
        InstituicaoFinanceira recovered = new InstituicaoFinanceira();
        BaseDados baseDados = new BaseDados();

        instituicaoFinanceira.setCnpj("123");
        instituicaoFinanceira.setNomeFantasia("Fanstástica fábrica de titãs");
        instituicaoFinanceira.setRazaoSocial("Gigantosma SA");

        baseDados.saveInstituicaoFinanceira(instituicaoFinanceira);
        recovered = baseDados.deleteInstituicaoFinanceiraByCnpj(instituicaoFinanceira.getCnpj());

        assertEquals(instituicaoFinanceira.getCnpj(), recovered.getCnpj());
        assertEquals(instituicaoFinanceira.getNomeFantasia(), recovered.getNomeFantasia());
        assertEquals(instituicaoFinanceira.getRazaoSocial(), recovered.getRazaoSocial());
        assertNull(baseDados.findInstituicaoFinanceiraByCnpj(instituicaoFinanceira.getCnpj()));
    }

    @Test
    public void baseDadosPersistInstituicaoFinanceira_thenUpdate(){
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira();
        InstituicaoFinanceira recovered = new InstituicaoFinanceira();
        BaseDados baseDados = new BaseDados();

        instituicaoFinanceira.setCnpj("123");
        instituicaoFinanceira.setNomeFantasia("Fanstástica fábrica de titãs");
        instituicaoFinanceira.setRazaoSocial("Gigantosma SA");

        baseDados.saveInstituicaoFinanceira(instituicaoFinanceira);
        instituicaoFinanceira.setNomeFantasia("Testandinho");
        baseDados.saveInstituicaoFinanceira(instituicaoFinanceira);
        recovered = baseDados.findInstituicaoFinanceiraByCnpj(instituicaoFinanceira.getCnpj());

        assertEquals(instituicaoFinanceira.getNomeFantasia(), recovered.getNomeFantasia());
    }

    @Test
    public void baseDadosPersistLeilao_thenRecover(){

    }

}