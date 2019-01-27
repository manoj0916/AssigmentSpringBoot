The is a Spring Boot Project, can be started by executing com.jlp.application.BootProductRESTService.java from STS.
Once started we can hit following urls to find the results 

http://localhost:8080/productsWithReducedPrice

http://localhost:8080/productsWithReducedPrice?labelType=ShowWasNow

http://localhost:8080/productsWithReducedPrice?labelType=ShowWasThenNow

http://localhost:8080/productsWithReducedPrice?labelType=ShowPercDiscount  

To check the implementation the starting class is com.jlp.application.webservices.ProductInfoRESTService