package com.dds.flippers.designpatterns.bridge;

import com.dds.flippers.model.UserModel;

public class EmailNotifier implements Notifier {
    @Override
    public void sendNotification(UserModel user) {
        System.out.println("Enviando correo a " + user.getEmailUsuario());

    }
}
