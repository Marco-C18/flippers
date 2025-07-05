package com.dds.flippers.designpatterns.acl;

import com.dds.flippers.model.UserModel;

public interface UserNotifierPort {
    void notifyUser(UserModel user);
}
