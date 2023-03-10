package com.example.my_perfect_chat.ui.fragments.test.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.my_perfect_chat.R
import com.example.my_perfect_chat.databinding.FragmentTestBinding
import com.example.my_perfect_chat.extensions.showToast
import com.example.my_perfect_chat.ui.fragments.test.*
import com.example.my_perfect_chat.utils.BaseFragment
import com.example.my_perfect_chat.utils.SharedPreferenceUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class TestFragment : BaseFragment(R.layout.fragment_test) {

    private val binding by viewBinding(FragmentTestBinding::bind)
    private val lessons: Array<String> = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8")
    private val database = Firebase.database
    private val message = database.getReference("message")
    private val color = database.getReference("color")
    private val textColor = database.getReference("textColor")

    private val nameDataPerson1 = database.getReference("testName1")
    private val nameDataPerson2 = database.getReference("testName2")
    private val nameDataPerson3 = database.getReference("testName3")
    private val nameDataPerson4 = database.getReference("testName4")
    private val nameDataPerson5 = database.getReference("testName5")
    private val nameDataPerson6 = database.getReference("testName6")
    private val nameDataPerson7 = database.getReference("testName7")
    private val nameDataPerson8 = database.getReference("testName8")

    private val ballData1 = database.getReference("testBall1")
    private val ballData2 = database.getReference("testBall2")
    private val ballData3 = database.getReference("testBall3")
    private val ballData4 = database.getReference("testBall4")
    private val ballData5 = database.getReference("testBall5")
    private val ballData6 = database.getReference("testBall6")
    private val ballData7 = database.getReference("testBall7")
    private val ballData8 = database.getReference("testBall8")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        readData()
        setEdittexts()
    }

    private fun initialize() = with(binding) {
        if (SharedPreferenceUtil.isColor) {
            btnSaveDataMessage.visibility = View.VISIBLE
            etMessageData.visibility = View.VISIBLE
            btnSaveDataColor.visibility = View.VISIBLE
            etColorData.visibility = View.VISIBLE
            btnSaveDataTextColor.visibility = View.VISIBLE
            etTextColorData.visibility = View.VISIBLE
        }

        if (SharedPreferenceUtil.isProver) {
            btnSaveData.visibility = View.VISIBLE
            btnSaveDataMessage.visibility = View.VISIBLE
            etMessageData.visibility = View.VISIBLE
        }

        val lessonIs: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item, lessons
        )

        lessonIs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerOfFragments.adapter = lessonIs
        spinnerOfFragments.prompt = "Уроки"

        spinnerOfFragments.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        replacedFragments(OneFragment())
                    }
                    2 -> {
                        replacedFragments(TwoFragment())
                    }
                    3 -> {
                        replacedFragments(ThreeFragment())
                    }
                    4 -> {
                        replacedFragments(FourFragment())
                    }
                    5 -> {
                        replacedFragments(FiveFragment())
                    }
                    6 -> {
                        replacedFragments(SixFragment())
                    }
                    7 -> {
                        replacedFragments(SevenFragment())
                    }
                    8 -> {
                        replacedFragments(EightFragment())
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun replacedFragments(fragment: BaseFragment) {
        fragmentReplacer.replace(pagePos, fragment)
    }

    private fun setupListener() = with(binding) {
        btnSaveDataMessage.setOnClickListener {
            showToast("Сообщение отправлено")
            val et1 = binding.etMessageData.text.toString()
            message.setValue(et1)
            etMessageData.text.clear()
        }
        btnSaveDataColor.setOnClickListener {
            showToast("Выполнено")
            val et2 = binding.etColorData.text.toString()
            color.setValue(et2)
            etColorData.text.clear()
        }
        btnSaveDataTextColor.setOnClickListener {
            showToast("Выполнено")
            val et3 = binding.etTextColorData.text.toString()
            textColor.setValue(et3)
            etTextColorData.text.clear()
        }
        btnBackSpace.setOnClickListener {
            fragmentReplacer.replaceCurrentToDef()
        }
        btnSaveData.setOnClickListener {
            showToast("Данные успешно сохранены")
            nameDataPerson1.setValue(namePerson1.text.toString())
            nameDataPerson2.setValue(namePerson2.text.toString())
            nameDataPerson3.setValue(namePerson3.text.toString())
            nameDataPerson4.setValue(namePerson4.text.toString())
            nameDataPerson5.setValue(namePerson5.text.toString())
            nameDataPerson6.setValue(namePerson6.text.toString())
            nameDataPerson7.setValue(namePerson7.text.toString())
            nameDataPerson8.setValue(namePerson8.text.toString())

            ballData1.setValue(ballPerson1.text.toString())
            ballData2.setValue(ballPerson2.text.toString())
            ballData3.setValue(ballPerson3.text.toString())
            ballData4.setValue(ballPerson4.text.toString())
            ballData5.setValue(ballPerson5.text.toString())
            ballData6.setValue(ballPerson6.text.toString())
            ballData7.setValue(ballPerson7.text.toString())
            ballData8.setValue(ballPerson8.text.toString())

        }
    }

    private fun readData() = with(binding) {
        txtNews.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val view = layoutInflater.inflate(R.layout.best_rest, null)

            builder.setView(view)
            val dialog = builder.create()

            message.addValueEventListener(object : ValueEventListener {
                @SuppressLint("MissingInflatedId")
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue<String>()
                    view.findViewById<TextView>(R.id.txt_message).text = value.toString()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            color.addValueEventListener(object : ValueEventListener {
                @SuppressLint("MissingInflatedId")
                override fun onDataChange(snapshot: DataSnapshot) {
                    when (snapshot.getValue<String>()) {
                        "black" -> {
                            view.findViewById<ConstraintLayout>(R.id.dialog_message)
                                .setBackgroundColor(Color.parseColor("#000000"))
                        }
                        "white" -> {
                            view.findViewById<ConstraintLayout>(R.id.dialog_message)
                                .setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                        }
                        "blue" -> {
                            view.findViewById<ConstraintLayout>(R.id.dialog_message)
                                .setBackgroundColor(Color.parseColor("#FF00BCD4"))
                        }
                        "red" -> {
                            view.findViewById<ConstraintLayout>(R.id.dialog_message)
                                .setBackgroundColor(Color.parseColor("#FFFF0000"))
                        }
                        "yellow" -> {
                            view.findViewById<ConstraintLayout>(R.id.dialog_message)
                                .setBackgroundColor(Color.parseColor("#FFFFE500"))
                        }
                        "green" -> {
                            view.findViewById<ConstraintLayout>(R.id.dialog_message)
                                .setBackgroundColor(Color.parseColor("#FF4CFF00"))
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            textColor.addValueEventListener(object : ValueEventListener {
                @SuppressLint("MissingInflatedId")
                override fun onDataChange(snapshot: DataSnapshot) {
                    when (snapshot.getValue<String>()) {
                        "black" -> {
                            view.findViewById<TextView>(R.id.txt_message)
                                .setTextColor(Color.parseColor("#000000"))
                        }
                        "white" -> {
                            view.findViewById<TextView>(R.id.txt_message)
                                .setTextColor(Color.parseColor("#FFFFFFFF"))
                        }
                        "blue" -> {
                            view.findViewById<TextView>(R.id.txt_message)
                                .setTextColor(Color.parseColor("#FF00BCD4"))
                        }
                        "red" -> {
                            view.findViewById<TextView>(R.id.txt_message)
                                .setTextColor(Color.parseColor("#FFFF0000"))
                        }
                        "yellow" -> {
                            view.findViewById<TextView>(R.id.txt_message)
                                .setTextColor(Color.parseColor("#FFFFE500"))
                        }
                        "green" -> {
                            view.findViewById<TextView>(R.id.txt_message)
                                .setTextColor(Color.parseColor("#FF4CFF00"))
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            if (dialog.window != null) {
                dialog.window?.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }

        nameDataPerson1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson1.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        nameDataPerson2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson2.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        nameDataPerson3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson3.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        nameDataPerson4.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson4.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        nameDataPerson5.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson5.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        nameDataPerson6.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson6.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        nameDataPerson7.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson7.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        nameDataPerson8.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                namePerson8.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        ballData1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson1.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        ballData2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson2.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        ballData3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson3.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        ballData4.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson4.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        ballData5.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson5.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        ballData6.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson6.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        ballData7.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson7.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        ballData8.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                ballPerson8.setText(value.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun setEdittexts() = with(binding) {
        if (!SharedPreferenceUtil.isProver) {
            namePerson1.isEnabled = false
            namePerson1.isInEditMode
            namePerson1.isCursorVisible = false
            namePerson1.keyListener = null

            namePerson2.isEnabled = false
            namePerson2.isInEditMode
            namePerson2.isCursorVisible = false
            namePerson2.keyListener = null

            namePerson3.isEnabled = false
            namePerson3.isInEditMode
            namePerson3.isCursorVisible = false
            namePerson3.keyListener = null

            namePerson4.isEnabled = false
            namePerson4.isInEditMode
            namePerson4.isCursorVisible = false
            namePerson4.keyListener = null

            namePerson5.isEnabled = false
            namePerson5.isInEditMode
            namePerson5.isCursorVisible = false
            namePerson5.keyListener = null

            namePerson6.isEnabled = false
            namePerson6.isInEditMode
            namePerson6.isCursorVisible = false
            namePerson6.keyListener = null

            namePerson7.isEnabled = false
            namePerson7.isInEditMode
            namePerson7.isCursorVisible = false
            namePerson7.keyListener = null

            namePerson8.isEnabled = false
            namePerson8.isInEditMode
            namePerson8.isCursorVisible = false
            namePerson8.keyListener = null

            ballPerson1.isEnabled = false
            ballPerson1.isInEditMode
            ballPerson1.isCursorVisible = false
            ballPerson1.keyListener = null

            ballPerson2.isEnabled = false
            ballPerson2.isInEditMode
            ballPerson2.isCursorVisible = false
            ballPerson2.keyListener = null

            ballPerson3.isEnabled = false
            ballPerson3.isInEditMode
            ballPerson3.isCursorVisible = false
            ballPerson3.keyListener = null

            ballPerson4.isEnabled = false
            ballPerson4.isInEditMode
            ballPerson4.isCursorVisible = false
            ballPerson4.keyListener = null

            ballPerson5.isEnabled = false
            ballPerson5.isInEditMode
            ballPerson5.isCursorVisible = false
            ballPerson5.keyListener = null

            ballPerson6.isEnabled = false
            ballPerson6.isInEditMode
            ballPerson6.isCursorVisible = false
            ballPerson6.keyListener = null

            ballPerson7.isEnabled = false
            ballPerson7.isInEditMode
            ballPerson7.isCursorVisible = false
            ballPerson7.keyListener = null

            ballPerson8.isEnabled = false
            ballPerson8.isInEditMode
            ballPerson8.isCursorVisible = false
            ballPerson8.keyListener = null
        }
    }
}