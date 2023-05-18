package org.joyroom.carespoon.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.firebase.auth.FirebaseAuth
import org.joyroom.carespoon.R
import org.joyroom.carespoon.databinding.FragmentSignBinding
import org.joyroom.carespoon.ui.MainActivity
import org.joyroom.carespoon.ui.viewModel.SignViewModel


class SignFragment : Fragment() {
    private lateinit var binding: FragmentSignBinding
    private val viewModel: SignViewModel by activityViewModels()
    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }
    private val googleAuthLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            // 푸시 알림 관련 토큰인 듯
            // val pushToken = CareSpoonSharedPreferences.getPushToken() ?: return@registerForActivityResult

            try {
                val account = task.getResult(ApiException::class.java)
                val userName = account.givenName
                val serverAuth = account.serverAuthCode
                val email = account.email
                viewModel.saveUserName(userName)

                if (serverAuth != null) {
                    Log.d("*************serverAuth", serverAuth)
                } else Log.d("*************serverAuth", "null")

                if (email != null) {
                    Log.d("*************email", email)
                } else Log.d("*************email", "null")

                moveSignUpActivity()
                /* api 연동 시 viewModel 수정 (role 포함해야 함)
                viewModel.loginUser(
                    idToken,
                    //pushToken = pushToken
                )
                */
            } catch (e: ApiException) {
                Log.e(SignFragment::class.java.simpleName, e.stackTraceToString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(inflater, container, false)

        // 임시 인텐트
        binding.ivLogo.setOnClickListener{
            startActivity(Intent(requireContext(), SignUpActivity::class.java))
        }
        addListener()
        return binding.root
    }

    private fun addListener() {
        binding.clGoogleLogin.setOnClickListener {
            requestGoogleLogin()
        }
    }


    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope("https://www.googleapis.com/auth/pubsub"))
            .requestServerAuthCode(getString(R.string.google_login_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), googleSignInOption)
    }

    private fun moveSignUpActivity() {
        requireActivity().run {
            startActivity(Intent(requireContext(), SignUpActivity::class.java))
            finish()
        }
    }
}