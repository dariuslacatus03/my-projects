#pragma once
#include <qabstractitemmodel.h>
#include "Service.h"

class QuestionsModel : public QAbstractListModel
{
private:
	Service& service;

public:
	QuestionsModel(Service& service) : service{ service } {};

	int rowCount(const QModelIndex& parent = QModelIndex()) const override;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;

	void emitDataChanged() { emit dataChanged(index(0, 0), index(service.getAllQuestions().size(), service.getAllQuestions().size()), {Qt::DisplayRole}); }
};

