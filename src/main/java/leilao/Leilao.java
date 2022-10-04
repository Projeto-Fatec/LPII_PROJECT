package leilao;

import lombok.Getter;
import lombok.Setter;
import produtos.Imovel;
import produtos.Veiculo;
import tipos.TipoImovel;
import tipos.TipoVeiculo;


import java.util.*;

@Getter
@Setter
public class Leilao {
    private Integer idLeilao;
    private Date data;
    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Imovel> imoveis = new ArrayList<>();

    public Leilao(Integer id, Date data){
        this.idLeilao = id;
        this.data = data;
    }

    public void novoVeiculo(
            Integer id, Double value,
            String modelo, TipoVeiculo tipoVeiculo,
            Integer ano
    ){
        Veiculo veiculo = new Veiculo(id, value, idLeilao, tipoVeiculo, modelo, ano);
        veiculos.add(veiculo);
        System.out.println(veiculo.getTipoVeiculo().getTipo() + " foi cadastrado");
    }

    public void removeVeiculo(Integer id){
        Iterator<Veiculo> iterator = veiculos.iterator();
        boolean stop = false;
        while (iterator.hasNext() && !stop){
            Veiculo veiculo = iterator.next();
            if (veiculo.getId().equals(id)){
                iterator.remove();
                stop = true;
            }
        }
    }

    public void removeImovel(Integer id){
        Iterator<Imovel> iterator = imoveis.iterator();
        boolean stop = false;
        while (iterator.hasNext() && !stop){
            Imovel imovel = iterator.next();
            if (imovel.getId().equals(id)){
                iterator.remove();
                stop = true;
            }
        }
    }

    public void atualizaVeiculo(
            Integer id, Double valor,
            String modelo, TipoVeiculo tipoVeiculo,
            Integer ano
    ){
        Iterator<Veiculo> iterator = veiculos.iterator();
        boolean stop = false;
        while (iterator.hasNext() && !stop){
            Veiculo veiculo = iterator.next();
            if (veiculo.getId().equals(id)){
                if (valor!=null){
                    veiculo.setValor(valor);
                }
                if (valor!=null){
                    veiculo.setModelo(modelo);
                }
                if (tipoVeiculo!=null){
                    veiculo.setTipoVeiculo(tipoVeiculo);
                }
                if (ano!=null){
                    veiculo.setAno(ano);
                }
                stop = true;
            }
        }
    }

    public void atualizaImovel(
            Integer id, Double valor,
            TipoImovel tipoImovel,
            String endereco, Integer cep
    ){
        Iterator<Imovel> iterator = imoveis.iterator();
        boolean stop = false;
    }

    public void novoImovel(
            Integer id, Double valor,
            TipoImovel tipoImovel,
            String endereco, Integer cep
    ){
        Imovel imovel = new Imovel(id, valor, idLeilao, endereco, tipoImovel, cep);
        imoveis.add(imovel);
        System.out.println(imovel.getTipoImovel().getTipo() + " foi cadastrado");
    }



    public void exibirTodosProdutos(){
        imoveis.forEach(imovel ->  System.out.println(imovel.getTipoImovel().getTipo()));
        veiculos.forEach(veiculo -> System.out.println(veiculo.getTipoVeiculo().getTipo()));
    }

}


