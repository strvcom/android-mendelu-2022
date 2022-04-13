package com.strv.archdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.strv.archdemo.ui.ComposeDemoNavGraph
import com.strv.archdemo.ui.theme.ArchDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchDemoTheme {
                ComposeDemoNavGraph()
            }
        }
    }
}