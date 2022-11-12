package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoFinanceira {

    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;

    public InstituicaoFinanceira(String cnpj){
        this.cnpj = cnpj;
    }

}