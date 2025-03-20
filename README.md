# calculator-api

This is a simple calculator API built with Clojure. It provides endpoints for performing basic arithmetic operations and managing user balances.

## Description

The calculator-api allows users to perform operations like addition, subtraction, multiplication, and division via a RESTful API. It also handles user balance management. It is designed to demonstrate basic Clojure web development using libraries like Compojure API and HugSQL.

## Prerequisites

* Clojure installed
* MySQL database running (configured in `calculator-api.config.config`)

## Running

1.  **Start the MySQL Database:** Create the database schema running the following cmd.
    ```bash
    docker compose up
    ```
    TODO - Add categories scripts.
2.  **Run the API:** To start a web server for the application, run:

    ```bash
    clojure -M -m calculator-api.api.handler
    ```

3.  **Access the API:** The API will be accessible at `http://localhost:4300`. You can view the Swagger UI at `http://localhost:4300/`.

## API Endpoints

* `/calculator/health` (GET): Returns a sample text.
* `/calculator/operation` (POST): Performs an arithmetic operation.
* `/calculator/random-string` (POST): Get a random string.

## Database Setup

* **Start the MySQL Database:** Create the database schema running the following cmd.
    ```bash
    docker compose up
    ```

## Development

* Use `clojure -M -e "(require 'calculator-api.api.handler)" -r` to start a REPL.
* Use `(require 'calculator-api.api.handler :reload)` to reload namespaces.
* Add tests using `clojure.test`.

## Deploy
```bash
    clj -T:build clean
    clj -T:build jar
```

## Dependencies

* compojure
* ring
* prismatic/schema
* metosin/compojure-api
* com.layerware/hugsql
* com.mysql/mysql-connector-j

## License

Copyright © 2025
