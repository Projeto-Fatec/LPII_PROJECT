package service;

import java.util.List;

import database.BaseDados;
import model.InstituicaoFinanceira;
import jakarta.inject.Inject;

public class InstituicaoFinanceiraService {

    @Inject
    BaseDados baseDados;

    public List<InstituicaoFinanceira> list(){
        return baseDados.findAllInstituicoesFinanceiras();
    }

    public InstituicaoFinanceira get(InstituicaoFinanceira instituicaoFinanceira){
        return baseDados.findInstituicaoFinanceiraByCnpj(instituicaoFinanceira.getCnpj());
    }

    public InstituicaoFinanceira set(InstituicaoFinanceira instituicaoFinanceira){
        return baseDados.saveInstituicaoFinanceira(instituicaoFinanceira);
    }

    public InstituicaoFinanceira remove(InstituicaoFinanceira instituicaoFinanceira){
        return baseDados.deleteInstituicaoFinanceiraByCnpj(instituicaoFinanceira.getCnpj());
    }
}