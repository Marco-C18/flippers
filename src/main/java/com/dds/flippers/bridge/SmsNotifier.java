package com.dds.flippers.bridge;

import com.dds.flippers.models.UserModel;

public class SmsNotifier implements Notifier {

    @Override
    public void sendNotification(UserModel user) {
        System.out.println("Enviando SMS a " + user.getTelefonoUsuario());

    }

}
