# SpringbootWithMongoDB

## Overview of Project Packages
<img width="661" alt="Screenshot 2021-03-18 at 9 22 38 AM" src="https://user-images.githubusercontent.com/60062248/111570913-ccdbcd80-87cb-11eb-94ca-bbaf586f9234.png">

## How to use it.
**api Package** consists of different packages for different purposes.

1. **Controller**: Classes from this package will accept http request and route to appropriate service class.
2. **Service**: Classes from this package consist of business logic that is required to fullfil controller request. But for any database request, it will route request to appropriate Class of Dao package.
3. **Dao**: Classes from this package are first point of contact with database(in this case, MongoDB) instance for spring boot application.
4. **Repository**: There are different repository interfaces for different collections.
5. **Model**: This package consists of different Java classes which represents, and map to,different collections in given Database of MongoDB.

**Config Package** has configurations of elasticsearch and some basic beans which will be required to interact with MongoDB.
