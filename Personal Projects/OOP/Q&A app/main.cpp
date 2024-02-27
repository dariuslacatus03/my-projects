#include "GUI.h"
#include <QtWidgets/QApplication>
#include "Repository.h"
#include "QuestionsModel.h"


int main(int argc, char *argv[])
{

    QApplication a(argc, argv);
    Service service;
    QuestionsModel* questionsModel = new QuestionsModel(service);

    std::vector<GUI*> userGUIs;
    User user(" ");
    for (int i = 0; i < service.getAllUsers().size(); i++)
    {
        user = service.getAllUsers().at(i);
        GUI* gui = new GUI(service, questionsModel, user.getName());
        
        userGUIs.push_back(gui);
    }

    for (int i = 0; i < service.getAllUsers().size(); i++)
    {
        userGUIs[i]->show();
    }

    return a.exec();
}
