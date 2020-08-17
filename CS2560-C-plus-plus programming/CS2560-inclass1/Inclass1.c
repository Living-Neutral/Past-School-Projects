/*
 ============================================================================
 Name        : Inclass1.c
 Author      : Jeffrey McDonald
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include<string.h>

typedef int number;
union Data
{
   int i;
   float f;
   char str[20];
};

struct Books
{
   char  title[50];
   char  author[50];
   char  subject[100];
   int   book_id;
};

void swap(int *x, int *y)  //defining function called swap
{
    int z = *x;
    *x = *y;
    *y = z;
}
void printBook( struct Books *book );
int main(void)
{
    printf("swap\n");
    int a = 45, b = 35;
    printf("Before Swap\n");
    printf("a = %d b = %d\n",a,b);
    swap(&a, &b);
    printf("After Swap with pass by reference\n");
    printf("a = %d b = %d\n",a,b);

    printf("Structure ");
    struct Books Book1;        /* Declare Book1 of type Book */
    struct Books Book2;        /* Declare Book2 of type Book */


    strcpy( Book1.title, "C Programming");
    strcpy( Book1.author, "Nuha Ali");
    strcpy( Book1.subject, "C Programming Tutorial");
    Book1.book_id = 6495407;


    strcpy( Book2.title, "Telecom Billing");
    strcpy( Book2.author, "Zara Ali");
    strcpy( Book2.subject, "Telecom Billing Tutorial");
    Book2.book_id = 6495700;

       /* print Book1 info by passing address of Book1 */
       printBook( &Book1 );

       /* print Book2 info by passing address of Book2 */
       printBook( &Book2 );

printf("Union");
       union Data data;

         data.i = 10;
         printf( "data.i : %d\n", data.i);

         data.f = 220.5;
         printf( "data.f : %f\n", data.f);

         strcpy( data.str, "C Programming");
         printf( "data.str : %s\n", data.str);

         printf("\nSwitch program");
         char grade = 'B';

            switch(grade) {
               case 'A' :
                  printf("Excellent!\n" );
                  break;
               case 'B' :
               case 'C' :
                  printf("\nWell done\n" );
                  break;
               case 'D' :
                  printf("You passed\n" );
                  break;
               case 'F' :
                  printf("Better try again\n" );
                  break;
               default :
                  printf("Invalid grade\n" );
            }

            printf("Your grade is  %c\n", grade );

            printf("\nInput\n");
            number c;
            printf( "Enter a value :");
            fflush(stdout);  //ECLIPSE BUG
            c = getchar( );

            printf( "\nYou entered: ");
            putchar( c );

            char str[100];
            int i;
            printf("\nEnter a value: ");
            fflush(stdout);
            scanf("%s %d",str,&i);
            printf("\nYou entered:%s %d ",str,i);
}
void printBook( struct Books *book )
    {

         printf( "Book title : %s\n", book->title);
         printf( "Book author : %s\n", book->author);
         printf( "Book subject : %s\n", book->subject);
         printf( "Book book_id : %d\n", book->book_id);
    }



