import model.Endereco;
import model.InstituicaoFinanceira;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import database.BaseDados;
import model.Leilao;

import java.util.Date;

public class LeilaoTest{

    @Test
    public void IncluirLeilaoTest() {
        Leilao leilao = new Leilao();
        long now = new Date().getTime();
        leilao.setInstituicaoFinanceira(new InstituicaoFinanceira("2123456543"));
        leilao.setDataInicial(new Date(now + 2));
        leilao.setDataFinal(new Date(now + 3));
        leilao.setLocal(new Endereco(12345444,23,"rua um","Morumbi","São Paulo","São Paulo"));

        BaseDados baseDados = new BaseDados();

        baseDados.saveLeilao(leilao);

        Leilao recovered = baseDados.findLeilaoById(leilao.getId());

        assertEquals(leilao.getInstituicaoFinanceira().getCnpj(),recovered.getInstituicaoFinanceira().getCnpj());
        assertEquals(leilao.getInstituicaoFinanceira().getNomeFantasia(),recovered.getInstituicaoFinanceira().getNomeFantasia());
        assertEquals(leilao.getInstituicaoFinanceira().getRazaoSocial(),recovered.getInstituicaoFinanceira().getRazaoSocial());

        assertEquals(leilao.getDataInicial(), recovered.getDataInicial());
        assertEquals(leilao.getDataFinal(), recovered.getDataFinal());

        assertEquals(leilao.getLocal().getCep(),recovered.getLocal().getCep());
        assertEquals(leilao.getLocal().getNumero(),recovered.getLocal().getNumero());
        assertEquals(leilao.getLocal().getBairro(),recovered.getLocal().getBairro());
        assertEquals(leilao.getLocal().getCidade(),recovered.getLocal().getCidade());
        assertEquals(leilao.getLocal().getEstado(),recovered.getLocal().getEstado());
    }

    @Test
    public void LeilaoInexistenteTest() {
        Leilao leilao = new Leilao();

        long now = new Date().getTime();
        leilao.setInstituicaoFinanceira(new InstituicaoFinanceira("2123456543"));
        leilao.setDataInicial(new Date(now + 2));
        leilao.setDataFinal(new Date(now + 3));
        leilao.setLocal(new Endereco(12345444,23,"rua um","Morumbi","São Paulo","São Paulo"));

        BaseDados baseDados = new BaseDados();

        Leilao recovered = baseDados.findLeilaoById(leilao.getId());

        assertNull(recovered);
    }
    @Test
    public void DeletatLeilaoTest() {
        Leilao leilao = new Leilao();
        long now = new Date().getTime();
        leilao.setInstituicaoFinanceira(new InstituicaoFinanceira("2123456543"));
        leilao.setDataInicial(new Date(now + 2));
        leilao.setDataFinal(new Date(now + 3));
        leilao.setLocal(new Endereco(12345444,23,"rua um","Morumbi","São Paulo","São Paulo"));

        BaseDados baseDados = new BaseDados();

        leilao = baseDados.saveLeilao(leilao);
        Leilao recovered = baseDados.deleteLeilaoById(leilao.getId());

        assertEquals(leilao.getInstituicaoFinanceira().getCnpj(),recovered.getInstituicaoFinanceira().getCnpj());
        assertEquals(leilao.getInstituicaoFinanceira().getNomeFantasia(),recovered.getInstituicaoFinanceira().getNomeFantasia());
        assertEquals(leilao.getInstituicaoFinanceira().getRazaoSocial(),recovered.getInstituicaoFinanceira().getRazaoSocial());

        assertEquals(leilao.getDataInicial(), recovered.getDataInicial());
        assertEquals(leilao.getDataFinal(), recovered.getDataFinal());

        assertEquals(leilao.getLocal().getCep(),recovered.getLocal().getCep());
        assertEquals(leilao.getLocal().getNumero(),recovered.getLocal().getNumero());
        assertEquals(leilao.getLocal().getBairro(),recovered.getLocal().getBairro());
        assertEquals(leilao.getLocal().getCidade(),recovered.getLocal().getCidade());
        assertEquals(leilao.getLocal().getEstado(),recovered.getLocal().getEstado());

        recovered = baseDados.findLeilaoById(leilao.getId());
        assertNull(recovered);
    }
}