## Requirements
- Java 11
- Gradle
- Lombok
- Serenity BDD

## Usage
```
gradle clean test
```

-Dtags='ui:Practice' - to run only UI tests  
-Dtags='api:Booking' - to run only API tests

## Reports
After execution reports should be available from 

```
target/site/serenity/index.html
```
