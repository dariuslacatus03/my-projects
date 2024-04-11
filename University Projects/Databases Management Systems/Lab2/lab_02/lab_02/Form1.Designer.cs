using lab_02;

namespace lab_02
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
            this.parentTableGrid = new System.Windows.Forms.DataGridView();
            this.speciesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSetBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSet = new lab_02.FishingAppDataSet();
            this.championshipBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.currentVersionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillSpeciesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillSpeciesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.childTableGrid = new System.Windows.Forms.DataGridView();
            this.fishBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillFishesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillFishesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.championshipTableAdapter = new lab_02.FishingAppDataSetTableAdapters.ChampionshipTableAdapter();
            this.currentVersionTableAdapter = new lab_02.FishingAppDataSetTableAdapters.CurrentVersionTableAdapter();
            this.speciesTableAdapter = new lab_02.FishingAppDataSetTableAdapters.SpeciesTableAdapter();
            this.fishTableAdapter = new lab_02.FishingAppDataSetTableAdapters.FishTableAdapter();
            this.childByParentGrid = new System.Windows.Forms.DataGridView();
            this.addButton = new System.Windows.Forms.Button();
            this.updateButton = new System.Windows.Forms.Button();
            this.removeButton = new System.Windows.Forms.Button();
            this.allFishTableAdapter1 = new lab_02.FishingAppDataSetTableAdapters.AllFishTableAdapter();
            ((System.ComponentModel.ISupportInitialize)(this.parentTableGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childTableGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childByParentGrid)).BeginInit();
            this.SuspendLayout();
            // 
            // parentTableGrid
            // 
            this.parentTableGrid.AutoGenerateColumns = false;
            this.parentTableGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentTableGrid.DataSource = this.speciesBindingSource;
            this.parentTableGrid.Location = new System.Drawing.Point(15, 80);
            this.parentTableGrid.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.parentTableGrid.Name = "parentTableGrid";
            this.parentTableGrid.RowHeadersWidth = 51;
            this.parentTableGrid.RowTemplate.Height = 24;
            this.parentTableGrid.Size = new System.Drawing.Size(581, 199);
            this.parentTableGrid.TabIndex = 2;
            this.parentTableGrid.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.parentTableGrid_CellClick);
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
            this.fillSpeciesToolStrip.Location = new System.Drawing.Point(0, 0);
            this.fillSpeciesToolStrip.Name = "fillSpeciesToolStrip";
            this.fillSpeciesToolStrip.Size = new System.Drawing.Size(1452, 25);
            this.fillSpeciesToolStrip.TabIndex = 11;
            // 
            // fillSpeciesToolStripButton
            // 
            this.fillSpeciesToolStripButton.Name = "fillSpeciesToolStripButton";
            this.fillSpeciesToolStripButton.Size = new System.Drawing.Size(23, 23);
            // 
            // childTableGrid
            // 
            this.childTableGrid.AutoGenerateColumns = false;
            this.childTableGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childTableGrid.DataSource = this.fishBindingSource;
            this.childTableGrid.Location = new System.Drawing.Point(15, 392);
            this.childTableGrid.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.childTableGrid.Name = "childTableGrid";
            this.childTableGrid.RowHeadersWidth = 51;
            this.childTableGrid.RowTemplate.Height = 24;
            this.childTableGrid.Size = new System.Drawing.Size(1085, 192);
            this.childTableGrid.TabIndex = 4;
            // 
            // fillFishesToolStrip
            // 
            this.fillFishesToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillFishesToolStrip.Location = new System.Drawing.Point(0, 25);
            this.fillFishesToolStrip.Name = "fillFishesToolStrip";
            this.fillFishesToolStrip.Size = new System.Drawing.Size(1452, 25);
            this.fillFishesToolStrip.TabIndex = 10;
            // 
            // fillFishesToolStripButton
            // 
            this.fillFishesToolStripButton.Name = "fillFishesToolStripButton";
            this.fillFishesToolStripButton.Size = new System.Drawing.Size(23, 23);
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
            // childByParentGrid
            // 
            this.childByParentGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childByParentGrid.Location = new System.Drawing.Point(690, 80);
            this.childByParentGrid.Name = "childByParentGrid";
            this.childByParentGrid.RowHeadersWidth = 51;
            this.childByParentGrid.RowTemplate.Height = 24;
            this.childByParentGrid.Size = new System.Drawing.Size(583, 199);
            this.childByParentGrid.TabIndex = 6;
            // 
            // addButton
            // 
            this.addButton.Location = new System.Drawing.Point(15, 313);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(75, 23);
            this.addButton.TabIndex = 7;
            this.addButton.Text = "Add";
            this.addButton.UseVisualStyleBackColor = true;
            // 
            // updateButton
            // 
            this.updateButton.Location = new System.Drawing.Point(96, 313);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(92, 23);
            this.updateButton.TabIndex = 8;
            this.updateButton.Text = "Update";
            this.updateButton.UseVisualStyleBackColor = true;
            // 
            // removeButton
            // 
            this.removeButton.Location = new System.Drawing.Point(194, 313);
            this.removeButton.Name = "removeButton";
            this.removeButton.Size = new System.Drawing.Size(99, 23);
            this.removeButton.TabIndex = 9;
            this.removeButton.Text = "Remove";
            this.removeButton.UseVisualStyleBackColor = true;
            // 
            // allFishTableAdapter1
            // 
            this.allFishTableAdapter1.ClearBeforeFill = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1452, 630);
            this.Controls.Add(this.removeButton);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.childByParentGrid);
            this.Controls.Add(this.fillFishesToolStrip);
            this.Controls.Add(this.childTableGrid);
            this.Controls.Add(this.fillSpeciesToolStrip);
            this.Controls.Add(this.parentTableGrid);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.parentTableGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childTableGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childByParentGrid)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.DataGridView parentTableGrid;
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
        private System.Windows.Forms.DataGridView childTableGrid;
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
        private System.Windows.Forms.DataGridView childByParentGrid;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button updateButton;
        private System.Windows.Forms.Button removeButton;
        private FishingAppDataSetTableAdapters.AllFishTableAdapter allFishTableAdapter1;
    }
}

