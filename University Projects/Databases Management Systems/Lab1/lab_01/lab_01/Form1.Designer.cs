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
            this.button2 = new System.Windows.Forms.Button();
            this.speciesGrid = new System.Windows.Forms.DataGridView();
            this.speciesNameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mediumSizeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.recordDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.speciesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSetBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSet = new lab_01.FishingAppDataSet();
            this.championshipBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.currentVersionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillSpeciesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillSpeciesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.fishGrid = new System.Windows.Forms.DataGridView();
            this.fishIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sizeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.speciesNameDataGridViewTextBoxColumn1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.userIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.riverDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.locationNameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fishBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillFishesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillFishesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.championshipTableAdapter = new lab_01.FishingAppDataSetTableAdapters.ChampionshipTableAdapter();
            this.currentVersionTableAdapter = new lab_01.FishingAppDataSetTableAdapters.CurrentVersionTableAdapter();
            this.speciesTableAdapter = new lab_01.FishingAppDataSetTableAdapters.SpeciesTableAdapter();
            this.fishTableAdapter = new lab_01.FishingAppDataSetTableAdapters.FishTableAdapter();
            this.fishBySpeciesGrid = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.speciesGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).BeginInit();
            this.fillSpeciesToolStrip.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).BeginInit();
            this.fillFishesToolStrip.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishBySpeciesGrid)).BeginInit();
            this.SuspendLayout();
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(263, 272);
            this.button2.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(8, 7);
            this.button2.TabIndex = 1;
            this.button2.Text = "button2";
            this.button2.UseVisualStyleBackColor = true;
            // 
            // speciesGrid
            // 
            this.speciesGrid.AutoGenerateColumns = false;
            this.speciesGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.speciesGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.speciesNameDataGridViewTextBoxColumn,
            this.mediumSizeDataGridViewTextBoxColumn,
            this.recordDataGridViewTextBoxColumn});
            this.speciesGrid.DataSource = this.speciesBindingSource;
            this.speciesGrid.Location = new System.Drawing.Point(15, 80);
            this.speciesGrid.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.speciesGrid.Name = "speciesGrid";
            this.speciesGrid.RowHeadersWidth = 51;
            this.speciesGrid.RowTemplate.Height = 24;
            this.speciesGrid.Size = new System.Drawing.Size(581, 199);
            this.speciesGrid.TabIndex = 2;
            this.speciesGrid.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.speciesGrid_CellClick);
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
            // speciesBindingSource
            // 
            this.speciesBindingSource.DataMember = "Species";
            this.speciesBindingSource.DataSource = this.fishingAppDataSetBindingSource;
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
            // fillSpeciesToolStrip
            // 
            this.fillSpeciesToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillSpeciesToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fillSpeciesToolStripButton});
            this.fillSpeciesToolStrip.Location = new System.Drawing.Point(0, 0);
            this.fillSpeciesToolStrip.Name = "fillSpeciesToolStrip";
            this.fillSpeciesToolStrip.Size = new System.Drawing.Size(1452, 27);
            this.fillSpeciesToolStrip.TabIndex = 3;
            this.fillSpeciesToolStrip.Text = "fillSpeciesToolStrip";
            // 
            // fishGrid
            // 
            this.fishGrid.AutoGenerateColumns = false;
            this.fishGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.fishGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.fishIDDataGridViewTextBoxColumn,
            this.sizeDataGridViewTextBoxColumn,
            this.speciesNameDataGridViewTextBoxColumn1,
            this.userIDDataGridViewTextBoxColumn,
            this.riverDataGridViewTextBoxColumn,
            this.locationNameDataGridViewTextBoxColumn});
            this.fishGrid.DataSource = this.fishBindingSource;
            this.fishGrid.Location = new System.Drawing.Point(15, 316);
            this.fishGrid.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.fishGrid.Name = "fishGrid";
            this.fishGrid.RowHeadersWidth = 51;
            this.fishGrid.RowTemplate.Height = 24;
            this.fishGrid.Size = new System.Drawing.Size(1085, 192);
            this.fishGrid.TabIndex = 4;
            // 
            // fishIDDataGridViewTextBoxColumn
            // 
            this.fishIDDataGridViewTextBoxColumn.DataPropertyName = "FishID";
            this.fishIDDataGridViewTextBoxColumn.HeaderText = "FishID";
            this.fishIDDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.fishIDDataGridViewTextBoxColumn.Name = "fishIDDataGridViewTextBoxColumn";
            this.fishIDDataGridViewTextBoxColumn.Width = 125;
            // 
            // sizeDataGridViewTextBoxColumn
            // 
            this.sizeDataGridViewTextBoxColumn.DataPropertyName = "Size";
            this.sizeDataGridViewTextBoxColumn.HeaderText = "Size";
            this.sizeDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.sizeDataGridViewTextBoxColumn.Name = "sizeDataGridViewTextBoxColumn";
            this.sizeDataGridViewTextBoxColumn.Width = 125;
            // 
            // speciesNameDataGridViewTextBoxColumn1
            // 
            this.speciesNameDataGridViewTextBoxColumn1.DataPropertyName = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn1.HeaderText = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn1.MinimumWidth = 6;
            this.speciesNameDataGridViewTextBoxColumn1.Name = "speciesNameDataGridViewTextBoxColumn1";
            this.speciesNameDataGridViewTextBoxColumn1.Width = 125;
            // 
            // userIDDataGridViewTextBoxColumn
            // 
            this.userIDDataGridViewTextBoxColumn.DataPropertyName = "UserID";
            this.userIDDataGridViewTextBoxColumn.HeaderText = "UserID";
            this.userIDDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.userIDDataGridViewTextBoxColumn.Name = "userIDDataGridViewTextBoxColumn";
            this.userIDDataGridViewTextBoxColumn.Width = 125;
            // 
            // riverDataGridViewTextBoxColumn
            // 
            this.riverDataGridViewTextBoxColumn.DataPropertyName = "River";
            this.riverDataGridViewTextBoxColumn.HeaderText = "River";
            this.riverDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.riverDataGridViewTextBoxColumn.Name = "riverDataGridViewTextBoxColumn";
            this.riverDataGridViewTextBoxColumn.Width = 125;
            // 
            // locationNameDataGridViewTextBoxColumn
            // 
            this.locationNameDataGridViewTextBoxColumn.DataPropertyName = "LocationName";
            this.locationNameDataGridViewTextBoxColumn.HeaderText = "LocationName";
            this.locationNameDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.locationNameDataGridViewTextBoxColumn.Name = "locationNameDataGridViewTextBoxColumn";
            this.locationNameDataGridViewTextBoxColumn.Width = 125;
            // 
            // fishBindingSource
            // 
            this.fishBindingSource.DataMember = "Fish";
            this.fishBindingSource.DataSource = this.fishingAppDataSetBindingSource;
            // 
            // fillFishesToolStrip
            // 
            this.fillFishesToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillFishesToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fillFishesToolStripButton});
            this.fillFishesToolStrip.Location = new System.Drawing.Point(0, 27);
            this.fillFishesToolStrip.Name = "fillFishesToolStrip";
            this.fillFishesToolStrip.Size = new System.Drawing.Size(1452, 27);
            this.fillFishesToolStrip.TabIndex = 5;
            this.fillFishesToolStrip.Text = "fillFishesToolStrip";
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
            // fishTableAdapter
            // 
            this.fishTableAdapter.ClearBeforeFill = true;
            // 
            // fishBySpeciesGrid
            // 
            this.fishBySpeciesGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.fishBySpeciesGrid.Location = new System.Drawing.Point(690, 80);
            this.fishBySpeciesGrid.Name = "fishBySpeciesGrid";
            this.fishBySpeciesGrid.RowHeadersWidth = 51;
            this.fishBySpeciesGrid.RowTemplate.Height = 24;
            this.fishBySpeciesGrid.Size = new System.Drawing.Size(583, 199);
            this.fishBySpeciesGrid.TabIndex = 6;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1452, 576);
            this.Controls.Add(this.fishBySpeciesGrid);
            this.Controls.Add(this.fillFishesToolStrip);
            this.Controls.Add(this.fishGrid);
            this.Controls.Add(this.fillSpeciesToolStrip);
            this.Controls.Add(this.speciesGrid);
            this.Controls.Add(this.button2);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.speciesGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).EndInit();
            this.fillSpeciesToolStrip.ResumeLayout(false);
            this.fillSpeciesToolStrip.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).EndInit();
            this.fillFishesToolStrip.ResumeLayout(false);
            this.fillFishesToolStrip.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishBySpeciesGrid)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.DataGridView speciesGrid;
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
        private System.Windows.Forms.DataGridView fishGrid;
        private System.Windows.Forms.BindingSource fishBindingSource;
        private FishingAppDataSetTableAdapters.FishTableAdapter fishTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn fishIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn sizeDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn speciesNameDataGridViewTextBoxColumn1;
        private System.Windows.Forms.DataGridViewTextBoxColumn userIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn riverDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn locationNameDataGridViewTextBoxColumn;
        private System.Windows.Forms.ToolStrip fillFishesToolStrip;
        private System.Windows.Forms.ToolStripButton fillFishesToolStripButton;
        private System.Windows.Forms.DataGridView fishBySpeciesGrid;
    }
}

