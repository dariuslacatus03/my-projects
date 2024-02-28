#pragma once

#include <QMainWindow>
#include "ui_SearchWidget.h"
#include "Service.h"
class SearchWidget : public QMainWindow
{
	Q_OBJECT

public:
	SearchWidget(Service& service, QWidget *parent = nullptr);
	~SearchWidget();

	void connectSignalsAndSlots();

	void showResult();

	void searchQuestion();

	void populateSearchList(std::string searchText);

private:
	Ui::SearchWidgetClass ui;
	Service& service;
};
