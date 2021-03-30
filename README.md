# Phonebook API Implementation

## Somewhat important
This API works exclusively with dtos, meaning everything you pass to API should be a DTO and everything you receive would be a DTO.
Fields description are in DTOs section.

## Util

Build and launch (tested on aws ubuntu 20.04 t2.micro)
```bash
sudo apt install openjdk-8-jdk -y
git clone https://github.com/flutt13/Phonebook.git
cd ./Phonebook/
./mvnw clean package
java -jar ./target/phonebook-0.0.1-SNAPSHOT.jar
```

## Did somebody say docker?

Build and launch from Dockerfile (tested on aws ubuntu 20.04 t2.micro)
```bash
sudo snap install docker
git clone https://github.com/flutt13/Phonebook.git
cd ./Phonebook
sudo docker build -t phonebook:0.0.1 .
sudo docker run -p 8080:8080 -d phonebook:0.0.1
```

## Entities
```yaml
User:
  - userId		# Long
  - userName		# String
  - firstName		# String
  - lastName		# String
  - fullName		# String (Set automatically with firstname + " " + lastName)

PhonebookEntry:
  - entryId		# Long
  - alias		# String
  - phoneNumber		# String
  - firstName		# String
  - lastName		# String
  - fullName		# String (Set automatically with firstname + " " + lastName)
```

## DTOs

```yaml
UserDto:
  - userName		# String (If not passed will be set automatically with random string of length 8)
  - firstName		# String
  - lastName		# String

PhonebookEntryDto:
  - alias		# String (If not passed will be set automatically with upper cased first letters of firstName + lastName)
  - phoneNumber		# String
  - firstName		# String
  - lastName		# String
```
## API

Base Address: http://localhost:8080/api/

```yaml
User:

    # Create user
  - POST /api/user

    # Get user by name (or it's part)
  - GET /api/user

    # Get all users
  - GET /api/user/all

    # Get user by username {userName}
  - GET /api/user/{userName}

    # Delete user by username {userName}
  - DELETE /api/user/{userName}

    # Update user (Works dinamically and updates only passed parameters)
  - PATCH /api/user/{userName}

Phonebook:
    # Create phonebook entry for user {userName}
  - POST /api/user/{userName}/phonebook

    # Get all phonebook entries owned by user {userName} with phoneNumber parameter (ex. +79275821745)
  - GET /api/user/{userName}/phonebook

    # Get all phonebook entries owned by user {userName}
  - GET /api/user/{userName}/phonebook/all

    # Get phonebook entry for user {userName} with alias {alias}
  - GET /api/user/{userName}/phonebook/{alias}

    # Update phonebook entry for user {userName} with alias {alias} 
    # Works dinamically and updates only passed parameters
  - PATCH /api/user/{userName}/phonebook/{alias}
```
