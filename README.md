
## codefellowship
- rout "/" ; you can find the home page which contains some information about the website, and an acces to the users accounts.
- rout "/login" ; you can log in to your acoount.
- rout "/signup" ; you can create a new account.
- rout "/users" ; the user can see the information about user who login.

## run this app:

clone the repo: git clone git@github.com:shathasmadi/codefellowship.git

update the application.properties file with your:

spring.jpa.database=POSTGRESQL
spring.sql.init.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/codefellows
spring.datasource.username=shatha
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update

Run the project and see the result from the following link: http://localhost:8080

