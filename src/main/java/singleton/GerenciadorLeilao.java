package singleton;

import java.util.ArrayList;
import java.util.List;

import model.Leilao;

public class GerenciadorLeilao {

    private static GerenciadorLeilao instance;

    private List<Leilao> leiloes;

    private GerenciadorLeilao(){
        this.leiloes = new ArrayList<Leilao>();
    }

    public static GerenciadorLeilao getInstance(){
        if(instance == null){
            instance = new GerenciadorLeilao();
        }

        return instance;
    }

    public List<Leilao> getLeiloes(){
        return leiloes;
    }

    public void addLeilao(Leilao leilao){
        leiloes.add(leilao);
    }
}
