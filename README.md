
## Drones Service REST API


---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task. 

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

> Feel free to make assumptions for the design approach. 

---

### Requirements

While implementing your solution **please take care of the following requirements**: 

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---
### How to build

#### Requirements

- Java 8
- Java IDE (Visual Studio Code/Eclipse/IntelliJ)
- MYSQL databse (Optional you can use H2 in-memory databse)
- Postman(For testing the endpoints) 

### Steps by step for building and runing the project locally

- Clone the from the link git clone https://github.com/juanmanueldesana/drones-rest-api.git

![git-clone](/screenshots/git-clone.png)

- Open the cloned project in your IDE

- Go to maven the update Project to update all the maven dependencies

- Maven Build the project and run

---

### Testing the API
- Some of the assumption made for the purpose of this API design are:-

- Once the Medication is loaded to a specific drone it cannot be loaded to another drone at the same time.

- You can find the postman collection in the respective folder to import it.

![postman-collection](/screenshots/postman-collection.png)


----
- **Registering a drone** localhost:8080/api/drone/register
The payload should be in json format like this

![register-drone-json](/screenshots/register-drone-json.png)

The response should be 

![register-drone-response](/screenshots/register-drone-response.png)


---
- **Checking available drones for loading;**


Before loading a drone with Medication you can first check the available drones to confirm that the drone is not in use

**localhost:8080/api/drone/available**

![available-drones](/screenshots/available-drones.png)


---
- **Loading a drone with medication items;** 
 
**localhost:8080/api/drone/load**

The payload will have the following fields

- serialNumber is the unique serial for the drone being loaded
- code id the unique code for the medication load being loaded to the drone
- source is the loading point
- destination is where the load is being taken

the Medication items to be loaded for testing are code : **WE232344, WE232345, WE232346, WE232347, WE232348, WE232349, WE2323510, WE2323511, WE2323512, WE2323513**

the destination and the source are any places

 - The serialNumber is the unique serialNumber a drone that you register

![load-medication](/screenshots/load-medication.png)


---
- **Checking loaded medication items for a given drone;**

**localhost:8080/api/drone/details/Q23RT5676695**

- Check which medication item is loaded to a specific drone.

![check-loaded-medication](/screenshots/check-loaded-medication.png)


---

- **Check drone battery level for a given drone;**

**localhost:8080/api/drone/battery**

![check-drone-battery](/screenshots/check-drone-battery.png)


---
- **Delivery of medication item**

**localhost:8080/api/drone/deliver**

When the drone delivers the item it call this end-point and its status is change drop loaded to delivering then delivered

![deliver-medication](/screenshots/deliver-medication.png)




---

:scroll: **END** 


