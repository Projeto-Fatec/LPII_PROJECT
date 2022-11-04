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

    private Integer cnpj;
    private String nomeFantasia;

    public InstituicaoFinanceira(Integer cnpj){
        this.cnpj = cnpj;
    }

}