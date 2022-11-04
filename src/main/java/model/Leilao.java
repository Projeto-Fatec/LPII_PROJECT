package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import numeration.LeilaoStatus;

@Getter
@Setter
@NoArgsConstructor
public class Leilao {

    private Long id;
    private String descricao;
    private Date dataInicial;
    private Date dataFinal;
    private InstituicaoFinanceira instituicaoFinanceira;

    private LeilaoStatus leilaoStatus;
    private List<Produto> produtos;

    public Leilao(Date dataInicial, Date dataFinal, InstituicaoFinanceira instituicaoFinanceira){
        produtos = new ArrayList<Produto>();

        this.instituicaoFinanceira = instituicaoFinanceira;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;

        updateLeilaoStatus();
    }

    public Leilao(Long id){
        this.id = id;
    }

    public void updateLeilaoStatus(){
        Date now = new Date();

        if(now.after(dataFinal)){
            leilaoStatus = LeilaoStatus.FECHADO;
            return;
        }

        if(now.before(dataInicial)){
            leilaoStatus = LeilaoStatus.EM_ABERTO;
            return;
        }

        leilaoStatus = LeilaoStatus.EM_ANDAMENTO;
    }
}    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public InstituicaoFinanceira getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
