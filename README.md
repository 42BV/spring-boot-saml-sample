# Installation

- Start SAML server:

```
cd docker
docker-compose up
```

- Start Spring Boot environment:

```
mvn spring-boot:run
```

- Open browser on `http://localhost:8080`
- Press 'login'
- Authenticate with user 'saml.user' and password 'password'
- Should now redirect back to localhost:8080 with the user 'saml.user'