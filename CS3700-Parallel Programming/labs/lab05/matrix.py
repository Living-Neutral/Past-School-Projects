import numpy as np
from pycuda import driver, compiler, gpuarray, tools
import pycuda.autoinit

kernel_code_mult = """
__global__ void product(float *d_a, float *d_b, float *d_c) {
    int col = blockIdx.x * blockDim.x + threadIdx.x;
    int row = blockIdx.y * blockDim.y + threadIdx.y;

    float sum = 0;

    // %(MATRIX_SIZE)s is a substitution expression
    // it gets replaced by the actual size of the matrix
    // by the Host before this kernel is called
    for (int i = 0; i < %(MATRIX_SIZE)s; ++i) {
        float aEle = d_a[row * %(MATRIX_SIZE)s + i];
        float bEle = d_b[i * %(MATRIX_SIZE)s + col];
        sum += aEle * bEle;
    }

    d_c[row * %(MATRIX_SIZE)s + col] = sum;
}

"""

kernel_code_add = """
__global__ void sum(float *d_a,float *d_b, float *d_c){
   int row = blockIdx.y * blockDim.y + threadIdx.y;
   for(int i = 0; i<%(MATRIX_SIZE)s;++i){
      d_c[row*%(MATRIX_SIZE)s+i] = d_a[row*%(MATRIX_SIZE)s+i] + d_b[row*%(MATRIX_SIZE)s+i];
   }
}
"""
WIDTH = 6

# create two host random square matrices
host_a = np.random.randint(1, 10, size=(WIDTH, WIDTH)).astype(np.float32)
host_b = np.random.randint(1, 10, size=(WIDTH, WIDTH)).astype(np.float32)

# transfer host (CPU) memory to device (GPU) memory 
device_a = gpuarray.to_gpu(host_a) 
device_b = gpuarray.to_gpu(host_b)
device_c = gpuarray.empty((WIDTH,WIDTH), np.float32)
device_d = gpuarray.empty((WIDTH,WIDTH), np.float32)


#get the kernel code from the template
#by specifying the constant MATRIX_SIZE
kernel_code_1 = kernel_code_mult %{
   'MATRIX_SIZE':WIDTH
}

kernel_code_2 = kernel_code_add %{
  'MATRIX_SIZE':WIDTH
}

#compile the kernel code
mod = compiler.SourceModule(kernel_code_1)
mod2 = compiler.SourceModule(kernel_code_2)

# get the kernel function from the compiled module
product = mod.get_function("product")

sum = mod2.get_function("sum")

# call the kernel on the card
product(device_a, device_b, device_c, 
    block = (WIDTH, WIDTH, 1),
    grid = (WIDTH//2, WIDTH//2))

sum(device_a, device_b, device_d,
   block = (WIDTH,WIDTH,1),
   grid = (WIDTH//2, WIDTH//2))

# print the results
print("-" * 60)
print("Matrix A (GPU):")
print(device_a.get())

print("-" * 60)
print("Matrix B (GPU):")
print(device_b.get())

print("-" * 60)
print("A + B (GPU):")
print(device_d.get())

print("-" * 60)
print("A X B (GPU):")
print(device_c.get())
