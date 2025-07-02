package com.dds.flippers.bridge;

import com.dds.flippers.models.UserModel;

public interface Notifier {
    void sendNotification(UserModel user);

}
