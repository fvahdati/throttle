package com.farahnaz.tests.throttle.controller;


import com.farahnaz.tests.throttle.biz.LiveUsersImp;
import com.farahnaz.tests.throttle.model.UsersInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThrottleController {

    @RequestMapping("/activeUsers")
    public UsersInfo provideNumberOfActiveUsers(@RequestParam(value="clientID") Long clientID) {
        return LiveUsersImp.getInstance().getLiveUsers(clientID);
    }
}
