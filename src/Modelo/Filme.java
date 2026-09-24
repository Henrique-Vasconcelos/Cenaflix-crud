package Modelo;

import java.time.LocalDate;

public class Filme {
    private int id;
    private String nome;
    private LocalDate dataLancamento;
    private String categoria;
    
    public Filme(){}
    
    public Filme(String nome, LocalDate dataLancamento, String categoria){
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}

