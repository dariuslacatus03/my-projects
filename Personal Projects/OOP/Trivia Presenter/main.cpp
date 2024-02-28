#include "MainWindow.h"
#include <QtWidgets/QApplication>

#include "Service.h"
#include "Tests.h"
int main(int argc, char *argv[])
{
    Tests::testAdd();
    Tests::testUpdate();

    QApplication a(argc, argv);
    
    Service service;

    vector<MainWindow*> userGUIs;
    MainWindow* w = new MainWindow(service, "Presenter");
    userGUIs.push_back(w);
    for (int i = 0; i < service.getAllParticipants().size(); i++)
    {
        MainWindow* w = new MainWindow(service, service.getAllParticipants().at(i).getName());
        service.addObserver(w);
        userGUIs.push_back(w);
    }
    
    for (int i = 0; i < userGUIs.size(); i++)
    {
        userGUIs[i]->show();
    }

    return a.exec();
}
