
Описание
Сервис ms-client представляет собой REST API для управления клиентами. Он предоставляет возможность создавать, обновлять, удалять, искать и получать информацию о клиентах. Также реализована документация через Swagger и мониторинг через Actuator.

Технологии и зависимости:
+ Java версии 17 или выше
+ Spring Boot 3.4.4
+ PostgreSQL 14 или выше
+ Liquibase
+ MapStruct
+ Spring Data JPA
+ Springdoc OpenAPI (Swagger)
+ JUnit 5 + Zonky embedded PostgreSQL
+ Apache JMeter (для нагрузочного тестирования)

Настройка проекта
Важно: перед запуском можно указать переменные окружения:

export DB_URL=jdbc:postgresql://localhost:5432/ms_client?currentSchema=ms_client_schema
export DB_USERNAME=postgres
export DB_PASSWORD=your_password

Также можно указать параметры подключения к PostgreSQL в файле:
src/main/resources/application.properties

spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/ms_client?currentSchema=ms_client_schema}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:your_password}

API Методы:
POST /api/clients — создание клиента
PUT /api/clients/{id} — обновление клиента по ID
GET /api/clients/{id} — получение клиента по ID
DELETE /api/clients/{id} — удаление клиента
GET /api/clients/search — поиск с фильтрами и пагинацией

Структура данных:
Client:
- id (UUID)
- fullName (обязательное)
- shortName (обязательное)
- inn (обязательное)
- active (по умолчанию true)
- clientType (связь с ClientType)
- createDateTime
- updateDateTime

ClientType:
- key (например, "UL" или "IP")
- name (наименование типа клиента)

Миграции:
Используется Liquibase, миграции находятся в:
src/main/resources/db/changelog

Данные загружаются из CSV-файлов.

Swagger UI:
После запуска доступен по адресу:
http://localhost:8080/swagger-ui.html

Нагрузочное тестирование:
Проведено с помощью Apache JMeter. Скрипты находятся в директории:
jmeter-scenarios/

Тестирование:
Реализованы unit-тесты для API с поднятием Spring контекста.
Используется embedded PostgreSQL от Zonky.
Покрываются как успешные случаи, так и ошибки (400, 404, 422).

Запуск:
./mvnw spring-boot:run (или mvn spring-boot:run при установленном Maven)
git --version
