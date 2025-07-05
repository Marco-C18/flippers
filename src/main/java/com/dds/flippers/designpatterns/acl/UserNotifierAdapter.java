package com.dds.flippers.designpatterns.acl;

import com.dds.flippers.designpatterns.bridge.*;
import com.dds.flippers.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserNotifierAdapter implements UserNotifierPort {

    @Override
    public void notifyUser(UserModel user) {
        // Aquí podrías transformar datos si viene de otro sistema, o validar.
        System.out.println("Notificando desde ACL: " + user.getNombreUsuario());

        Notifier emailNotifier = new EmailNotifier();
        Notifier smsNotifier = new SmsNotifier();

        emailNotifier.sendNotification(user);
        smsNotifier.sendNotification(user);
    }
}
