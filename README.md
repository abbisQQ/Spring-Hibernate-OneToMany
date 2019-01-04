# Spring-Hibernate-OneToMany
Spring-Hibernate OneToMany

This is a project that consumes an api that supplies data  json format about prefectures and municipalities of Greece.

We get the data and manipulate it using jackson-databind

Then we make a oneToMany relationship for prefectures and municipalities we save those values in a database
and then we retrieve all the municipalities for each prefecture.
An example of outcome can be seen below:

Prefecture ETOLOAKARNANIA   
Municipalities{   
Id:672 Name:AGRINIO ParentId:484    
Id:673 Name:AMFILOCHIA ParentId:484 
Id:674 Name:MESOLOGI ParentId:484  
Id:675 Name:NAYPAKTOS ParentId:484   
} 

Prefecture ARKADIA   
Municipalities{    
Id:534 Name:ASTROS ParentId:475  
Id:535 Name:TRIPOLI ParentId:475  
Id:536 Name:PARALIO ASTROS ParentId:475  
} 
