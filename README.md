# Dev You To Do List

Exercise for the Dev You Java 3 class. 

Here are the specifications from the instructor:

"Create an application that gives the user the ability to add activities to a To Do List
"The user should be able to 

- Add to the To Do List
- Remove activities from the To Do List
- print the activities in the To Do list
- Sort activities by name(alphabetically), sort by Due Date, show only activities that are completed, show activities 
that are not completed, priority Score
- Change the status of an activity from complete to incomplete 
- Change the due date of an activity 
- You should not be able to change the due date to a date in the past unless the status is completed
- Priority score should be calculated using number of days until due &times; Importance Score (importance score should 
be a number between 0-1. Zero mean no importance at all and 1 represents the most important item. 
    
"(hint: In this application you will need to create an `Activity` class that has multiple properties and methods)

"Be sure to use some application planning tools to plan out your application."

For a long while I got hung up on the priority score. I now think the formula should be (1 divided by number of days) times importance score, so that of two items with the same importance but different due dates, the item that is due sooner has a lower priority score and is therefore understood to be more urgent. I'll have to watch out for division by zero.
