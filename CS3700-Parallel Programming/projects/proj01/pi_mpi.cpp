#include "mpi.h"
#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <time.h>

double doubleRand(){
  return ((double)rand()) / ((double)RAND_MAX);
}

double distance(double x,double y, double x2, double y2){
  return sqrt( pow((x2-x),2) + pow((y2-y),2) );
}

int main(int argc, char *argv[]){
   
   /* init */
   int myid, numprocs;
   
   int namelen;

   char processor_name[MPI_MAX_PROCESSOR_NAME];
   
   MPI_Init(&argc,&argv);
   MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
   MPI_Comm_rank(MPI_COMM_WORLD, &myid);
   MPI_Get_processor_name(processor_name, &namelen);
   
     
   int points = 1000000;
   int s = (int)floor(points/numprocs);
   int s0 = s+points%numprocs;
   int startIndex = s0+(myid-1)*s;
   int endIndex= startIndex + s;
   
   srand((unsigned)(myid));
   
   /* worktime*/

   

   double startwtime;
   if(myid==0){
      startwtime = MPI_Wtime();
   }   
   
   int count = 0;

   // masters work
   if (myid == 0){
      // masters work     
      for(int i = 0; i<s0;i++)
      {
         double master_x = doubleRand(); 
         double master_y = doubleRand();
         double dist = distance(master_x,master_y,0,0);
         if(dist<=1)
         {
            count++;
         }
         //printf("Process %d - startIndex 0 endIndex %d; count %ld\n" ,myid, startIndex, endIndex-1,count);
      }

   }

   // slave's work 
   else{
    for(int i =startIndex; i<endIndex;i++)
    {
       double slave_x = doubleRand();
       double slave_y = doubleRand();
       double dist2 = distance(slave_x,slave_y,0,0);
       if(dist2<=1)
       {
          count++;
       }
       //printf("Process %d - startIndex %d endIndex %d; count %ld\n" ,myid, startIndex, endIndex-1, count);
    }
    
   }

   int total_count = 0;
   
 
   MPI_Reduce(&count,&total_count, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);


   if(myid == 0){
      double ratio = (double)total_count/points;
      double estimated_pi = 4 * ratio;
      printf("Total count:%d ratio:%f\n",total_count, ratio);
      double delta = fabs(estimated_pi-M_PI);
      printf("Delta:%lf\n",delta);
      double runTime = MPI_Wtime() - startwtime;
      printf("Execution time (sec):%.6f\n",runTime);
      printf("True pi:%f\n",M_PI);
      printf("Estimated pi:%f\n",estimated_pi);
   }

   /*cleaning up*/
   MPI_Finalize();
   

   return 0;
}
