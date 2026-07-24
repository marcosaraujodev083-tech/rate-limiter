## 💡 O que este projeto faz? (Resumo Executivo)

Imagine que um site muito popular lança uma promoção e milhares de pessoas tentam entrar ao mesmo tempo. Sem uma proteção adequada, o servidor fica sobrecarregado e o sistema "cai" para todo mundo.

Este projeto é um **Rate Limiter (Controlador de Tráfego)**. Ele funciona como uma **"catraca digital inteligente"** para aplicações e sistemas web:

- **Proteção em Tempo Real:** Limita a quantidade de acessos que um mesmo usuário ou computador pode fazer por segundo.
- **Evita Quedas e Ataques:** Impede que acessos maliciosos ou requisições em excesso derrubem o sistema.
- **Alta Performance:** Processa milhares de verificações por segundo sem deixar o site lento para os usuários legítimos.
- **Monitoramento ao Vivo:** Transmite visualmente o tráfego em tempo real para a equipe de tecnologia.


# ⚡ High-Performance Distributed Rate Limiter

Um sistema de limitação de taxa (*Rate Limiting*) baixa latência construído com **Java 21**, **Spring Boot 3** e **Redis**. O projeto foi projetado para proteger APIs REST contra abusos, ataques de negação de serviço (DoS) e requisições excessivas.

---

## 🚀 Arquitetura & Tecnologias

- **Java 21**
- **Spring Boot 3.3.0** (Spring Web, Spring Data Redis)
- **Redis (Docker)** — Armazenamento em memória para contagem atômica de requisições e controle de TTL.
- **Jakarta Servlet Filter** — Interceptação de requisições HTTP antes de atingirem a camada de Controller (`OncePerRequestFilter`).
- **Maven** — Gerenciamento de dependências.

---

## 📅 Roadmap de Desenvolvimento (Sprints)

### 🟢 Sprint 1: Core Limiter & Integração com Redis (Concluído)
- [x] Configuração do ambiente e container Docker com Redis.
- [x] Implementação da estratégia de **Fixed Window Counter** utilizando Redis (`INCR` + `EXPIRE`).
- [x] Criação do filtro HTTP interceptador (`RateLimiterFilter`) para controle por IP do cliente.
- [x] Retorno padronizado de erro `HTTP 429 Too Many Requests`.
- [x] Mapeamento de endpoints de teste (`/api/v1/ping`).

### 🟡 Sprint 2: Observabilidade & Dashboard em Tempo Real (Em Progresso)
- [ ] Integração com **WebSockets (STOMP / SockJS)**.
- [ ] Publicação de eventos de métricas de requisições permitidas/bloqueadas.
- [ ] Dashboard frontend em tempo real (HTML5, Chart.js) para monitoramento do tráfego.

### 🔵 Sprint 3: Algoritmos Avançados & Resiliência (Futuro)
- [ ] Migração/Suporte para o algoritmo **Sliding Window Log** utilizando Scripts Lua no Redis.
- [ ] Configuração dinâmica de limites via `application.properties`.
- [ ] Headers padrão de resposta HTTP (`X-RateLimit-Limit`, `X-RateLimit-Remaining`).

---

## 🛠️ Como Executar o Projeto

### Pré-requisitos
- Docker & Docker Compose
- Java 21+
- Maven 3.8+

### 1. Iniciar o container do Redis
```bash
docker run --name redis-ratelimiter -p 6379:6379 -d redis:alpine
```

Executar a aplicação Spring Boot
```
mvn spring-boot:run
```

Testar o Rate Limiter
```
for i in {1..15}; do curl -i http://localhost:8081/api/v1/ping; echo ""; done
```

Resultado esperado: As primeiras 10 requisições retornam 200 OK. Da 11ª em diante (dentro do mesmo segundo), a API responde com 429 Too Many Requests.


--

<div style="font-family: Arial, sans-serif; color: #333; line-height: 1.4; text-align: center;">
  <p style="margin: 0; font-size: 22px; font-weight: bold; color: #111;">Marcos Araújo</p>
  <p style="margin: 4px 0; font-size: 18px; color: #0066cc; font-weight: 500;">Software Engineer</p>
  <p style="margin: 0 0 12px 0; font-size: 15px; color: #666;">Java • Spring Boot • Redis • Docker</p>

  <div style="display: flex; align-items: center; justify-content: center; gap: 16px; margin-top: 8px;">
    <a href="https://www.linkedin.com/in/marcos-araujo-517201212/" target="_blank" style="text-decoration: none; color: #0077b5; font-size: 16px; font-weight: bold; display: flex; align-items: center; gap: 6px;">
      <img src="https://cdn-icons-png.flaticon.com/24/174/174857.png" alt="LinkedIn" width="20" height="20" style="vertical-align: middle;"> LinkedIn
    </a>
    <span style="color: #ccc; font-size: 16px;">|</span>
    <a href="https://wa.me/5511940292792" target="_blank" style="text-decoration: none; color: #25d366; font-size: 16px; font-weight: bold; display: flex; align-items: center; gap: 6px;">
      <img src="https://cdn-icons-png.flaticon.com/24/733/733585.png" alt="WhatsApp" width="20" height="20" style="vertical-align: middle;"> WhatsApp
    </a>
  </div>
</div>
