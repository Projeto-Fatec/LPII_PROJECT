package controller;

import model.Imovel;
import model.Leilao;
import service.ImovelService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/imovel")
public class ImovelController {


    @Inject
    ImovelService imovelService;


    @Post
    public Imovel addImovel(String nome, String descricao, Double valorInicial, String imovelTipo, Long leilaoId){
        Imovel imovel = new Imovel(nome, descricao, valorInicial, imovelTipo);
        Leilao leilao = new Leilao(leilaoId);
        return imovelService.addImovel(imovel, leilao);
    } }
}