# Product List Api
Get formatted list of discounted products under a Category sorted as "highest price reduction first". Price reduction is calculated using [price.was - price.now].

## Requirements
* Oracle JDK 1.8.0_121 or higher
* Java IDE (e.g. Eclipse IDE)

## Design for the project
 	We used J2EE design patterns such as facade, service etc. to implement the project. It has the structure as below:
 	
	View Layer (REST call in this case)
		|
		V
	Facade Layer (Facade classes to make sure abstract function available for omni channels & re-usability) <-> convertor/populator (Generic populator to populate final data model from DTO)
		|
		V
	Service Layer (Provide facilitation the concept of re-usability of service across enterprise)
 			
## Control flow

	Request <-> ProductInfoRESTService <-> ProductInfoFacade <-> ProductInfoService <-> Service call via OkHttp3 (Retrofit)
							|
							V
					ProductInfoPopulator (via generic convertor)

## For Creating Eclipse (with Spring tool Suite 3 plugin) OR Spring tool Suite (STS) IDE project

* Download project from GitHub https://github.com/manoj0916/AssigmentSpringBoot/tree/master/ProductRESTService
* Open eclipse
* Using `Import > General > Existing project to workspace` (or from the welcome screen, use `Import Project`). OR


## To Build & the application

Clean and build the project by selecting 'Project > Clean...'

To run the Spring boot for Product API please follow below steps.

#### Eclipse IDE
	
	Select com.jlp.application.BootProductRESTService.java

    'right click > Run As > Spring Boot App'

#### Maven command prompt

    Go to the project folder in command prompt
    
    Execute command 'mvnw package'
    
    Execute command 'mvnw install'
    
    Execute command 'mvnw spring-boot:run'

    
Above should start the application on embedded Tomcat server @ localhost, port: 8080, with sample console messages like below

INFO 19224 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
INFO 19224 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
INFO 19224 --- [           main] c.j.application.BootProductRESTService   : Started BootProductRESTService in 4.854 seconds (JVM running for 10.976)


Application output can be seen in browser by hitting the URL with category Id and label type(optional)

    Endpoint: http://localhost:8080/v1/categories/{categoryId}/productsWithReducedPrice?labelType={labelType}
   
    Example: 
    
      - http://localhost:8080/v1/categories/600001506/productsWithReducedPrice
      - http://localhost:8080/v1/categories/600001506/productsWithReducedPrice?labelType=ShowWasNow
      - http://localhost:8080/v1/categories/600001506/productsWithReducedPrice?labelType=ShowWasThenNow
      - http://localhost:8080/v1/categories/600001506/productsWithReducedPrice?labelType=ShowPercDiscount
 
Formatted JSON response of products with reduced price under that category or Respective Error messages should get displayed on the browser. Price label formatting is according to the allowed labelType passed as URL parameter (default is - Show Was £x.xx, Now £y.yy). 
 
