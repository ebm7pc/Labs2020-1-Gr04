package co.edu.udea.compumovil.gr04_20121.Lab3Architecture

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.base.AppDatabase
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.model.UserEntity
import co.edu.udea.compumovil.gr04_20121.Lab3Architecture.valueObjet.RegisterUserActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if (isRemembered) {

            findNavController().navigate(R.id.placesFragment)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = this.context?.let {
            AppDatabase.getDatabase(it, this.viewLifecycleOwner.lifecycleScope)
        }

        btnLog.findViewById<Button>(R.id.btnLog)
        btnLog.setOnClickListener {
            val userLogin = txtUser_Login.text.toString()
            val userpasslog = txtPass_Login.text.toString()
            txtUser_Login.findViewById<EditText>(R.id.txtUser_Login)
            txtPass_Login.findViewById<EditText>(R.id.txtPass_Login)

            val checked: Boolean = checkbox1.isChecked

            if (userLogin.isEmpty() || userpasslog.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Por favor diligencie todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                database?.let {
                    it.UserDao().getUser(userLogin).observe(
                        viewLifecycleOwner,
                        Observer { result: UserEntity ->
                            if (result != null && result.password.equals(userpasslog)) {
                                Toast.makeText(requireContext(), "Bienvenido " + userLogin, Toast.LENGTH_SHORT)
                                    .show()
                                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                                editor.putString("NAME", userLogin)
                                editor.putBoolean("CHECKBOX", checked)
                                editor.apply()

                                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_LONG).show()



                                findNavController().navigate(R.id.placesFragment)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Usuario ó Contrseña Incorrecto ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
                }
            }
        }

        btnregirter.setOnClickListener {
            val intent: Intent = Intent(activity, RegisterUserActivity::class.java)
            startActivity(intent)
        }
    }




}