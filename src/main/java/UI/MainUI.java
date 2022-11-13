package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;


import org.json.JSONArray;
import org.json.JSONObject;


public class MainUI {

    private static String uri = "http://localhost:8081";
    private static String cliente = null;
    private static Integer leilaoId = null;

    public static void main(String[] args){
        String command = "";
        Scanner sc = new Scanner(System.in);

        do{
            setScreen("", "Digite uma opção ([1]Cliente, [2]Instituicao-financeira, [3]Leilao, [0]Sair)\n> ");
            
            command = sc.next().toLowerCase();

            switch (command) {
                case "1":
                case "cliente":
                    cliente(sc);
                    break;
                case "2":
                case "instituicao-financeira":
                    instituicaoFinanceira(sc);
                    break;
                case "3":
                case "leilao":
                    leilao(sc);
            }
            
        }while(!command.equals("sair") && !command.equals("0"));

        sc.close();
    }

    // Cliente
    public static void cliente(Scanner sc){
        String command = "";

        do{
            setScreen("Cliente", "Digite uma opção ([1]Consultar, [2]Definir, [3]Excluir, [4]Selecionar, [0]Voltar)\n> ");

            command = sc.next().toLowerCase();

            switch (command) {
                case "1":
                case "consultar":
                    consultarCliente(sc);
                    break;
                case "2":
                case "definir":
                    definirCliente(sc);
                    break;
                case "3":
                case "excluir":
                    excluirCliente(sc);
                    break;
                case "4":
                case "selecionar":
                    selecionarCliente(sc);
                }
        }while(!command.equals("voltar") && !command.equals("0"));
    }

    public static void consultarCliente(Scanner sc){
        setScreen("Cliente :: Consultar", "Lista de clientes:\n");

        String content = getRequest("/cliente");
        JSONObject jsonObj = new JSONObject(content);
        
        String clientesRetorno = "";
        if(jsonObj.has("clientes")){
            JSONArray jsonArray = jsonObj.getJSONArray("clientes");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject cliente = jsonArray.getJSONObject(i);
    
                clientesRetorno += "Cliente " + cliente.getString("cpf");
                clientesRetorno += "\nNome: " + cliente.getString("nome");
                clientesRetorno += "\nE-mail: " + cliente.getString("email");
                clientesRetorno += "\n---------------------\n";
            }
        }

        waitScreen(sc, clientesRetorno);
    }

    public static void definirCliente(Scanner sc){
        setScreen("Cliente :: Adicionar", "Insira os dados do cliente:\n");
        sc.nextLine();

        JSONObject jsonObject = new JSONObject();

        System.out.print("CPF > ");
        jsonObject.append("cpf", sc.next());
        sc.nextLine();

        System.out.print("Nome > ");
        jsonObject.append("nome", sc.nextLine());

        System.out.print("E-Mail > ");
        jsonObject.append("email", sc.nextLine());

        postRequest(jsonObject, "/cliente");
    }

    public static void excluirCliente(Scanner sc){
        setScreen("Cliente :: Excluir", "Insira os CPF do cliente a ser excluído:\n");

        JSONObject jsonObject = new JSONObject();

        System.out.print("CPF > ");
        jsonObject.append("cpf", sc.next());
        sc.nextLine();

        deleteRequest(jsonObject, "/cliente");
    }

    public static void selecionarCliente(Scanner sc){
        setScreen("Cliente :: Selecionar", "Insira o CPF do cliente usando a aplicação:\n");
        
        System.out.print("CPF > ");
        cliente = sc.next();
        sc.nextLine();
    }


    //Instituicao-Financeira
    public static void instituicaoFinanceira(Scanner sc){
        String command = "";

        do{
            setScreen("Instituição Financeira", "Digite uma opção ([1]Consultar, [2]Definir, [3]Excluir, [0]Voltar)\n> ");

            command = sc.next().toLowerCase();

            switch (command) {
                case "1":
                case "consultar":
                    consultarInstituicaoFinanceira(sc);
                    break;
                case "2":
                case "definir":
                    definirInstituicaoFinanceira(sc);
                    break;
                case "3":
                case "excluir":
                    excluirInstituicaoFinanceira(sc);
            }
            
        }while(!command.equals("voltar") && !command.equals("0"));
    }

    public static void consultarInstituicaoFinanceira(Scanner sc){
        setScreen("Instituição Financeira :: Consultar", "Lista de instituições financeiras:\n");

        String content = getRequest("/instituicao-financeira");
        JSONObject jsonObj = new JSONObject(content);

        String instituicoesRetorno = "";
        if(jsonObj.has("instituicoesFinanceiras")){
            JSONArray jsonArray = jsonObj.getJSONArray("instituicoesFinanceiras");

            
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject instituicaoFinanceira = jsonArray.getJSONObject(i);
    
                instituicoesRetorno += "Instituição " + instituicaoFinanceira.getString("nomeFantasia");
                instituicoesRetorno += "\nCNPJ: " + instituicaoFinanceira.getString("cnpj");
                instituicoesRetorno += "\nRazão Social: " + instituicaoFinanceira.getString("razaoSocial");
                instituicoesRetorno += "\n---------------------\n";
            }
        }

        waitScreen(sc, instituicoesRetorno);
    }

    public static void definirInstituicaoFinanceira(Scanner sc){
        setScreen("Instituição Financeira :: Adicionar", "Insira os dados da instituição:\n");
        sc.nextLine();

        JSONObject jsonObject = new JSONObject();

        System.out.print("CNPJ > ");
        jsonObject.append("cnpj", sc.next());
        sc.nextLine();

        System.out.print("Razão Social > ");
        jsonObject.append("razaoSocial", sc.nextLine());

        System.out.print("Nome Fantasia > ");
        jsonObject.append("nomeFantasia", sc.nextLine());

        postRequest(jsonObject, "/instituicao-financeira");
    }

    public static void excluirInstituicaoFinanceira(Scanner sc){
        setScreen("Instituição Financeira :: Excluir", "Insira os CNPJ da instituição a ser excluída:\n");

        JSONObject jsonObject = new JSONObject();

        System.out.print("CNPJ > ");
        jsonObject.append("cnpj", sc.next());
        sc.nextLine();

        deleteRequest(jsonObject, "/instituicao-financeira");
    }


    //Leilão
    public static void leilao(Scanner sc){
        String command = "";

        do{
            setScreen("Leilão", "Digite uma opção ([1]Consultar, [2]Definir, [3]Excluir, [4]Operar, [0]Voltar)\n> ");

            command = sc.next().toLowerCase();

            switch (command) {
                case "1":
                case "consultar":
                    consultarLeilao(sc);
                    break;
                case "2":
                case "definir":
                    definirLeilao(sc);
                    break;
                case "3":
                case "excluir":
                    excluirLeilao(sc);
                    break;
                case "4":
                case "operar":
                    operarLeilao(sc);
            }

        }while(!command.equals("voltar") && !command.equals("0"));
    }

    public static void consultarLeilao(Scanner sc){
        setScreen("Leilão :: Consultar", "Lista de leilões:\n");

        String content = getRequest("/leilao");
        JSONObject jsonObj = new JSONObject(content);

        String leilaoRetorno = "";
        if(jsonObj.has("leiloes")){
            JSONArray jsonArray = jsonObj.getJSONArray("leiloes");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject leilao = jsonArray.getJSONObject(i);
    
                leilaoRetorno += "Leilão " + leilao.getInt("id");
                leilaoRetorno += "\nDescrição: " + leilao.getString("descricao");
                leilaoRetorno += "\nStatus: " + leilao.getString("leilaoStatus");
                leilaoRetorno += "\nData de início: " + new Date(leilao.getLong("dataInicial"));
                leilaoRetorno += "\nData de finalização: " + new Date(leilao.getLong("dataFinal")).toString();
                leilaoRetorno += "\nInstituição Financeira: " + leilao.getJSONObject("instituicaoFinanceira").getString("nomeFantasia");
                leilaoRetorno += "\n---------------------\n";
            }
        }

        waitScreen(sc, leilaoRetorno);
    }

    public static void definirLeilao(Scanner sc){
        setScreen("Leilão :: Adicionar", "Insira os dados do leilão:\n");
        sc.nextLine();

        JSONObject jsonObject = new JSONObject();
        JSONObject instituicaoJSON = new JSONObject();
        JSONObject enderecoJSON = new JSONObject();

        System.out.print("Descrição > ");
        jsonObject.append("descricao", sc.nextLine());
        
        System.out.print("Data de Início > ");
        jsonObject.append("dataInicial", sc.nextLine());

        System.out.print("Data de Finalização > ");
        jsonObject.append("dataFinal", sc.nextLine());

        System.out.print("Local: CEP > ");
        enderecoJSON.append("cep", sc.nextInt());
        
        System.out.print("Local: Número > ");
        enderecoJSON.append("numero", sc.nextInt());
        sc.nextLine();

        System.out.print("Local: Logradouro > ");
        enderecoJSON.append("logradouro", sc.nextLine());

        System.out.print("Local: Cidade > ");
        enderecoJSON.append("cidade", sc.nextLine());

        System.out.print("Local: Estado > ");
        enderecoJSON.append("estado", sc.nextLine());

        System.out.print("Instituição Financeira: CNPJ > ");
        instituicaoJSON.append("cnpj", sc.next());
        sc.nextLine();

        jsonObject.append("instituicaoFinanceira", instituicaoJSON);
        jsonObject.append("local", enderecoJSON);

        postRequest(jsonObject, "/leilao");
    }

    public static void excluirLeilao(Scanner sc){
        setScreen("Leilão :: Excluir", "Insira o id do leilão a ser excluído:\n");

        JSONObject jsonObject = new JSONObject();

        System.out.print("ID > ");
        jsonObject.append("id", sc.next());
        sc.nextLine();

        deleteRequest(jsonObject, "/leilao");
    }

    public static void operarLeilao(Scanner sc){
        setScreen("Leilão :: Selecionar", "Insira o ID do leião:\n");
        
        System.out.print("ID > ");
        leilaoId = sc.nextInt();
        sc.nextLine();

        String content = getRequest("/leilao");
        JSONObject jsonObj = new JSONObject(content);
        if(jsonObj.has("leiloes")){
            JSONArray jsonArray = jsonObj.getJSONArray("leiloes");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject leilao = jsonArray.getJSONObject(i);
                if(leilao.getInt("id") == leilaoId){
                    if(leilao.getString("leilaoStatus").equals("EM_ABERTO"))
                        produto(sc);
                    if(leilao.getString("leilaoStatus").equals("EM_ANDAMENTO"))
                        lance(sc);
                    if(leilao.getString("leilaoStatus").equals("FINALIZAD"))
                        leilaoFinalizado(sc);
                }
            }
        }
    }

    //Produtos
    public static void produto(Scanner sc){
        String command = "";

        do{
            setScreen("Produto", "Digite uma opção ([1]Consultar, [2]Definir, [3]Excluir, [0]Voltar)\n> ");

            command = sc.next().toLowerCase();

            switch (command) {
                case "1":
                case "consultar":
                    consultarProduto(sc);
                    break;
                case "2":
                case "definir":
                    definirProduto(sc);
                    break;
                case "3":
                case "excluir":
                    excluirProduto(sc);
            }

        }while(!command.equals("voltar") && !command.equals("0"));

        leilaoId = null;
    }

    public static void consultarProduto(Scanner sc){
        setScreen("Produto :: Consultar", "Lista de produtos:\n");

        String content = getRequest("/produto?leilaoId=" + leilaoId);
        
        JSONObject jsonObj = new JSONObject(content);

        String produtosRetorno = "";
        if(jsonObj.has("produtos")){
            JSONArray jsonArray = jsonObj.getJSONArray("produtos");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject produto = jsonArray.getJSONObject(i);
    
                produtosRetorno += "Produto " + produto.getInt("id");
                produtosRetorno += "\nNome: " + produto.getString("nome");
                produtosRetorno += "\nDescrição: " + produto.getString("descricao");
                produtosRetorno += "\nValor inicial: " + produto.getFloat("valorInicial");

                if(produto.has("endereco")){
                    
                    produtosRetorno += "\nÁrea Total: " + produto.getFloat("areaTotal");
                    
                    if(produto.has("areaConstruida")){

                        produtosRetorno += "\nÁrea Construída: " + produto.getFloat("areaConstruida");

                        if(produto.has("bloco")){
                            produtosRetorno += "\nBloco: " + produto.getInt("bloco");
                            produtosRetorno += "\nNúmero do Apartamento: " + produto.getInt("numeroApartamento");
                        }

                        if(produto.has("qtdeAndares")) {
                            produtosRetorno += "\nQtde. Andares: " + produto.getInt("qtdeAndares");
                            produtosRetorno += "\nQtde. Salas: " + produto.getInt("qtdeSalas");
                        }
                    }
                } else {

                    produtosRetorno += "\nCondição: " + produto.getString("condicao");
                    produtosRetorno += "\nMarca: " + produto.getString("marca");
                    produtosRetorno += "\nModelo: " + produto.getString("modelo");
                    produtosRetorno += "\nCor: " + produto.getString("cor");

                    if(produto.has("bagageiro")){
                        String bagageiro = "Não";
                        if(produto.getBoolean("bagageiro"))
                            bagageiro = "Sim";
                        produtosRetorno += "\nBagageiro: " + bagageiro;
                    }
                }

                produtosRetorno += "\n---------------------\n";
            }
        }
        waitScreen(sc, produtosRetorno);
    }

    public static void definirProduto(Scanner sc){
        setScreen("Produto :: Definir", "Definir Produto:\n");
        JSONObject produtoObject = new JSONObject();
        String tipo = "";

        sc.nextLine();

        System.out.print("Nome > ");
        produtoObject.append("nome", sc.nextLine());

        System.out.print("Descrição > ");
        produtoObject.append("descricao", sc.nextLine());

        System.out.print("Valor Inicial > ");
        produtoObject.append("valorInicial", sc.nextFloat());

        sc.nextLine();

        System.out.print("Tipo(terreno, casa, edificio comercial, apartamento, carro, motocicleta) > ");
        tipo = sc.nextLine().toLowerCase().trim();

        switch(tipo){
            case "terreno":
                definirTerreno(sc, produtoObject);
                break;
            case "casa":
                definirCasa(sc, produtoObject);
                break;
            case "edificio comercial":
                definirEdificioComercial(sc, produtoObject);
                break;
            case "apartamento":
                definirApartamento(sc, produtoObject);
                break;
            case "carro":
                definirCarro(sc, produtoObject);
                break;
            case "motocicleta":
                definirMotocicleta(sc, produtoObject);
                break;
            default:
                return;
        }
    }

    public static void definirImovel(Scanner sc, JSONObject produtoObject){
        JSONObject enderecoJSON = new JSONObject();

        System.out.print("Área Total > ");
        produtoObject.append("areaTotal", sc.nextInt());

        System.out.print("Endereço: CEP > ");
        enderecoJSON.append("cep", sc.nextInt());
        
        System.out.print("Endereço: Número > ");
        enderecoJSON.append("numero", sc.nextInt());
        sc.nextLine();

        System.out.print("Endereço: Logradouro > ");
        enderecoJSON.append("logradouro", sc.nextLine());

        System.out.print("Endereço: Cidade > ");
        enderecoJSON.append("cidade", sc.nextLine());

        System.out.print("Endereço: Estado > ");
        enderecoJSON.append("estado", sc.nextLine());

        produtoObject.append("endereco", enderecoJSON);
    }

    public static void definirVeiculo(Scanner sc, JSONObject produtoObject){
        System.out.print("Condição > ");
        produtoObject.append("condicao", sc.nextLine());

        System.out.print("Marca > ");
        produtoObject.append("marca", sc.nextLine());

        System.out.print("Modelo > ");
        produtoObject.append("modelo", sc.nextLine());

        System.out.print("Cor > ");
        produtoObject.append("cor", sc.nextLine());
    }

    public static void definirTerreno(Scanner sc, JSONObject produtoObject){
        definirImovel(sc, produtoObject);
        JSONObject jsonObject = new JSONObject();
        JSONObject leilaoObject = new JSONObject();
        
        leilaoObject.append("id", leilaoId);

        jsonObject.append("casa", produtoObject);
        jsonObject.append("leilao", leilaoObject);

        postRequest(jsonObject, "/produto/terreno");
    }

    public static void definirCasa(Scanner sc, JSONObject produtoObject){
        definirImovel(sc, produtoObject);
        JSONObject jsonObject = new JSONObject();
        JSONObject leilaoObject = new JSONObject();

        System.out.print("Área Construída > ");
        produtoObject.append("areaConstruida", sc.nextFloat());
        
        leilaoObject.append("id", leilaoId);

        jsonObject.append("casa", produtoObject);
        jsonObject.append("leilao", leilaoObject);

        postRequest(jsonObject, "/produto/casa");
    }

    public static void definirEdificioComercial(Scanner sc, JSONObject produtoObject){
        definirImovel(sc, produtoObject);
        JSONObject jsonObject = new JSONObject();
        JSONObject leilaoObject = new JSONObject();

        System.out.print("Área Construída > ");
        produtoObject.append("areaConstruida", sc.nextFloat());

        System.out.print("Quantidade de andares > ");
        produtoObject.append("qtdeAndares", sc.nextInt());

        System.out.print("Quantidade de salas > ");
        produtoObject.append("qtdeSalas", sc.nextInt());
        
        leilaoObject.append("id", leilaoId);

        jsonObject.append("edificio-comercial", produtoObject);
        jsonObject.append("leilao", leilaoObject);

        postRequest(jsonObject, "/produto/edificio-comercial");
    }

    public static void definirApartamento(Scanner sc, JSONObject produtoObject){
        definirImovel(sc, produtoObject);
        JSONObject jsonObject = new JSONObject();
        JSONObject leilaoObject = new JSONObject();

        System.out.print("Área Construída > ");
        produtoObject.append("areaConstruida", sc.nextFloat());

        System.out.print("Bloco > ");
        produtoObject.append("bloco", sc.nextInt());

        System.out.print("Número Apartamento > ");
        produtoObject.append("numeroApartamento", sc.nextInt());
        
        leilaoObject.append("id", leilaoId);

        jsonObject.append("apartamento", produtoObject);
        jsonObject.append("leilao", leilaoObject);

        postRequest(jsonObject, "/produto/apartamento");
    }

    public static void definirCarro(Scanner sc, JSONObject produtoObject){
        definirVeiculo(sc, produtoObject);

        JSONObject jsonObject = new JSONObject();
        JSONObject leilaoObject = new JSONObject();
        
        leilaoObject.append("id", leilaoId);

        jsonObject.append("carro", produtoObject);
        jsonObject.append("leilao", leilaoObject);

        postRequest(jsonObject, "/produto/carro");
    }

    public static void definirMotocicleta(Scanner sc, JSONObject produtoObject){
        definirVeiculo(sc, produtoObject);

        JSONObject jsonObject = new JSONObject();
        JSONObject leilaoObject = new JSONObject();

        System.out.print("Bagageiro(S/N) > ");
        String bagageiro = sc.next().toLowerCase();

        if(bagageiro.equals("s")){
            produtoObject.append("bagageiro", true);
        } else {
            produtoObject.append("bagageiro", false);
        }
        
        leilaoObject.append("id", leilaoId);

        jsonObject.append("motocicleta", produtoObject);
        jsonObject.append("leilao", leilaoObject);

        postRequest(jsonObject, "/produto/motocicleta");
    }

    public static void excluirProduto(Scanner sc){
        setScreen("Produto :: Excluir", "Insira os ID do produto a ser excluído:\n");

        JSONObject jsonObject = new JSONObject();
        JSONObject produtoObject = new JSONObject();
        JSONObject leilaoObject = new JSONObject();

        System.out.print("ID > ");
        produtoObject.append("id", sc.next());
        leilaoObject.append("id", leilaoId);
        sc.nextLine();

        jsonObject.append("produto", produtoObject);
        jsonObject.append("leilao", leilaoObject);

        deleteRequest(jsonObject, "/produto");
    }

    //Lance
    public static void lance(Scanner sc){
        String command = "";

        if(cliente == null)
            selecionarCliente(sc);

        do{
            setScreen("Produto :: Lance", "Digite uma opção ([1]Consultar, [2]Filtrar, [3]Lance, [0]Voltar)\n> ");

            command = sc.next().toLowerCase();

            switch (command) {
                case "1":
                case "consultar":
                    consultarProduto(sc);
                    break;
                case "2":
                case "filtrar":
                    filtrarProduto(sc);
                    break;
                case "3":
                case "lance":
                    realizarLance(sc);
            }
        }while(!command.equals("voltar") && !command.equals("0"));

        leilaoId = null;
    }

    public static void filtrarProduto(Scanner sc){

    }

    public static void realizarLance(Scanner sc){
        setScreen("Produto :: Lance :: Realizar", "Insira o valor o ID do Produto e o valor do Lance\n");
        sc.nextLine();

        JSONObject jsonObject = new JSONObject();
        JSONObject produtoJson = new JSONObject();
        JSONObject leilaoJson = new JSONObject();
        JSONObject clienteJson = new JSONObject();

        System.out.print("Produto : ID > ");
        produtoJson.append("id", sc.nextInt());

        System.out.print("Valor > ");
        jsonObject.append("valor", sc.nextFloat());

        leilaoJson.append("id", leilaoId);
        clienteJson.append("cpf", cliente);

        jsonObject.append("leilao", leilaoJson);
        jsonObject.append("cliente", clienteJson);
        jsonObject.append("produto", produtoJson);

        postRequest(jsonObject, "/cliente");
    }

    //Leilão Finalizado
    public static void leilaoFinalizado(Scanner sc){
        setScreen("Leilão :: Finalizado", "Lista de produtos:\n");

        String content = getRequest("/produto?leilaoId=" + leilaoId);
        
        JSONObject jsonObj = new JSONObject(content);

        String produtosRetorno = "";
        if(jsonObj.has("produtos")){
            JSONArray jsonArray = jsonObj.getJSONArray("produtos");

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject produto = jsonArray.getJSONObject(i);
    
                produtosRetorno += "Produto " + produto.getInt("id");
                produtosRetorno += "\nNome: " + produto.getString("nome");
                produtosRetorno += "\nDescrição: " + produto.getString("descricao");
                produtosRetorno += "\nValor inicial: " + produto.getFloat("valorInicial");

                if(produto.has("endereco")){
                    
                    produtosRetorno += "\nÁrea Total: " + produto.getFloat("areaTotal");
                    
                    if(produto.has("areaConstruida")){

                        produtosRetorno += "\nÁrea Construída: " + produto.getFloat("areaConstruida");

                        if(produto.has("bloco")){
                            produtosRetorno += "\nBloco: " + produto.getInt("bloco");
                            produtosRetorno += "\nNúmero do Apartamento: " + produto.getInt("numeroApartamento");
                        }

                        if(produto.has("qtdeAndares")) {
                            produtosRetorno += "\nQtde. Andares: " + produto.getInt("qtdeAndares");
                            produtosRetorno += "\nQtde. Salas: " + produto.getInt("qtdeSalas");
                        }
                    }
                } else {

                    produtosRetorno += "\nCondição: " + produto.getString("condicao");
                    produtosRetorno += "\nMarca: " + produto.getString("marca");
                    produtosRetorno += "\nModelo: " + produto.getString("modelo");
                    produtosRetorno += "\nCor: " + produto.getString("cor");

                    if(produto.has("bagageiro")){
                        String bagageiro = "Não";
                        if(produto.getBoolean("bagageiro"))
                            bagageiro = "Sim";
                        produtosRetorno += "\nBagageiro: " + bagageiro;
                    }
                }

                if(produto.has("maiorLance")){
                    JSONObject maiorLance = produto.getJSONObject("maiorLance");
                    produtosRetorno += "\nVencedor: " + maiorLance.getJSONObject("cliente").getString("cpf");
                    produtosRetorno += "\nValor vencedor: " + maiorLance.getFloat("valor");
                } else {
                    produtosRetorno += "\nVencedor: N/A";
                }
                    
                produtosRetorno += "\n---------------------\n";
            }
        }
        waitScreen(sc, produtosRetorno);
    }

    //Auxiliares
    public static void setScreen(String context, String menu){
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        String screenText = "";

        if(cliente != null){
            screenText += "Cliente: ";
            screenText += cliente;
            screenText += "\t";
        }

        if(leilaoId != null){
            screenText += "Leilao: ";
            screenText += leilaoId;
        }

        if(!screenText.isEmpty()){
            screenText += "\n";
        }

        screenText += "----- Leilão Online ----- ";

        if(!context.isEmpty()){
            screenText += " :: " + context;
        }

        System.out.println(screenText);
        System.out.print(menu);
    }

    public static void waitScreen(Scanner sc, String content){
        sc.nextLine();
        System.out.println("\n" + content);
        System.out.println("\nAperte enter para sair");
        sc.nextLine();
    }

    public static String getRequest(String path){
        String contentResponse = null;

        try {
            URL url = new URL(uri + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
    
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    
            String inputLine;
            StringBuffer content = new StringBuffer();
    
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
    
            in.close();
            con.disconnect();

            contentResponse = content.toString();

        } catch(Exception e){
            e.printStackTrace();
        }

        return contentResponse;
    }

    public static String postRequest(JSONObject body, String path){
        String contentResponse = null;

        try {
            URL url = new URL(uri + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = body.toString().getBytes("utf-8");
                os.write(input, 0, input.length);			
            }
    
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    
            String inputLine;
            StringBuffer content = new StringBuffer();
    
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
    
            in.close();
            con.disconnect();

            contentResponse = content.toString();

        } catch(Exception e){
            e.printStackTrace();
        }

        return contentResponse;
    }

    public static String deleteRequest(JSONObject body, String path){
        String contentResponse = null;

        try {
            URL url = new URL(uri + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setRequestMethod("DELETE");

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = body.toString().getBytes("utf-8");
                os.write(input, 0, input.length);			
            }
    
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    
            String inputLine;
            StringBuffer content = new StringBuffer();
    
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
    
            in.close();
            con.disconnect();

            contentResponse = content.toString();

        } catch(Exception e){
            e.printStackTrace();
        }

        return contentResponse;
    }

}