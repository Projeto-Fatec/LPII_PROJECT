package controller;

import java.util.ArrayList;
import java.util.List;

import model.Leilao;
import model.Produto;
import service.ProdutoService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ProdutoList{
    List<Produto> produtos = new ArrayList<Produto>();
}

@Controller("/produto")
public class ProdutoController {

    @Inject
    private ProdutoService produtoService;

    @Get
    public ProdutoList getProdutosByLeilao(Long leilaoId, String tipo, Double max, Double min, String search){
        Leilao leilao = new Leilao();
        leilao.setId(leilaoId);

        List<Produto> produtos = produtoService.listByLeilao(leilao);

        if(tipo != null){
            produtos = produtoService.filterType(produtos, tipo);
        }

        if(search != null){
            produtos = produtoService.filterSearch(produtos, search);
        }

        if(min != null && max != null){
            produtos = produtoService.filterPrice(produtos, max, min);
        }

        ProdutoList produtoList = new ProdutoList();
        produtoList.setProdutos(produtos);

        return produtoList;
    }

    @Delete
    public Produto deleteProdutoByLeilao(Produto produto, Leilao leilao){
        return produtoService.removeByLeilao(produto, leilao);
    }

}