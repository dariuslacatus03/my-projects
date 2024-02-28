#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_MainWindow.h"
#include "Service.h"
#include "IdeasTableModel.h"

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(Service& service, IdeasTableModel* tableModel, std::string writerName, QWidget *parent = nullptr);
    ~MainWindow();

    void connectSignalsAndSlots();

    void savePlot();

    void reviseIdea();

    int getCurrentRowIdeaTable();

    int getCurrentColumnIdeaTable();

    void acceptIdea();

    void addIdea();

private:
    Ui::MainWindowClass ui;
    Service& service;
    IdeasTableModel* tableModel;
    std::string writerName;
};
