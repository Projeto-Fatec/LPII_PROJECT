package controller;

import java.util.ArrayList;
import java.util.List;


import model.InstituicaoFinanceira;
import service.InstituicaoFinanceiraService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class InstituicaoFinanceiraList{
    List<InstituicaoFinanceira> instituicoesFinanceiras = new ArrayList<InstituicaoFinanceira>();
}

@Controller("/instituicao-financeira")
public class InstituicaoFinanceiraController {

    @Inject
    InstituicaoFinanceiraService instituicaoFinanceiraService;

    @Get
    public InstituicaoFinanceiraList getInstituicaoFinanceira(){
        InstituicaoFinanceiraList instituicaoFinanceiraList = new InstituicaoFinanceiraList();
        instituicaoFinanceiraList.setInstituicoesFinanceiras(instituicaoFinanceiraService.list());
        return instituicaoFinanceiraList;
    }

    @Post
    public InstituicaoFinanceira postInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        return instituicaoFinanceiraService.set(instituicaoFinanceira);
    }

    @Delete
    public InstituicaoFinanceira deleteInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        return instituicaoFinanceiraService.remove(instituicaoFinanceira);
    }
}