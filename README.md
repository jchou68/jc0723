# Tool Rental Point of Sale System :wrench:

The Tool Rental Point of Sale System is a Java-based application that allows users to manage tools, track holidays, and process tool checkouts. It provides a command-line interface for interacting with the system.

## Background
This is a self project to re-learn Java and revist Object Oriented Programming (OOP) principles.

## Features

- Manage tools: Add, update, and delete tools in the system.
- Track holidays: Define holidays and their observed dates.
- Process tool checkouts: Check out tools for a specified number of days, calculate rental charges, and generate rental agreements.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/jchou68/jc0723.git
   ```

2. Navigate to the project directory:

   ```bash
   cd jc0723
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

### Usage

1. Run the application:

   ```bash
   java -jar target/tool-rental-pos.jar
   ```

2. Follow the on-screen instructions to interact with the Tool Rental Point of Sale System.

## Development

### Libraries and Frameworks Used

- Java
- Maven
- JUnit
- Mockito

### Project Structure

The project follows the standard Maven project structure:

```
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── tool_rental_pos
│   │               ├── other        # Additional utility classes
│   │                   └── classes      # Implementation of tool rental point of sale system
│   │               └── App.java     # Main entry point of the application
│   ├── test
│   │   └── java
│   │       └── com
│   │           └── tool_rental_pos
│   │               └── classes      # Unit tests for tool rental point of sale system
│   └── resources
│       └── tools.csv               # CSV file for storing tool data
├── README.md                      # Project documentation (you're currently reading it)
└── pom.xml                        # Maven project configuration
```

### Testing

To run the unit tests, use the following command:

```bash
mvn test
```

### Contributing

Contributions to the Tool Rental Point of Sale System are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

### License

This project is licensed under the [MIT License](LICENSE).

```

Feel free to customize and expand upon this README file to provide more detailed information about your project.
