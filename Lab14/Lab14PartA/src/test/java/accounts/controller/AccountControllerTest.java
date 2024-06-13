package accounts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

import accounts.service.AccountResponse;
import accounts.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    AccountService accountService;

    @Test
    void testGetAccountFound() throws Exception{
        when(accountService.getAccount("123")).thenReturn(new AccountResponse(
                "123", 2000, "Ben Badi"));
        mock.perform(get("/account/123"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(2000))
                .andExpect(MockMvcResultMatchers.jsonPath("accountHolder").value("Ben Badi"));
    }

    @Test
    void testGetAccountNotFound() throws Exception{
        when(accountService.getAccount("123")).thenReturn(null);
        mock.perform(get("/account/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAccount() throws Exception{
        mock.perform(post("/createaccount/123/2000/Matt"))
                .andExpect(status().isOk());
    }
}