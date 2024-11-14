# 📚 Bookstore Backend

This project implements backend functionalities for an online bookstore, covering book inventory management, search functionality, shopping cart operations, checkout simulation, and purchase history tracking.

---

## 🌟 Features

### 📘 Book Inventory
- Manage book information, including title, genre, ISBN, author, and publication year.

### 🔍 Search Functionality
- Search books by title, author, genre, or publication year.

### 🛒 Shopping Cart
- Add books to the cart.
- View all items in the cart.
- Remove books from the cart.

### 🛍️ Checkout Simulation
- Simulate checkout with options for **Web**, **USSD**, and **Transfer** payments.
- **Note**: No actual payment gateway integration.

### 📈 Purchase History
- Track and view all purchases made after checkout.

---

## 🛠️ Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL** (or any preferred database)
- **Lombok**
- **ModelMapper**
- **JUnit 5 & Mockito** (for unit testing)


---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/bookstore-backend.git
cd bookstore-backend
```
### 2. Build the Application
```bash
mvn clean install
```
### 3. Configure the Database
Before running the application, configure your PostgreSQL database settings in the following file:

**`src/main/resources/application.properties`**:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
### 4. Run the Application
To start the application, run the following command:

```bash
mvn spring-boot:run
```
### 📋 API Endpoints

#### 📚 Book Inventory

- **Add Book**  
  `POST /api/book/addBook`  
  Request Body:
  ```json
  {
    "title": "Book Title",
    "author": "Author Name",
    "genre": "Genre",
    "isbn": "123456789",
    "publicationYear": 2023
  }
  ```
 - **Get All Books**  
  `GET /api/book`

- **Search Books by Title**  
  `GET /api/book/search/title?title=BookTitle`
  
- **Search Books by Author**  
  `GET /api/book/search/authors?authors=AuthorName`
  
 - **Search Books by Genre**  
  `GET /api/book/search/genre?genre=BookGenre`

 - **Search Books by Year**  
  `GET /api/book/search/year?year=PublicationYear`

#### 🛒 Shopping Cart

- **Add Book to Cart**  
  `POST /api/cart/add`  
  Request Body:
  ```json
  {
    "bookTitle": "Book Title",
    "quantity": 1
  }
  ```
 - **View Cart**  
  `GET /api/cart/view`

- **Remove Book from Cart**  
  `DELETE /api/cart/remove/{cartItemId}`

#### 🛍️ Checkout Simulation

- **Checkout**  
  `POST /api/cart/checkout/{paymentMethod}`  
  Payment Methods: `web`, `ussd`, `transfer`

#### 📈 Purchase History

- **View Purchase History**  
  `GET /api/purchase-history/view`

### 🧪 Unit Testing

Unit tests are written using JUnit 5 and Mockito to ensure code functionality.

To run the tests:

```bash
mvn test
```
### 🏗️ High-Level Design

The system is designed with scalability and fault tolerance in mind. The architecture follows a layered approach:

- **Controller Layer**: Handles HTTP requests and responses.
- **Service Layer**: Contains business logic.
- **Repository Layer**: Manages database interactions using Spring Data JPA.
- **DTOs (Data Transfer Objects)**: Transfer data between layers without exposing internal entities.

### 🤝 Contributing

Contributions are welcome! Please fork this repository and create a pull request for any features, bug fixes, or enhancements.
