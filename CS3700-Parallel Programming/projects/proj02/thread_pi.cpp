#include <math.h>
#include <cstdio>
#include <time.h>
#include <iostream>
#include <thread>
#include <mutex>
#include <utility>
#include <chrono>
#include <random>
std::mutex myMutex;

// random double func
double randDouble(){
  thread_local std::mt19937_64 generator(std::random_device{}());
  std::uniform_real_distribution<double> dis(0.0,1.0);
  return dis(generator)*1.352;
}

// double func to test dist
double Dist(double x, double y, double x2, double y2){
   return sqrt(pow((x2-x),2)+pow((y2-y),2));
}


void sumPoint(int& global_count, int beg, int end){
   int local_count = 0;
   double x_one,y_one,x_two,y_two;
   for(auto it = beg; it<end;++it){
      x_one = randDouble();
      y_one = randDouble();
      x_two = randDouble();
      y_two = randDouble();
      if(Dist(x_one,y_one,x_two,y_two)<=1){
         local_count++;
      }
   }
   std::cout<<"Thread at:"<<beg<<std::endl;
   std::cout<<"Local count:"<<local_count<<std::endl;
   std::lock_guard<std::mutex> theLock(myMutex);
   global_count+=local_count;
}

int main(int argc, char *argv[]){
   // too many args
   if(argc>3){
      std::cout<<"invalid input"<<std::endl;
      std::cout<<"too many args only 2"<<std::endl;
      return 0;
   }
   // convert the args into ints
   int threads = atoi(argv[1]);
   int points = atoi(argv[2]);
   if((threads<1||threads>10)&&(points<1||points>1000000)){
      std::cout<<"Invalid Input"<<std::endl;
      std::cout<<"monte carlo's range is 1-10 threads and 10-1000000"<<std::endl;
      return 0;
   }
   std::cout<<std::endl;
   auto start = std::chrono::system_clock::now();
   int count = 0;
   // work of the counting the threads

   std::thread trd[threads];
   int slice = points/threads;
   int startIdx = 0;
   double my_pi = 0;
   double ratio = 0;

   for(int i = 0; i<threads;++i){
      std::cout<<"Thread{"<< i << "] - slice["
          <<startIdx << ":" << startIdx+slice-1<< "]" << std::endl;
      trd[i] = std::thread(sumPoint,std::ref(count),startIdx,startIdx+slice-1);
      startIdx+=slice;
   }
   
   for(int i = 0; i<threads;++i){
      trd[i].join();
   }
   
   std::cout<<std::endl;
   ratio  = (double)count/(double)points;
   my_pi = 4.0 * ratio;
   double delta = std::abs(my_pi-M_PI);
   std::chrono::duration<double> dur = std::chrono::system_clock::now()-start;
   printf("Monte Carlo time:%.6f seconds\n",dur.count());
   std::cout<< "Count:"<<count<<std::endl;
   std::cout<< "Points:"<<points<<std::endl;
   std::cout<< "Ratio:"<<ratio<<std::endl;
   printf("Ratio:%.4f\n",ratio);
   printf("Actual PI:%.4f\n",M_PI);
   printf("My PI:%.4f\n",my_pi);
   printf("Delta:%.4f\n",delta);   
   return 0;
}
