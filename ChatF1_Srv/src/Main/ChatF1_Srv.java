/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Comunicacao.presencesServer;

/**
 *
 * @author jorgeleal
 */
public class ChatF1_Srv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        presencesServer server = new presencesServer();
        server.startSrv();
    }

}
