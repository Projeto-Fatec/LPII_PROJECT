package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Leilao {

    private List<Produto> produtos;

    private String descricao;
    private Date data;
    private InstituicaoFinanceira instituicaoFinanceira;

    public Leilao(Date data, InstituicaoFinanceira instituicaoFinanceira){
        produtos = new ArrayList<Produto>();

        this.instituicaoFinanceira = instituicaoFinanceira;
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto) {
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
