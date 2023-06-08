package com.clienteservice.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnderecoResponse {
    private String codigo;
    private int indice;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

    public EnderecoResponse() {
        this.codigo = "";
        this.indice = 0;
        this.logradouro = "";
        this.numero = "";
        this.complemento = "";
        this.cidade = "";
        this.estado = "";
        this.cep = "";
    }

    public EnderecoResponse(EnderecoResponse enderecoResponse) {
        this.codigo = enderecoResponse.getCodigo();
        this.indice = enderecoResponse.getIndice();
        this.logradouro = enderecoResponse.getLogradouro();
        this.numero = enderecoResponse.getNumero();
        this.complemento = enderecoResponse.getComplemento();
        this.cidade = enderecoResponse.getCidade();
        this.estado = enderecoResponse.getEstado();
        this.cep = enderecoResponse.getCep();
    }
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public int getIndice() {
        return indice;
    }
    public void setIndice(int indice) {
        this.indice = indice;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    
}
