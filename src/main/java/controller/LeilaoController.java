package controller;

import java.util.Date;
import java.util.List;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import model.InstituicaoFinanceira;
import model.Leilao;
import singleton.GerenciadorInstituicaoFinanceira;
import singleton.GerenciadorLeilao;


@Controller("/leilao")
public class LeilaoController {

    @Get
    public List<Leilao> getLeiloes(){
        return GerenciadorLeilao.getInstance().getLeiloes();
    }

    @Post
    public void addLeilao(Date data, Integer cnpj){
        InstituicaoFinanceira instituicaoFinanceira = GerenciadorInstituicaoFinanceira.getInstance().getInstituicaoFinanceira(cnpj);

        if(instituicaoFinanceira == null) {
            //throw new
        }

        Leilao novoLeilao = new Leilao(data, instituicaoFinanceira);
        GerenciadorLeilao.getInstance().addLeilao(novoLeilao);
    }

}
