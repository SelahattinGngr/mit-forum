# Mitforum

## Kullanılan Teknolojiler

- [Back-end](./ws/)
  - Java Spring
  - h2.database
- [Front-end](./frontend/)
  - node.js
  - vite.js
  - react.js

## Kurulum

2 farklı terminal açın

1. terminalde webserver(ws) dosyamıza girip back-end'imizi çalıştıralım

```bash
cd ws
./mvnw spring-boot:run
```

2. terminalde frontend dosyamıza girip front-end'imizi çalıştırmak için gerekli dosyaları indirip çalıştıralım

```bash
cd frontend
npm i
npm run dev
```
