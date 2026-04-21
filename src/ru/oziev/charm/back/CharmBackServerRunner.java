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
        CharmHttpServer server = new CharmHttpServer(5);
        server.start();
    }
}
