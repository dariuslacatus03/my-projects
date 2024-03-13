namespace lab_01
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.championshipBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.currentVersionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.speciesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.speciesNameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mediumSizeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.recordDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fishingAppDataSetBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSet = new lab_01.FishingAppDataSet();
            this.championshipTableAdapter = new lab_01.FishingAppDataSetTableAdapters.ChampionshipTableAdapter();
            this.currentVersionTableAdapter = new lab_01.FishingAppDataSetTableAdapters.CurrentVersionTableAdapter();
            this.speciesTableAdapter = new lab_01.FishingAppDataSetTableAdapters.SpeciesTableAdapter();
            this.fillSpeciesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillSpeciesToolStripButton = new System.Windows.Forms.ToolStripButton();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).BeginInit();
            this.fillSpeciesToolStrip.SuspendLayout();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(306, 368);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(184, 58);
            this.button1.TabIndex = 0;
            this.button1.Text = "View Data Source";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(263, 272);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(8, 8);
            this.button2.TabIndex = 1;
            this.button2.Text = "button2";
            this.button2.UseVisualStyleBackColor = true;
            // 
            // dataGridView1
            // 
            this.dataGridView1.AutoGenerateColumns = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.speciesNameDataGridViewTextBoxColumn,
            this.mediumSizeDataGridViewTextBoxColumn,
            this.recordDataGridViewTextBoxColumn});
            this.dataGridView1.DataSource = this.speciesBindingSource;
            this.dataGridView1.Location = new System.Drawing.Point(39, 48);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.Size = new System.Drawing.Size(408, 271);
            this.dataGridView1.TabIndex = 2;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // championshipBindingSource
            // 
            this.championshipBindingSource.DataMember = "Championship";
            this.championshipBindingSource.DataSource = this.fishingAppDataSetBindingSource;
            // 
            // currentVersionBindingSource
            // 
            this.currentVersionBindingSource.DataMember = "CurrentVersion";
            this.currentVersionBindingSource.DataSource = this.fishingAppDataSetBindingSource;
            // 
            // speciesBindingSource
            // 
            this.speciesBindingSource.DataMember = "Species";
            this.speciesBindingSource.DataSource = this.fishingAppDataSetBindingSource;
            // 
            // speciesNameDataGridViewTextBoxColumn
            // 
            this.speciesNameDataGridViewTextBoxColumn.DataPropertyName = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn.HeaderText = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.speciesNameDataGridViewTextBoxColumn.Name = "speciesNameDataGridViewTextBoxColumn";
            this.speciesNameDataGridViewTextBoxColumn.Width = 125;
            // 
            // mediumSizeDataGridViewTextBoxColumn
            // 
            this.mediumSizeDataGridViewTextBoxColumn.DataPropertyName = "MediumSize";
            this.mediumSizeDataGridViewTextBoxColumn.HeaderText = "MediumSize";
            this.mediumSizeDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.mediumSizeDataGridViewTextBoxColumn.Name = "mediumSizeDataGridViewTextBoxColumn";
            this.mediumSizeDataGridViewTextBoxColumn.Width = 125;
            // 
            // recordDataGridViewTextBoxColumn
            // 
            this.recordDataGridViewTextBoxColumn.DataPropertyName = "Record";
            this.recordDataGridViewTextBoxColumn.HeaderText = "Record";
            this.recordDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.recordDataGridViewTextBoxColumn.Name = "recordDataGridViewTextBoxColumn";
            this.recordDataGridViewTextBoxColumn.Width = 125;
            // 
            // fishingAppDataSetBindingSource
            // 
            this.fishingAppDataSetBindingSource.DataSource = this.fishingAppDataSet;
            this.fishingAppDataSetBindingSource.Position = 0;
            // 
            // fishingAppDataSet
            // 
            this.fishingAppDataSet.DataSetName = "FishingAppDataSet";
            this.fishingAppDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // championshipTableAdapter
            // 
            this.championshipTableAdapter.ClearBeforeFill = true;
            // 
            // currentVersionTableAdapter
            // 
            this.currentVersionTableAdapter.ClearBeforeFill = true;
            // 
            // speciesTableAdapter
            // 
            this.speciesTableAdapter.ClearBeforeFill = true;
            // 
            // fillSpeciesToolStrip
            // 
            this.fillSpeciesToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillSpeciesToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fillSpeciesToolStripButton});
            this.fillSpeciesToolStrip.Location = new System.Drawing.Point(0, 0);
            this.fillSpeciesToolStrip.Name = "fillSpeciesToolStrip";
            this.fillSpeciesToolStrip.Size = new System.Drawing.Size(800, 27);
            this.fillSpeciesToolStrip.TabIndex = 3;
            this.fillSpeciesToolStrip.Text = "fillSpeciesToolStrip";
            // 
            // fillSpeciesToolStripButton
            // 
            this.fillSpeciesToolStripButton.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Text;
            this.fillSpeciesToolStripButton.Name = "fillSpeciesToolStripButton";
            this.fillSpeciesToolStripButton.Size = new System.Drawing.Size(82, 22);
            this.fillSpeciesToolStripButton.Text = "FillSpecies";
            this.fillSpeciesToolStripButton.Click += new System.EventHandler(this.fillSpeciesToolStripButton_Click_1);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.fillSpeciesToolStrip);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).EndInit();
            this.fillSpeciesToolStrip.ResumeLayout(false);
            this.fillSpeciesToolStrip.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.BindingSource fishingAppDataSetBindingSource;
        private FishingAppDataSet fishingAppDataSet;
        private System.Windows.Forms.BindingSource championshipBindingSource;
        private FishingAppDataSetTableAdapters.ChampionshipTableAdapter championshipTableAdapter;
        private System.Windows.Forms.BindingSource currentVersionBindingSource;
        private FishingAppDataSetTableAdapters.CurrentVersionTableAdapter currentVersionTableAdapter;
        private System.Windows.Forms.BindingSource speciesBindingSource;
        private FishingAppDataSetTableAdapters.SpeciesTableAdapter speciesTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn speciesNameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn mediumSizeDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn recordDataGridViewTextBoxColumn;
        private System.Windows.Forms.ToolStrip fillSpeciesToolStrip;
        private System.Windows.Forms.ToolStripButton fillSpeciesToolStripButton;
    }
}

