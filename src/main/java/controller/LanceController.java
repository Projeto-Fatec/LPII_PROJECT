package controller;

import model.Lance;
import model.Leilao;
import model.Produto;
import service.ProdutoService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/lance")
public class LanceController {

    @Inject
    ProdutoService produtoService;

    @Post
    public Lance postLance(Lance lance, Produto produto, Leilao leilao){
        return produtoService.addLanceByProdutoLeilao(lance, produto, leilao);
    }

}