
--- 
## API Documentation (DRAFT)

### Email Verification

| Request Type | URL                                           | Functionality             | Access | 
|--------------|-----------------------------------------------|---------------------------|--------|
| GET          | /auth/users/verify?token=(verification-token) | Verify Email              | PUBLIC |
| POST         | /auth/users/resend-verification               | Resend Email Verification | PUBLIC |

### Airport

| Method | Endpoint                    | Functionality             | Acceess |
| ------ | --------------------------- | ------------------------- | ------- |
| GET    | `/api/airports/{airportId}` | Retrieve airport          | PUBLIC  |
| GET    | `/api/airports`             | Retrieve List of airports | PUBLIC  |
| POST   | `/api/airports`             | Create airport            | PRIVATE |
| PUT    | `/api/airports/{airportId}` | Update airport            | PRIVATE |
| DELETE | `/api/airports/{airportId}` | Delete airport            | PRIVATE |

### Flight

  | Method | Endpoint                                             | Functionality                      | Acceess |
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