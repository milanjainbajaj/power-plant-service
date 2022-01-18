# power-plant-service

**/auth/token**

curl --location -- request POST 'http://localhost:8080/oauth/token' \
--header 'Authorization: Basic cG93ZXJwbGFudC1jbGllbnQ6cG93ZXJwbGFudC1zZWNyZXQ=' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \

**/api/plant**

curl --location -- request POST 'http://localhost:8080/api/plant' \
--header 'Authorization: Bearer cG93ZXJwbGFudC1jbGllbnQ6cG93ZXJwbGFudC1zZWNyZXQ=' \

**/api/plant/search?location=**

curl --location -- request POST 'http://localhost:8080/api/plant/search?location=AK' \
--header 'Authorization: Bearer cG93ZXJwbGFudC1jbGllbnQ6cG93ZXJwbGFudC1zZWNyZXQ=' \

**/api/plant/search?name=**

curl --location -- request POST 'http://localhost:8080/api/plant/search?name=Annex Creek' \
--header 'Authorization: Bearer cG93ZXJwbGFudC1jbGllbnQ6cG93ZXJwbGFudC1zZWNyZXQ=' \
