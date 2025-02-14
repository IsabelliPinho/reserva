package model;

public class Usuario {
    private String nome;
    private String senha;
    private String role; // Papel do usuário (GERENTE ou VENDEDOR)

    public Usuario(String nome, String senha, String role) {
        this.nome = nome;
        this.senha = senha;
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getRole() {
        return role;
    }

    public boolean autenticar(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }
}
