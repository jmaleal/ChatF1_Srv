/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author jorgeleal
 */
public class Corrida {

    private ArrayList<Participante> participantes;
    private int nVolta;
    private int nAtualizacoes;

    public Corrida() {
        this.participantes = new ArrayList<>();
        this.nVolta = 1;
        this.nAtualizacoes = 0;
        carregarParticipantes();
    }

    private void carregarParticipantes() {
        try {
            //carrega dados da ligacao
            File f = new File("./ListaCarros.txt");
            Scanner sc;

            sc = new Scanner(f);
            while (sc.hasNextLine()) {

                String linha = sc.nextLine();
                int nCarro = Integer.parseInt(linha);
                participantes.add(new Participante(new Carro(nCarro)));
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro na execucao do servidor: " + ex);
            System.exit(1);
        }

    }

    public synchronized String obterClassificacao() {
        String classificacao = "";
        int pos = 1;
        for (int i = 0; i <= 5; i++) { // i<=5 porque só apresenta as rimeiras 6 posições
            classificacao += participantes.get(i).getCarro().getnCarro() + ",";
        }
        classificacao = classificacao + "\n";
        return classificacao;
    }

    public synchronized String ultrapassagem(int ultrapassou, int ultrapassado) {
        int i = 0;
        for (Participante p : participantes) {
            if (p.getCarro().getnCarro() == ultrapassado) {
                Collections.swap(participantes, i, i + 1);
                nAtualizacoes++;
                continue;
            }
            i++;
        }

        return obterClassificacao();
    }
}

//<editor-fold defaultstate="collapsed" desc="Inner Class participante">
class Participante {

    private Carro carro;
    private int nVoltaCarro;

    public Participante(Carro carro) {
        this.carro = carro;
        this.nVoltaCarro = 0;
    }

    public Carro getCarro() {
        return carro;
    }

}
//</editor-fold>

