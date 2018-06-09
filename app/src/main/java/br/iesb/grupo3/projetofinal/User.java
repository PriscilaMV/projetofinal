package br.iesb.grupo3.projetofinal;

import java.io.Serializable;

/**
 * Created by 1614290077 on 11/05/2018.
 */

public class User implements Serializable {
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String confirmsenha;

    public User(){

    }

    public User(String nome, String email, String senha, String confirmsenha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.confirmsenha = confirmsenha;
    }

    public User(long id, String nome, String email, String senha, String confirmsenha) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.confirmsenha = confirmsenha;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmsenha() {
        return confirmsenha;
    }

    public void setConfirmsenha(String confirmsenha) {
        this.confirmsenha = confirmsenha;
    }

}
