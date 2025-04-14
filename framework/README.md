# API Automation Training

Welcome to the API Automation Training! This repository serves as the foundation for the training, providing a base API Automation Framework and step-by-step guidance for participants to build their API automation skills.

Find the API Framework documentation here.

The training is designed for participants to fork this repository, develop their tests for a mock API, and create Pull Requests (PRs) for feedback and review. Mentors will review PRs regularly, providing feedback and guidance to ensure learning and progress.
Training Objectives

---

1.  **Learn the basics of Java:**
    Understand the fundamental features of Java, including classes, inheritage, interfaces, etc. These concepts will help you write better, more maintainable code for API automation.

2.  **Understand the Base Framework:**
    Familiarize yourself with the provided Java API Automation Framework using Maven? for test execution, Rest Assured for HTTP requests, and JUnit 5 for assertions. Learn how the framework is structured and how to extend it for your testing needs.

3.  **Learn API Automation Concepts:**
    Grasp core concepts like service modeling (encapsulating API endpoints), organizing test cases, setting up environments with .env files, and strategies for functional and non-functional API testing.

4.  **Implement Test Automation:**
    Use the base framework to write tests for real-world scenarios using the Petstore API. Implement robust, maintainable test scripts for CRUD operations and edge cases.

5.  **Collaborate Effectively:**
    Develop skills in using Git workflows for version control. Create feature branches, submit Pull Requests (PRs), and respond to feedback from mentors. Learn best practices for working in an asynchronous environment while maintaining high-quality contributions.

---

## Workflow and Guidelines

1.    **Fork the Repository:** Fork this repo to your GitHub account and create a local clone.
2.    **Add collaborators:** Add your mentors as collaborators to the repo.
3.    **Branching:** Use feature branches (e.g., feature/milestone-1) for your changes.
4.    **Pull Requests:** Create PRs for each milestone. Include a description of your changes and any challenges faced. Add your mentor as a reviewer.
5.    **Code Reviews:** Mentors will review your PRs on demand, providing feedback.
6.    **Feedback:** Address feedback promptly and resubmit your PR.

---

## Training Milestones

Before starting each milestone, create a feature branch with the name of the milestone, e.g.: feature/milestone-1 Follow each Milestone without reading the next one.

## **Milestone 1: Setup and Explore. Service model creation and first tests**

**Objective**:Set up the framework and understand its structure. Create a service model with methods for the Store service.

1. Move to the framework folder:
   ```bash
    cd framework
    ```

2. Install dependencies and set up your environment:

    ```bash
    mvn install
    copy example.env .env
    ```

    **Note:** If the execution does not work because you do not have Maven installed, go to https://maven.apache.org/ and follow the download and install instructions

3. Update .env with the test API base URL:
    ```yaml
    BASEURL=https://petstore.swagger.io/v2
    ```
4. Explore the framework:
    - Read the [API Automation Framework (TS+Mocha)](https://github.com/damianpereira86/api-automation-training/tree/main/framework#readme) Readme. <font color="red">AJUSTAR CUANDO TENGAMOS EL README MISMO DEL FRAMEWORK PRONTO</font>
    - Understand the `ServiceBase` class and its usage in service models.

5. Create a new `StoreService` extending `ServiceBase`.
6. Implement methods in `StoreService` for the following operations:
    - `GET /store/inventory`
    - `POST /store/order`
    - `GET /store/order/{orderId}`
    - `DELETE /store/order/{orderId}`
7. Add request and response models where appropriate.
8. Write the **first test** for the following main scenario:
    - Create an order and validate the response (`POST /store/order`).


**Deliverable**:

- Create a PR with the change, adding a short description of your implementation process.

### Milestone 2: CI/CD Pipeline

**Objective**: Configure and understand the GitHub Action to run tests on each PR and merge to `main`.

1. If you are not familiar with GitHub Actions, do some research to understand the basics, such as workflows, jobs, and steps. Refer to the [GitHub Actions Documentation](https://docs.github.com/en/actions).
2. Explore the `.github/workflows/main.yml` file to understand the workflow triggers and steps.
3. Based on the research you did on Github Actions and your experience runing the tests in Visual Code, add the missing line in the main.yml file so the tests are run in the pipeline.
4. Create a new environment called "Testing" in **Settings** > **Environments** > **New environment** <font color="red">ACTUALIZAR CON ALGO QUE TENGA SENTIDO</font>
5. Configure the `BASEURL` as an environment variable with value: `https://petstore.swagger.io/v2`

**Deliverable**:

- Create a PR with a summary of what you learned and confirm that the workflow ran successfully in the Actions tab.

---

### **Milestone 3: Complete the Create Order Suite**

**Objective**: Write tests for the rest of the Create Store test Suite.

1. Write additional tests for the Create Order (`POST /store/order`) endpoint.
2. Include positive and negative tests.
3. Use tags like `@Smoke` or `@Regression` for test categorization. `@Smoke` tests should be the ones that are absolutely required to pass. <font color="red">BUSCAR EQUIVALENTES PARA JAVA. PREGUNTAR A GIULI SI NO FALTA AGREGAR ALGUNA LIBRERIA</font>
4. In case a test fails due to a bug in the API, make sure to create the bug in the Issues tab and follow the [Bug Management documentation](https://github.com/damianpereira86/api-automation-training/tree/main/framework#bug-management). <font color="red">ACTUALIZAR CUANDO TENGA EL README DEL FRAMEWORK</font>

**Deliverable**:

- Create a PR with the tests and a brief summary of the scenarios covered.

---

### **Milestone 4: Verify the order was created**

**Objective**: Make a request to the get order endpoint to verify the order was actually created.

Note: when testing a POST endpoint you normally don't send the ID (it is generated automatically and returned to you in the response), but you might have noticed that this endpoint allows you to do that, and the order gets actually created with the ID you sent.
But in case you didn't already notice, if you didn't provide the ID in the Create Order endpoint, it does not actually create an order (all the data is mocked). For this reason, if you did that and only asserted against the response, your positive tests should have passed. This is why is so important to verify the resources were actually created.

1. For your positive tests, after the response assertions, obtain the created order ID from the response
2. Make a request to the `GET /store/order/{orderId}` endpoint with the order ID
3. Verify the response of the Get Order endpoint is 200, hence, the order was created successfully.
4. Since the test where you didn't provide the ID for the POST in the first place should now be failing, follow step 4 in Milestone 5 for handling it.

**Deliverable**:

- Create a PR with the tests and a brief summary of the changes.

---

### **Milestone 5: Create Test Suites for the rest of the Store Service**

**Objective**: Write tests for the rest of the Store service following the practices covered above.

1. Write a test suite for each of the remaining endpoints in the Store Service:
    - `GET /store/inventory`
    - `GET /store/order/{orderId}`
    - `DELETE /store/order/{orderId}`

**Deliverable**:

- **For each test suite**, create a PR with the tests and a brief summary of the scenarios covered.

---

### **Milestone 6: Pre and Post conditions: Hooks**
<font color="red">CONSULTAR CON GIULI SI ESTE MILESTONE SE MANTIENE IGUAL; LOS BEFORE FUNCIONAN UN POCO DISTINTO EN JAVA DE LO QUE SUGIERE </font>
**Objective**: Write hooks for pre and post-conditions.

Note: Remember that with this API, to create an order that you can actually use in the Get Order scenarios you must provide an order ID in the order creation.

1. Write a [before each](https://junit.org/junit5/docs/current/user-guide/#writing-tests-definitions) in the Get Order test suite.
    1. Add a Before hook that creates an order by calling the right method in the StoreService model.
    2. Obtain and store the order ID (the variable for this must be declared above the before hook).
    3. Use the saved Order ID in the Get Order test.
2. Write an after each hook in the Create Order test suite. This is very useful for cleaning up data after a test execution.
    1. Declare an orderId variable on top of the test suite
    2. After every positive test, update the orderId variable with the newly created Order ID.
    3. Add an AfterEach hook that deletes the created orders by calling the right method in the StoreService model.

**Deliverable**:

- Create a PR with the changes and a brief summary.

---

### **Milestone 7: Verify endpoints basic Performance**

**Objective**: Expand the test suite with basic performance test cases.

1. Add performance checks for the endpoints (e.g., response time < 1000ms).

**Deliverable**:

- Create a PR with the new tests and details covered.

---

### **Milestone 8: Extend to Other Services**

**Objective**: Implement automation for additional services (`Pet` and `User`).

1. Repeat the previous steps for **Pet** and **User** services.

**Deliverable**:

- Create separate PRs for each suite across both services.

---

### **Milestone 9: Authentication**

**Objective**: Implement the authenticate method.

1. Go to the ServiceBase class and find the example `authenticate` method
2. Read the method and the [documentation](https://github.com/damianpereira86/api-automation-training/tree/main/framework#authentication) to understand what it does. <font color="red">AJUSTAR CON LA DOC NUEVA DEL FRAMEWORK</font>
3. Modify the authenticate method implementation
    1. Add the USER and PASSWORD environment variables to the .env file
    2. Modify line 46 <font color="red">BUSCAR LA LINEA CORRECTA</font> to call the `GET /user/login` endpoint. Note that this endpoint is a GET, and expects two params instead of a payload as in the example.
    3. Set the obtained Session ID correctly according to the API documentation. Check the [Delete Pet](https://petstore.swagger.io/#/pet/deletePet) request for information on how to set the session ID.
    4. Modify the Delete Pet test to call the authenticate method from a before hook.

**Deliverable**:

- Create a PR with the new authenticate method and the modified Delete.

---

## Schedule and Communication

- **Weekly Reviews**: Mentors will provide feedback and approval for completed milestones.
- **Support Channels**: Chat with your mentors for queries and discussions.

---

## Tips for Success

1. **Engage Actively**: Reach out for help if you're stuck or need clarification.
2. **Focus on Quality**: Write clean, maintainable code and meaningful tests.
3. **Learn from Feedback**: Incorporate mentor feedback to refine your implementation.

---