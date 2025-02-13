# Category_Product_CRUD_Operation_SpringBoot


This project implements a RESTful API for managing categories and products, with a one-to-many relationship between them.  It uses Spring Boot, JPA/Hibernate, and a relational database (MySQL).

## Requirements

The following requirements were implemented:

*   **A) Spring Boot:** The project is built using the Spring Boot framework.
*   **B) REST Controller:** REST controllers are used to handle API requests.
*   **C) Database Configuration (RDB):** The project uses a relational database (MySQL) for persistence.  In-memory databases are not used.
*   **D) Annotation-Based Configuration:** The project uses annotation-based configuration (no XML configuration).
*   **E) JPA & Hibernate:** JPA and Hibernate are used for database interaction.

## API Endpoints

### Category CRUD Operations

1.  **GET all categories (with pagination):**

    *   URL: `localhost:8181/api/categories?page={page}&size={size}`
    *   Method: GET
    *   Description: Retrieves all categories with server-side pagination.
    *   Parameters:
        *   `page`: Page number (starting from 0).
        *   `size`: Number of categories per page.

2.  **Create a new category:**

    *   URL: `/api/categories`
    *   Method: POST
    *   Description: Creates a new category.
    *   Request Body (JSON):
        ```json
        {
          "name": "Category Name"
        }
        ```

3.  **Get category by ID:**

    *   URL: `/api/categories/{id}`
    *   Method: GET
    *   Description: Retrieves a category by its ID.

4.  **Update category by ID:**

    *   URL: `/api/categories/{id}`
    *   Method: PUT
    *   Description: Updates a category.
    *   Request Body (JSON):
        ```json
        {
          "name": "Updated Category Name"
        }
        ```

5.  **Delete category by ID:**

    *   URL: `/api/categories/{id}`
    *   Method: DELETE
    *   Description: Deletes a category.

### Product CRUD Operations

1.  **GET all products (with pagination):**

    *   URL: `/api/products?page={page}&size={size}`
    *   Method: GET
    *   Description: Retrieves all products with server-side pagination.
    *   Parameters:
        *   `page`: Page number (starting from 0).
        *   `size`: Number of products per page.

2.  **Create a new product:**

    *   URL: `/api/products?categoryId={categoryId}`
    *   Method: POST
    *   Description: Creates a new product, associating it with a category.
    *   Parameters:
        *   `categoryId`: ID of the category to associate with the product.
    *   Request Body (JSON):
        ```json
        {
          "name": "Product Name",
          "price": 9.99
        }
        ```

3.  **Get product by ID:**

    *   URL: `/api/products/{id}`
    *   Method: GET
    *   Description: Retrieves a product by its ID, including category details.

4.  **Update product by ID:**

    *   URL: `/api/products/{id}?categoryId={categoryId}`
    *   Method: PUT
    *   Description: Updates a product, optionally changing the associated category.
    *   Parameters:
        *   `categoryId`: ID of the new category to associate with the product (if changing).
    *   Request Body (JSON):
        ```json
        {
          "name": "Updated Product Name",
          "price": 19.99
        }
        ```

5.  **Delete product by ID:**

    *   URL: `/api/products/{id}`
    *   Method: DELETE
    *   Description: Deletes a product.

## Relationship between Category and Product

A one-to-many relationship is implemented between Category and Product. One category can have multiple products.

## How to Run the Code

1.  **Clone the Repository:** (You'll need to create the repository and push the code first)
    ```bash
    git clone <[repository_url](https://github.com/Moinuddinchhipa/Category_Product_CRUD_Operation_SpringBoot/tree/main)>
    ```

2.  **Build the Project (Maven):**
    ```bash
    mvn clean install
    ```

3.  **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```

    Or from your IDE by running the `Application.java` class.

## How to Run the Machine Test

1.  **Start the application** as described above.

2.  **Use Postman:**
    *   Import the Postman collection (if provided - you can create one yourself).
    *   Make requests to the API endpoints using the correct HTTP methods (GET, POST, PUT, DELETE) and URLs.
    *   Verify the responses, including status codes, JSON content, and pagination.
    *   Test pagination by changing the `page` and `size` parameters.
    *   Test the category details in the product response by retrieving a product and checking the nested `category` object.

## DB Design

*   **Database:** MySQL
*   **Tables:**
    *   `category`:
        *   `id` (INT, primary key, auto-increment)
        *   `name` (VARCHAR)
    *   `product`:
        *   `id` (INT, primary key, auto-increment)
        *   `name` (VARCHAR)
        *   `price` (DECIMAL)
        *   `category_id` (INT, foreign key referencing `category.id`)

The `category_id` in the `product` table is a foreign key that establishes the one-to-many relationship between the `category` and `product` tables.  JPA annotations (`@ManyToOne`, `@JoinColumn`) in the entity classes handle the mapping.  The `spring.jpa.hibernate.ddl-auto=update` property in `application.properties` tells Hibernate to create the tables if they don't exist, and update them if there are changes to your entity classes.  (In a production environment, you would typically use a more controlled approach to database schema migrations).
