# My Personal Project

## Proposal

A personal **agenda application** designed to help keep students on track with their courses
and make personalized to-do lists. This application is intended for all UBC students and especially those who need extra
assistance in managing their time and meeting deadlines. With the move to online learning, many courses use various
platforms to deliver course content. With this application, you will now be able to manage your tasks for all your 
courses in one spot.

*Features*:

- A list of your currently enrolled classes
- A personalized todo list with due dates
- Individual course tabs with tasks specific to the course

Phase 4: Task 2

I have implemented a robust design for the getCourse() method in the CourseList class and the getTask() method in the
TaskList class. The exceptions are caught in the TasksPage class in the deletingAction() and completingAction() methods,
as well as in the CoursesPage class in the deleteHelper() method.

Phase 4: Task 3
 
- introduce a super class that is abstract to remove duplicating methods in the TaskList & CourseList classes, and the
  TasksPage & CoursesPage classes. These classes would extend the abstract super class and provide different
  implementations for methods that require the differentiation (such as the different positions for certain buttons/
  text fields between the TasksPage & CoursesPage).

## User Stories

- As a user, I want to be able to add a course to my course list (done)
- As a user, I want to be able to remove a course from my course list (done)
- As a user, I want to be able to view all my courses in the course list (done)
- As a user, I want to be able to set a start and end time for my courses (done)

- As a user, I want to be able to add a task to my task list (done)
- As a user, I want to be able to remove a task from my task list (done)
- As a user, I want to be able to complete a task from my task list (done)
- As a user, I want to be able to view all my completed tasks (done)
- As a user, I want to be able to view all my tasks in my task list (done)
- As a user, I want to be able to set a deadline for my tasks (done)


- As a user, I want to be able to save my tasks to file (done)
- As a user, I want to be able to save my courses to file (done)
- As a user, I want to be able to save my completed tasks to file (done)
- As a user, I want to be able to load my tasks list from file (done)
- As a user, I want to be able to load my courses from file (done)
- As a user, I want to be able to load my completed tasks to file (done)