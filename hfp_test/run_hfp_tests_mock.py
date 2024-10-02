import logging
import time

# Set up logging
logging.basicConfig(filename='/usr/src/hfp_test/results/hfp_test_results.txt', level=logging.INFO)

def mock_test_hfp():
    logging.info("Starting mock HFP test...")

    # Simulate discovering a mock device with HFP support
    mock_device = ("11:22:33:44:55:66", "MockHFPDevice")

    logging.info(f"Simulated found Bluetooth HFP device {mock_device[1]} with address {mock_device[0]}")
    
    # Simulate connecting and testing HFP functionality
    logging.info(f"Simulating HFP test with {mock_device[1]}")
    time.sleep(1)  # Simulate connection delay

    # Simulate success
    logging.info(f"HFP test with {mock_device[1]} successful")

if __name__ == "__main__":
    mock_test_hfp()
