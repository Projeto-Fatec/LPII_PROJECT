package enumeration;

public enum VeiculoTipo {

    CARRO("Carro"),
    MOTO("Moto"),
    CAMINHAO("Caminhão");

    public String tipo;

    VeiculoTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
