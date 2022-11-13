package controller;

import model.Leilao;
import model.Produto;
import model.imovel.Apartamento;
import model.imovel.Casa;
import model.imovel.EdificioComercial;
import model.imovel.Terreno;
import service.ProdutoService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/imovel")
public class ImovelController {

    @Inject
    ProdutoService produtoService;

    @Post("/terreno")
    public Produto postTerreno(Terreno terreno, Leilao leilao){
        return produtoService.addByLeilao(terreno, leilao);
    }

    @Post("/apartamento")
    public Produto postApartamento(Apartamento apartamento, Leilao leilao){
        return produtoService.addByLeilao(apartamento, leilao);
    }

    @Post("/casa")
    public Produto postCasa(Casa casa, Leilao leilao){
        return produtoService.addByLeilao(casa, leilao);
    }

    @Post("/edificio-comercial")
    public Produto postEdificioComercial(EdificioComercial edificioComercial, Leilao leilao){
        return produtoService.addByLeilao(edificioComercial, leilao);
    }
}