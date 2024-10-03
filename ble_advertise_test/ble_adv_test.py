import logging
import time

# Set up logging
logging.basicConfig(filename='/usr/src/a2dp_test/results/a2dp_test_results.txt', level=logging.INFO)

def mock_test_a2dp_streaming():
    logging.info("Starting mock A2DP streaming test...")

    # Simulate discovering 2 mock devices
    mock_devices = [
        ("00:11:22:33:44:55", "MockDevice1"),
        ("66:77:88:99:AA:BB", "MockDevice2")
    ]

    for addr, name in mock_devices:
        logging.info(f"Simulated found Bluetooth device {name} with address {addr}")
        # Simulate A2DP-specific streaming logic
        logging.info(f"Simulating A2DP streaming with {name}")
        time.sleep(1)  # Simulate some delay during streaming

        # Simulate success or failure condition
        if addr == "00:11:22:33:44:55":  # Mock success for the first device
            logging.info(f"A2DP streaming with {name} successful")
        else:
            logging.error(f"A2DP streaming with {name} failed")

if __name__ == "__main__":
    mock_test_a2dp_streaming()
