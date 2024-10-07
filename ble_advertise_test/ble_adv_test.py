import asyncio
from bleak import BleakAdvertiser

async def real_advertise():
    print("Starting real BLE advertising...")
    async with BleakAdvertiser() as advertiser:
        await asyncio.sleep(10)
        print("Real advertising complete.")

asyncio.run(real_advertise())
