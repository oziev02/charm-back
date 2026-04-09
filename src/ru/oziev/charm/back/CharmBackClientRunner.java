package ru.oziev.charm.back;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

// Сокет у нас для клиента (а сервер-сокет для TCP-сервера. Он реализован в другом файле)
public class CharmBackClientRunner {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             DataOutputStream rqStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream rsStream = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in);) {
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                rqStream.writeUTF(request);
                String response = rsStream.readUTF();
                System.out.println(response);
            }
        }
    }
}
