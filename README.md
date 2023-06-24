# Online Book Library Application

This project demonstrates the implementation of microservice using Spring Boot, Netflix Eureka Client and Feign Client.


## Features

* User registration.
* See all books.
* Book order with quantity.
* Order books.
* User payment.


## Technologies

* Spring Boot
* JPA
* Gradle
* MySQL
* Netflix Eureka
* Feign Client


## Getting Started

To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Gradle
* MySQL driver

To build and run the project, follow these steps:

* Login to mySQL driver with user "root" and password "root"
* Add database "project_db" to mySQL
* Set the path of configRepo in application.properties in configServer
* Run the project

The application will run at http://localhost:9090



## Functions (Links and Mappings)


Post mapping (username, email and password) for registration: http://localhost:8096/user/register

![img.png](images/img.png)

Get mapping to view all books: http://localhost:8096/books/all

![img_2.png](images/img_2.png)

Get mapping to view details of a book: http://localhost:8096/books/id/{id}

![img_3.png](images/img_3.png)

Get mapping to view all books by an author: http://localhost:8096/books/author/{author}

![img_4.png](images/img_4.png)

Post mapping (title, author, genre and price)  to create a book: http://localhost:8096/books/create

![img_5.png](images/img_5.png)

Patch mapping to update a book: http://localhost:8096/books/update/{id}

![img_6.png](images/img_6.png)

Delete mapping to delete a book: http://localhost:8096/books/delete/{id}

![img_7.png](images/img_7.png)
