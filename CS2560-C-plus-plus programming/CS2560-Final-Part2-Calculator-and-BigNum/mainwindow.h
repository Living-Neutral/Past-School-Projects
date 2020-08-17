#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private:    
    Ui::MainWindow *ui;


private slots:
void digit_pressed();

void unary_button_pressed();
void on_pushButton_clear_released();
void on_pushButton_equal_released();
void binary_button_pressed();
};

#endif // MAINWINDOW_H
