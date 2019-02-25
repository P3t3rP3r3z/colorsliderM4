package com.example.peterperez.colorsliderm4

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.SurfaceView
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import java.io.Serializable

data class ColorCustom(var redval: Int, var grnval: Int, var blueval: Int): Serializable

class MainActivity : AppCompatActivity() {
    lateinit var rslider: SeekBar
    lateinit var gslider: SeekBar
    lateinit var bslider: SeekBar
    lateinit var value : TextView
    lateinit var surface : SurfaceView
    lateinit var rval : EditText
    lateinit var bval : EditText
    lateinit var gval : EditText
    lateinit var tosave : String
    var FNAME = "Colors2.txt"
    val colorlist = mutableListOf<String>()
   // lateinit var nametosave : nameET
//    val fin = File(filesDir, FNAME)
//    val sc = Scanner







    override fun onCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.my_toolbar1))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar1))

        handleIntent()

        var nametosave = nameET
        filesDir.mkdirs()
        val fin = File(filesDir, FNAME)

        if (!fin.exists()){
        val out = fin.printWriter()
        out.print(" ")
        out.close()
        }

        val path = getExternalFilesDir(null)
        val letDirectory =File(path,"LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, FNAME)

        rslider = r_slider
        gslider = g_slider
        value = textv_result
        bslider = b_slider
        surface = surface_view
        rval = redval
        bval = blueval
        gval = greenval
        rslider.max=255
        gslider.max=255
        bslider.max=255
        //actbar.setLogo(R.drawable.ic_launcher_foreground)





            rslider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    rval.setText(rslider.getProgress().toString())
                    updateSurface()

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            gslider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    gval.setText(gslider.getProgress().toString())
                    updateSurface()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            bslider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    bval.setText(bslider.getProgress().toString())
                    updateSurface()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            rval.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    rval.setSelection(rval.getText().length)
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    rval.setSelection(rval.getText().length)
                    try {
                        rslider.setProgress(Integer.parseInt(rval.getText().toString()))
                        updateSurface()
                    } catch (ex: Exception) {
                        value.text = "invalid value"
                        rslider.setProgress(0)
                    }
                }
            })

            bval.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    bval.setSelection(bval.getText().length)
                    try {
                        bslider.setProgress(Integer.parseInt(bval.getText().toString()))
                        updateSurface()
                    } catch (ex: Exception) {
                        value.text = "invalid value"
                        bslider.setProgress(0)
                    }
                }
            })

            gval.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    gslider.setProgress(Integer.parseInt(gval.getText().toString()))
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    gval.setSelection(gval.getText().length)
                    try {
                        gslider.setProgress(Integer.parseInt(gval.getText().toString()))
                        updateSurface()
                    } catch (ex: Exception) {
                        value.text = "invalid value"
                        gslider.setProgress(0)
                    }
                }
            })


        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            colorlist.add(" ") //array that stores the list of colors to be accessed by the spinner adapter
            fin.bufferedReader().useLines { lines -> lines.forEach { colorlist.add(it)} }
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorlist)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    val strs = selectedItem.split(" ")

//                    val strs0 = strs[0]
//                    val strs1 = strs[1]
//                   val strs2 = strs[2]
//                    val strs3 = strs[3]
                    if(position!=0) {
                        for (i in 0..2) {
                            if (strs[i] != null) {
                                Toast.makeText(this@MainActivity, "${strs}", Toast.LENGTH_SHORT).show()
                                rslider.progress = strs[0].toInt()
                                gslider.progress = strs[1].toInt()
                                bslider.progress = strs[2].toInt()
                            }
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }

            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var nametosave = nameET
        //filesDir.mkdirs()
        val fin = File(filesDir, FNAME)
        val br = fin.bufferedReader()
        var line:String?
        line=br.readLine()

        nametosave.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                nameET.setSelection(nameET.getText().length)

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nametosave.setSelection(nametosave.getText().length)

            }

        })
        tosave = rslider.progress.toString() + " " + gslider.progress.toString() +
                " " + bslider.progress.toString()  + " "+ nametosave.text.toString()  +"\n"
        while (null != line){
            colorlist.add(line.toString())
            line=br.readLine()

        }
        tosave = rslider.progress.toString() + " " + gslider.progress.toString() +
                " " + bslider.progress.toString()  + " "+ nametosave.text.toString()  +"\n"
        if (fin.exists()) {
            fin.appendText(tosave)
            updateList()
        }
        else {
            val out = fin.printWriter()
            out.print(" ")
            out.print(tosave)
            out.close()
            updateList()
        }


        Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show()

        return true
    }

    fun updateList(){
        val fin = File(filesDir, FNAME)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            colorlist.clear()
            colorlist.add(" ")
            fin.bufferedReader().useLines { lines -> lines.forEach { colorlist.add(it)} }
            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorlist)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    val strs = selectedItem.split(" ")
//                    val strs0 = strs[0]
//                    val strs1 = strs[1]
//                    val strs2 = strs[2]
                    if(position!=0) {
                        for (i in 0..2) {
                            if (strs[i] != null) {
                                Toast.makeText(this@MainActivity, "${strs}", Toast.LENGTH_SHORT).show()
                                rslider.progress = strs[0].toInt()
                                gslider.progress = strs[1].toInt()
                                bslider.progress = strs[2].toInt()
                            }
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }

            }
        }
    }

    fun updateSurface() {
        var rslide = rslider.getProgress()
        var gslide = gslider.getProgress()
        var bslide = bslider.getProgress()

        var status = 0xff000000 + rslide * 0x10000 + gslide * 0x100 + bslide
        value.text = Integer.toHexString(status.toInt())
        surface.setBackgroundColor(status.toInt())
        //tosave = rslide.toString() + " " + gslide.toString() + " " + bslide.toString() + " " + nametosave.text.toString() + "\n"

    }

    private fun handleIntent() {

        val info = intent.extras
        if (info != null) {
            if (info.containsKey("Color a")) {
                sendbutton.visibility = View.VISIBLE
                sendbutton.setOnClickListener {
                    sendA()
                }
            } else if (info.containsKey("Color b")) {
                sendbutton.visibility = View.VISIBLE
                sendbutton.setOnClickListener {
                    sendB()
                }
            }
        }

    }
    fun sendA(){
        var r=rslider.progress
        var g=gslider.progress
        var b=bslider.progress
        var colorToSend: String = ("$r $g $b")

        val returnIntent: Intent = Intent()
        returnIntent.putExtra("Return_Color",colorToSend)
        intent.putExtra("Color A",colorToSend)
        setResult(RESULT_OK, intent)
        super.finish()
    }

    fun sendB(){
        var r=rslider.progress
        var g=gslider.progress
        var b=bslider.progress
        var colorToSend: String = ("$r $g $b")

        val returnIntent: Intent = Intent()
        returnIntent.putExtra("Return_Color",colorToSend)
        intent.putExtra("Color B",colorToSend)
        setResult(RESULT_OK, intent)
        super.finish()
    }

}