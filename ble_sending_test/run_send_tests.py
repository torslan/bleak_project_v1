import asyncio
from bleak import BleakClient

async def real_send(device_address):
    async with BleakClient(device_address) as client:
        print(f"Connected to {device_address}, sending data...")
        await client.write_gatt_char("some-characteristic-uuid", b"data")
        print("Data sent successfully.")

device_address = "XX:XX:XX:XX:XX:XX"  # Replace with actual device address
asyncio.run(real_send(device_address))
