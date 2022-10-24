# Microsserviços na prática 

## O que tem nessa aplicação?
- Microsserviço com Java e Spring, conectando a um banco de dados MySQL
- Implementado técnica de service discovery utilizando o Eureka
- Centralização de requisições através de um API Gateway 
- Comunicação síncrona entre dois microsserviços com Open Feign
- Implementado conceitos de circuit breaker e fallback

## Como executar 

- Executar o "docker-compose up -d" para subir a base de dados
- Executar a aplicação server
- Executar a aplicação Gateway
- Executar a aplicação Pagamentos
- Executar a aplicação Pedidos
