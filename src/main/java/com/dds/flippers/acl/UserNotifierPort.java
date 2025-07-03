package com.dds.flippers.acl;

import com.dds.flippers.models.UserModel;

public interface UserNotifierPort {
    void notifyUser(UserModel user);
}
