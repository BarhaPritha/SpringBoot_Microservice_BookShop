# Online Book Library Application

This project demonstrates the implementation of security using Spring Boot and JSON Web Tokens (JWT).


## Features

* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based (CUSTOMER, ADMIN) authorization with Spring Security


## Technologies

* Spring Boot
* Spring Security
* JPA
* JSON Web Tokens (JWT)
* BCrypt
* Gradle
* MySQL


## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Gradle
* MySQL driver

To build and run the project, follow these steps:

* Login to mySQL driver with user "root" and password "root"
* Add database "project_db" to mySQL
* Run the project

The application will run at http://localhost:8096



## Functions (Links and Mappings)


**Permitted by all:**

Post mapping (firstName, lastName, email and password) for registration: http://localhost:8096/user/register

![img.png](Images/img.png)

Post mapping (email and password) for login: http://localhost:8096/user/login

![img_1.png](Images/img_1.png)

<br>

**Permitted by CUSTOMER and ADMIN roles:**

Get mapping to view all books: http://localhost:8096/books/all

![img_2.png](Images/img_2.png)

Get mapping to view details of a book: http://localhost:8096/books/id/{id}

![img_3.png](Images/img_3.png)

Get mapping to view all books by an author: http://localhost:8096/books/author/{author}

![img_4.png](Images/img_4.png)

<br>

**Permitted by ADMIN roles:**

Post mapping (title, author, genre and price)  to create a book: http://localhost:8096/books/create

![img_5.png](Images/img_5.png)

Patch mapping to update a book: http://localhost:8096/books/update/{id}

![img_6.png](Images/img_6.png)

Delete mapping to delete a book: http://localhost:8096/books/delete/{id}

![img_7.png](Images/img_7.png)
