package model;

public class Usuario {
    private String nome;
    private String senha;
    private String role;

    public Usuario(String nome, String senha, String role) {
        this.nome = nome;
        this.senha = senha;
        this.role = role;
    }

    public boolean autenticar(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return nome + "," + senha + "," + role;
    }
}
