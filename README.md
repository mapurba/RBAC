# locus-rar (Access controled , Policy based Roles and Resources managment  )

## How to Build
```shel
mvn clean install

or 

mvn clean package
```
# Locus Roles and Resources Application

## How to deploy and run
- Copy */locus/target/locus-rar.war* to your tomcat *webapps* folder
-  Start tomcat

# This has to run in apache tomcat only i have not tested this in some other framework

## How to test
- Add new Tasks using **POST http://localhost:8080/tasks/**
- Assign A Project Manager to the Tasks using **PUT http://localhost:8080/taskss/{Tasks_id}/pm/**
- Add users to the Tasks using **POST http://localhost:8080/tasks/{tasks_id}/users/**
- Add Resources to the Tasks using **POST http://localhost:8080/tasks/{tasks_id}/Resources/**
- Assign Resources to users using **PUT http://localhost:8080/tasks/{tasks_id}/Resources/{Resource_id}/assignee**
- Update Resource's status **PUT http://localhost:8080/tasks/{Tasks_id}/Resources/{Resource_id}/status**


There are total 17 api end points please go through them all 



Docker config command to easy deploy the maven war

```
    This runs the docker process in background
    application should run ht 8080 only as the postman collection have this url
        Docker run -it -p 8080:8080 -v locus-assig/locus/target/locus-rar.war:/usr/local/tomcat/webapps/ROOT.war tomcat &

    To see the logs  
        Docker logs -f <docker id>  

    Use this to Run in Foregroung
        Docker run -it -p 8080:8080 -v locus-assig/locus/target/locus-rar.war:/usr/local/tomcat/webapps/ROOT.war tomcat 


```

##Roles in the System 

	ADMIN, PM, DEVELOPER, TESTER


Resource Type in the System

	LAPTOP,CAR,SALARY

Resourcs Status are 

	NEW, ASSIGNED, BLOCKED


Classes in the Code     
    Entity class 
    Policy class
    Resource Class
    Role    Class
    Task    Class


As this is a policy based role and resouce access .. plicies and be changed to twick the whole  system ad behave in different way 

as specified in the mail  by locus  i have created few roles in the system as ENM so that they can be extend further

A sample policy is below " /locus/src/main/resources/assig/apurba/rar/security/policy/json/default-policy.json "

```
Sample Policy

    {

        name:"A user can have access to a Car"
        disc:"A user who is a pary of a company has a Resource"
		"target": "subject.hasRole('ADMIN',subject.role) && subject.hasRole('DEVELOPER',subject.role) && action = 'ASSIGN' ",
        "condition :"subject.resource.id == resource.id && resource.name ='CAR'  " 

    }

```

    There are fudther scope to this where wverything can be a entity and a resouce can have multiple entitlements 
    out of which some can be dynamicly assigned some created while creating the resource .
    who knows the possibility !!!!! 

    As this is a very open worled question 

    I have gone with a generic approche where everything can be a resource and anyone who falls under a policy that is defined in default-policy.json and know the user name password of the system users can play with the system 


    Authonication used in the system is basic auth 
    
    I would suggest using postman








## User Authentication
The sample application uses HTTP Basic Authentication and blow are the users that can be used:
- admin/password	Role ADMIN
- pm1/password      Role PM
- pm2/password      Role PM
- dev2/password     Role DEVELOPER
- test1/password    Role TESTER
- test2/password    Role TESTER

All users can be found and modified in **assig.apurba.rar.config.InMemoryUserDetailsService** class.


```
Postman api test collection . feel free to link my github
```
Postman collection : https://www.getpostman.com/collections/93da10f4868b1544dc01

# User List apis 

```
endpoint : http://localhost:8080/users/list/
payload  : none

curl --location --request GET 'http://localhost:8080/users/list/' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' --header 'Content-Type: application/json' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' --header 'Cookie: JSESSIONID=545DB292E77C221B16871329FB9806FB'

# Delete User


    http://localhost:8080/users/1 (deletes user with id 1)
    payload  : none


# Task list in the system


http://localhost:8080/task/



# assign Project manager to the task


    http://localhost:8080/task/1/pm/



# assign Developer to the system 


    http://localhost:8080/task/1/users/

    {
	    "name": "dev2",
    	"roles": ["DEVELOPER"]
    }

    same for othe roles 

```


# List the users for a task (given the task id is known)
```
http://localhost:8080/task/1/users/

```

# Task APi
```
    Task View       :  http://localhost:8080/task/1/
    Task Delete     :  http://localhost:8080/task/1/
    
```

# Resource Apis 

```
  Create a Resource with known task id : http://localhost:8080/task/1/resources/
  Listes the Resource for task one  :  http://localhost:8080/task/1/resources/
  update Resource : http://localhost:8080/task/2/resources/1/ 
        payload     {
                        "type": "CAR",
                        "name": "New Fast Car",
                        "description": "GTR"
                    }
   Delete resource : http://localhost:8080/task/2/resource/2/

   Assign resource in task with id 1 to user id 'dev1' :  http://localhost:8080/task/1/resources/1/assignee 
                                        Payload     :   dev1

    Change Resource Status :  http://localhost:8080/task/1/resources/1/status
                payload    :  BLOCKED  
```


