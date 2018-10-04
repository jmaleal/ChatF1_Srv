/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacao;

import Evento.Corrida;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class GetPresencesRequestHandler extends Thread {

    Socket ligacao;
    Presences presences;
    BufferedReader in;
    PrintWriter out;

    //ADICIONEI A CORRIDA
    private Corrida corrida;

    public GetPresencesRequestHandler(Socket ligacao, Presences presences, Corrida corrida) {
        this.ligacao = ligacao;
        this.presences = presences;
        this.corrida = corrida;
        try {
            this.in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            this.out = new PrintWriter(ligacao.getOutputStream());
        } catch (IOException e) {
            System.out.println("Erro na execucao do servidor: " + e);
            System.exit(1);
        }
    }

    public void run() {
        try {
            System.out.println("Aceitou ligacao de cliente no endereco " + ligacao.getInetAddress() + " na porta " + ligacao.getPort());

            String response;
            String msg = in.readLine();
            System.out.println("Request Ligacao = " + msg);

            StringTokenizer tokens = new StringTokenizer(msg);
            String metodo = tokens.nextToken();

            if (metodo.equals("get")) {
                response = "Conexão: OK\n";
                String ip = tokens.nextToken();
                Vector<String> ipList = presences.getPresences(ip);
                response += ipList.size() + "\n";
                for (Iterator<String> it = ipList.iterator(); it.hasNext();) {
                    String next = it.next();
                    response += next + ";";
                }
                System.out.println(response);
                out.println(response);
            } else {

                String msgArray[] = msg.split(",");
                int n_monolugar_que_ultrapassou = Integer.parseInt(msgArray[0]);//ignorado se contador = 0
                int que_foi_ultrapassado = Integer.parseInt(msgArray[1]);//ignorado se contador = 0
                int n_volta = Integer.parseInt(msgArray[2]);//ignorado se contador = 0
                String nome_cliente = msgArray[3];
                int n_atualizações = Integer.parseInt(msgArray[4]);//deve ser 0 na primeira mensagem enviada pelo cliente */

                //out.println("201;method not found");
            }

            out.flush();
            in.close();
            out.close();
            ligacao.close();
        } catch (IOException e) {
            System.out.println("Erro na execucao do servidor: " + e);
            System.exit(1);
        }
    }
}
