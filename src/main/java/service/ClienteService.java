package service;

import java.util.List;

import database.BaseDados;
import model.Cliente;
import jakarta.inject.Inject;

public class ClienteService {

    @Inject
    BaseDados baseDados;

    public List<Cliente> list(){
        return baseDados.findAllClientes();
    }

    public Cliente get(Cliente cliente){
        return baseDados.findClienteByCpf(cliente.getCpf());
    }

    public Cliente set(Cliente cliente){
        return baseDados.saveCliente(cliente);
    }

    public Cliente remove(Cliente cliente){
        return baseDados.deleteClienteByCpf(cliente.getCpf());
    }
}