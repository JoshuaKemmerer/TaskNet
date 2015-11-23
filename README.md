# TaskNet
An Android application for keeping track of deadlines, events, and habits to be developed.

The purpose of this Android application is to provide a simple and intuitive interface for the purpose of creating, managing, and accomplishing several different types of tasks:

- **Deadline**  Tasks that have a date, but do not end. Useful for reminding one's self of upcoming homework assignments.
- **Event**  Very similar to on-going tasks, these also have a begin and end date; however, begin and end date must be specified by the user. This type of tasks is usually created for a future event, and will contain reminders that may be added.
- **Habit**  This entity is for developing habits and can range from something that should be done every day, two days, week, two weeks, and so on. This class will also extend from the Task class, but they will be displayed fairly differently from Deadlines and Events.


***

## Motivation for This Project

The motivation for this project was that I personally became confused with what projects/assignments were due for school, mostly during the busier weeks. I want an intuitive and sensible interface to combine all of the different tasks I want/need to accomplish, and this is my solution.

I also have a desire to help other people become more organized, so I hope that this solution will one day help others.


***

## Details

### Habit Class

Habit class records a begin and end time (habits may be removed, and that will mark the end time)

All habits are to be created in the same way that Deadlines and Events are, except there will be a different View used upon selection of the "Habit" option during creation.

All habits will be viewable by clicking on an item in the TaskNet that says "Developing Habits."

- The habits will show up in a fragment, and habits can be "checked-off" to be marked as complete for a single duration of time (today, this week, etc.)
- Habits may be deleted by using this view
- The "Why" should either be plainly visible or easily accessible by some button to the side (ie. WHY?)
