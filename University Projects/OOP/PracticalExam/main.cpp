#include "MainWindow.h"
#include <QtWidgets/QApplication>

#include "Service.h"
#include "DepartmentsWidget.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    Service service;
    DepartmentsWidget* dw = new DepartmentsWidget(service);
    vector<MainWindow*> userGUIs;
    for (int i = 0; i < service.getAllDepartments().size(); i++)
    {
        MainWindow* w = new MainWindow(service, service.getAllDepartments().at(i), dw);
        service.addObserver(w);
        userGUIs.push_back(w);
    }
    for (int i = 0; i < service.getAllDepartments().size(); i++)
    {
        userGUIs[i]->show();
    }
    dw->show();
    return a.exec();
}
