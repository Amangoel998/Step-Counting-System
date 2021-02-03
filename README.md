# Step-Counting-System

Implement a REST based API server using Java 8
and Spring Boot with following functions:
1. `_create?start=<startval:integer>, step=<steptime:integer>`
    Result in creation of a new go routine in the program that starts counting every step time seconds. The response should return a unique identifier for that routine.

2. `_check?id=<unique id:string>`
    Returns the current counter value, creation time and step time for that service.

3. `_check`
    Returns same as (2) above but for all the unique identifiers present in the system.

4. `_render`
    Returns an HTML page stating in tabular form, the unique identifiers and their current counts.

5. `_clear/:id or _clear?id=<identifier:string>`
    Clears the timer and cleanly exit setting the service status to stopped.

6. `_pause?id=<identifier:string>`
    Pause a given service if it exists, else return error. If it exists, and is a valid target, change the modifiedAt time to reflect this call’s response.

• 7) Use dependency injection patterns to
implement the above and discuss about the
same.

• 8) Document the above program in an
appropriate README file.

• 9) Discuss the use of interfaces in the above
program if relevant.

• 10) You can use any database backend if
required by the above program for persistence
and state retrieval on abrupt kills. Discuss about
persistency in the above context and update the
README.