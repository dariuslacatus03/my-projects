#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_MainWindow.h"
#include "Service.h"
#include "DepartmentsWidget.h"

class MainWindow : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    MainWindow(Service& serv, Department dep, DepartmentsWidget* dw, QWidget *parent = nullptr);
    void connectSignalsAndSlots();
    void searchVolunteers();
    void assignVolunteer();
    void addVolunteer();
    void update();
    void notify();
    void populateVolWidgets();
    ~MainWindow();

private:
    Ui::MainWindowClass ui;
    Service& service;
    Department department;
    DepartmentsWidget* dw;
};
