# Clean Architecture Backend Challenge
This is my solution to a Backend test for a Senior Engineer job position.
For the sake of confidentiality I will not be linking or referencing the actual test.

## Instructions
### Executing this project
WIP (this is a Springboot project, insert the mvn command to boot this up with H2 console enabled)

## About the Solution
### Base Clean Architecture
This project is developed using "The Clean Architecture", originally written by Robert C. Martin (Uncle Bob).
Below there is an image comparing Robert's proposal versus this project's proposed architecture.

![Screenshot of the base diagrams used for this project](./assets/images/clean_arch_base.png)

### Detailed Clean Architecture
Below is a diagram with estimate detail of how each architecture layer should look like by the end of the project.
![Screenshot of the detailed diagrams](./assets/images/layers_detailed.png)

#### Presentation Layer
In our project this layer will contain a few classes to help us dealing with HTTP (Request/Response classes, mostly) and Framework code(Spring MVC Controller)

#### Infrastructure Layer
This layer have the responsibility of keeping Interface Adapters(Database Context, Repository etc) away from other layers. This project will use this layer to keep the Repository classes and a DbContext with H2 memory database.

#### Application Layer
This layer will keep our "Use Cases" or features separated from the Domain Layer.

#### Domain Layer
Lastly, this layer will keep the definitions of our business.
