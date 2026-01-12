
--- 
## API Documentation (DRAFT)

### Auth

| Request Type | Endpoint                           | Functionality                     | Acceess |
|--------------|------------------------------------|-----------------------------------|---------|
| POST         | `/auth/users/register`             | Register new user                 | PUBLIC  |
| POST         | `/auth/users/login`                | Login user                        | PUBLIC  |
| POST         | `/auth/users/resend-verification`  | Request resend verification email | PUBLIC  |
| GET          | `/auth/users/verify/{token}`       | Verify User Email                 | PUBLIC  |
| POST         | `/auth/users/forgot-password`      | Request password reset email      | PUBLIC  |
| POST         | `/auth/users/reset-password/token` | Reset password with token         | PUBLIC  |
| PATCH        | `/auth/users/change-password`      | Change password while logged-in   | PRIVATE |

### Airport

| Request Type | Endpoint                    | Functionality             | Acceess |
| ------ | --------------------------- | ------------------------- | ------- |
| GET    | `/api/airports/{airportId}` | Retrieve airport          | PUBLIC  |
| GET    | `/api/airports`             | Retrieve List of airports | PUBLIC  |
| POST   | `/api/airports`             | Create airport            | PRIVATE |
| PUT    | `/api/airports/{airportId}` | Update airport            | PRIVATE |
| DELETE | `/api/airports/{airportId}` | Delete airport            | PRIVATE |

### Flight

  | Request Type | Endpoint                                             | Functionality                      | Acceess |
  | ------ | ---------------------------------------------------- | ---------------------------------- | ------- |
  | GET    | `/api/flights`                                       | Retrieve list of flights           | PUBLIC  |
  | GET    | `/api/airports/{airportId}/flights/{airportId}`      | Retrieve flight                    | PUBLIC  |
  | GET    | `/api/airports/{airportId}/flights`                  | Retrieve List of flights           | PUBLIC  |
  | POST   | `/api/flights`                                       | Create flight                      | PRIVATE |
  | POST   | `/api/airports/{originAirportId}/flights`            | Create flight                      | PRIVATE |
  | PUT    | `/api/flights/{flightId}`                            | Update flight                      | PRIVATE |
  | PUT    | `/api/airports/{originAirportId}/flights/{flightId}` | Update flight                      | PRIVATE |
  | DELETE | `/api/flights/{flightId}`                             | Delete flight                      | PRIVATE |
  | GET    | `/api/airports/{airportId}/departures`               | Retrieve List of departing flights | PUBLIC  |
  | GET    | `/api/airports/{airportId}/arrivals`                 | Retrieve List of arriving flights  | PUBLIC  |
---
