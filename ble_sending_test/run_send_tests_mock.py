import asyncio

async def mock_send():
    print("Simulating BLE data sending... (Mock Test)")
    await asyncio.sleep(2)
    print("Sending simulation complete.")

asyncio.run(mock_send())
