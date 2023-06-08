package com.clienteservice.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteResponse {

    private String codigo;
    private String nome;

    public ClienteResponse(ClienteResponse clienteResponse) {
        this.codigo = clienteResponse.getCodigo();
        this.nome = clienteResponse.getNome();
    }
    public ClienteResponse() {
        this.codigo = "";
        this.nome = "";
    }
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
