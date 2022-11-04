package service;

import java.util.List;

import model.InstituicaoFinanceira;
import GerenciadorInstituicaoFinanceira;
import jakarta.inject.Inject;

public class InstituicaoFinanceiraService {

    @Inject
    GerenciadorInstituicaoFinanceira gerenciadorInstituicaoFinanceira;

    public InstituicaoFinanceira getInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        return gerenciadorInstituicaoFinanceira.getInstituicaoFinanceira(instituicaoFinanceira);
    }

    public List<InstituicaoFinanceira> listInstituicaoFinanceira(){
        return gerenciadorInstituicaoFinanceira.getInstituicoesFinanceiras();
    }

    public InstituicaoFinanceira addInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        return gerenciadorInstituicaoFinanceira.addInstituicaoFinanceira(instituicaoFinanceira);
    }

    public InstituicaoFinanceira setInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        return gerenciadorInstituicaoFinanceira.setInstituicaoFinanceira(instituicaoFinanceira);
    }

    public InstituicaoFinanceira delInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        return gerenciadorInstituicaoFinanceira.delInstituicaoFinanceira(instituicaoFinanceira);
    }

}