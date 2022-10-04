package tipos;

public enum TipoVeiculo {
        CARRO("Carro"),
        MOTO("Moto"),
        CAMINHAO("Caminhão");

        public String tipo;
        TipoVeiculo(String tipo){
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }
}
