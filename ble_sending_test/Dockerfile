# Use a lightweight Python image
FROM python:3.9-slim

# Set the working directory inside the container
WORKDIR /usr/src/ble_sending_test

# Install system dependencies for Bluetooth
RUN apt-get update && apt-get install -y \
    bluetooth \
    bluez-hcidump \
    build-essential \
    git \
    && rm -rf /var/lib/apt/lists/*

# Upgrade pip and setuptools
RUN pip install --upgrade pip setuptools

# Install Bleak for BLE communication
RUN pip install bleak

# Create a directory for results and logging
RUN mkdir -p /usr/src/ble_sending_test/results

# Copy the test scripts into the container
COPY . /usr/src/ble_sending_test/

# Set default command to run real sending tests and output logs
CMD ["python3", "run_send_tests.py > ./results/send_test_results.txt"]

