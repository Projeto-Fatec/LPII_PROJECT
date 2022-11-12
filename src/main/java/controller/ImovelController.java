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

<<<<<<< HEAD
}
=======
    @Post
    public Imovel addImovel(String nome, String descricao, Double valorInicial, String imovelTipo, Long leilaoId){
        Imovel imovel = new Imovel(nome, descricao, valorInicial, imovelTipo);
        Leilao leilao = new Leilao(leilaoId);
        return imovelService.addImovel(imovel, leilao);
    }
}
>>>>>>> a9186a67aedca6356d9878150c3904fb077679a9
