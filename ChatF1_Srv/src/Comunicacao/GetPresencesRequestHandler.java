package Comunicacao;

import Evento.Corrida;
import java.net.*;
import java.io.*;
import java.util.*;

public class GetPresencesRequestHandler extends Thread {

    Socket ligacao;
    Presences presences;
    BufferedReader in;
    PrintWriter out;

    //ADICIONEI A CORRIDA
    private Corrida corrida;

    boolean ativo = true;

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
            System.out.println("Aceitou ligacao de cliente no endereco " + ligacao.getInetAddress() + " na porta " + ligacao.getPort() + "\n");
            while (ativo) {
                String response = "";
                String msg = in.readLine();
                System.out.println("Request=" + msg);

                StringTokenizer tokens = new StringTokenizer(msg);
                String metodo = tokens.nextToken();

                switch (metodo.toLowerCase()) {
                    case "get":
                        response = "101\n";
                        String ip = tokens.nextToken();
                        Vector<String> ipList = presences.getPresences(ip);
                        response += ipList.size() + "\n";
                        for (Iterator<String> it = ipList.iterator(); it.hasNext();) {
                            String next = it.next();
                            response += next + ";";
                        }
                        response = response + "\n";
                        System.out.println("Enviada resposta: " + response);
                        out.println(response);
                        out.flush();
                        break;

                    case "class":
                        response = "teste aceite pelo servidor\n";
                        response = corrida.obterClassificacao();
                        System.out.println("Enviada resposta: " + response);
                        out.println(response);
                        out.flush();
                        break;

                    case "ult":
                        String[] partes = metodo.split(" ");
                        int carroUltrapassou = Integer.parseInt(partes[1]);
                        int carroUltrapassado = Integer.parseInt(partes[2]);

                        corrida.ultrapassagem(carroUltrapassou, carroUltrapassado);
                        response = corrida.obterClassificacao();
                        out.println(response);
                        out.flush();
                        break;

                    default: // Optional
                        response = "mensagem desconhecida:" + msg + "\n";
                        System.out.println("Enviada resposta: " + response);
                        out.println(response);
                        out.flush();

                    /*String msgArray[] = msg.split(",");
                int n_monolugar_que_ultrapassou = Integer.parseInt(msgArray[0]);//ignorado se contador = 0
                int que_foi_ultrapassado = Integer.parseInt(msgArray[1]);//ignorado se contador = 0
                int n_volta = Integer.parseInt(msgArray[2]);//ignorado se contador = 0
                String nome_cliente = msgArray[3];
                int n_atualizações = Integer.parseInt(msgArray[4]);//deve ser 0 na primeira mensagem enviada pelo cliente */
                    //out.println("201;method not found");
                }

            }
            System.out.println("Servidor terminado");
            in.close();
            out.close();
            ligacao.close();
        } catch (IOException e) {
            System.out.println("Erro na execucao do servidor: " + e);
            System.exit(1);
        }
    }
}
