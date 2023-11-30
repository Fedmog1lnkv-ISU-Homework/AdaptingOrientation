package com.fedmog1lnkv.adaptingorientation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fedmog1lnkv.adaptingorientation.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import java.util.Locale

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<CharSequence>
    private var currentPictureIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter.createFromResource(this, R.array.pictures, R.layout.item)
        binding.picturesList?.adapter = adapter
        binding.picturesList?.onItemSelectedListener = this
    }

    fun onChangePictureClick(v: View) {
        val iv: ImageView = binding.picture

        val pictureNames = resources.getStringArray(R.array.pictures)
        currentPictureIndex = (currentPictureIndex + 1) % pictureNames.size

        val formattedPictureName =
            pictureNames[currentPictureIndex].replace(" ", "").lowercase(Locale.getDefault())
        val pictureResource = resources.getIdentifier(formattedPictureName, "drawable", packageName)
        iv.setImageResource(pictureResource)

        binding.picturesList?.setSelection(currentPictureIndex)
    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "выбран элемент $position", Toast.LENGTH_SHORT).show()

        val selectedPictureName = resources.getStringArray(R.array.pictures)[position]
        currentPictureIndex = position

        val formattedPictureName = selectedPictureName.replace(" ", "").lowercase(Locale.getDefault())

        val pictureResource = resources.getIdentifier(formattedPictureName, "drawable", packageName)
        binding.picture.setImageResource(pictureResource)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "не выбран элемент", Toast.LENGTH_SHORT).show()
    }
}
