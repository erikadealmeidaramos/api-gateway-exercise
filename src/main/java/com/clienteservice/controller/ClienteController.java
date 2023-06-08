package com.clienteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clienteservice.controller.request.ClienteRequest;
import com.clienteservice.controller.request.EnderecoRequest;
import com.clienteservice.controller.response.ClienteResponse;
import com.clienteservice.controller.response.EnderecoResponse;
import com.clienteservice.service.ClienteService;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> postCliente(@RequestBody ClienteRequest cliente) {

        ClienteResponse novoCliente = this.clienteService.insereCliente(cliente);

        return new ResponseEntity<ClienteResponse>(new ClienteResponse(novoCliente), HttpStatus.CREATED);
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClienteResponse>> getListaClientes(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        List<ClienteResponse> listaClientes = this.clienteService.recuperaListaClientes(pageable);

        return new ResponseEntity<List<ClienteResponse>>(listaClientes, HttpStatus.OK);
    }

    @GetMapping(value = "/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClienteResponse> getCliente(@PathVariable("codigo") String codigo) {

        ClienteResponse cliente = this.clienteService.recuperaCliente(codigo);

        return new ResponseEntity<ClienteResponse>(cliente, HttpStatus.OK);
    }

    @PostMapping(value = "/{codigo}/endereco", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EnderecoResponse> postEnderecoCliente(@PathVariable("codigo") String codigoCliente, 
    @RequestBody EnderecoRequest endereco) {

        EnderecoResponse novoEndereco = this.clienteService.insereEndereco(codigoCliente, endereco);

        return new ResponseEntity<EnderecoResponse>(new EnderecoResponse(novoEndereco), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{codigo}/endereco/{indice}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EnderecoResponse> getEnderecoCliente(@PathVariable("codigo") String codigoCliente,
    @PathVariable("indice") int indice) {

        EnderecoResponse endereco = this.clienteService.recuperaEnderecoCliente(codigoCliente, indice);

        return new ResponseEntity<EnderecoResponse>(endereco, HttpStatus.OK);
    }

    @GetMapping(value = "/{codigo}/endereco/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EnderecoResponse>> getEnderecosCliente(@PathVariable("codigo") String codigoCliente,
    @RequestParam(value = "cidade", required = false) String cidade,
    @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        List<EnderecoResponse> listaEnderecos;

        Pageable pageable = PageRequest.of(page, size);

        if(cidade == null)
            listaEnderecos = this.clienteService.recuperaEnderecoCliente(codigoCliente, pageable);
        else
            listaEnderecos = this.clienteService.recuperaEnderecosClientePorCidade(codigoCliente, cidade, pageable);

        return new ResponseEntity<List<EnderecoResponse>>(listaEnderecos, HttpStatus.OK);
    }
    
}
