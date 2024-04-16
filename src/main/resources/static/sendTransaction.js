document.getElementById('transactionForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const amount = document.getElementById('amount').value;
    const recipientAddress = document.getElementById('recipientAddress').value;

    prepareAndSendTransaction(amount, recipientAddress);
});

async function prepareAndSendTransaction(amount, recipientAddress) {
    try {
        // Выполняем AJAX запрос к методу контроллера /sendTransaction
        const response = await fetch('/sendTransaction', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                amount: amount,
                recipientAddress: recipientAddress
            })
        });

        if (!response.ok) {
            throw new Error('Error preparing transaction');
        }

        // Получаем данные для транзакции от сервера
        const transactionData = await response.json();

        // Выполняем транзакцию с использованием полученных данных и MetaMask
        const { fromAddress, amountInWei } = transactionData;

        const ethereum = window.ethereum;
        if (!ethereum || !ethereum.isMetaMask) {
            throw new Error('Please install MetaMask.');
        }

        const transactionParameters = {
            nonce: '0x00', // ignored by MetaMask
            gasPrice: '0x09184e72a000', // customizable by user during MetaMask confirmation.
            gas: '0x2710',  // customizable by user during MetaMask confirmation.
            to: recipientAddress, // Required except during contract publications.
            from: fromAddress, // must match user's active address.
            value: amountInWei, // Only required to send ether to the recipient from the initiating external account.
            chainId: 3 // Used to prevent transaction reuse across blockchains. Auto-filled by MetaMask.
        };

        // txHash is a hex string
        // As with any RPC call, it may throw an error
        const txHash = await ethereum.request({
            method: 'eth_sendTransaction',
            params: [transactionParameters],
        });

        console.log('Transaction sent successfully! Transaction hash: ' + txHash);
    } catch (error) {
        console.error('Error preparing and sending transaction:', error);
    }
}
