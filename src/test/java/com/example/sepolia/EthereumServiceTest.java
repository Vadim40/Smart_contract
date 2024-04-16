package com.example.sepolia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EthereumServiceTest {

    @Autowired
    private EthereumService ethereumService;

    @Test
    public void     testConnection() {
        try {
            String clientVersion = ethereumService.getClientVersion();
            assertNotNull(clientVersion);
            System.out.println("Ethereum Client Version: " + clientVersion);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
