package com.clienteservice.controller.request;

public class ClienteRequest {

    private String codigo;
    private String nome;

    public ClienteRequest(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    public ClienteRequest() {
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
