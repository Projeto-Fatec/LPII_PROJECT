package produtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Produto {
    public Integer id;
    public Double valor;
    public Produto (Integer id, Double valor){
        this.id = id;
        this.valor = valor;

    }


}

