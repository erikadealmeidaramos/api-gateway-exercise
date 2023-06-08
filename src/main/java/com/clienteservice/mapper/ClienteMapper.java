package com.clienteservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.clienteservice.controller.request.ClienteRequest;
import com.clienteservice.controller.response.ClienteResponse;
import com.clienteservice.domain.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toClienteDomain(ClienteRequest clienteRequest);
    ClienteResponse toClienteResponse(Cliente clienteDomain);
    List<ClienteResponse> map(List<Cliente> clienteResponses);
    List<ClienteResponse> page(Page<Cliente> clienteResponses);
}
