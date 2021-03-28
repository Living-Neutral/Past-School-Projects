#include <chrono>
#include <iostream>
#include <mutex>
#include <random>
#include <utility>
#include <vector>
#include <thread>
using namespace std;

constexpr long long size= 1000000;   
mutex myMutex;

void myMinMax(int&global_min, int &global_max, const vector<int> &val,
   unsigned long long beg, unsigned long long end){

   int local_min = 1000002;
   int local_max = -1;
   for(auto it=beg; it<end;++it){
      if(val[it]>local_max){
         local_max = val[it]; 
      }
     
      if(val[it]<local_min){
        local_min = val[it];
      }
   } 

   lock_guard<mutex> myLock(myMutex);
   if(local_min<global_min)
      global_min = local_min;
   if(local_max>global_max)
      global_max = local_max;   
}


void print(std::vector<int> const &a){
   cout<<"The first ten is:"<<endl;
   for(int i = 0;i<10;i++)
     std::cout<<a.at(i)<< ' ';
   std::cout<<endl;
}

int main(){

  cout << endl;

  vector<int> randValues;
  randValues.reserve(size);
  mt19937 engine (0);
  srand(time(NULL));

  for ( long long i=0 ; i< size ; ++i)
     randValues.push_back(rand()%100000+1);

  print(randValues);
 
  int global_min = 100002;
  int global_max = -1;
  auto start = chrono::system_clock::now();

  int threads = 4;
  thread t[threads];
  long long slice = size / threads;
  int startIdx=0;
 /*
  for (int i = 0; i < threads; ++i) {
    cout << "Thread[" << i << "] - slice ["
         << startIdx << ":" << startIdx+slice-1 << "]" << endl;
    t[i] = thread(sumUp, ref(sum), ref(randValues), startIdx, startIdx+slice-1);
    startIdx += slice;
  }
*/

  for(int i = 0; i < threads; ++i){
    cout << "Thread[" <<i << "] - slice ["
         << startIdx << ":" << startIdx+slice-1 << "]" << endl;
    t[i] = thread(myMinMax, ref(global_min), ref(global_max), ref(randValues), startIdx, startIdx+slice-1);
    startIdx += slice;
  }

  for (int i = 0; i < threads; ++i)
     t[i].join();

  chrono::duration<double> dur= chrono::system_clock::now() - start;
  cout << "Time for finding min and max " << dur.count() << " seconds" << endl;
  //cout << "Sum: " << sum << endl;
  cout << "Min:" << global_min << " and "<< "Max:" << global_max << endl;

}
