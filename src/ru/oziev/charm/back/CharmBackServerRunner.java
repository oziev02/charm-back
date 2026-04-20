package ru.oziev.charm.back;

import ru.oziev.charm.back.controller.ProfileController;
import ru.oziev.charm.back.dao.ProfileDao;
import ru.oziev.charm.back.service.ProfileService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static ru.oziev.charm.back.model.Commands.*;

public class CharmBackServerRunner {
    public static void main(String[] args) throws IOException {

        ProfileController controller = new ProfileController(new ProfileService(new ProfileDao()));

        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             DataInputStream requestStream = new DataInputStream(socket.getInputStream());
             DataOutputStream responseStream = new DataOutputStream(socket.getOutputStream());
             ) {
            String request = requestStream.readUTF();

            while (!"stop".equals(request)) {
                String response;
                if (request.startsWith(SAVE.getPrefix())) {
                    response = controller.save(request.split(SAVE.getPrefix())[1]);
                } else if (request.startsWith(FIND_BY_ID.getPrefix())) {
                    response = controller.save(request.split(FIND_BY_ID.getPrefix())[1]);
                } else if (request.startsWith(FIND_ALL.getPrefix())) {
                    response = controller.findAll();
                } else if (request.startsWith(UPDATE.getPrefix())) {
                    response = controller.save(request.split(UPDATE.getPrefix())[1]);
                } else if (request.startsWith(DELETE.getPrefix())) {
                    response = controller.save(request.split(DELETE.getPrefix())[1]);
                } else {
                    response = "Unsupported command";
                }

                System.out.println("Client request: " +  request);
                responseStream.writeUTF(request);
                request = requestStream.readUTF();
            }
        }
    }
}
