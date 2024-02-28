#include "MainWindow.h"
#include <qmessagebox.h>

MainWindow::MainWindow(Service& service, IdeasTableModel* tableModel, std::string writerName, QWidget *parent)
    :service{ service }, tableModel{ tableModel }, writerName{ writerName }, QMainWindow(parent)
{
    ui.setupUi(this);
    ui.ideasTableView->setModel(tableModel);
    this->setWindowTitle(QString::fromStdString(writerName));
    connectSignalsAndSlots();
}

MainWindow::~MainWindow()
{}

void MainWindow::connectSignalsAndSlots()
{
    QObject::connect(ui.addIdeaButton, &QPushButton::clicked, this, &MainWindow::addIdea);
    QObject::connect(ui.acceptIdeaButton, &QPushButton::clicked, this, &MainWindow::acceptIdea);
    QObject::connect(ui.reviseIdeaButton, &QPushButton::clicked, this, &MainWindow::reviseIdea);
    QObject::connect(ui.savePlotButton, &QPushButton::clicked, this, &MainWindow::savePlot);
}

void MainWindow::savePlot()
{
    this->service.savePlot();
}

void MainWindow::reviseIdea()
{
    for (Writer writer : this->service.getAllWriters())
    {
        if (writer.getName() == writerName)
        {
            if (writer.getExpertise() != "Senior")
            {
                QMessageBox::critical(this, "Warning", "Only seniors can accept/revise ideas");
                return;
            }
        }
    }
    int row = this->getCurrentRowIdeaTable();
    if (row == -1)
        return;
    int col = this->getCurrentColumnIdeaTable();
    if (col == -1)
        return;
    if (col != 0)
    {
        QMessageBox::critical(this, "Warning", "No idea selected");
        return;
    }
    std::string revisedIdea = ui.reviseLineEdit->text().toStdString();
    if (revisedIdea == "")
    {
        QMessageBox::critical(this, "Warning", "No idea wrriten");
        return;
    }
    this->service.getAllIdeas().at(row).setDescription(revisedIdea);
    this->tableModel->emitDataChanged();
}

int MainWindow::getCurrentRowIdeaTable()
{
    int row = ui.ideasTableView->currentIndex().row();
    if (row == -1)
    {
        QMessageBox::critical(this, "Warning", "No cell selected");
        return -1;
    }
    return row;
}

int MainWindow::getCurrentColumnIdeaTable()
{
    int col = ui.ideasTableView->currentIndex().column();
    if (col == -1)
    {
        QMessageBox::critical(this, "Warning", "No cell selected");
        return -1;
    }
    return col;
}

void MainWindow::acceptIdea()
{
    for (Writer writer : this->service.getAllWriters())
    {
        if (writer.getName() == writerName)
        {
            if (writer.getExpertise() != "Senior")
            {
                QMessageBox::critical(this, "Warning", "Only seniors can accept ideas");
                return ;
            }
        }
    }
    int row = this->getCurrentRowIdeaTable();
    if (row == -1)
        return;
    int col = this->getCurrentColumnIdeaTable();
    if (col == -1)
        return;
    if (col != 0)
    {
        QMessageBox::critical(this, "Warning", "No idea selected");
        return;
    }
    if (this->service.getAllIdeas().at(row).getStatus() == "accepted")
    {
        QMessageBox::critical(this, "Warning", "Idea is already accepted");
        return;
    }
    this->service.updateStatus(this->service.getAllIdeas().at(row), "accepted");
    this->tableModel->emitDataChanged();
}

void MainWindow::addIdea()
{
    std::string ideaText = ui.descriptionLineEdit->text().toStdString();
    int act = stoi(ui.actLineEdit->text().toStdString());
    if (act < 1 || act > 3)
    {
        QMessageBox::critical(this, "Warning", "Act must be 1, 2 or 3");
        return;
    }
    if (ideaText == "")
    {
        QMessageBox::critical(this, "Warning", "Must write description");
        return;
    }
    for (Idea idea : this->service.getAllIdeas())
    {
        if (ideaText == idea.getDescription() && act == idea.getAct())
        {
            QMessageBox::critical(this, "Warning", "Idea and act already exists");
            return;
        }
    }
    Idea ideaToAdd;
    ideaToAdd.setAct(act);
    ideaToAdd.setDescription(ideaText);
    ideaToAdd.setCreator(writerName);
    ideaToAdd.setStatus("proposed");
    this->service.addIdea(ideaToAdd);
    this->tableModel->emitDataChanged();
}