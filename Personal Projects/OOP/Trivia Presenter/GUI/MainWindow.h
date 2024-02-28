#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_MainWindow.h"
#include "Service.h"

class MainWindow : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    MainWindow(Service& service, string userName, QWidget *parent = nullptr);
    void connectSignalsAndSlots();
    void setAnswerButton();
    void updateUserScore();
    int getIndexOfSelectedQuestion();
    void answerQuestion();
    void addQuestion();
    void update();
    void notify();
    void populateListAdmin();
    void populateListUser();
    ~MainWindow();

private:
    Ui::MainWindowClass ui;
    Service& service;
    string userName;
};
