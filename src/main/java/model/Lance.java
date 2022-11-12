package model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lance {

    private Cliente cliente;
    private Double valor;
    private Date data;  
    
    public Lance(Cliente cliente, Double valor){
        this.cliente = cliente;
        this.valor = valor;
        this.data = new Date();
    }
}