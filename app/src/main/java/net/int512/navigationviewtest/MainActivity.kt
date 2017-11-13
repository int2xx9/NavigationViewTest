package net.int512.navigationviewtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer: DrawerLayout = findViewById(R.id.drawer)
        drawerToggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        )
        drawer.addDrawerListener(drawerToggle)

        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item1 -> Toast.makeText(this, "menu1 selected", Toast.LENGTH_SHORT).show()
                R.id.menu_item2 -> Toast.makeText(this, "menu2 selected", Toast.LENGTH_SHORT).show()
                R.id.menu_item3 -> Toast.makeText(this, "menu3 selected", Toast.LENGTH_SHORT).show()
            }
            true
        }

        //
        // setActionViewを使う場合
        //

        // トグルスイッチの生成
        val switch1 = Switch(this)
        // トグルスイッチにイベントを設定
        switch1.setOnCheckedChangeListener({button, isChecked ->
            val text = if (isChecked) "checked" else "unchecked"
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        })
        // トグルスイッチを配置するメニューを取得
        val menuItem2 = navigationView.menu.findItem(R.id.menu_item2)
        // 取得したメニューにトグルスイッチを設定
        menuItem2.actionView = switch1

        //
        // actionLayout属性を使う場合
        //

        // actionLayoutに設定したレイアウトから生成されたビューを取得
        val customView: View = navigationView.menu.findItem(R.id.menu_item3).actionView
        // ビューからトグルボタンを取得
        val toggleButton: ToggleButton = customView.findViewById(R.id.toggle_button)
        // トグルボタンにイベントを設定
        toggleButton.setOnCheckedChangeListener { button, isChecked ->
            val text = if (isChecked) "checked" else "unchecked"
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }
}
