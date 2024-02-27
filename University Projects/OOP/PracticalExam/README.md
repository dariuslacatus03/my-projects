# Volunteer Management System with Qt

## Description

Welcome to the Volunteer Management System, a C++ application built with the Qt framework to streamline the management of volunteers and departments.

### Key Features

1. **Departments Overview:**
   - Upon running the application, a "DepartmentsWidget" is displayed, presenting a comprehensive overview of all N departments along with the number of volunteers assigned to each.

2. **Individual Department Windows:**
   - N individual windows are generated, one for each department, offering a dedicated space for department heads to manage their volunteers effectively.

3. **Volunteer Assignment:**
   - Department heads can view a list of both assigned and unassigned volunteers within their department's window.
   - Using an intuitive interface, department heads can assign volunteers to specific tasks or roles directly from their department window.

4. **Observer Pattern Implementation:**
   - The application leverages the observer pattern to create a dynamic communication channel between departments and unassigned volunteers.
   - When a volunteer is assigned to a department, the volunteer is automatically removed from the unassigned volunteers' list across all department windows.

### Data Input

- The application reads the number of departments (N) and volunteer information from the "departments.txt" and "volunteers.txt" files, respectively.
- This modular approach allows easy customization and adaptation to varying organizational structures and volunteer pools.

### Getting Started

1. Ensure you have the necessary dependencies, including Qt.
2. Compile and run the application.
3. Explore the "DepartmentsWidget" to view an overall summary of departments and volunteer distribution.
4. Navigate through individual department windows to manage and assign volunteers.

