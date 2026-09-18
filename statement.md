# Project Statement

## Project Title

**CLI CSV Data Query Engine**

## Course

**Programming in Java**

## Problem Statement

CSV (Comma-Separated Values) files are widely used to store tabular data. However, when a CSV file contains a large number of records, manually searching, sorting, grouping, and calculating values can become time-consuming.

For example, in an employee dataset, a user may need to find all active employees, sort employees according to their age, group employees by department, or calculate the average salary of each department. Performing these operations manually can be inconvenient, while using a complete database system may be unnecessary for simple data analysis.

Therefore, this project aims to develop a **lightweight command-line CSV Data Query Engine using Java** that allows users to perform common data analysis operations directly from the terminal.

## Objectives

The main objectives of the project are:

* To read and process CSV files using Java.
* To filter records based on column values.
* To sort records using selected columns.
* To group records based on a particular column.
* To perform numerical aggregation operations.
* To handle invalid or non-numerical data safely.
* To provide meaningful results through the command line.
* To apply Java programming concepts in a practical application.

## Proposed Solution

The **CLI CSV Data Query Engine** accepts a CSV file and user-defined commands through command-line arguments.

The application reads the CSV file, identifies its headers and records, and performs the requested operation.

The supported operations include:

### Filtering

Users can filter records based on a column and value.

Example:

```bash
java Main data.csv --filter status=active
```

### Sorting

Users can sort records according to a selected column.

Example:

```bash
java Main data.csv --sort age
```

The program supports both numerical and alphabetical sorting.

### Grouping

Users can group records according to a selected column.

Example:

```bash
java Main data.csv --group department --aggregate avg:salary
```

### Aggregation

The application supports the following aggregation operations:

* `sum` – Calculates the total of numerical values.
* `avg` – Calculates the average of numerical values.
* `min` – Finds the minimum value.
* `max` – Finds the maximum value.
* `count` – Counts valid numerical values.

## Technologies Used

* **Programming Language:** Java
* **Input Format:** CSV
* **IDE:** Visual Studio Code
* **Libraries:** Java Standard Library (`java.io`, `java.util`)
* **Data Structures:** ArrayList, List, Map, LinkedHashMap
* **Version Control:** GitHub

No external libraries or database systems are required.

## Key Java Concepts Used

The project demonstrates the practical use of:

* File Handling
* Command-Line Arguments
* Classes and Objects
* Arrays
* ArrayList and Collections
* Maps and LinkedHashMap
* Lambda Expressions
* Sorting
* Conditional Statements
* Loops
* Exception Handling
* String Processing

## Expected Outcome

The project provides a simple command-line tool through which users can analyze CSV datasets without manually processing the data or using a separate database system.

The application can:

**Read CSV → Process Records → Filter / Sort / Group → Aggregate Data → Display Results**

## Dataset

The project uses a mock employee dataset named `data.csv`.

The dataset contains employee-related information such as:

* Employee ID
* Name
* Age
* Department
* Role
* Status
* Location
* Salary
* Performance Score
* Years of Experience

The dataset contains more than 150 records and includes some irregular or invalid values to test the application's exception-handling capabilities.

## Limitations

The current implementation uses simple comma-based parsing with `String.split(",")`. Therefore, CSV fields containing commas inside quotation marks may not be handled correctly.

The application is also command-line based and does not currently provide a graphical user interface.

## Future Scope

The project can be enhanced in the future by adding:

* Multiple filtering conditions.
* Advanced comparison operators.
* Multiple-column sorting.
* Exporting results to a new CSV file.
* Improved CSV parsing for quoted fields.
* GUI-based interaction.
* More advanced query operations.
* Memory-efficient processing for very large datasets.

## Conclusion

The **CLI CSV Data Query Engine** provides a lightweight solution for performing common data analysis operations on CSV files using Java.

The project demonstrates how Java concepts such as file handling, collections, maps, sorting, lambda expressions, command-line arguments, and exception handling can be combined to build a practical application.

It provides a foundation that can be extended into a more advanced CSV analysis and querying system in the future.
