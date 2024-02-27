#include "DepartmentsWidget.h"
#include <tuple>


DepartmentsWidget::DepartmentsWidget(Service& service, QWidget *parent)
	: service{ service }, QMainWindow(parent)
{
	ui.setupUi(this);
	populateList();
}

void DepartmentsWidget::update()
{
	this->populateList();
}

void DepartmentsWidget::populateList()
{
	ui.departmentsListWidget->clear();
	vector<tuple<string, int>> deps;
	string dep;
	int count;
	for (Department d : this->service.getAllDepartments())
	{
		count = 0;
		for (Volunteer v : this->service.getAllVolunteers())
		{
			if (d == v.getDepartment())
				count++;
		}
		dep = d.getName() + " | " + d.getDescription() + " | " + "Number of volunteers: " + to_string(count);
		deps.push_back(make_tuple(dep, count));
	}
	sort(deps.begin(), deps.end(), [](tuple<string, int> t1, tuple<string, int> t2)
		{
			return std::get<1>(t1) < std::get<1>(t2);
		});
	for (tuple<string, int> t : deps)
	{
		ui.departmentsListWidget->addItem(QString::fromStdString(std::get<0>(t)));
	}
}

DepartmentsWidget::~DepartmentsWidget()
{}
