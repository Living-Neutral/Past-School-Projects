#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDebug>

int firstNum;
bool userIsTypingSecondNum=false;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    // Linking the digits to the calc screen
    connect(ui->pushButton_Num0,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num1,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num2,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num3,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num4,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num5,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num6,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num7,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num8,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_Num9,SIGNAL(released()),this,SLOT(digit_pressed()));

    //Linking to the unary operations
    connect(ui->pushButton_signChange,SIGNAL(released()),this,SLOT(unary_button_pressed()));
    connect(ui->pushButton_mod,SIGNAL(released()),this,SLOT(unary_button_pressed()));

    //Linking to the binary operations
    connect(ui->pushButton_add,SIGNAL(released()),this,SLOT(binary_button_pressed()));
    connect(ui->pushButton_subtract,SIGNAL(released()),this,SLOT(binary_button_pressed()));
    connect(ui->pushButton_multiply,SIGNAL(released()),this,SLOT(binary_button_pressed()));
    connect(ui->pushButton_divide,SIGNAL(released()),this,SLOT(binary_button_pressed()));
    connect(ui->pushButton_mod,SIGNAL(released()),this,SLOT(binary_button_pressed()));

    ui->pushButton_add->setCheckable(true);
    ui->pushButton_subtract->setCheckable(true);
    ui->pushButton_multiply->setCheckable(true);
    ui->pushButton_divide->setCheckable(true);
    ui->pushButton_mod->setCheckable(true);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::digit_pressed()
{
    //qDebug()<<"test";

    QPushButton *Button=(QPushButton*)sender();
    double labelNumber;
    QString newLabel;

    if(ui->pushButton_add->isChecked()||ui->pushButton_subtract->isChecked()||
            ui->pushButton_multiply->isChecked()||ui->pushButton_divide->isChecked()||
            ui->pushButton_mod->isChecked()&& (!userIsTypingSecondNum))
    {
        labelNumber=Button->text().toInt();
        userIsTypingSecondNum=true;

    }
    else
    {
        labelNumber=(ui->label->text()+Button->text()).toInt();
    }


    newLabel=QString::number(labelNumber,'g',17);
    ui->label->setText(newLabel);
}


void MainWindow::unary_button_pressed()
{
    QPushButton *Button=(QPushButton*)sender();
    int labelNumber;
    QString newLabel;

    if(Button->text()=="+/-")
    {
        labelNumber=ui->label->text().toInt();
        labelNumber=labelNumber*-1;
        newLabel=QString::number(labelNumber,'g',17);
        ui->label->setText(newLabel);
    }


}

void MainWindow::on_pushButton_clear_released()
{
    ui->pushButton_add->setChecked(false);
    ui->pushButton_subtract->setChecked(false);
    ui->pushButton_multiply->setChecked(false);
    ui->pushButton_divide->setChecked(false);
    ui->pushButton_mod->setChecked(false);

    ui->label->setText("0");
}

void MainWindow::on_pushButton_equal_released()
{
    int labelNumber,secondNum;
    QString newLabel;

    secondNum=ui->label->text().toInt();


    if(ui->pushButton_add->isChecked())
    {
        labelNumber=firstNum+secondNum;
        newLabel=QString::number(labelNumber,'g',17);
        ui->label->setText(newLabel);
        ui->pushButton_add->setChecked(false);
    }


    else if(ui->pushButton_subtract->isChecked())
    {
        labelNumber=firstNum-secondNum;
        newLabel=QString::number(labelNumber,'g',17);
        ui->label->setText(newLabel);
        ui->pushButton_subtract->setChecked(false);
    }


    else if(ui->pushButton_multiply->isChecked())
    {
        labelNumber=firstNum*secondNum;
        newLabel=QString::number(labelNumber,'g',17);
        ui->label->setText(newLabel);
        ui->pushButton_multiply->setChecked(false);
    }



    else if(ui->pushButton_divide->isChecked())
    {
        labelNumber=firstNum/secondNum;
        newLabel=QString::number(labelNumber,'g',17);
        ui->label->setText(newLabel);
        ui->pushButton_divide->setChecked(false);
    }


    else if(ui->pushButton_mod->isChecked())
    {
        labelNumber=firstNum%secondNum;
        newLabel=QString::number(labelNumber,'g',17);
        ui->label->setText(newLabel);
        ui->pushButton_mod->setChecked(false);
    }

    userIsTypingSecondNum=false;
}

void MainWindow::binary_button_pressed()
{
    QPushButton *Button=(QPushButton*)sender();

    Button->setChecked(true);

    firstNum=ui->label->text().toInt();
}
