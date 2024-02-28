#include "SearchWidget.h"
#include "GUI.h"

SearchWidget::SearchWidget(Service& service, QWidget *parent)
	:service{ service }, QMainWindow(parent)
{
	ui.setupUi(this);
	connectSignalsAndSlots();
}

SearchWidget::~SearchWidget()
{}

void SearchWidget::connectSignalsAndSlots()
{
	QObject::connect(ui.searchLineEdit, &QLineEdit::textChanged, this, &SearchWidget::searchQuestion);
	QObject::connect(ui.searchLineEdit, &QLineEdit::editingFinished, this, &SearchWidget::showResult);
}
#include <algorithm>
void SearchWidget::showResult()
{
	ui.searchListWidget->clear();
	std::string searchText = ui.searchLineEdit->text().toStdString();
	Question shownQuestion(0, " ", " ");
	for (Question question : this->service.getAllQuestions())
	{
		if (question.getText().find(searchText) != std::string::npos)
		{
			shownQuestion = question;
			ui.searchListWidget->addItem(QString::fromStdString(question.toString()));
			break;
		}
	}
	std::vector<Answer> questionAnswers;
	questionAnswers.clear();
	for (Answer answer : this->service.getAllAnswers())
	{
		if (answer.getQuestionId() == shownQuestion.getId())
			questionAnswers.push_back(answer);
	}
	sort(questionAnswers.begin(), questionAnswers.end(), [](Answer a1, Answer a2) {
		return a1.getVotes() > a2.getVotes();
		});
	int count = 0;
	for (Answer answer : questionAnswers)
	{
		if (count == 3)
			break;
		ui.searchListWidget->addItem(QString::fromStdString(answer.toString()));
		count++;
	}
}

void SearchWidget::searchQuestion()
{
	std::string searchText = ui.searchLineEdit->text().toStdString();

	populateSearchList(searchText);
}

void SearchWidget::populateSearchList(std::string searchText)
{
	ui.searchListWidget->clear();
	for (Question question : this->service.getAllQuestions())
	{
		if (question.getText().find(searchText) != std::string::npos)
		{
			ui.searchListWidget->addItem(QString::fromStdString(question.toString()));
		}
	}
}