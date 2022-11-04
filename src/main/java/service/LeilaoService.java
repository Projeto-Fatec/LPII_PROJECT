package service;

import java.util.Date;
import java.util.List;

import model.InstituicaoFinanceira;
import model.Leilao;
import model.Produto;
import singleton.GerenciadorLeilao;
import jakarta.inject.Inject;

public class LeilaoService {

    @Inject
    private GerenciadorLeilao gerenciadorLeilao;

    @Inject
    private InstituicaoFinanceiraService instituicaoFinanceiraService;


    public List<Leilao> listLeilao(){
        return gerenciadorLeilao.getLeiloes();
    }

    public Leilao getLeilao(Leilao leilao){
        return gerenciadorLeilao.getLeilao(leilao);
    }

    public Leilao addLeilao(Leilao leilao){
        if(new Date().after(leilao.getDataInicial()) || leilao.getDataInicial().after(leilao.getDataFinal()))
            return null;

        InstituicaoFinanceira instituicaoFinanceira = instituicaoFinanceiraService.getInstituicaoFinanceira(leilao.getInstituicaoFinanceira());

        if(instituicaoFinanceira == null)
            return null;

        leilao.setInstituicaoFinanceira(instituicaoFinanceira);
        return gerenciadorLeilao.addLeilao(leilao);
    }

    public Leilao addProdutoOnLeilao(Produto produto, Leilao leilao){
        leilao = gerenciadorLeilao.getLeilao(leilao);
        leilao.getProdutos().add(produto);
        return leilao;
    }

}
