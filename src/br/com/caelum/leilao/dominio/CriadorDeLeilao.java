package br.com.caelum.leilao.dominio;

public class CriadorDeLeilao {

    private Leilao leilao;


    public CriadorDeLeilao para(String desc){
        this.leilao = new Leilao(desc);
        return this;
    }
    public CriadorDeLeilao lance(Usuario usuario, double valor){
        leilao.propoe(new Lance(usuario,valor));
        return this;
    }

    public Leilao constroi(){
        return leilao;
    }

}
