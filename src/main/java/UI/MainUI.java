package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainUI {

    private static String uri = "http://localhost:8082";
    private static String cliente = null;
    
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
            }
        }while(!command.equals("voltar") && !command.equals("0"));
    }

    public static void consultarCliente(Scanner sc){
        setScreen("Cliente :: Consultar", "Lista de clientes:\n");

        String content = getRequest("/cliente");
        JSONObject jsonObj = new JSONObject(content);
        JSONArray jsonArray = jsonObj.getJSONArray("clientes");

        String clientesRetorno = "";
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject cliente = jsonArray.getJSONObject(i);

            clientesRetorno += "Cliente " + cliente.getString("cpf");
            clientesRetorno += "\nNome: " + cliente.getString("nome");
            clientesRetorno += "\nE-mail: " + cliente.getString("email");
            clientesRetorno += "\n---------------------\n";
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

    public static void selecionaCliente(Scanner sc){
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
        JSONArray jsonArray = jsonObj.getJSONArray("instituicoesFinanceiras");

        String instituicoesRetorno = "";
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject instituicaoFinanceira = jsonArray.getJSONObject(i);

            instituicoesRetorno += "Instituição " + instituicaoFinanceira.getString("nomeFantasia");
            instituicoesRetorno += "\nCNPJ: " + instituicaoFinanceira.getString("cnpj");
            instituicoesRetorno += "\nRazão Social: " + instituicaoFinanceira.getString("razaoSocial");
            instituicoesRetorno += "\n---------------------\n";
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
        setScreen("Instituição Financeira :: Excluir", "Insira os CNOJ da instituição a ser excluída:\n");

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
            

        }while(!command.equals("voltar"));
    }


    //Auxiliares
    public static void setScreen(String context, String menu){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        if(!context.isEmpty()){
            context = " :: " + context;
        }
        System.out.println("----- Leilão Online -----" + context);
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