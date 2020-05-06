package br.com.caelum.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    @Test
    public void deveReceberUmLance(){
        Leilao leilao = new Leilao("Macbook Pro");
        assertEquals(0,leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Steve Jobs"),200));
        assertEquals(1,leilao.getLances().size());

        assertEquals(200.0,leilao.getLances().get(0).getValor(),0.00001);
    }



    @Test
    public void deveReceberDoisLances(){
        Leilao leilao = new Leilao("Macbook Pro");
        assertEquals(0,leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Steve Jobs"),200));
        leilao.propoe(new Lance(new Usuario("Steve Woznik"),300));
        assertEquals(2,leilao.getLances().size());

        assertEquals(200.0,leilao.getLances().get(0).getValor(),0.00001);
        assertEquals(300.0,leilao.getLances().get(1).getValor(),0.00001);
    }

    @Test
    public void naoDeveAceitar2LancesSeguidosDoMesmoUser(){
        Leilao leilao = new Leilao("Xbox One");

        leilao.propoe(new Lance(new Usuario("Steve Jobs"),200));
        leilao.propoe(new Lance(new Usuario("Steve Jobs"),300));

        assertEquals(1,leilao.getLances().size());
        assertEquals(200,leilao.getLances().get(0).getValor(),0.0001);
    }

    @Test
    public void naoDeveAceitar5LancesdoMesmoUser(){
        Leilao leilao = new Leilao("Nintendo Wii");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bil gattes");

        leilao.propoe(new Lance(steveJobs,200));
        leilao.propoe(new Lance(billGates,300));

        leilao.propoe(new Lance(steveJobs,400));
        leilao.propoe(new Lance(billGates,500));

        leilao.propoe(new Lance(steveJobs,600));
        leilao.propoe(new Lance(billGates,700));

        leilao.propoe(new Lance(steveJobs,800));
        leilao.propoe(new Lance(billGates,900));

        leilao.propoe(new Lance(steveJobs,1000));
        leilao.propoe(new Lance(billGates,1100));

        //deve ser ignorado
        leilao.propoe(new Lance(steveJobs,1200));

        assertEquals(10,leilao.getLances().size());
        assertEquals(1100,leilao.getLances().get(leilao.getLances().size()-1).getValor(),0.00001);

    }
}
