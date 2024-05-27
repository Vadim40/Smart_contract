const SimpleStorage = artifacts.require("SimpleStorage");

module.exports = async function(deployer) {
    try {
        await deployer.deploy(SimpleStorage);
        const instance = await SimpleStorage.deployed();
        console.log('Адрес контракта', instance.address);
    } catch (error) {
        console.error('Ошибка миграции:', error);
    }
};
