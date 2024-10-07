import asyncio

async def mock_advertise():
    print("Simulating BLE advertising... (Mock Test)")
    await asyncio.sleep(2)
    print("Advertising simulation complete.")

asyncio.run(mock_advertise())

