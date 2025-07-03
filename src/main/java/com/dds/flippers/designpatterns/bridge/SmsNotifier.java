package com.dds.flippers.designpatterns.bridge;

import com.dds.flippers.model.UserModel;

public class SmsNotifier implements Notifier {

    @Override
    public void sendNotification(UserModel user) {
        System.out.println("Enviando SMS a " + user.getTelefonoUsuario());
    }

}
