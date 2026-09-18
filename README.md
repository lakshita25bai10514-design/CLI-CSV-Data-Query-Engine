# CLI CSV Data Query Engine

**Dataset Origin:** The included `data.csv` file is a mock dataset of 150+ employee records that It was a random data set which is written by meself. I specifically designed it with some injected "bad data" (like empty cells, text inside numeric columns like salary, stray spaces, and rows with missing/extra columns) to prove that the CLI tool handles worst-case scenarios gracefully without crashing.

This is a simple Java command-line tool built for my CS class project. It basically lets you query and analyze CSV files directly from the terminal without having to use a heavy database or open Excel. You can filter rows, sort stuff, and even group columns to run math like sum or averages.

## Step-by-Step Instructions

Here is how you can set up and run the project from scratch.

### 1. Environment Setup
You don't need much to run this. Just make sure you have the standard Java Development Kit (JDK) installed on your machine (Java 8 or higher is perfectly fine).
* Open your terminal or command prompt.
* Check if java is installed by typing `java -version`.
* Download or clone this repository to your local machine and open the terminal in the folder where `Main.java` is located.

### 2. Dependency Installation
There are literally zero external dependencies for this project. 
I built the entire CSV parser using only standard `java.io` and `java.util` libraries (like `BufferedReader` and `String.split()`). You don't need Maven, Gradle, or any third-party jars. Just standard Java.

### 3. Configuration
No extra configuration files or environment variables are needed. The tool runs directly against whatever CSV file you pass to it in the arguments. 
I've included the mock `data.csv` file in the folder so you can test it right away.

### 4. Execution
To run the project, you first need to compile the Java file and then execute it with your query arguments.

**First, compile the code:**
```bash
javac Main.java
```

**Next, run the program:**
The basic syntax is `java Main <your_file.csv> [options]`.

Here are some execution examples using the included dataset:

**Example A: Filter and Sort**
To see only the active employees and sort them by their age:
```bash
java Main data.csv --filter status=active --sort age
```

**Example B: Grouping and Math Aggregation**
To group the data by department and find the average salary for each department:
```bash
java Main data.csv --group department --aggregate avg:salary
```

*(Note: The supported math ops for aggregation are `sum`, `avg`, `min`, `max`, and `count`)*
