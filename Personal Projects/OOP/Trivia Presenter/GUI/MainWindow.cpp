#include "MainWindow.h"
#include "qmessagebox.h"

MainWindow::MainWindow(Service& service, string userName, QWidget *parent)
    :service{ service }, userName{ userName }, QMainWindow(parent)
{
    ui.setupUi(this);
    this->setWindowTitle(QString::fromStdString(userName));
    if (userName == "Presenter")
    {
        populateListAdmin();
    }
    else
    {
        populateListUser();
        updateUserScore();
    }
    connectSignalsAndSlots();

}

void MainWindow::connectSignalsAndSlots()
{
    QObject::connect(ui.addQuestionButton, &QPushButton::clicked, this, &MainWindow::addQuestion);
    QObject::connect(ui.answerButton, &QPushButton::clicked, this, &MainWindow::answerQuestion);
    QObject::connect(ui.questionsListWidget, &QListWidget::clicked, this, &MainWindow::setAnswerButton);
}

void MainWindow::setAnswerButton()
{
    int index = getIndexOfSelectedQuestion();
    if (ui.questionsListWidget->currentItem()->background() == Qt::green)
    {
        ui.answerButton->setEnabled(false);
    }
    else
    {
        ui.answerButton->setEnabled(true);
    }
}

void MainWindow::updateUserScore()
{
    for (Participant p : this->service.getAllParticipants())
    {
        if (p.getName() == userName)
            ui.scoreLineEdit->setText(QString::fromStdString(to_string(p.getScore())));
    }
}

int MainWindow::getIndexOfSelectedQuestion()
{
    return ui.questionsListWidget->currentIndex().row();
}

void MainWindow::answerQuestion()
{
    if (this->userName == "Presenter")
    {
        QMessageBox::critical(this, "Warning", "Only the users can answer questions!");
        return;
    }
    string answer = ui.answerLineEdit->text().toStdString();
    if (answer == "")
    {
        QMessageBox::critical(this, "Warning", "Make sure to answer the question!");
        return;
    }
    int index = getIndexOfSelectedQuestion();
    int questionId = service.getAllQuestions().at(index).getId();


    for (Question& q : this->service.getAllQuestions())
    {
        if (q.getId() == questionId)
        {
            if (answer == q.getAnswer())
            {
                this->service.updateScore(userName, q.getScore());
                ui.questionsListWidget->currentItem()->setBackground(Qt::green);
            }
            else
            {
                ui.questionsListWidget->currentItem()->setBackground(Qt::red);
            }
        }
    }
    this->updateUserScore();
}

void MainWindow::addQuestion()
{
    if (this->userName != "Presenter")
    {
        QMessageBox::critical(this, "Warning", "Only the presenter can add questions!");
        return;
    }
    string sid = (ui.idLineEdit->text().toStdString());
    string question = ui.questionLineEdit->text().toStdString();
    string answer = ui.qAnswerLineEdit->text().toStdString();
    if (sid == "" || question == "" || answer == "")
    {
        QMessageBox::critical(this, "Warning", "No input!");
        return;
    }
    int id = stoi(sid);
    for (Question q : this->service.getAllQuestions())
    {
        if (q.getId() == id)
        {
            QMessageBox::critical(this, "Warning", "A question with the same id already exists!");
            return;
        }
    }
    Question q;
    q.setId(id);
    q.setText(question);
    q.setAnswer(answer);
    q.setScore(10);
    this->service.addQuestion(q);
    this->service.updateFile();
    this->notify();
}


void MainWindow::update()
{
    if (this->userName == "Presenter")
        this->populateListAdmin();
    else
        this->populateListUser();
}


void MainWindow::notify()
{
    for (Observer* o : this->service.getObservers())
    {
        o->update();
    }
}

void MainWindow::populateListAdmin()
{
    ui.questionsListWidget->clear();
    for (Question q : this->service.getAllQuestions())
    {
        ui.questionsListWidget->addItem(QString::fromStdString(q.toStringAdmin()));
    }
}
#include <algorithm>
void MainWindow::populateListUser()
{
    ui.questionsListWidget->clear();
    vector<Question> sortedQuestions = this->service.getAllQuestions();
    sort(sortedQuestions.begin(), sortedQuestions.end(), [this](Question q1, Question q2)
        {
            return q1.getScore() > q2.getScore();
        });
    for (Question q : sortedQuestions)
    {
        ui.questionsListWidget->addItem(QString::fromStdString(q.toStringUser()));
    }
}

MainWindow::~MainWindow()
{}
