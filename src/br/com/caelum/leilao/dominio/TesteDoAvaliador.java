package br.com.caelum.leilao.dominio;

import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;



public class TesteDoAvaliador {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @Before
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
         this.joao = new Usuario("Joao");
         this.jose = new Usuario("José");
         this.maria = new Usuario("Maria");
    }
    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        //parte 1 cenario
        Leilao leilao = new CriadorDeLeilao().para("Playstatio 3")
                .lance(joao,100)
                .lance(maria,200)
                .lance(jose,400)
                .constroi();

        //parte 2: acao
        leiloeiro.avalia(leilao);

        //parte 3: validacao
        double maiorEsperado = 400;
        double menorEsperado = 100;

        assertEquals(maiorEsperado,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(menorEsperado,leiloeiro.getMenorLance(),0.00001);
        assertThat(leiloeiro.getMenorLance(),equalTo(menorEsperado));
    }

    @Test
    public void deveEntenderComApenasUm(){
        //parte 1 cenario
        Leilao leilao = new CriadorDeLeilao().para("Playstatio 3")
                .lance(joao,1000)
                .constroi();
        //parte 2: acao
        leiloeiro.avalia(leilao);

        //parte 3 validacao

        assertEquals(1000,leiloeiro.getMaiorLance(),0.00001);
        assertEquals(1000,leiloeiro.getMenorLance(),0.00001);

    }

    @Test
    public void os3Maiores(){
        //parte 1 cenario
        Leilao leilao = new CriadorDeLeilao().para("Playstatio 3")
                .lance(joao,100)
                .lance(maria,200)
                .lance(joao,300)
                .lance(maria,400)
                .constroi();


        //parte 2: acao
        leiloeiro.avalia(leilao);

        List<Lance> maiores =  leiloeiro.get3Maiores();

        //parte 3 validacao
        assertEquals(3,maiores.size());
        assertEquals(400,maiores.get(0).getValor(),0.0001);
        assertEquals(300,maiores.get(1).getValor(),0.0001);
        assertEquals(200,maiores.get(2).getValor(),0.0001);

    }
    @Test
    public void emOrdemRandomica() {
        //parte 1 cenario
        Leilao leilao = new CriadorDeLeilao().para("Playstatio 3")
                .lance(joao,200.0)
                .lance(maria,450.0)
                .lance(joao,120.0)
                .lance(maria,700.0)
                .lance(joao,630.0)
                .lance(maria,230.0)
                .constroi();


        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }
    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarSemLanceDado(){
            Leilao leilao = new CriadorDeLeilao().para("PSP").constroi();
            leiloeiro.avalia(leilao);
    }


}
