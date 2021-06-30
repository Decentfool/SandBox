# Модуль получения информации по транспортной карте
Этот модуль предназначен для получения информации о клиенте по номеру телефона,
получение информации о картах по идентификатору клиента, а также получения баланса карт"
## Таблицы
CSMB_CLIENT - содержит информацию о клиентах
```
ID           INT AUTO_INCREMENT PRIMARY KEY,
FIRST_NAME   VARCHAR(255) NOT NULL,
LAST_NAME    VARCHAR(255) NOT NULL,
PHONE_NUMBER VARCHAR(255),
EMAIL        VARCHAR(255)
```
CSMB_CARDS - содержит информацию о картах клиента
```
ID          INT AUTO_INCREMENT PRIMARY KEY,
CLIENT_ID   INT NOT NULL,
TARIFF      INT NOT NULL,
CARD_NUMBER VARCHAR(255),
```
CSMB_TARIFF - содержит информацию о тарифе карты
```
ID   INT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(255) NOT NULL,
COST FLOAT
```
CSMB_BALANCE - содержит информацию о балансе карты
```
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CARD_ID INT NOT NULL,
    BALANCE FLOAT,
```

##Примеры запросов
Получение информации о клиенте
```
http://127.0.0.1:9090/getClientInfoByPhone?phoneNumber=1111
```
Получение информации о картах по клиенту
```
http://127.0.0.1:9090/getCardsByClientId?ClientId=1
```
Получшение баланса
```
http://127.0.0.1:9090/getBalanceByCard?cardId=2
```