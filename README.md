# README – інструкція запуску

## Склад
- **Бекенд**: Java + Spring Boot  
- **Фронтенд**: React

## Вимоги
- Java 17+  
- Node.js 16+  
- Maven або Gradle  
- npm або yarn

## Запуск бекенду
1. Перейти в папку бекенду  
2. Зібрати і запустити:
   - Maven: `./mvnw spring-boot:run`  
   - Gradle: `./gradlew bootRun`  
3. Доступ: [http://localhost:8080/api/notes](http://localhost:8080/api/notes)

## Запуск фронтенду
1. Перейти в папку фронтенду  
2. Встановити залежності: `npm install`  
3. Запуск: `npm start`  
4. Доступ: [http://localhost:3000](http://localhost:3000)

## Функціонал
- Додавання, редагування, видалення нотаток  
- Пошук за заголовком  
- Фільтр за датою  
- Кнопка для підтвердження пошуку або фільтра  
- Автооновлення після змін  

## Примітка
API_URL у фронтенді має відповідати адресу бекенду.

## [Video](https://youtu.be/H0Lcwy0WqKk?si=DT2VHrAxB6IPMU95)
