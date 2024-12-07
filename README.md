# SSF Projects - Practice Workshop
- Create a webapp To Do List

## Workshop Tasks
1. Create a Task POJO with the required fields, include validation
2. Using the CommandLineRunner interface, load the data found in todos.txt as a List or Map object to Redis with the help of JSON-P library
   - todos.txt can be found in resources/data
   - todos.txt has a number of errors
     - Formatting errors, have to edit the file
     - Date errors, have to handle during file reading
3. Create a page that shows the To Do List of Tasks
   - Filter tasks displayed based on status (Completed/In progress/Pending)
4. Create functionalities to create new, edit existing, and delete tasks
5. Create a login page and function
   - The page should allow users to enter their username and age
     - Username must not be null
     - Age must be at least 10 years old
   - On submission, the age should be stored as a HTTP Session
   - User will be redirected to the To Do List page upon login
6. Modify the To Do List page
   - Page will only show if a valid HTTP Session exists
   - If a valid HTTP Session does not exist, redirect to a "refused" page
     - "refused" page will have a link button to direct user back to the login page
7. Modify the login function
   - If a user's age is below 10 years old, redirect to an "underage" page
     - "underage" page will have a link button to direct user back to login page
8. Dockerise your application and deploy to railway
   - Use a multi-stage docker process

## Additional notes
- Made use of redis MAP
