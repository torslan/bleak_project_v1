import asyncio
from bleak import BleakScanner

async def real_receive():
    print("Scanning for BLE devices...")
    devices = await BleakScanner.discover()
    for device in devices:
        print(f"Device: {device.name}, Address: {device.address}")
    print("BLE scan complete.")

asyncio.run(real_receive())

