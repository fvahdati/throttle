package com.farahnaz.tests.throttle.biz;

import com.farahnaz.tests.throttle.model.UsersInfo;

public interface LiveUsers {

    UsersInfo getLiveUsers(Long clientID);
}
