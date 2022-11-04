package controller;

import model.InstituicaoFinanceira;
import service.InstituicaoFinanceiraService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

@Controller("/instituicao-financeira")
public class InstituicaoFinanceiraController {

    @Inject
    InstituicaoFinanceiraService instituicaoFinanceiraService;

    @Get
    public List<InstituicaoFinanceira> getInstituicaoFinanceira(){
        return instituicaoFinanceiraService.listInstituicaoFinanceira();
    }

    @Post
    public InstituicaoFinanceira addInstituicaoFinanceira(Integer cnpj, String nomeFantasia){
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira(cnpj, nomeFantasia);
        return instituicaoFinanceiraService.addInstituicaoFinanceira(instituicaoFinanceira);
    }

    @Put
    public InstituicaoFinanceira setInstituicaoFinanceira(Integer cnpj, String nomeFantasia){
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira(cnpj, nomeFantasia);
        return instituicaoFinanceiraService.setInstituicaoFinanceira(instituicaoFinanceira);
    }

    @Delete
    public InstituicaoFinanceira delInstituicaoFinanceira(Integer cnpj){
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira(cnpj);
        return instituicaoFinanceiraService.delInstituicaoFinanceira(instituicaoFinanceira);
    }
}