#include "MainWindow.h"
#include "Service.h"
#include <algorithm>
#include <sstream>
#include <qmessagebox.h>


MainWindow::MainWindow(Service& serv, Department dep, DepartmentsWidget* dw, QWidget* parent)
    : service{ serv }, department{ dep }, dw{ dw }, QMainWindow(parent)
{
    ui.setupUi(this);
    this->setWindowTitle(QString::fromStdString(department.getName()));
    ui.depDescLineEdit->setText(QString::fromStdString(department.getDescription()));
    populateVolWidgets();
    connectSignalsAndSlots();
}

void MainWindow::connectSignalsAndSlots()
{
    QObject::connect(ui.addVolButton, &QPushButton::clicked, this, &MainWindow::addVolunteer);
    QObject::connect(ui.assignButton, &QPushButton::clicked, this, &MainWindow::assignVolunteer);
    QObject::connect(ui.searchButton, &QPushButton::clicked, this, &MainWindow::searchVolunteers);
}

void MainWindow::searchVolunteers()
{
    ui.unasgnVolListWidget->clear();
    vector<tuple<Volunteer, int>> volTuple;
    vector<string> interests;
    int score;
    int foundWords;
    vector<string> departmentDesc;
    string word;
    for (Volunteer v : this->service.getAllVolunteers())
    {
        if (v.getDepartment().getName() == "-" && v.getDepartment().getDescription() == "-")
        {
            score = 0;
            foundWords = 0;
            interests = v.getInterests();
            departmentDesc.clear();
            for (string i : interests)
            {
                if (department.getDescription().find(i) != -1)
                {
                    foundWords++;
                }
                stringstream descSS(department.getDescription());
                while (getline(descSS, word, ' '))
                {
                    departmentDesc.push_back(word);
                }
                score = foundWords / departmentDesc.size();
            }
            volTuple.push_back(make_tuple(v, score));
        }
    }
    sort(volTuple.begin(), volTuple.end(), [](tuple<Volunteer, int> t1, tuple<Volunteer, int> t2)
        {
            return std::get<1>(t1) > std::get<1>(t2);
        });
    int c = 0;
    for (tuple<Volunteer, int> t : volTuple)
    {
        if (c == 3)
            break;
        ui.unasgnVolListWidget->addItem(QString::fromStdString(std::get<0>(t).toString()));
        c++;
    }

}

void MainWindow::assignVolunteer()
{
    int index = ui.unasgnVolListWidget->currentIndex().row();
    vector<Volunteer> unasgnVol;
    for (Volunteer v : this->service.getAllVolunteers())
    {
        if (v.getDepartment().getName() == "-" && v.getDepartment().getDescription() == "-")
        {
            unasgnVol.push_back(v);
        }
    }
    for (Volunteer& v : this->service.getAllVolunteers())
    {
        if (v.getName() == unasgnVol.at(index).getName())
        {
            v.setDepartment(department);
            break;
        }
    }
    this->service.updateVolFile();
    this->notify();
}

void MainWindow::addVolunteer()
{
    string name = ui.nameLineEdit->text().toStdString();
    string email = ui.emailLineEdit->text().toStdString();
    if (name == "" || email == "")
    {
        QMessageBox::critical(this, "Warning", "No input at name/email !");
        return;
    }
    string interestsLine = ui.intsLineEdit->text().toStdString();
    vector<string> interests;
    stringstream interestsSS(interestsLine);
    string interest;
    while (getline(interestsSS, interest, ','))
    {
        interests.push_back(interest);
    }
    Department dep;
    dep.setName("-");
    dep.setDescription("-");
    Volunteer v;
    v.setName(name);
    v.setEmail(email);
    v.setInterests(interests);
    v.setDepartment(dep);
    service.getAllVolunteers().push_back(v);
    this->service.updateVolFile();
    this->notify();
}

void MainWindow::update()
{
    this->populateVolWidgets();
    this->dw->update();
}

void MainWindow::notify()
{
    for (Observer* o : this->service.getObservers())
    {
        o->update();
    }
}

void MainWindow::populateVolWidgets()
{
    ui.asgnVolListWidget->clear();
    ui.unasgnVolListWidget->clear();
    vector<Volunteer> sortedAsgnVolunteers;
    vector<Volunteer> unasgnVol;
    for (Volunteer v : this->service.getAllVolunteers())
    {
        if (v.getDepartment() == department)
        {
            sortedAsgnVolunteers.push_back(v);
        }
        else if (v.getDepartment().getName() == "-" && v.getDepartment().getDescription() == "-")
        {
            unasgnVol.push_back(v);
        }
    }
    sort(sortedAsgnVolunteers.begin(), sortedAsgnVolunteers.end(), [](Volunteer v1, Volunteer v2)
        {
            return v1.getName() < v2.getName();
        });
    for (Volunteer v : sortedAsgnVolunteers)
    {
        ui.asgnVolListWidget->addItem(QString::fromStdString(v.toString()));
    }
    for (Volunteer v : unasgnVol)
    {
        ui.unasgnVolListWidget->addItem(QString::fromStdString(v.toString()));
    }
}

MainWindow::~MainWindow()
{}
