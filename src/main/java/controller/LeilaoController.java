package controller;

import java.util.ArrayList;
import java.util.List;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import model.Leilao;
import service.LeilaoService;

@Getter
@Setter
class LeilaoList{
    List<Leilao> leiloes = new ArrayList<Leilao>();
}

@Controller("/leilao")
public class LeilaoController {

    @Inject
    private LeilaoService leilaoService;

    @Get
    public LeilaoList getLeiloes(){
        LeilaoList leilaoList = new LeilaoList();
        leilaoList.setLeiloes(leilaoService.list());
        return leilaoList;
    }

    @Post
    public Leilao postLeilao(Leilao leilao){
        return leilaoService.set(leilao);
    }

    @Delete
    public Leilao deleteLeilao(Leilao leilao){
        return leilaoService.remove(leilao);
    }

}