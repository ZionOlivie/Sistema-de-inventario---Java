package br.com.inventario.modelo;

public enum Categoria {
    ELETRONICOS("eletrônicos"),
    ALIMENTOS("alimentos"),
    ESCRITORIO("escritório"),
    OUTROS("outros");

    private String nomeExibicao;

    Categoria(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}