package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import type.LeilaoStatus;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leilao {

    private Long id;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;
    private Endereco local;
    private InstituicaoFinanceira instituicaoFinanceira;

    private LeilaoStatus leilaoStatus;
    private List<Produto> produtos;

    public Leilao(Date dataInicial, Date dataFinal, InstituicaoFinanceira instituicaoFinanceira, Endereco local){
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.local = local;

        this.leilaoStatus = LeilaoStatus.EM_ABERTO;

        this.produtos = new ArrayList<Produto>();
    }
}