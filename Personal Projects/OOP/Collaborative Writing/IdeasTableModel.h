#pragma once
#include <qabstractitemmodel.h>
#include "Service.h"

class IdeasTableModel : public QAbstractTableModel
{
private:
	Service& service;

public:
	IdeasTableModel(Service& service) : service{ service } { service.sortIdeasByAct(); };

	int rowCount(const QModelIndex& parent = QModelIndex()) const;
	int columnCount(const QModelIndex& parent = QModelIndex()) const;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;

	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;

	void emitDataChanged() { emit layoutChanged(); }
};


