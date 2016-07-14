/*
   Copyright 2015 Jianan - qinxiandiqi@foxmail.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.molidt.easyandroid.bluetooth;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BluetoothLeService extends Service {

	private BluetoothManager mBluetoothManager;
	private BluetoothAdapter mBluetoothAdapter;
	private Map<String, BluetoothGatt> mBluetoothGattMap = new HashMap<String, BluetoothGatt>();

	private final IBinder mBinder = new LocalBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// After using a given device, you should make sure that
		// BluetoothGatt.close() is called
		// such that resources are cleaned up properly. In this particular
		// example, close() is
		// invoked when the UI is disconnected from the Service.
		closeAll();
		return super.onUnbind(intent);
	}

	/**
	 * Initializes a reference to the local Bluetooth adapter.
	 * 
	 * @return Return true if the initialization is successful.
	 */
	@SuppressLint("NewApi")
	public boolean initialize() {
		// For API level 18 and above, get a reference to BluetoothAdapter
		// through
		// BluetoothManager.
		if (mBluetoothManager == null) {
			mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
			if (mBluetoothManager == null) {
				if (BuildConfig.DEBUG)
					Log.e(getClass().getName(),
							"Unable to initialize BluetoothManager.");
				return false;
			}
		}

		mBluetoothAdapter = mBluetoothManager.getAdapter();
		if (mBluetoothAdapter == null) {
			if (BuildConfig.DEBUG)
				Log.e(getClass().getName(),
						"Unable to obtain a BluetoothAdapter.");
			return false;
		}

		return true;
	}

	/**
	 * Connects to the GATT server hosted on the Bluetooth LE device.
	 * 
	 * @param address
	 *            The device address of the destination device.
	 * 
	 * @return Return true if the connection is initiated successfully. The
	 *         connection result is reported asynchronously through the
	 *         {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
	 *         callback.
	 */
	@SuppressLint("NewApi")
	public boolean connect(String address, boolean isAutoConnect,
			BluetoothGattCallback callback) {
		if (mBluetoothAdapter == null || address == null) {
			if (BuildConfig.DEBUG)
				Log.w(getClass().getName(),
						"BluetoothAdapter not initialized or unspecified address.");
			return false;
		}

		// Previously connected device. Try to reconnect.
		BluetoothGatt bluetoothGatt = mBluetoothGattMap.get(address);

		if (bluetoothGatt != null) {
			if (BuildConfig.DEBUG)
				Log.d(getClass().getName(),
						"Trying to use an existing mBluetoothGatt for connection.");
			if (bluetoothGatt.connect()) {
				return true;
			} else {
				return false;
			}
		}

		final BluetoothDevice device = mBluetoothAdapter
				.getRemoteDevice(address);
		if (device == null) {
			if (BuildConfig.DEBUG)
				Log.w(getClass().getName(),
						"Device not found.  Unable to connect.");
			return false;
		}
		// We want to directly connect to the device, so we are setting the
		// autoConnect
		// parameter to false.
		bluetoothGatt = device.connectGatt(this, isAutoConnect, callback);
		if (BuildConfig.DEBUG)
			Log.d(getClass().getName(), "Trying to create a new connection.");
		mBluetoothGattMap.put(address, bluetoothGatt);
		return true;
	}

	/**
	 * see {@link #connect(String, boolean, BluetoothGattCallback)}
	 * @param device
	 * @param isAutoConnect
	 * @param callback
	 * @return
	 */
	public boolean connect(BluetoothDevice device, boolean isAutoConnect,
			BluetoothGattCallback callback) {
		return connect(device.getAddress(), isAutoConnect, callback);
	}

	/**
	 * Disconnects an existing connection or cancel a pending connection. The
	 * disconnection result is reported asynchronously through the
	 * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
	 * callback.
	 */
	@SuppressLint("NewApi")
	public boolean disconnect(String address) {
		BluetoothGatt bluetoothGatt = mBluetoothGattMap.get(address);
		if (mBluetoothAdapter == null || bluetoothGatt == null) {
			if (BuildConfig.DEBUG)
				Log.w(getClass().getName(), "BluetoothAdapter not initialized");
			return false;
		}
		bluetoothGatt.disconnect();
		return true;
	}

	/**
	 * see {@link #disconnect(String)}
	 * @param device
	 */
	public boolean disconnect(BluetoothDevice device) {
		return disconnect(device.getAddress());
	}

	/**
	 * After using a given BLE device, the app must call this method to ensure
	 * resources are released properly.
	 */
	@SuppressLint("NewApi")
	public boolean close(String address) {
		BluetoothGatt bluetoothGatt = mBluetoothGattMap.get(address);
		if (bluetoothGatt == null) {
			return false;
		}
		bluetoothGatt.close();
		mBluetoothGattMap.remove(address);
		return true;
	}

	/**
	 * see {@link #close(String)}
	 * @param device
	 */
	public boolean close(BluetoothDevice device) {
		return close(device.getAddress());
	}

	public void closeAll() {
		for (String key : mBluetoothGattMap.keySet()) {
			close(key);
		}
	}

    /**
     * Request a read on a given {@code BluetoothGattCharacteristic}. The read result is reported
     * asynchronously through the {@code BluetoothGattCallback#onCharacteristicRead(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int)}
     * callback.
     *
     * @param characteristic The characteristic to read from.
     */
    @SuppressLint("NewApi")
	public boolean readCharacteristic(String address, BluetoothGattCharacteristic characteristic) {
    	BluetoothGatt bluetoothGatt = mBluetoothGattMap.get(address);
        if (mBluetoothAdapter == null || bluetoothGatt == null) {
            if(BuildConfig.DEBUG)Log.w(getClass().getName(), "BluetoothAdapter not initialized");
            return false;
        }
        bluetoothGatt.readCharacteristic(characteristic);
        return true;
    }

    /**
     * see {@link #readCharacteristic(String, BluetoothGattCharacteristic)}
     * @param device
     * @param characteristic
     */
    public boolean readCharacteristic(BluetoothDevice device, BluetoothGattCharacteristic characteristic){
    	return readCharacteristic(device.getAddress(), characteristic);
    }

	/**
	 * Enables or disables notification on a give characteristic.
	 * see {@link BluetoothGatt#setCharacteristicNotification(BluetoothGattCharacteristic, boolean)}
	 *
	 * @param address the remote device's address
	 * @param characteristic
	 *            Characteristic to act on.
	 * @param enabled
	 *            If true, enable notification. False otherwise.
	 */
	@SuppressLint("NewApi")
	public boolean setCharacteristicNotification(String address,
			BluetoothGattCharacteristic characteristic, boolean enabled) {
		BluetoothGatt bluetoothGatt = mBluetoothGattMap.get(address);
		if (mBluetoothAdapter == null || bluetoothGatt == null) {
			if(BuildConfig.DEBUG)Log.w(getClass().getName(), "BluetoothAdapter not initialized");
			return false;
		}
		bluetoothGatt.setCharacteristicNotification(characteristic, enabled);
		return true;
	}

	/**
	 * see {@link #setCharacteristicNotification(String, BluetoothGattCharacteristic, boolean)}
	 * @param device
	 * @param characteristic
	 * @param enabled
	 */
	public boolean setCharacteristicNotification(BluetoothDevice device, BluetoothGattCharacteristic characteristic, boolean enabled){
		return setCharacteristicNotification(device.getAddress(), characteristic, enabled);
	}

    /**
     * Retrieves a list of supported GATT services on the connected device. This should be
     * invoked only after {@code BluetoothGatt#discoverServices()} completes successfully.
     *
     * @return A {@code List} of supported services.
     */
    @SuppressLint("NewApi")
	public List<BluetoothGattService> getSupportedGattServices(String address) {
    	BluetoothGatt bluetoothGatt = mBluetoothGattMap.get(address);
        if (bluetoothGatt == null) return null;

        return bluetoothGatt.getServices();
    }
    
    /**
     * see {@link #getSupportedGattServices(String)}
     * @param device
     * @return
     */
    public List<BluetoothGattService> getSupportedGattServices(BluetoothDevice device){
    	return getSupportedGattServices(device.getAddress());
    }
    
    public int getBLEConnectedSize(){
    	return mBluetoothGattMap.size();
    }
    
	public class LocalBinder extends Binder {
		BluetoothLeService getService() {
			return BluetoothLeService.this;
		}
	}
}
