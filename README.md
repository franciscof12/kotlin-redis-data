# kotlin-redis-cached-data
![kotlin-redis-chaed-data.png](kotlin-redis-chaed-data.png)

This project is a **Kotlin** API built with **Ktor**, leveraging **Redis** for caching and **PostgreSQL** as the database. The goal is to reduce unnecessary database requests by caching user data in Redis for 24 hours.

## Features
- Retrieves user data from **Redis** if available.
- If the data is not cached, it fetches it from **PostgreSQL** and stores it in Redis for 24 hours.
- Simple **Dockerized setup** using `Makefile`.
- When a new user is created, the data is stored in **PostgreSQL** and then cached in **Redis** for 24 hours.
- Two API endpoints:
    - `GET /user/{id}` → Fetch user data.
    - `POST /user` → Create a new user.

## API Endpoints

### 1. Get User Data
**Request**
```http
GET /user/{id}
```

**Response (Example)**
```json
{
  "user": {
    "name": "Alice",
    "id": 9
  }
}
```

If the user data is cached, it will be retrieved from **Redis**, otherwise, it will be fetched from **PostgreSQL** and then stored in Redis.

---

### 2. Create a New User
**Request**
```http
POST /user
Content-Type: application/json
```

**Body (Example)**
```json
{
    "name": "user_name"
}
```

**Response**
```json
{
  "message": "User created successfully"
}
```

When the user is created, the data is stored in **PostgreSQL** and then cached in **Redis** for 24 hours.

---

## Installation & Setup

### Prerequisites
Make sure you have:
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Make](https://www.gnu.org/software/make/)

### Run the Project

Clone the repository:
```sh
git clone https://github.com/franciscof12/kotlin-redis-data.git
cd kotlin-redis-data
```

Start the application:
```sh
make up
```

To stop the application:
```sh
make down
```

To restart:
```sh
make restart
```

To check logs:
```sh
make logs
```

### Database & Cache Management
Start only **PostgreSQL**:
```sh
make postgresql-up
```

Start only **Redis**:
```sh
make redis-up
```

Clear all containers, volumes, and orphaned services:
```sh
make clean
```