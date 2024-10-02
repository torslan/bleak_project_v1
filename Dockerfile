# Base image
FROM ubuntu:22.04

# Install basic development tools
RUN apt-get update && apt-get install -y \
    build-essential \
    python3 \
    python3-pip \
    libbluetooth-dev \
    bluez \
    git \
    wget

# Install an older version of setuptools that supports use_2to3
RUN pip3 install setuptools==58.0.4

# Download and patch PyBluez source code
RUN wget https://github.com/pybluez/pybluez/archive/refs/tags/0.23.tar.gz && \
    tar xzf 0.23.tar.gz && \
    sed -i '/use_2to3/d' pybluez-0.23/setup.py && \
    cd pybluez-0.23 && \
    python3 setup.py install

# Install pytest
RUN pip3 install pytest

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the current directory contents into the container
COPY . .

# Command to run when the container starts
CMD ["bash"]

