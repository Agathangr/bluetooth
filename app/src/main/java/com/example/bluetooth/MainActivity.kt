package com.example.bluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bluetooth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Configuração do view binding
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //variaveis para habilitar e deixar disponível o Bluetooth
    private val REQUEST_CODE_ENABLE_BT = 1;
    private val REQUEST_CODE_DISCOVERABLE_BT = 2;

    //adaptador Bluetooth
    private lateinit var bluetoothAdapter: BluetoothAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Inicializar o Adaptador do Bluetooth
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        //Status do Bluetooth ON/OF
        if (bluetoothAdapter = null){
            binding.bluetoothStatus.text = "Não Disponível"
        }else{
            binding.bluetoothStatus.text = "Disponível"
        }

        //Status Bluetooth ON/OFF com imagem
        if (bluetoothAdapter.isEnabled) {
            binding.Bluetoothimg.setImageResource(R.drawable.ic_bluetooth_on)
        }else{
            binding.Bluetoothimg.setImageResource(R.drawable.ic_bluetooth_off)
        }
        //Ação de clique para habilitar Bluetooth
        binding.btnLigarB.setOnClickListener {
            if (bluetoothAdapter.isEnabled){
                Toast.makeText(this, "Ligado", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BT)
            }
        }
        //Ação de clique para desabilitar Bluetooth
        binding.btnDesligarB.setOnClickListener {
            if (!bluetoothAdapter.isEnabled){
                Toast.makeText(this, "Ligado", Toast.LENGTH_SHORT).show()
            }else{
                bluetoothAdapter.disable()
                binding.Bluetoothimg.setImageResource(R.drawable.ic_bluetooth_off)
                Toast.makeText(this, "Bluetooth Desligado ", Toast.LENGTH_SHORT).show()
            }
        }

        //Ação para deixar meu bluetooth visível
        binding.btnMeuB.setOnClickListener {
            if (!bluetoothAdapter.isDiscovering) {
                Toast.makeText(this, "Este é meu dispositivo,", Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
                startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BT)
            }
        }
        //Listagem dos dispositivos que se conectaram ao meu
        binding.btnAparelhos.setOnClickListener {
            if (bluetoothAdapter.isEnabled){
                binding.statusConectados.text = "Dispositivos conectados: "
                val devices = bluetoothAdapter.bondedDevices
                for (device in devices){
                    val deviceName = device.name
                    val devideAdress = device
                    binding.statusConectados.append("\n Dispositivos:" +
                            "$deviceName $device")
                }
            }else{
                Toast.makeText(this, "Favor ligar o Bluetooth", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CODE_ENABLE_BT -->
                    if (resultCode == Activity.)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}