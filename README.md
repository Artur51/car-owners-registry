## Overview:

car-owners-registry-backend is a Spring backend project to supply endpoints with data for user and user cars.

The data for the backend application is located in the data.sql file. 
The current settings for this application are to load data each time the application starts.
Data is loaded into the Postgres database instance.

Endpoint information can be shown via swagger documentation.
The default documentation url is: 
http://localhost:8080/swagger-ui/index.html

frontend-angular-car-owners-registry is a frontend Angular application.
It has a search filter, data type selection, and sorting options.
After search options are selected user has to press the search button.
Queried data will be shown with an Angular material table and pagination navigation.

Applications are deployed to the docker hub and deployed images are used in the docker-compose file.


## How to start applications:

To start the application on the local computer, docker software is required to be installed.
From the project root folder (where the docker-compose.yml file is located) open the command line and type the command:
'docker-compose up'
This will start the backend application on port 8080 and the frontend application on port 4200
On the localhost user can open the application by url: http://localhost:4200/

## Application deployment:

As demo deployment frontend application is deployed to the firebase web hosting and available by the url:
https://car-owners-registry.firebaseapp.com/
NOTE: The Firebase deployed application is not linked with any running backend.

Deploy scripts are located in the folder:
frontend-angular-car-owners-registry\.github\workflows

The backend application is complied with gradle and has gradle build target to compile and deploy the docker image to the docker hub.
The backend application has two Dockerfiles:
1. car-owners-registry-backend/Docker 
2. car-owners-registry-backend/docker/Docker

The first file can be used for manual deployment.
The second file is used by the gradle file and can be deployed by the dockerPush target.

The backend application has Jenkinsfile as the deployment solution for running and provisioned Jenkins instance which has created a 'seed' job for the project repository. 


## Application settings:

For the frontend application backend url can be changed in environment files located by:
frontend-angular-car-owners-registry\src\environments

During local development and testing for the backend application, it is possible to set up variables in the .env file.
car-owners-registry-backend/.env
NOTE: env file is committed for the demo purpose.
