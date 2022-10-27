package enumeration;

public enum ImovelTipo {

    CASA("Casa"),
    APARTAMENTO("Apartamento"),
    TERRENO("Terreno"),
    SOBRADO("Sobrado"),
    BANGALO("Bangalo"),
    EDICULA("Edicula"),
    LOFT("Loft"),
    QUITINET("Quitinet"),
    MANSAO("Mansão"),
    FLAT("Flat");

    private String tipo;

    ImovelTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){

        return tipo;
    }

}
