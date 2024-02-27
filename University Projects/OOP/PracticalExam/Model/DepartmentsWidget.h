#pragma once

#include <QMainWindow>
#include "ui_DepartmentsWidget.h"

#include "Service.h"


class DepartmentsWidget : public QMainWindow, public Observer
{
	Q_OBJECT

public:
	DepartmentsWidget(Service& service, QWidget *parent = nullptr);
	void update();
	void populateList();
	~DepartmentsWidget();

private:
	Ui::DepartmentsWidgetClass ui;
	Service& service;
};
