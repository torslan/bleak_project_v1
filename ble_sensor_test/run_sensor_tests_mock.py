import asyncio

async def mock_sensor_data():
    print("Simulating BLE sensor data... (Mock Test)")
    await asyncio.sleep(2)
    print("Sensor data simulation complete.")

asyncio.run(mock_sensor_data())
