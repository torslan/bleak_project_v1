import asyncio

async def mock_receive():
    print("Simulating BLE receiving... (Mock Test)")
    await asyncio.sleep(2)
    print("Receiving simulation complete.")

asyncio.run(mock_receive())

