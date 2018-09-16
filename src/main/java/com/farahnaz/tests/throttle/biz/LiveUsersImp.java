package com.farahnaz.tests.throttle.biz;

import com.farahnaz.tests.throttle.model.UsersInfo;

import java.util.Random;

public class LiveUsersImp implements LiveUsers {

    private LiveUsersImp() {}

    private static LiveUsersImp instance;


    public static LiveUsersImp getInstance(){
        if(instance == null){
            synchronized (LiveUsersImp.class) {
                if(instance == null){
                    instance = new LiveUsersImp();
                }
            }
        }
        return instance;
    }

    @Override
    public UsersInfo getLiveUsers(Long clientID) {

        UsersInfo usersInfo = new UsersInfo();
        usersInfo.numberOfUsers = new Random().nextInt(50) + 1;
        usersInfo.description = "active users: ".concat(usersInfo.numberOfUsers.toString()).concat(", which might increases dramatically");

        return usersInfo;
    }
}
