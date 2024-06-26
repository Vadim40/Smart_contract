package com.example.sepolia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
@Service
public class EthereumService {
    private final Web3j web3j;

    @Autowired
    public EthereumService(Web3j web3j) {
        this.web3j = web3j;
    }

    public String getClientVersion() throws Exception {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        return web3ClientVersion.getWeb3ClientVersion();
    }
}
