package com.example.bookmyslot

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

class TIcket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        val register = findViewById<ImageView>(R.id.qr)
        val nameofuu = findViewById<TextView>(R.id.nameofuu)
        val emailofuu = findViewById<TextView>(R.id.emailofuu)
        val noofuu = findViewById<TextView>(R.id.noofuu)
        val evetna =findViewById<TextView>(R.id.nameofe)

        val ss:String = intent.getStringExtra("username").toString()
        val sss:String = intent.getStringExtra("useremail").toString()
        var ssss:String = intent.getStringExtra("userno").toString()
        val sssss:String = intent.getStringExtra("eventname").toString()
        nameofuu.setText(ss)
        emailofuu.setText(sss)
        noofuu.setText(ssss)
        evetna.setText(sssss)
        register.setImageBitmap(getQrCodeBitmap(ss,sssss))


    }fun getQrCodeBitmap(ssid: String, password: String): Bitmap {
        val size = 512 //pixels
        val qrCodeContent = (ssid+"|"+password)
        val hints = hashMapOf<EncodeHintType, Int>().also { it[EncodeHintType.MARGIN] = 1 }
        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }
}