package tipos;

public enum Imoveis {
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

    public String tipo;
    Imove(String tipo){

        this.tipo = tipo;
    }

    public String getTipo(){

        return tipo;
    }

}

