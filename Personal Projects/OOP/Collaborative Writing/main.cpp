#include "MainWindow.h"
#include <QtWidgets/QApplication>
#include "service.h"
#include "IdeasTableModel.h"
#include "Tests.h"

int main(int argc, char *argv[])
{
    Tests tests;
    tests.testAdd();
    tests.testUpdate();

    QApplication a(argc, argv);
    Service service;
    IdeasTableModel* tableModel = new IdeasTableModel(service);

    std::vector<MainWindow*> userMainWindows;

    for (int i = 0; i < service.getAllWriters().size(); i++)
    {
        MainWindow* w = new MainWindow(service, tableModel, service.getAllWriters().at(i).getName());
        userMainWindows.push_back(w);
    }

    for (int i = 0; i < service.getAllWriters().size(); i++)
    {
        userMainWindows[i]->show();
    }
    
    return a.exec();
}
