package tipos;

public enum Veiculos {
        CARRO("Carro"),
        MOTO("Moto"),
        CAMINHAO("Caminhão");

        public String tipo;
        Veiculos(String tipo){
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }
}
