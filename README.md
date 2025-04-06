#TODO: CAMBIAR TODO PARA QUE SE ALINEE CON EL PROGRAMA DE LAS CLASES DE JAVA

API Automation Training

Welcome to the API Automation Training! This repository serves as the foundation for the training, providing a base API Automation Framework and step-by-step guidance for participants to build their API automation skills.

Find the API Framework documentation here.

The training is designed for participants to fork this repository, develop their tests for a mock API, and create Pull Requests (PRs) for feedback and review. Mentors will review PRs regularly, providing feedback and guidance to ensure learning and progress.
Training Objectives

    Learn the basics of TypeScript:
    Understand the fundamental features of TypeScript, including type annotations, interfaces, classes, and asynchronous programming. These concepts will help you write better, more maintainable code for API automation.

    Understand the Base Framework:
    Familiarize yourself with the provided TypeScript API Automation Framework using Mocha for test execution, Axios for HTTP requests, and Chai for assertions. Learn how the framework is structured and how to extend it for your testing needs.

    Learn API Automation Concepts:
    Grasp core concepts like service modeling (encapsulating API endpoints), organizing test cases, setting up environments with .env files, and strategies for functional and non-functional API testing.

    Implement Test Automation:
    Use the base framework to write tests for real-world scenarios using the Petstore API. Implement robust, maintainable test scripts for CRUD operations and edge cases.

    Collaborate Effectively:
    Develop skills in using Git workflows for version control. Create feature branches, submit Pull Requests (PRs), and respond to feedback from mentors. Learn best practices for working in an asynchronous environment while maintaining high-quality contributions.

Workflow and Guidelines

    Fork the Repository: Fork this repo to your GitHub account and create a local clone.
    Add collaborators: Add your mentors as collaborators to the repo.
    Branching: Use feature branches (e.g., feature/milestone-1) for your changes.
    Pull Requests: Create PRs for each milestone. Include a description of your changes and any challenges faced. Add your mentor as a reviewer.
    Code Reviews: Mentors will review your PRs on demand, providing feedback.
    Feedback: Address feedback promptly and resubmit your PR.

Training Milestones

Before starting each milestone, create a feature branch with the name of the milestone, e.g.: feature/milestone-1 Follow each Milestone without reading the next one.
Milestone 1: Learn the Basics of TypeScript

Objective: Gain a foundational understanding of TypeScript to support API automation.

    Complete an online TypeScript course:
        Follow the TypeScript Basics Course to learn key concepts.
    Create a folder named typescript-course in your repository.
    Add the completion certificate to the typescript-course folder.
    Download the course code and extract the Section 4\typescript_project_class_final folder to your typescrypt-course folder.
    Open the copied course code in Visual Studio Code and make a couple of improvements:
        Make the box ID unique even when you have pressed the button more than once.
        Create a second button to restart the game so you don't have to refresh the page each time you lose.

Deliverable:

    Create a PR with the typescript-course folder containing your improved code and the completion certificate.
    Include a brief summary in the PR description highlighting the topics you covered and any challenges you faced.

Milestone 2: Setup and Explore. Service model creation and first tests

Objective: Set up the framework and understand its structure. Create a service model with methods for the Store service.

    Move to the framework folder:

     cd framework

Install dependencies and set up your environment:

npm install
copy example.env .env

Update .env with the test API base URL:

BASEURL=https://petstore.swagger.io/v2

    Explore the framework:
        Read the API Automation Framework (TS+Mocha) Readme.
        Understand the ServiceBase class and its usage in service models.

    Create a new StoreService extending ServiceBase.

    Implement methods in StoreService for the following operations:
        GET /store/inventory
        POST /store/order
        GET /store/order/{orderId}
        DELETE /store/order/{orderId}

    Add request and response models where appropriate.

    Write the first test for the following main scenario:
        Create an order and validate the response (POST /store/order).

Deliverable:

    Create a PR with the change, adding a short description of your implementation process.

Milestone 3: CI/CD Pipeline

Objective: Configure and understand the GitHub Action to run tests on each PR and merge to main.

    If you are not familiar with GitHub Actions, do some research to understand the basics, such as workflows, jobs, and steps. Refer to the GitHub Actions Documentation.
    Explore the .github/workflows/main.yml file to understand the workflow triggers and steps.
    Based on the research you did on Github Actions and your experience runing the tests in Visual Code, add the missing line in the main.yml file so the tests are run in the pipeline.
    Create a new environment called "Testing" in Settings > Environments > New environment
    Configure the BASEURL as an environment variable with value: https://petstore.swagger.io/v2

Deliverable:

    Create a PR with a summary of what you learned and confirm that the workflow ran successfully in the Actions tab.

Milestone 4: Complete the Create Order Suite

Objective: Write tests for the rest of the Create Store test Suite.

    Write additional tests for the Create Order (POST /store/order) endpoint.
    Include positive and negative tests.
    Use tags like @Smoke or @Regression for test categorization. @Smoke tests should be the ones that are absolutely required to pass.
    In case a test fails due to a bug in the API, make sure to create the bug in the Issues tab and follow the Bug Management documentation.

Deliverable:

    Create a PR with the tests and a brief summary of the scenarios covered.

Milestone 5: Verify the order was created

Objective: Make a request to the get order endpoint to verify the order was actually created.

Note: when testing a POST endpoint you normally don't send the ID (it is generated automatically and returned to you in the response), but you might have noticed that this endpoint allows you to do that, and the order gets actually created with the ID you sent. But in case you didn't already notice, if you didn't provide the ID in the Create Order endpoint, it does not actually create an order (all the data is mocked). For this reason, if you did that and only asserted against the response, your positive tests should have passed. This is why is so important to verify the resources were actually created.

    For your positive tests, after the response assertions, obtain the created order ID from the response
    Make a request to the GET /store/order/{orderId} endpoint with the order ID
    Verify the response of the Get Order endpoint is 200, hence, the order was created successfully.
    Since the test where you didn't provide the ID for the POST in the first place should now be failing, follow step 4 in Milestone 5 for handling it.

Deliverable:

    Create a PR with the tests and a brief summary of the changes.

Milestone 6: Create Test Suites for the rest of the Store Service

Objective: Write tests for the rest of the Store service following the practices covered above.

    Write a test suite for each of the remaining endpoints in the Store Service:
        GET /store/inventory
        GET /store/order/{orderId}
        DELETE /store/order/{orderId}

Deliverable:

    For each test suite, create a PR with the tests and a brief summary of the scenarios covered.

Milestone 7: Pre and Post conditions: Hooks

Objective: Write hooks for pre and post-conditions.

Note: Remember that with this API, to create an order that you can actually use in the Get Order scenarios you must provide an order ID in the order creation.

    Write a before hook in the Get Order test suite.
        Add a Before hook that creates an order by calling the right method in the StoreService model.
        Obtain and store the order ID (the variable for this must be declared above the before hook).
        Use the saved Order ID in the Get Order test.
    Write an after each hook in the Create Order test suite. This is very useful for cleaning up data after a test execution.
        Declare an orderId variable on top of the test suite
        After every positive test, update the orderId variable with the newly created Order ID.
        Add an AfterEach hook that deletes the created orders by calling the right method in the StoreService model.

Deliverable:

    Create a PR with the changes and a brief summary.

Milestone 8: Verify endpoints basic Performance

Objective: Expand the test suite with basic performance test cases.

    Add performance checks for the endpoints (e.g., response time < 1000ms).

Deliverable:

    Create a PR with the new tests and details covered.

Milestone 9: Extend to Other Services

Objective: Implement automation for additional services (Pet and User).

    Repeat the previous steps for Pet and User services.

Deliverable:

    Create separate PRs for each suite across both services.

Milestone 10: Authentication

Objective: Implement the authenticate method.

    Go to the ServiceBase class and find the example authenticate method
    Read the method and the documentation to understand what it does.
    Modify the authenticate method implementation
        Add the USER and PASSWORD environment variables to the .env file
        Modify line 46 to call the GET /user/login endpoint. Note that this endpoint is a GET, and expects two params instead of a payload as in the example.
        Set the obtained Session ID correctly according to the API documentation. Check the Delete Pet request for information on how to set the session ID.
        Modify the Delete Pet test to call the authenticate method from a before hook.

Deliverable:

    Create a PR with the new authenticate method and the modified Delete.

Schedule and Communication

    Weekly Reviews: Mentors will provide feedback and approval for completed milestones.
    Support Channels: Chat with your mentors for queries and discussions.

Tips for Success

    Engage Actively: Reach out for help if you're stuck or need clarification.
    Focus on Quality: Write clean, maintainable code and meaningful tests.
    Learn from Feedback: Incorporate mentor feedback to refine your implementation.
