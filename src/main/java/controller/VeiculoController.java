package controller;

import model.Leilao;
import model.Produto;
import model.veiculo.Carro;
import model.veiculo.Motocicleta;
import service.ProdutoService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/veiculo")
public class VeiculoController {

    @Inject
    ProdutoService produtoService;

    @Post("/carro")
    public Produto postCarro(Carro carro, Leilao leilao){
        return produtoService.addByLeilao(carro, leilao);
    }

    @Post("/motocicleta")
    public Produto postMotocicleta(Motocicleta motocicleta, Leilao leilao){
        return produtoService.addByLeilao(motocicleta, leilao);
    }

}