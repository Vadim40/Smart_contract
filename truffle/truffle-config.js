const HDWalletProvider = require('@truffle/hdwallet-provider');
const mnemonic = '12 words';

module.exports = {
    networks: {
        development: {
            host: "localhost",
            port: 8545,
            network_id: "*",
            from: "0x3dACB9EDF2BDDa009Cc87e5bB8af15Aa496223FD"
        },
        ropsten: {

            provider: () =>
                new HDWalletProvider(mnemonic, "http://127.0.0.1:8545"),
            network_id: '3',
        }
    }
};
