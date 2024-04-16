package com.example.sepolia;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.protocol.Web3j;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MetaMaskController {
    private final Web3j web3j;

    public MetaMaskController(Web3j web3j) {
        this.web3j = web3j;
    }
    @PostMapping("/sendTransaction")
    public String sendTransactionPage() {
        return "sendTransaction";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/connectMetamask")
    public String connectMetaMask() {
        // Здесь можно добавить код для отображения страницы, которая содержит ваш интерфейс для подключения MetaMask
        return "connectMetaMaskPage"; // Имя представления в вашем шаблоне
    }

    @PostMapping("/prepareTransaction")
    public ResponseEntity<Map<String, String>> prepareTransaction(@RequestParam BigDecimal amount,
                                                                  @RequestParam String recipientAddress,
                                                                  HttpServletRequest request) {
        try {
            // Получаем активный адрес аккаунта из MetaMask
            String fromAddress = request.getHeader("X-MetaMask-Account");

            // Проверяем, есть ли активный адрес
            if (fromAddress == null || fromAddress.isEmpty()) {
                throw new RuntimeException("Active MetaMask account not found.");
            }

            // Переводим сумму из ETH в Wei
            BigInteger amountInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();

            // Подготавливаем данные для транзакции
            Map<String, String> transactionData = new HashMap<>();
            transactionData.put("fromAddress", fromAddress);
            transactionData.put("amountInWei", amountInWei.toString());
            transactionData.put("recipientAddress", recipientAddress);

            return ResponseEntity.ok(transactionData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
