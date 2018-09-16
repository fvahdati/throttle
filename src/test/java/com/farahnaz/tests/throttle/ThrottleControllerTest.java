package com.farahnaz.tests.throttle;


import com.farahnaz.tests.throttle.controller.ThrottleController;
import com.farahnaz.tests.throttle.model.UsersInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(ThrottleController.class)
public class ThrottleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ThrottleController throttleController;



    @Test
    public void permittedUserTest() throws Exception {

        UsersInfo usersInfo = new UsersInfo();
        usersInfo.numberOfUsers = 20;
        usersInfo.description = "this is a test object";

        given(throttleController.provideNumberOfActiveUsers(10L)).willReturn(usersInfo);

        mvc.perform(get("/activeUsers?clientID=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfUsers", is(both(greaterThan(1)).and(lessThanOrEqualTo(50)))));

    }
}
