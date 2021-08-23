# Product Review Application
###### Application that creates, views and generates statistics from product reviews.

Special note: please see database notes for a guide to activating the
self initializing database.

## Application functions
1. Create review.
2. View current users reviews.
3. View review statistics on a specific product.

## Database notes
Data managed with mySQL 8.0 during development and initial testing.
NOTE: Program will self initialize a database upon initial start.
Please view the interface class and enter the relevant local database
details into the static fields for the database to self initialize.
Database will auto generate tables and seed two product data fields.

## Database table information
1. Login table with user login credentials.
2. Products table with all product info.
3. Review table with all review info.

## User info
Program will auto allocate a admin user on initialization.
username: admin
password: admin

## Testing
JUnit5 test case has been implemented on the search functionality
of the program.
