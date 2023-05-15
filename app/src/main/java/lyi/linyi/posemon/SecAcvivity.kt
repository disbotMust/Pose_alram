package lyi.linyi.posemon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unique.simplealarmclock.activities.MainActivity

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 使用Intent啟動simplealarmclock的MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}