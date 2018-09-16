package com.farahnaz.tests.throttle.biz;

import com.farahnaz.tests.throttle.Utils.Contants;
import com.farahnaz.tests.throttle.model.ClientInfo;
import com.farahnaz.tests.throttle.model.RequestStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;

public class ClientThrottle {

    Map<Integer, ClientInfo> clientInfoMap = new Hashtable<>();

    private ClientThrottle() {}

    private static ClientThrottle instance;


    public static ClientThrottle getInstance(){
        if(instance == null){
            synchronized (LiveUsersImp.class) {
                if(instance == null){
                    instance = new ClientThrottle();
                }
            }
        }
        return instance;
    }

    public RequestStatus verifyAccessCap(Integer clientID) {


        RequestStatus requestStatus = new RequestStatus();

        requestStatus.accessCode = -1L;
        requestStatus.accessPermissionDescription = "none of the conditions met";

            ClientInfo clientInfo = (ClientInfo) clientInfoMap.entrySet().stream()
                    .filter( x -> x.getValue().ID == clientID)
                    .findFirst().map(x->x.getValue()).orElse(null);



            if (clientInfo != null) {

                Duration duration = Duration.between(clientInfo.lastAccess, LocalDateTime.now());
                long diffSec = Math.abs(duration.getSeconds());

                // RESET CAP, when cap reached or not but greater than 3min
                if(diffSec > Contants.CLIENT_ACCESS_TIME_FRAME_SEC) {

                    clientInfo.caps = 1;
                    clientInfo.lastAccess = LocalDateTime.now();
                    clientInfo.ID = clientID;


                    clientInfoMap.put(clientID, clientInfo);

                    requestStatus.accessCode = 1L;
                    requestStatus.accessPermissionDescription = "your cap is reset";

                    return requestStatus;
                }
                //  when reached cap and greater than 3m
                 else if((clientInfo.caps > Contants.CLIENT_ACCESS_CAP) && (diffSec > Contants.CLIENT_ACCESS_TIME_FRAME_SEC)) {

                     clientInfo.caps = clientInfo.caps + 1;
                     clientInfo.lastAccess = LocalDateTime.now();
                     clientInfo.ID = clientID;


                     clientInfoMap.put(clientID, clientInfo);

                     requestStatus.accessCode = -1L;
                     requestStatus.accessPermissionDescription = "Rate limit exceeded for 3min timeFrame, please try in "
                             .concat(String.valueOf(Contants.CLIENT_ACCESS_TIME_FRAME_SEC - diffSec)).concat("sec");

                     return requestStatus;

                 }
                //  when reached cap and less than 3m
                else if((clientInfo.caps > Contants.CLIENT_ACCESS_CAP) && (diffSec < Contants.CLIENT_ACCESS_TIME_FRAME_SEC)) {

                   // clientInfo.lastAccess = LocalDateTime.now();
                    clientInfo.ID = clientID;


                    clientInfoMap.put(clientID, clientInfo);

                    requestStatus.accessCode = -1L;
                    requestStatus.accessPermissionDescription = "Rate limit exceeded for in 3min timeFrame, please try in "
                            .concat(String.valueOf(Contants.CLIENT_ACCESS_TIME_FRAME_SEC - diffSec)).concat("sec");

                    return requestStatus;

                }
                 // not reached caps and but less than 3min
                 else if((clientInfo.caps <= Contants.CLIENT_ACCESS_CAP) && (diffSec < Contants.CLIENT_ACCESS_TIME_FRAME_SEC))  {

                     clientInfo.caps = clientInfo.caps + 1;
                     clientInfoMap.put(clientID, clientInfo);

                     requestStatus.accessCode = 1L;
                     requestStatus.accessPermissionDescription = "the number of try in last 3min is, ".concat(clientInfo.caps.toString());

                     return requestStatus;
                 }

            }
            // first time client
            else {

                ClientInfo newClient = new ClientInfo();
                newClient.ID = clientID;
                newClient.lastAccess = LocalDateTime.now();
                newClient.caps = 1;
                clientInfoMap.put(clientID, newClient);

                requestStatus.accessCode = 1L;
                requestStatus.accessPermissionDescription = "you have already reached the cap";

                return requestStatus;
            }

            return  requestStatus;
    }
}
