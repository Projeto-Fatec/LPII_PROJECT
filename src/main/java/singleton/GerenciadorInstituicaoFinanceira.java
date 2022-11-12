package singleton;

import java.util.ArrayList;
import java.util.List;

import model.InstituicaoFinanceira;

import jakarta.inject.Singleton;
@Singleton
public class GerenciadorInstituicaoFinanceira {
    
    private List<InstituicaoFinanceira> instituicoesFinanceiras = new ArrayList<InstituicaoFinanceira>();

    public List<InstituicaoFinanceira> getInstituicoesFinanceiras(){
        return instituicoesFinanceiras;
    }

    public InstituicaoFinanceira getInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira) {
        for(InstituicaoFinanceira i: instituicoesFinanceiras){
            if(i.getCnpj().equals(instituicaoFinanceira.getCnpj()))
                return i;
        }

        return null;
    }

    public InstituicaoFinanceira setInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        InstituicaoFinanceira oldInstituicaoFinanceira = getInstituicaoFinanceira(instituicaoFinanceira);
        oldInstituicaoFinanceira.setNomeFantasia(instituicaoFinanceira.getNomeFantasia());
        return oldInstituicaoFinanceira;
    }

    public InstituicaoFinanceira addInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        instituicoesFinanceiras.add(instituicaoFinanceira);
        return instituicaoFinanceira;
    }

    public InstituicaoFinanceira delInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        InstituicaoFinanceira oldInstituicaoFinanceira = getInstituicaoFinanceira(instituicaoFinanceira);
        instituicoesFinanceiras.remove(oldInstituicaoFinanceira);
        return oldInstituicaoFinanceira;
    }
    
}