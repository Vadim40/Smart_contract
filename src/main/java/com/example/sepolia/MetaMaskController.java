package com.example.sepolia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @GetMapping("/sendTransaction")
    public String sendTransactionPage() {
        return "sendTransaction";
    }
    @PostMapping("/sendTransaction")
    @ResponseBody
    public Map<String, String> sendTransaction(@RequestBody Map<String, String> payload) {
        try {
            String fromAddress = payload.get("fromAddress");
            if (fromAddress == null || fromAddress.isEmpty()) {
                throw new RuntimeException("Active MetaMask account not found.");
            }
            BigInteger amountInWei = Convert.toWei(new BigDecimal(payload.get("amount")), Convert.Unit.ETHER).toBigInteger();
            Map<String, String> transactionData = new HashMap<>();
            transactionData.put("fromAddress", fromAddress);
            transactionData.put("amountInWei", amountInWei.toString());
            transactionData.put("recipientAddress", payload.get("recipientAddress"));
            return transactionData;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing transaction.");
        }
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



}
