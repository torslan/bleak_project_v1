# Use a lightweight Python image
FROM python:3.9-slim

# Set the working directory inside the container
WORKDIR /usr/src/a2dp_test

# Install system dependencies including libbluetooth-dev to avoid pybluez issue
RUN apt-get update && apt-get install -y \
    bluetooth \
    bluez \
    bluez-hcidump \
    libbluetooth-dev \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# Install Python dependencies
RUN pip install pybluez

# Copy the A2DP test scripts (both mock and real) into the container
COPY run_a2dp_tests.py run_a2dp_tests_mock.py /usr/src/a2dp_test/

# Create a directory for results and logging
RUN mkdir -p /usr/src/a2dp_test/results

# Set default command to run real tests, this will be overridden in Jenkins if mock test is needed
CMD ["python3", "run_a2dp_tests.py"]

