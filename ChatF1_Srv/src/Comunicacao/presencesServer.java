package Comunicacao;

import Evento.Corrida;
import UI.Message;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class presencesServer {

    static int DEFAULT_PORT = 8082;
    private Corrida corrida = new Corrida();

    public void startSrv() {
        int port = DEFAULT_PORT;
        Presences presences = new Presences();

        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(port);
        } catch (Exception e) {
            Message.ShowMessage("erro ao criar socket servidor...!", "::: ERRO :::", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("Servidor á espera de ligacoes no porto " + port);

        while (true) {
            try {

                Socket ligacao = servidor.accept();

                GetPresencesRequestHandler t = new GetPresencesRequestHandler(ligacao, presences, corrida);
                t.start();

            } catch (IOException e) {
                Message.ShowMessage("Erro na execução do Servidor!", "::: ERRO :::", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }
}
