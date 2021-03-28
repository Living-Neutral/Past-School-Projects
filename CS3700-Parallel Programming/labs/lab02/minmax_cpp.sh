#!/bin/bash
#SBATCH --job-name=MINMAX_CPP # Job Name
#SBATCH --output=MINMAX_CPP_%j.log # Log file name
#SBATCH --partition=compute # use computing cluster
#SBATCH --mem=1gb           # Job memory request
#SBATCH --nodes=5           # Number of Computing nodes
#SBATCH --time=00:02:00    # Time Limit HH:MM:SS

. /etc/profile.d/modules.sh

module load openmpi/2.1.2

/opt/openmpi-2.1.2/bin/mpirun ./minmax

