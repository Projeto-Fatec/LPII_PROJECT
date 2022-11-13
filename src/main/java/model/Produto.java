package model;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    protected Integer id;
    protected String nome;
    protected String descricao;
    protected Double valorInicial;

    protected Lance maiorLance;
    protected List<Lance> lances;

    public Produto(String nome, String descricao, Double valorInicial){
        this.nome = nome;
        this.descricao = descricao;
        this.valorInicial = valorInicial;

        this.maiorLance = null;
        this.lances = new ArrayList<Lance>();
    }

}