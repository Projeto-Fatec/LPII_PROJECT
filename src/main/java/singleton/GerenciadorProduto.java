package singleton;

import java.util.ArrayList;
import java.util.List;

import model.Produto;
import jakarta.inject.Singleton;

@Singleton
public class GerenciadorProduto {
    
    private List<Produto> produtos = new ArrayList<Produto>(); 
    private Long produtoSequence = 0l;

    public Produto addProduto(Produto produto){
        produto.setId(++produtoSequence);
        produtos.add(produto);
        return produto;
    }

}
