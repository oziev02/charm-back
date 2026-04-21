package ru.oziev.charm.back;

import ru.oziev.charm.back.controller.LikeController;
import ru.oziev.charm.back.controller.ProfileController;
import ru.oziev.charm.back.dao.ProfileDao;
import ru.oziev.charm.back.service.ProfileService;

public class CharmBackServerRunner {
    public static void main(String[] args) {
        ProfileController profileController = new ProfileController(new ProfileService(new ProfileDao()));



        LikeController likeController = new LikeController();
        CharmHttpServer charmHttpServer = new CharmHttpServer(8080, 5, profileController, likeController);
        charmHttpServer.start();
    }
}