# Toll-fee
Trip calculator

Introducation

This work is a trip calculator for tolled highway in the Canadian province of Ontario, that calculates the cost of a vehicle driving from one point on a tolled highway, to another point. For this work, the toll rate will be $0.25/km. It was asked to use the file interchanges.json to calculate the distances between interchanges.


Basic idea

1. Read and parse the file interchanges.json by calling JSON.simple, which is a simple Java-based toolkit for JSON, which can encode and decode JSON data.
2. A 2d array named 'distances' is to store the distance between the locations. A list named 'roads' is to represents the connections among locations. In the hashmap called 'map', the relationship of the key of locations and the name of locations are established.
3. A method 'calculator' with parameters such as the start of the trip, the end of the trip, 'distances', and 'road' is to return the distance of the trip. This method is taken a breadth-first search to find the route for the trip.
4. A basic command-line interface is provided to get the input from users.


Usage

1. The program is running on the MS Windows 10 platform with JDK 17.
2. Download the package file 'calculator.rar', unzip it to the folder 'C:\JSON\'. If such folder does not exist, creat it.
3. Move the Solution.java to the desktop folder.
4. Click the Start Menu search bar icon and type Command Prompt. Select the best match option from the results.
5. Go to the desktop folder, such as 'C:\Users\your name\Desktop'.
6. Compile the Java file by the following command, 'javac -classpath C:\JSON\json=simple-1.1.1.jar; Solution.java -Xlint'.
7. Running the program on the command line, like 'java Solution'. The name of locations is case seneitive.


Note
1. If you can not compile the source code, you may need to set up the environment for JSON-simple. Please check the link on the following:
https://www.tutorialspoint.com/json_simple/json_simple_quick_guide.htm
2.  A screenshot is provided with this work.
