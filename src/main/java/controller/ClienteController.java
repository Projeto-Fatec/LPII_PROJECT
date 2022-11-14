package controller;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import service.ClienteService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class ClienteList {
    List<Cliente> clientes = new ArrayList<Cliente>();
}

@Controller("/cliente")
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @Get
    public ClienteList getClientes(){
        ClienteList clienteList = new ClienteList();
        clienteList.clientes = clienteService.list();
        return clienteList;
    }

    @Post
    public Cliente postCliente(Cliente cliente){
        return clienteService.set(cliente);
    }

    @Delete
    public Cliente deleteCliente(Cliente cliente){
        return clienteService.remove(cliente);
    }
}