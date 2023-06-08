package com.clienteservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clienteservice.controller.request.ClienteRequest;
import com.clienteservice.controller.request.EnderecoRequest;
import com.clienteservice.controller.response.ClienteResponse;
import com.clienteservice.controller.response.EnderecoResponse;
import com.clienteservice.domain.Cliente;
import com.clienteservice.domain.Endereco;
import com.clienteservice.exception.NotFoundException;
import com.clienteservice.mapper.ClienteMapper;
import com.clienteservice.mapper.EnderecoMapper;
import com.clienteservice.repository.ClienteRepository;
import com.clienteservice.repository.EnderecoRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository,
    ClienteMapper clienteMapper, EnderecoRepository enderecoRepository,
    EnderecoMapper enderecoMapper){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }

    public ClienteResponse insereCliente(ClienteRequest cliente){

        Cliente clienteResposta = clienteRepository.findByCodigo(cliente.getCodigo());

        if(clienteResposta == null){
            Cliente clienteDomain = this.clienteMapper.toClienteDomain(cliente);
            clienteResposta = clienteRepository.save(clienteDomain);            
        }

        return this.clienteMapper.toClienteResponse(clienteResposta);
    }

    public List<ClienteResponse> recuperaListaClientes(PageRequest pageable){

        return this.clienteMapper.page(clienteRepository.findAll(pageable));
    }

    public ClienteResponse recuperaCliente(String codigo){

        Cliente clienteResposta = this.clienteRepository.findByCodigo(codigo);

        if(clienteResposta == null){
            throw new NotFoundException("Cliente não encontrado com o código "+codigo);
        }

        return this.clienteMapper.toClienteResponse(clienteResposta);
    }

    public EnderecoResponse insereEndereco(String codigoCliente, EnderecoRequest endereco){
        Cliente cliente = clienteRepository.findByCodigo(codigoCliente);

        if(cliente == null){
            throw new NotFoundException("Cliente não encontrado com o código "+codigoCliente);
        }

        Endereco enderecoDomain = this.enderecoMapper.toEnderecoDomain(endereco);
        enderecoDomain.setCliente(cliente);

        Endereco enderecoResposta = this.enderecoRepository.findByCodigo(enderecoDomain.getCodigo());

        if(enderecoResposta == null){
            enderecoResposta = this.enderecoRepository.save(enderecoDomain);
        }

        return this.enderecoMapper.toEnderecoResponse(enderecoResposta);
    }
    
    public List<EnderecoResponse> recuperaEnderecoCliente(String codigoCliente, Pageable pageable){
        Cliente cliente = clienteRepository.findByCodigo(codigoCliente);

        if(cliente == null){
            throw new NotFoundException("Cliente não encontrado com o código "+codigoCliente);
        }

        return this.enderecoMapper.map(this.enderecoRepository.findAllByCliente(cliente, pageable));
    }

    public EnderecoResponse recuperaEnderecoCliente(String codigoCliente, int indice){

        Cliente cliente = clienteRepository.findByCodigo(codigoCliente);

        if(cliente == null){
            throw new NotFoundException("Cliente não encontrado com o código "+codigoCliente);
        }

        Endereco enderecoResposta = this.enderecoRepository.findByClienteAndIndice(cliente, indice);

        if(enderecoResposta == null){
            throw new NotFoundException("Endereço não encontrado com o índice "+indice);
        }

        return this.enderecoMapper.toEnderecoResponse(enderecoResposta);

    }

    public List<EnderecoResponse> recuperaEnderecosClientePorCidade(String codigoCliente, String cidade, Pageable pageable){
        Cliente cliente = clienteRepository.findByCodigo(codigoCliente);

        if(cliente == null){
            throw new NotFoundException("Cliente não encontrado com o código "+codigoCliente);
        }

        return this.enderecoMapper.map(this.enderecoRepository.findByClienteAndCidade(cliente, cidade, pageable));
    }
}
