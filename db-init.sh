#!/bin/bash

curl --location --request POST 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Batata",
  "price": 3.5,
  "pictureUrl": ""
}'
curl --location --request POST 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Refrigerante",
  "price": 7.5,
  "pictureUrl": ""
}'
curl --location --request POST 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Desodorante",
  "price": 10.5,
  "pictureUrl": ""
}'
curl --location --request POST 'localhost:8080/products' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Cebola",
  "price": 4.85,
  "pictureUrl": ""
}'

curl --location --request POST 'localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "John",
  "lastName": "Doe"
}'

curl --location --request POST 'localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "Mary",
  "lastName": "Doe"
}'
curl --location --request POST 'localhost:8080/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
  "firstName": "Joe",
  "lastName": "Don"
}'
