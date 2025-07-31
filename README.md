Java Calculator

A Java-based calculator application with a graphical user interface (GUI) built using Swing. The calculator supports basic arithmetic operations (addition, subtraction, multiplication, division) with high-precision calculations using BigDecimal. It features a responsive GUI, keyboard input support, and error handling for invalid operations like division by zero.

Features

Basic Arithmetic Operations: Supports addition (+), subtraction (-), multiplication (*), and division (/).
High-Precision Calculations: Uses BigDecimal for accurate decimal arithmetic, avoiding floating-point errors.
Graphical User Interface: Built with Java Swing, featuring a clean layout with buttons for digits, operators, and functions.
Keyboard Input: Supports keyboard input for digits, operators, decimal point, Enter, Backspace, and more.

Function Buttons:
Clear (C): Resets the calculator to its initial state.
Backspace (←): Deletes the last digit or decimal point.
Toggle Sign (±): Changes the sign of the current number or result.
Error Handling: Displays "Error" for invalid operations (e.g., division by zero).
Responsive Design: Grid-based layout adjusts to window resizing, with a professional Nimbus look-and-feel.

Technologies

Java: Core programming language (Java SE).
Swing: For the graphical user interface.
BigDecimal: For precise decimal calculations.
Nimbus Look and Feel: For a modern UI appearance.

Setup
To run the calculator locally, follow these steps:

Clone the Repository:
git clone https://github.com/JustisDutt/Java-Calculator.git
cd Java-Calculator

Prerequisites:

Java Development Kit (JDK) 8 or higher installed.
A Java IDE (e.g., IntelliJ IDEA, Eclipse) or a command-line environment.

Compile and Run:

If using an IDE, import the project and run CalculatorMain.java.
From the command line:javac -d bin src/Calculator/*.java
java -cp bin Calculator.CalculatorMain


Usage

GUI Controls:

Click digit buttons (0-9) to input numbers.
Click operator buttons (+, -, *, /) to perform calculations.
Use C to clear all, ← to delete the last digit, ± to toggle the sign, and . for decimal points.
Press = to compute the result.


Keyboard Controls:

Digits (0-9): Input numbers.
Operators (+, -, *, /): Apply arithmetic operations.
.: Add a decimal point.
Enter or Shift + =: Compute the result.
Backspace: Delete the last digit.
Esc: Clear all.
F9: Toggle sign.


Project Structure

Java-Calculator/
├── src/
│   └── Calculator/
│       ├── CalculatorModel.java    
│       ├── CalculatorGUI.java       
│       ├── CalculatorController.java 
│       ├── CalculatorMain.java      
├── README.md                       


License

This project is licensed under the MIT License. See the LICENSE file for details.
