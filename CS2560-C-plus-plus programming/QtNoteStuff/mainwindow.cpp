#include "mainwindow.h"
#include "ui_mainwindow.h"


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    this->setCentralWidget(ui->textEdit);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_actionOpen_triggered()
{
    QString fileName=QFileDialog::getOpenFileName(this, "Open the File");
    QFile file(fileName);
    currentFile=fileName;
    if(!file.open(QIODevice::ReadOnly|QFile::Text))
    {
        QMessageBox::warning(this,"Warning","Can Not open file: "+
                             file.errorString());
    }

    setWindowTitle(fileName);
    QTextStream in(&file);
    QString text = in.readAll();
    ui->textEdit->setText(text);
    file.close();

}


void MainWindow::on_actionSave_triggered()
{
    QString fileName=QFileDialog::getSaveFileName(this,"Save the File");
    QFile file(fileName);


    // Open the file notify user if problem
    if(!file.open(QFile::WriteOnly|QFile::Text))
    {
        QMessageBox::warning(this, "Warning", "Can not save file: "+
                                         file.errorString());
    }

    // Store current file
    currentFile=fileName;

    //Set window title to name of file
    setWindowTitle(fileName);


    // When writing to out writes to an actual file
    QTextStream out(&file);

    //Copy text in textEdit widget convert
    QString text=ui->textEdit->toPlainText();

    // writes to a file and then closes
    out<<text;
    file.close();


}

void MainWindow::on_actionExit_triggered()
{
    QApplication::quit();
}

void MainWindow::on_actionCopy_triggered()
{
    ui->textEdit->copy();
}

void MainWindow::on_actionPaste_triggered()
{
    ui->textEdit->paste();
}


void MainWindow::on_actionUndo_triggered()
{
    ui->textEdit->undo();
}

void MainWindow::on_actionRedo_triggered()
{
    ui->textEdit->undo();
}

void MainWindow::on_actionNew_triggered()
{
    currentFile.clear();

    ui->textEdit->setText(QString());
}
