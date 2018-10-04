/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evento;

import UI.Message;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
            Message.ShowMessage("Ficheiro/Lista de carros não encontrado!!", "::: ERRO :::", JOptionPane.ERROR_MESSAGE);
        }

    }

    public synchronized String obterClassificacao() {
        String classificacao = "";
        int pos = 1;
        for (int i = 0; i <= 5; i++) { // i<=5 porque só apresenta as rimeiras 6 posições
            classificacao +=participantes.get(i).getCarro().getnCarro()+",";
        }
        return classificacao;
    }

    public synchronized String atualizacao(String msgAtualizacao) {

        /*falta metodo de ler a msg e atualizar lista...PARA ISSO É NECESSÁRIO SABER A ESTRUTURA DA MSG*/
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
