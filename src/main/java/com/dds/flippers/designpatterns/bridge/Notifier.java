package com.dds.flippers.designpatterns.bridge;

import com.dds.flippers.model.UserModel;

public interface Notifier {
    void sendNotification(UserModel user);

}
