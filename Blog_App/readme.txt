	Basics of Validation

Java bean is validated with JSR 380 known as Bean Validation 2.0

JSR 380 is specification for the Java API for bean validation. Properties of bean meet the specific criteria.

For validation different annotations is used like @NotNull, @Min, @Size etc.

Hibernate Validator is a implementation of validation api.


	Important Annotations for validations

@NotNull

@Email

@Size

@Min

@Max

@NotEmpty


	How to use
Spring boot provides support for hibernate validator.

1. Add dependency - spring starter validator. it will load all the validations dependency related hibernate.
2. use the annotations on Bean
3. enable that annotation using controller


create bean of ModelMapper in main file so whenever we use modelmapper spring will return object of that modelmapper, it is necessary to add modelmapper dependency ti pom.xml

***********basics of validation**************
- Java bean is validated with JSR 380 known as Bean Validation 2.0
- JSR 380 is specification for the Java API for bean validation. Properties of bean meet the specific criteria.
- For validation different annotations is used like @NotNull, @Min, @Size etc.
Hibernate Validator is a implementation of validation api.
In pom.xml , we need to use spring-boot-starter-validator

if we put validator then MethodArgumentNotValidException get throws to handle that need to create method exceptionhadler in globalexception. by using getbindingErrors

//category
category api, service, entity is done now  
run all category api's, validate

all api's are done now working on pagination - 5-2-2025

jpa repository interface has paging and sorting repository
sorting done by Sort class 

searching is done by using derived query methods or can be done by custom query

note - must useclass for constants because thats a best practice 