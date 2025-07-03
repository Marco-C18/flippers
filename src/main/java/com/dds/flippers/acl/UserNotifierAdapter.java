package com.dds.flippers.acl;

import com.dds.flippers.bridge.EmailNotifier;
import com.dds.flippers.bridge.SmsNotifier;
import com.dds.flippers.bridge.Notifier;
import com.dds.flippers.models.UserModel;
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
