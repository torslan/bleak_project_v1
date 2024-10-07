import asyncio
from bleak import BleakClient

async def real_sensor_data(device_address):
    async with BleakClient(device_address) as client:
        print(f"Connected to {device_address}, reading sensor data...")
        data = await client.read_gatt_char("some-characteristic-uuid")
        print(f"Sensor data: {data}")

device_address = "XX:XX:XX:XX:XX:XX"  # Replace with actual sensor device address
asyncio.run(real_sensor_data(device_address))
