package singleton;

import java.util.ArrayList;
import java.util.List;

import model.InstituicaoFinanceira;

public class GerenciadorInstituicaoFinanceira {

    private static GerenciadorInstituicaoFinanceira instance;

    private List<InstituicaoFinanceira> instituicoesFinanceiras;

    private GerenciadorInstituicaoFinanceira(){
        this.instituicoesFinanceiras = new ArrayList<InstituicaoFinanceira>();
    }

    public static GerenciadorInstituicaoFinanceira getInstance(){
        if(instance == null){
            instance = new GerenciadorInstituicaoFinanceira();
        }

        return instance;
    }

    public List<InstituicaoFinanceira> getInstituicoesFinanceiras(){
        return instituicoesFinanceiras;
    }

    public InstituicaoFinanceira getInstituicaoFinanceira(Integer cnpj) {
        for(InstituicaoFinanceira instituicaoFinanceira: instituicoesFinanceiras){
            if(instituicaoFinanceira.getCnpj().equals(cnpj))
                return instituicaoFinanceira;
        }

        return null;
    }

    public void addInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        instituicoesFinanceiras.add(instituicaoFinanceira);
    }
}
