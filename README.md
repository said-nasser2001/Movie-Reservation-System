# Movie Reservation System

Welcome to the Movie Reservation System! This system provides a platform for managing movie reservations, including booking seats, storing movie details, and categorizing movies by genre. It is built using **Spring Framework** and incorporates various modern features for a robust experience.

## Features

- **User Authentication**: Secure access with authentication and authorization.
- **Movie Management**: Add, update, and delete movies.
- **Theater, screen, and Screening  Management**: Add, update, and delete.
- **Seat Booking**: Reserve seats for movies with real-time availability updates.

## Prerequisites

To run this project, you need:
- **Java 17 or above**
- **Maven**
- **Spring Boot 3.x**
- **MySQL Database**
- An IDE like IntelliJ IDEA or Eclipse


## Database Schema
![Capture](https://github.com/user-attachments/assets/d16c24a5-daa7-40a5-ade7-6fa3329bb44e)

The entities and their relationships can be visualized as follows:

- Movies → Screenings (1:N)
- Theaters → Screens (1:N)
- Screens → Seats (1:N)
- Screens → Screenings (1:N)
- Screenings → Screenings_Seats → Seats (N:M)
- Reservations →Screenings_Seats (1:N)
- Customers → Reservations (1:N)

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd movie-reservation-system
   ```

2. **Configure the Database**:
   Update the `application.properties` file with your MySQL database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/moviereservation
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**:
   Navigate to `http://localhost:8080` in your browser.

## API Endpoints

### Authentication
- `POST /login` - Login
- `POST /register` - Register

### Movies
- `GET /movies` - Fetch all movies
- `POST /movies` - Add a new movie
- `PUT /movies/{id}` - Update movie details
- `DELETE /movies/{id}` - Delete a movie
### Screenings
- `GET /Screenings` - Fetch all Screenings
- `GET /Screenings/date` - Fetch all Screenings for specific date
- `POST /Screenings` - Add a new Screenings
- `PUT /Screenings/{id}` - Update Screenings details
- `DELETE /Screenings/{id}` - Delete a Screenings
### Theater
- `GET /theater` - Fetch all Screenings
- `POST /theater` - Add a new Screenings
- `PUT /theater/{id}` - Update Screenings details
- `DELETE /theater/{id}` - Delete a Screenings
### screens
- `GET /screens` - Fetch all Screenings
- `POST /screens` - Add a new Screenings
- `PUT /screens/{id}` - Update Screenings details
- `DELETE /screens/{id}` - Delete a Screenings
### Booking
- `GET /reservation` - Fetch all reservations
- `GET /reservation/{id}` - Fetch a reservation
- `POST /reservation/create` - Make a reservation (request body will have Screening Seat Ids)
- `Delete /reservation/{id}` - Cancel a reservation

## Technologies Used

- **Spring Boot**: Backend framework
- **Hibernate**: ORM for database operations
- **MySQL**: Relational database
- **Spring Security**: Authentication and authorization


---

Thank you for checking out the Movie Reservation System! Feel free to report any issues or suggest improvements.

