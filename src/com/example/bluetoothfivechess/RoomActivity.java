package com.example.bluetoothfivechess;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bluetoothfivechess.utils.Constant;

public class RoomActivity extends Activity{
	
	private Button createButton;
	
	private Button searchButton;
	
	private ListView listView;
	
	private ArrayList<String> nameList;
	
	private ArrayList<String> addressList;
	
	private BluetoothAdapter adapter;
	
	private BluetoothDevice device;
	
	private ArrayAdapter<String> listAdapter;
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
				Log.d("haha", "开始搜索");
			}
			if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				Log.d("haha", "搜索结束");
			}
			if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
				if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
					Toast.makeText(RoomActivity.this, "蓝牙已经开启，请尝试创建或搜索", Toast.LENGTH_SHORT).show();
				}
			}
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				nameList.add(device.getName());
				addressList.add(device.getAddress());
				listAdapter.notifyDataSetChanged();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.room_activity);
		createButton = (Button)findViewById(R.id.create_button);
		searchButton = (Button)findViewById(R.id.search_button);
		listView = (ListView)findViewById(R.id.list_view);
		adapter = BluetoothAdapter.getDefaultAdapter();
		
		createButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (adapter.isEnabled()) {
					Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
					discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
					startActivityForResult(discoveryIntent, 1);
				} else {
					adapter.enable();
					Toast.makeText(RoomActivity.this, "正在为您开启蓝牙...", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (adapter.isEnabled()) {
					initData();
					adapter.startDiscovery();
					
				} else {
					adapter.enable();
					Toast.makeText(RoomActivity.this, "正在为您开启蓝牙...", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		registerReceiver(mReceiver, intentFilter);
		
		nameList = new ArrayList<String>();
		addressList = new ArrayList<String>();
		listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, nameList);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {           //列表点击
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Constant.address = addressList.get(position);
				if (Constant.address != null) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), ClientActivity.class);
					startActivity(intent);
				}
			}
			
		});
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		initData();
		listAdapter.notifyDataSetChanged();
		super.onRestart();
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	private void initData() {
		nameList.removeAll(nameList);
		addressList.removeAll(addressList);
		Constant.address = null;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == 300) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), ServerActivity.class);
				startActivity(intent);
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "创建失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
}
