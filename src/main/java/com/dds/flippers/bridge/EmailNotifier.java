package com.dds.flippers.bridge;

import com.dds.flippers.models.UserModel;

public class EmailNotifier implements Notifier {
    @Override
    public void sendNotification(UserModel user) {
        System.out.println("Enviando correo a " + user.getEmailUsuario());

    }
}
