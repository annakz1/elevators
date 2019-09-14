# Elevators library

### This is a basic elevators system that performs:

* The system has several elevators
* Each elevator gets it's missions (floors) from the manager 
* An elevator can pick up/drop off people on it's path -  
    for example if an elevator has the path <`from: 2`, `to: 7`> and it gets request for <`from: 5`, `to: 1`>, its updates path will be:
    <`2,5,7,1`>
