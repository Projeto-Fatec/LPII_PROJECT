package singleton;

import java.util.ArrayList;
import java.util.List;

import model.Leilao;

import jakarta.inject.Singleton;
@Singleton
public class GerenciadorLeilao {

    private Long leilaoSequence = 0l;

    private List<Leilao> leiloes = new ArrayList<Leilao>();

    public List<Leilao> getLeiloes(){
        return leiloes;
    }

    public Leilao getLeilao(Leilao leilao){
        for(Leilao l: leiloes){
            if(l.getId().equals(leilao.getId())){
                return l;
            }
        }

        return null;
    }

    public Leilao addLeilao(Leilao leilao){
        leilao.setId(++leilaoSequence);
        leiloes.add(leilao);
        
        return leilao;
    }
}
