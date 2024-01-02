Mobile application with facial recognition
for management attendance control
FISI UNMSM academic
=============================================================

Overview
--------

This repository contains the source code and documentation for an academic management application designed to streamline the attendance process for educators through facial recognition technology.

Features
--------

### Login

*   User authentication through username and password.
*   Google account login option available.

### Home Module

*   Displays a personalized greeting with the user's name and upcoming events.
*   Shows the four pending classes and corresponding attendance records.
*   Features a bottom navigation menu with options for Home, Schedule, Attendance, and Events.

### Schedule Module

*   Presents a calendar interface with the ability to view the current month or week.
*   Displays the course and time based on the selected date.
*   Provides course reminders and exact classroom location within the institution's facility upon course selection.

### Attendance Module

*   Varies based on user role (student or teacher).
*   For students:
    *   Option to mark attendance for the day.
    *   Access to attendance reports for each course, indicating attendance status (marked, to be marked, and unmarked).
    *   Progressive tracking of attendance over time.
    *   Facial recognition verification for marking attendance.
*   For teachers:
    *   Ability to activate attendance for students.

### Configuration Section

*   Toggle notification settings on/off.
*   Change password functionality.

### Help Section

*   Provides a detailed explanation of each interface's functionality for user reference.

Technology Stack
----------------

*   Interface prototypes developed using Figma.
*   Android Studio used for Android application development.
*   Backend implemented in .Net, deployed on Azure.
*   Firebase utilized for image storage.
*   Python service employed for facial recognition model storage.
