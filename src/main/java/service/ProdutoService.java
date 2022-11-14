package service;

import java.util.ArrayList;
import java.util.List;

import database.BaseDados;
import model.Cliente;
import model.Lance;
import model.Leilao;
import model.Produto;
import model.imovel.Apartamento;
import model.imovel.Casa;
import model.imovel.EdificioComercial;
import model.imovel.Imovel;
import model.imovel.Terreno;
import model.veiculo.Carro;
import model.veiculo.Motocicleta;
import model.veiculo.Veiculo;
import type.LeilaoStatus;
import jakarta.inject.Inject;

public class ProdutoService {

    @Inject
    private BaseDados baseDados;

    private Integer produtoId = 0;

    private long lanceId = 0;

    public List<Produto> listByLeilao(Leilao leilao){
        leilao = baseDados.findLeilaoById(leilao.getId());
        return leilao.getProdutos();
    }

    public List<Produto> filterPrice(List<Produto> produtos, Double max, Double min){
        List<Produto> filteredProdutos = new ArrayList<Produto>();

        for(Produto p: produtos){
            if(min < p.getValorInicial() && p.getValorInicial() < max)
                filteredProdutos.add(p);
        }

        return filteredProdutos;
    }

    public List<Produto> filterSearch(List<Produto> produtos, String search){
        List<Produto> filteredProdutos = new ArrayList<Produto>();
        search = search.toLowerCase();

        for(Produto p: produtos){
            if(p.getNome().toLowerCase().contains(search) || p.getDescricao().toLowerCase().contains(search))
                filteredProdutos.add(p);
        }

        return filteredProdutos;
    }

    public List<Produto> filterType(List<Produto> produtos, String type){
        List<Produto> filteredProdutos = new ArrayList<Produto>();
        type = type.toLowerCase();

        for(Produto p: produtos){
            if(p instanceof Imovel){
                if(type.equals("terreno") && p instanceof Terreno)
                    filteredProdutos.add(p);
                if(type.equals("casa") && p instanceof Casa)
                    filteredProdutos.add(p);
                if(type.equals("edificioComercial") && p instanceof EdificioComercial)
                    filteredProdutos.add(p);
                if(type.equals("apartamento") && p instanceof Apartamento)
                    filteredProdutos.add(p);
                if(type.equals("apartamento") && p instanceof Apartamento)
                    filteredProdutos.add(p);
            }

            if(p instanceof Veiculo){
                if(type.equals("carro") && p instanceof Carro)
                    filteredProdutos.add(p);
                if(type.equals("motocicleta") && p instanceof Motocicleta)
                    filteredProdutos.add(p);
            }
        }

        return filteredProdutos;
    }

    public Produto addByLeilao(Produto produto, Leilao leilao){
        leilao = baseDados.findLeilaoById(leilao.getId());
        if(leilao.getLeilaoStatus() != LeilaoStatus.EM_ABERTO)
            return null;

        if(produto.getId() == null){
            produto.setId(++produtoId);
            produto.setLances(new ArrayList<Lance>());
            leilao.getProdutos().add(produto);
        }else{
            for(Produto p: leilao.getProdutos()){
                if(p.getId() == produto.getId()){
                    p = produto;
                }
            }
        }

        return produto;
    }

    public Produto removeByLeilao(Produto produto, Leilao leilao){
        leilao = baseDados.findLeilaoById(leilao.getId());
        if(leilao.getLeilaoStatus() != LeilaoStatus.EM_ABERTO)
            return null;

        for(Produto p: leilao.getProdutos()){
            if(p.getId().equals(produto.getId())){
                leilao.getProdutos().remove(p);
                break;
            }
        }

        return produto;
    }

    public Lance addLanceByProdutoLeilao(Lance lance, Produto produto, Leilao leilao){
        leilao = baseDados.findLeilaoById(leilao.getId());

        if(leilao.getLeilaoStatus() != LeilaoStatus.EM_ANDAMENTO){
            return null;
        }

        for(Produto p: leilao.getProdutos()){
            if(p.getId().equals(produto.getId())){
                produto = p;
                break;
            }
        }

        if(lance.getValor() < produto.getValorInicial()){
            return null;
        }

        for(Lance l: produto.getLances()){
            if(l.getValor() >= lance.getValor())
                return null;
        }

        Cliente cliente = baseDados.findClienteByCpf(lance.getCliente().getCpf());
        if(cliente == null){
            return null;
        }

        lance = new Lance(cliente, lance.getValor());
        produto.getLances().add(lance);
        produto.setMaiorLance(lance);

        return lance;
    }
}