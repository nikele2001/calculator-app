# Calculator App

This repository contains a simple calculator application with a frontend built using React and a backend built using Spring Boot. The calculator supports basic arithmetic operations such as addition and subtraction.

## Getting Started

### Prerequisites

- Node.js and npm installed
- Java Development Kit (JDK) installed
- Maven installed

### Running the Frontend

1. Navigate to the [calculator-frontend](https://github.com/nikele2001/calculator-app/tree/main/calculator-frontend) directory:

    ```bash
    cd calculator-frontend
    ```

2. Install the dependencies:

    ```bash
    npm install
    ```

3. Start the development server:

    ```bash
    npm run dev
    ```

4. Open your browser and navigate to `http://localhost:5173` to see the calculator app.

### Running the Backend

1. Navigate to the [calculator-backend](https://github.com/nikele2001/calculator-app/tree/main/calculator-backend) directory:

    ```bash
    cd calculator-backend
    ```

2. Install the dependencies and build the project:

    ```bash
    mvn install
    ```

3. Run the backend server:

    ```bash
    java -jar target/calculator-backend-0.0.1-SNAPSHOT.jar
    ```

4. The backend server will start on `http://localhost:5000`.

## Running Tests

### Frontend Tests

1. Navigate to the [calculator-frontend](https://github.com/nikele2001/calculator-app/tree/main/calculator-frontend) directory:

    ```bash
    cd calculator-frontend
    ```

2. Run the tests:

    ```bash
    npm test
    ```

### Backend Tests

1. Navigate to the [calculator-backend](https://github.com/nikele2001/calculator-app/tree/main/calculator-backend) directory:

    ```bash
    cd calculator-backend
    ```

2. Run the tests:

    ```bash
    mvn test
    ```
