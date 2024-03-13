using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab_01
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'fishingAppDataSet.Species' table. You can move, or remove it, as needed.
            this.speciesTableAdapter.Fill(this.fishingAppDataSet.Species);
            // TODO: This line of code loads data into the 'fishingAppDataSet.CurrentVersion' table. You can move, or remove it, as needed.
            this.currentVersionTableAdapter.Fill(this.fishingAppDataSet.CurrentVersion);
            // TODO: This line of code loads data into the 'fishingAppDataSet.Championship' table. You can move, or remove it, as needed.
            this.championshipTableAdapter.Fill(this.fishingAppDataSet.Championship);

        }

        private void fillSpeciesToolStripButton_Click(object sender, EventArgs e)
        {
            try
            {
                this.speciesTableAdapter.FillSpecies(this.fishingAppDataSet.Species);
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }

        }

        private void fillSpeciesToolStripButton_Click_1(object sender, EventArgs e)
        {
            try
            {
                this.speciesTableAdapter.FillSpecies(this.fishingAppDataSet.Species);
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }

        }
    }
}
