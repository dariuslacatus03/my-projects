using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab_02
{
    public partial class Form1 : Form
    {

        const string connectionString = "Data Source=DESKTOP-FCVPJ3V\\SQLEXPRESS;Initial Catalog=FishingApp;User=sa;Password=admin";
        SqlConnection dbConnection = new SqlConnection(connectionString);

        SqlDataAdapter dataAdapterParentTable;
        SqlDataAdapter dataAdapterChildTable;

        DataSet parentAndChildDataSet = new DataSet();

        SqlCommandBuilder sqlCommandBuilder;

        BindingSource bindingSourceParentTable;
        BindingSource bindingSourceChildTable;

        String parentTableName = ConfigurationManager.AppSettings["parentTableName"];
        String childTableName = ConfigurationManager.AppSettings["childTableName"];



        public Form1()
        {
            InitializeComponent();
            // Set AutoGenerateColumns for parentTableGrid
            parentTableGrid.AutoGenerateColumns = true;
        }


        private void Form1_Load(object sender, EventArgs e)
        {
            String parentToChildRelationName = ConfigurationManager.AppSettings["parentToChildRelationName"];
            String childToParentRelationName = ConfigurationManager.AppSettings["childToParentRelationName"];

            string selectQueryParentTable = $"SELECT * FROM {parentTableName}";
            string selectQueryChildTable = $"SELECT * FROM {childTableName}";

            dataAdapterParentTable = new SqlDataAdapter(selectQueryParentTable, dbConnection);
            dataAdapterChildTable = new SqlDataAdapter(selectQueryChildTable, dbConnection);

            dataAdapterParentTable.Fill(parentAndChildDataSet, parentTableName);
            dataAdapterChildTable.Fill(parentAndChildDataSet, childTableName);

            sqlCommandBuilder = new SqlCommandBuilder(dataAdapterChildTable);

            DataRelation dataRelation = new DataRelation("FK_ParentTable_ChildTable",
                    parentAndChildDataSet.Tables[parentTableName].Columns[parentToChildRelationName],
                    parentAndChildDataSet.Tables[childTableName].Columns[childToParentRelationName]);

            parentAndChildDataSet.Relations.Add(dataRelation);

            bindingSourceParentTable = new BindingSource();
            bindingSourceParentTable.DataSource = parentAndChildDataSet;
            bindingSourceParentTable.DataMember = parentTableName;

            bindingSourceChildTable = new BindingSource();
            bindingSourceChildTable.DataSource = bindingSourceParentTable;
            bindingSourceChildTable.DataMember = "FK_ParentTable_ChildTable";

            parentTableGrid.DataSource = bindingSourceParentTable;
            childTableGrid.DataSource= bindingSourceChildTable;
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            try
            {
                dataAdapterChildTable.Update(parentAndChildDataSet, childTableName);
            }
            catch (Exception ex) 
            {
                MessageBox.Show(ex.ToString());
            }
        }

    }
}
