The code serves the happy path @ http//:localhost:8080/swagger-ui.html

1) reading a US.txt fine given in resource folder
2) It has a swagger URL to see whats has been coded and API documentation http:localhost:8080/swagger-ui.html.
3) Central Exception using ExceptionHandler
4) The data loads from the file on startup
5) Junits for service layer and Controller layer
6) Code is using lombok for getter/setter/constructor. Make sure you IDE has lombok installed


Planned next tasks:
1) instead of US.txt it should accept .zip. uzip it and than do what its doing currently
2) and upload API where a new zip file can be provided
3) For performance/maintainability/extend-ability reasons 
    a) Instead of storing the zipInformation in java maps/lists i would like to store it in H2
    b) And fetch using SQL commands

5) the line_parser to be changes into an interface and multiple such parsers can be generated for different file formats

-Cheers Abhishek 
    
 
