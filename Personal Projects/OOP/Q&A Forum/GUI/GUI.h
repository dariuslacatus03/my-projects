#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_GUI.h"
#include "Service.h"
#include "QuestionsModel.h"

class GUI : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    GUI(Service& service, QuestionsModel* questionsModel, std::string userName, QWidget *parent = nullptr);
    ~GUI();

    void connectSignalsAndSlots();
    void initializeGUI();
    int getSelectedIndex();
    void updateAnswers();
    void addQuestion();
    void addAnswer();
    void update();
    void notify();

    void startSearch();

    void setSpinBox();

    void vote();

private:
    Ui::GUIClass ui;

    Service& service;
    std::string userName;
    QuestionsModel* questionsModel;

};
