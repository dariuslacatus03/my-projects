#include "GUI.h"
#include <algorithm>
#include <random>
#include "SearchWidget.h"


GUI::GUI(Service& service, QuestionsModel* questionsModel, std::string userName, QWidget *parent)
    :service{ service }, questionsModel{ questionsModel }, userName{ userName },  QMainWindow(parent)
{
    ui.setupUi(this);
    this->initializeGUI();
    this->connectSignalsAndSlots();
}

GUI::~GUI()
{}

void GUI::connectSignalsAndSlots()
{
    QObject::connect(ui.questionsListView, &QListView::clicked, this, &GUI::updateAnswers);
    QObject::connect(ui.addQuestionButton, &QPushButton::clicked, this, &GUI::addQuestion);
    QObject::connect(ui.addAnswerButton, &QPushButton::clicked, this, &GUI::addAnswer);
    QObject::connect(ui.searchPushButton, &QPushButton::clicked, this, &GUI::startSearch);
    QObject::connect(ui.answersListWidget, &QListWidget::clicked, this, &GUI::setSpinBox);
    QObject::connect(ui.voteSpinBox, &QSpinBox::valueChanged, this, &GUI::vote);
}

void GUI::initializeGUI()
{
    this->service.addObserver(this);
    service.sortQuestions();
    ui.questionsListView->setModel(questionsModel);
    this->setWindowTitle(QString::fromStdString(userName));
}

int GUI::getSelectedIndex()
{
    return ui.questionsListView->currentIndex().row();
}

void GUI::updateAnswers()
{
    ui.answersListWidget->clear();
    std::vector<Answer>& sortedAnswers = this->service.getAllAnswers();
    sort(sortedAnswers.begin(), sortedAnswers.end(), [](Answer a1, Answer a2)
        {
            return a1.getVotes() > a2.getVotes();
        });
    for (Answer answer : sortedAnswers)
    {
        if (answer.getQuestionId() == this->service.getAllQuestions().at(this->getSelectedIndex()).getId())
        {
            ui.answersListWidget->addItem(QString::fromStdString(answer.toString()));
        }
    }
}

void GUI::addQuestion()
{
    std::string questionText = ui.questionLineEdit->text().toStdString();
    int id = 0;
    for (Question question : this->service.getAllQuestions())
    {
        id = id + question.getId();
    }
    Question question(id, questionText, this->userName);
    this->service.addQuestion(question);
    this->update();
}

void GUI::addAnswer()
{
    int index = this->getSelectedIndex();
    std::string answerText = ui.answerLineEdit->text().toStdString();
    int aId = rand() % 1000;
    Answer answer(aId, this->service.getAllQuestions().at(index).getId(), this->userName, answerText, 0);
    this->service.addAnswer(answer);
    this->notify();
}

void GUI::update()
{
    this->questionsModel->emitDataChanged();
}

void GUI::notify()
{
    for (Observer* observer : this->service.getAllObservers())
    {
        observer->updateAnswers();
    }
}

void GUI::startSearch()
{
    SearchWidget* searchWidget = new SearchWidget(this->service);

    searchWidget->show();
}
#include <sstream>
void GUI::setSpinBox()
{
    std::string data = ui.answersListWidget->currentItem()->text().toStdString();
    std::stringstream datass(data);
    getline(datass, data, '|');
    int id = stoi(data);
    for (Answer answer : this->service.getAllAnswers())
    {
        if (answer.getAnswerId() == id)
        {
            ui.voteSpinBox->setValue(answer.getVotes());
            ui.voteSpinBox->setMaximum(answer.getVotes() + 1);
            ui.voteSpinBox->setMinimum(answer.getVotes() - 1);
            break;
        }
    }
    
}

void GUI::vote()
{
    std::string data = ui.answersListWidget->currentItem()->text().toStdString();
    std::stringstream datass(data);
    getline(datass, data, '|');
    int id = stoi(data);
    for (Answer& answer : this->service.getAllAnswers())
    {
        if (answer.getAnswerId() == id)
        {
            answer.setVotes(ui.voteSpinBox->value());
        }
    }

}