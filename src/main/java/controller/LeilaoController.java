package controller;

import java.util.Date;
import java.util.List;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;
import model.InstituicaoFinanceira;
import model.Leilao;
import service.LeilaoService;


@Controller("/leilao")
public class LeilaoController {

    @Inject
    private LeilaoService leilaoService;

    @Get
    public List<Leilao> getLeiloes(){
        return leilaoService.listLeilao();
    }

    @Post
    public Leilao addLeilao(Date dataInicial, Date dataFinal, Integer cnpj){
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira(cnpj);
        Leilao leilao = new Leilao(dataInicial, dataFinal, instituicaoFinanceira);
        return leilaoService.addLeilao(leilao);
    }

    @Put
    public Leilao setLeilao(Date dataInicial, Date dataFinal){
        return null;
    }

}

    @Post
    public Leilao addLeilao(Date dataInicial, Date dataFinal, Integer cnpj){
        InstituicaoFinanceira instituicaoFinanceira = instituicaoFinanceiraService.getInstituicaoFinanceiraByCNPJ(cnpj);
        Leilao leilao = new Leilao(dataInicial, dataFinal, instituicaoFinanceira);

        return leilaoService.addLeilao(leilao);
    }

}