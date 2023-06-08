package com.clienteservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.clienteservice.controller.request.EnderecoRequest;
import com.clienteservice.controller.response.EnderecoResponse;
import com.clienteservice.domain.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    Endereco toEnderecoDomain(EnderecoRequest enderecoRequest);
    EnderecoResponse toEnderecoResponse(Endereco enderecoDomain);
    List<EnderecoResponse> map(List<Endereco> enderecoResponses);
}
