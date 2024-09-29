Note for Running the Project
During the setup on my local machine, the image storage path was configured in application.properties as follows:
root.folder=D:\\EntranceTest\\imageProduct

When you clone the project from Git, you will need to update this path to match your local environment.
Open the application.properties file located in the src/main/resources directory.
Change the value of root.folder to the appropriate path on your machine:
root.folder=/path/to/your/local/folder
This will ensure the project can correctly access the image storage directory and function properly.

GitHub URL including:
1. Database design (ERD): Database design Product (ERD).png
2. Spring Boot application source code: EntranceTest
3. Exported Postman collection for localhost: EntranceTest.postman_collection.json
4. Exported database (sql, json, ...): dump-product-202409300328.sql
