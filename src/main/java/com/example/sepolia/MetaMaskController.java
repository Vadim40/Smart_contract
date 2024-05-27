package com.example.sepolia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.web3j.protocol.Web3j;




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

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/connectMetamask")
    public String connectMetaMask() {
        return "connectMetaMaskPage";
    }

    @GetMapping("/smartcontract")
    public String smartcontract() {
        return "smartcontract";
    }

    @GetMapping("/storage")
    public String storage() {
        return "storage";
    }

    @GetMapping("/voting")
    public String voting() {
        return "voting";
    }

}
