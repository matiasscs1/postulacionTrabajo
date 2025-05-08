# Mi Negocio Backend

API REST para gestión de clientes y direcciones, parte del ejercicio práctico de Alquimiasoft.

---

## 📋 Tecnologías

- Java 17
- Spring Boot 3.4.5  
  - spring-boot-starter-web  
  - spring-boot-starter-data-jpa  
  - spring-boot-starter-validation  
- PostgreSQL  
- Hibernate ORM  
- HikariCP (pool JDBC)  
- JUnit 5 + Mockito (pruebas unitarias)  
- Lombok (opcional)  
- MapStruct (opcional)

---

## ⚙️ Configuración

Edita `src/main/resources/application.properties` con tus datos:

```properties
# --- Datos de conexión a PostgreSQL ---
spring.datasource.url=jdbc:postgresql://localhost:5432/mi_negocio
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.datasource.driver-class-name=org.postgresql.Driver

# --- Hibernate ---
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# --- Desactivar Liquibase en mvn spring-boot:run ---
spring.liquibase.enabled=false
# Arranca la app (con desactivación de Liquibase inyectada)
mvn clean spring-boot:run
# Empaquetar (tests omitidos)
mvn clean package -DskipTests

# Ejecutar el JAR
java -jar target/mi-negocio-0.0.1-SNAPSHOT.jar
