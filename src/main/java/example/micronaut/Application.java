package example.micronaut;

import io.micronaut.runtime.Micronaut;

import leilao.Leilao;
import tipos.TipoImovel;
import tipos.TipoVeiculo;

import java.util.Date;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);

        Date data = new Date(System.currentTimeMillis());
        Leilao leilao = new Leilao(1, data);

        /* Cadastro de Imovel */
        TipoImovel tipoImovel = TipoImovel.CASA;
        String endereco = "Rua dois";
        Integer cep = 21999;
        System.out.println("|| CADASTRANDO IMOVEL ||");
        leilao.novoImovel(1, 40000.50, TipoImovel.CASA, endereco, cep);
        leilao.novoImovel(2, 7777100.00, TipoImovel.MANSAO, endereco, cep);

        System.out.println("|| CADASTRANDO movel ||");
        leilao.novoVeiculo(1, 35000.0, "CIVIC", TipoVeiculo.CARRO, 2021);
        leilao.novoVeiculo(2, 4000.0, "ASTRA", TipoVeiculo.CARRO, 2019);
        leilao.atualizaVeiculo(2, 50000.0, "FUSCA", TipoVeiculo.CARRO, 2000);
        leilao.removeVeiculo(1);
        leilao.removeImovel(1);
        System.out.println("\n || EXIBINDO IMOVEIS CADASTRADOS ||");
        leilao.exibirTodosProdutos();

    }
}

